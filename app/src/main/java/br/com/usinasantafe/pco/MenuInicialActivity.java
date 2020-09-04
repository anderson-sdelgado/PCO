package br.com.usinasantafe.pco;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.usinasantafe.pco.util.ConexaoWeb;
import br.com.usinasantafe.pco.util.EnvioDadosServ;
import br.com.usinasantafe.pco.util.VerifDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private PCOContext pcoContext;
    private ListView menuInicialListView;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pcoContext = (PCOContext) getApplication();
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.CAMERA)) {
            String[] PERMISSIONS = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        progressBar = new ProgressDialog(this);

        if(pcoContext.getConfigCTR().hasElements()){
            if(pcoContext.getPassageiroCTR().verPassageiroViagemList(pcoContext.getConfigCTR().getConfig().getDtrhViagemConfig())){
                startTimer("N_SD");
                Intent it = new Intent(MenuInicialActivity.this, ListaPassageiroActivity.class);
                startActivity(it);
                finish();
            }
            else{
                atualizarAplic();
            }
        }

        verifEnvio();
        clearBD();

        ArrayList<String> itens = new ArrayList<>();

        itens.add("INICIAR VIAGEM");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuInicialListView = (ListView) findViewById(R.id.listaMenuInicial);
        menuInicialListView.setAdapter(adapterList);

        menuInicialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("INICIAR VIAGEM")) {
                    if (pcoContext.getPassageiroCTR().hasElementsMotorista()
                            && pcoContext.getConfigCTR().hasElements()) {

                        Intent it = new Intent(MenuInicialActivity.this, MotoristaActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else if (text.equals("RELATÓRIO")) {

                } else if (text.equals("CONFIGURAÇÃO")) {
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("SAIR")) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }

        });

    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void atualizarAplic(){
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(this)) {
            if (pcoContext.getConfigCTR().hasElements()) {
                progressBar.setCancelable(true);
                progressBar.setMessage("BUSCANDO ATUALIZAÇÃO...");
                progressBar.show();
                customHandler.postDelayed(updateTimerThread, 10000);
                VerifDadosServ.getInstance().setVerTerm(false);
                VerifDadosServ.getInstance().verAtualAplic(pcoContext.versaoAplic, this, progressBar);
            }
        } else {
            VerifDadosServ.getInstance().setVerTerm(true);
            startTimer("N_SD");
        }
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            verifEnvio();
            if(!VerifDadosServ.getInstance().isVerTerm()) {
                VerifDadosServ.getInstance().cancelVer();
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
                startTimer("N_SD");
            }
            customHandler.postDelayed(this, 10000);
        }
    };

    public void startTimer(String verAtual) {

        Log.i("PMM", "VERATUAL = " + verAtual);

        if(!verAtual.equals("N_SD")){
            int pos1 = verAtual.indexOf("#") + 1;
            String dthr = verAtual.substring(pos1);
            pcoContext.getConfigCTR().setDthrServConfig(dthr);
        }

        Intent intent = new Intent(this, ReceberAlarme.class);

        if (progressBar.isShowing()) {
            progressBar.dismiss();
        }

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, 1);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, pendingIntent);

    }

    public void verifEnvio(){
        if (pcoContext.getConfigCTR().hasElements()) {
            if (EnvioDadosServ.getInstance().getStatusEnvio() == 1) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 2) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }
        } else {
            textViewProcesso.setTextColor(Color.RED);
            textViewProcesso.setText("Aparelho sem Equipamento");
        }
    }

    public void clearBD() {
        pcoContext.getPassageiroCTR().delPassageiro();
    }

}