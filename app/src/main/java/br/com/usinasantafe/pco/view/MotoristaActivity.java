package br.com.usinasantafe.pco.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.util.ConexaoWeb;
import br.com.usinasantafe.pco.util.VerifDadosServ;
import br.com.usinasantafe.pco.zxing.CaptureActivity;

public class MotoristaActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCOContext pcoContext;
    private TextView txtRetPassageiro;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista);

        pcoContext = (PCOContext) getApplication();

        txtRetPassageiro = (TextView) findViewById(R.id.txtRetPassageiro);
        Button buttonOkPassageiro = (Button) findViewById(R.id.buttonOkPassageiro);
        Button buttonCancPassageiro = (Button) findViewById(R.id.buttonCancPassageiro);
        Button buttonAcionarCamera = (Button) findViewById(R.id.buttonAcionarCamera);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        if(pcoContext.getVerTela() == 1){
            MotoristaBean motoristaBean = new MotoristaBean();
            motoristaBean.setMatricMoto(0L);
            pcoContext.getConfigCTR().setMotoConfig(motoristaBean);
        }

        if(pcoContext.getConfigCTR().getConfig().getMatricMotoConfig() == 0){
            txtRetPassageiro.setText("");
        }
        else{
            txtRetPassageiro.setText(pcoContext.getConfigCTR().getMotoConfig().getNomeMoto());
        }

        buttonOkPassageiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pcoContext.getConfigCTR().getMotoConfig().getMatricMoto() > 0){
                    Intent it = new Intent(MotoristaActivity.this, ListaTurnoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonCancPassageiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(MotoristaActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonAcionarCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(MotoristaActivity.this, CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(MotoristaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(MotoristaActivity.this)) {

                            progressBar = new ProgressDialog(MotoristaActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO MOTORISTA...");
                            progressBar.show();

                            pcoContext.getPassageiroCTR().atualDadosMotorista(MotoristaActivity.this
                                    , MotoristaActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MotoristaActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });

                            alerta.show();

                        }
                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            String matricula = data.getStringExtra("SCAN_RESULT");
            if(matricula.length() == 8){
                matricula = matricula.substring(0,7);
                if (pcoContext.getPassageiroCTR().verMotorista(Long.parseLong(matricula))) {
                    pcoContext.getConfigCTR().setMotoConfig(pcoContext.getPassageiroCTR().getMotorista(Long.parseLong(matricula)));
                    txtRetPassageiro.setText(pcoContext.getConfigCTR().getMotoConfig().getMatricMoto() + "\n" + pcoContext.getConfigCTR().getMotoConfig().getNomeMoto());
                }
                else{

                    progressBar = new ProgressDialog(MotoristaActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("PESQUISANDO MOTORISTA...");
                    progressBar.show();

                    customHandler.postDelayed(updateTimerThread, 10000);

                    pcoContext.setVerTela(3);
                    pcoContext.getPassageiroCTR().verMotorista(matricula, MotoristaActivity.this, MotoristaActivity.class, progressBar);

                }
            }
        }

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(!VerifDadosServ.getInstance().isVerTerm()) {

                VerifDadosServ.getInstance().cancelVer();

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                AlertDialog.Builder alerta = new AlertDialog.Builder(MotoristaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA DE PESQUISA DE MOTORISTA! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                alerta.show();

            }

        }
    };

    public void onBackPressed() {
    }

}