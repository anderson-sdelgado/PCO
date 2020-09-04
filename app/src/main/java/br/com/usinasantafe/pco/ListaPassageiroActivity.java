package br.com.usinasantafe.pco;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.model.bean.variaveis.PassageiroBean;
import br.com.usinasantafe.pco.util.EnvioDadosServ;
import br.com.usinasantafe.pco.util.Tempo;
import br.com.usinasantafe.pco.zxing.CaptureActivity;

public class ListaPassageiroActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private ListView passageiroListView;
    private List<PassageiroBean> passageiroList;
    private PCOContext pcoContext;
    private TextView textViewMotorista;
    private TextView textViewTurno;
    private AdapterListPassageiro adapterList;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_passageiro);

        pcoContext = (PCOContext) getApplication();
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

        textViewMotorista = (TextView) findViewById(R.id.textViewMotorista);
        textViewTurno = (TextView) findViewById(R.id.textViewTurno);
        Button buttonInserirPassageiro = (Button) findViewById(R.id.buttonInserirPassageiro);
        Button buttonFecharViagem = (Button) findViewById(R.id.buttonFecharViagem);

        customHandler.postDelayed(updateTimerThread, 0);

        MotoristaBean motoristaBean = pcoContext.getPassageiroCTR().getMotorista(pcoContext.getConfigCTR().getConfig().getMatricMotoConfig());
        textViewMotorista.setText(motoristaBean.getMatricMoto() + " - " + motoristaBean.getNomeMoto());
        textViewTurno.setText(pcoContext.getPassageiroCTR().getTurno(pcoContext.getConfigCTR().getConfig().getIdTurnoConfig()).getDescTurno());

        passageiroList = pcoContext.getPassageiroCTR().passageiroList(pcoContext.getConfigCTR().getConfig().getDtrhViagemConfig());

        ArrayList<String> itens = new ArrayList<>();

        for(PassageiroBean passageiroBean : passageiroList){
            ColabBean colabBean = pcoContext.getPassageiroCTR().getColab(passageiroBean.getMatricColabPassageiro());
            itens.add(passageiroBean.getDthrPassageiro() + "\n"
                    + colabBean.getMatricColab() + " - " + colabBean.getNomeColab());
        }

        adapterList = new AdapterListPassageiro(this, itens);
        passageiroListView = (ListView) findViewById(R.id.passageiroListView);
        passageiroListView.setAdapter(adapterList);

        buttonInserirPassageiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaPassageiroActivity.this, CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonFecharViagem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaPassageiroActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            String matricula = data.getStringExtra("SCAN_RESULT");
            if(matricula.length() == 8){
                matricula = matricula.substring(0,7);
                if (pcoContext.getPassageiroCTR().verColab(Long.parseLong(matricula))) {
                    pcoContext.getPassageiroCTR().salvarPassageiro(Long.parseLong(matricula));
                    ColabBean colabBean = pcoContext.getPassageiroCTR().getColab(Long.parseLong(matricula));
                    adapterList.addItem(Tempo.getInstance().dataComHora() + "\n"
                            + colabBean.getMatricColab() + " - " + colabBean.getNomeColab());
                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPassageiroActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA LEITURA DE CARTÃO OU FUNCIONÁRIO INEXISTENTE. POR FAVOR, ATUALIZE A BASE DE DADOS E TENTE NOVAMENTE.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();

                }
            }
        }

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
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
            customHandler.postDelayed(this, 10000);
        }
    };

}