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
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;
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

        txtRetPassageiro = findViewById(R.id.txtRetPassageiro);
        Button buttonOkPassageiro = findViewById(R.id.buttonOkPassageiro);
        Button buttonCancPassageiro = findViewById(R.id.buttonCancPassageiro);
        Button buttonAcionarCamera = findViewById(R.id.buttonAcionarCamera);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        LogProcessoDAO.getInstance().insertLogProcesso("MotoristaBean motoristaBean = new MotoristaBean();\n" +
                "        motoristaBean.setMatricMoto(0L);\n" +
                "        motoristaBean.setNomeMoto(\"\");\n" +
                "        txtRetPassageiro.setText(\"POR FAVOR, REALIZE A LEITURA DO CRACHÁ DO MOTORISTA.\");", getLocalClassName());

        VerifDadosServ.getInstance().setMatricula("");
        txtRetPassageiro.setText("POR FAVOR, REALIZE A LEITURA DO CRACHÁ DO MOTORISTA.");

        buttonOkPassageiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkPassageiro.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(!VerifDadosServ.getInstance().getMatricula().equals("")){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(motoristaBean.getMatricMoto() > 0){\n" +
                            "                    pcoContext.getViagemCTR().setCabecViagemBean();\n" +
                            "                    pcoContext.getViagemCTR().getCabecViagemBean().setMatricMotoCabecViagem(motoristaBean.getMatricMoto());", getLocalClassName());

                    pcoContext.getViagemCTR().setCabecViagemBean();
                    pcoContext.getViagemCTR().getCabecViagemBean().setMatricMotoCabecViagem(Long.valueOf(VerifDadosServ.getInstance().getMatricula()));

                    if(pcoContext.getConfigCTR().getConfig().getTipoEquipConfig() == 1L){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcoContext.getConfigCTR().getConfig().getTipoEquipConfig() == 1L){\n" +
                                "                        Intent it = new Intent(MotoristaActivity.this, ListaTurnoActivity.class);", getLocalClassName());
                        Intent it = new Intent(MotoristaActivity.this, ListaTurnoActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(MotoristaActivity.this, EquipActivity.class);", getLocalClassName());
                        Intent it = new Intent(MotoristaActivity.this, EquipActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        });

        buttonCancPassageiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancPassageiro.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(MotoristaActivity.this, MenuInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(MotoristaActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonAcionarCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAcionarCamera.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(MotoristaActivity.this, CaptureActivity.class);\n" +
                        "                startActivityForResult(it, REQUEST_CODE);", getLocalClassName());
                Intent it = new Intent(MotoristaActivity.this, CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(MotoristaActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(MotoristaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "                            progressBar = new ProgressDialog(MotoristaActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO MOTORISTA...\");\n" +
                                    "                            progressBar.show();\n" +
                                    "                            pcoContext.getPassageiroCTR().atualDados(MotoristaActivity.this\n" +
                                    "                                    , MotoristaActivity.class, progressBar, \"Motorista\");", getLocalClassName());

                            progressBar = new ProgressDialog(MotoristaActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO MOTORISTA...");
                            progressBar.show();

                            pcoContext.getViagemCTR().atualDados(MotoristaActivity.this
                                    , MotoristaActivity.class, progressBar, "Motorista");

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MotoristaActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                            "                            AlertDialog.Builder alerta = new AlertDialog.Builder(MotoristaActivity.this);\n" +
                                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                            "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                            "                                @Override\n" +
                                            "                                public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                                }
                            });

                            alerta.show();

                        }
                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });

                alerta.show();

            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){

            LogProcessoDAO.getInstance().insertLogProcesso("@Override\n" +
                    "    public void onActivityResult(int requestCode, int resultCode, Intent data){\n" +
                    "        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){\n" +
                    "            String matricula = data.getStringExtra(\"SCAN_RESULT\");", getLocalClassName());
            String matric = data.getStringExtra("SCAN_RESULT");

            if(matric.length() == 8){

                LogProcessoDAO.getInstance().insertLogProcesso("if(matricula.length() == 8){\n" +
                        "                matricula = matricula.substring(0, 7);", getLocalClassName());
                VerifDadosServ.getInstance().setMatricula(matric.substring(0, 7));

                if (pcoContext.getViagemCTR().verMotorista(Long.parseLong(VerifDadosServ.getInstance().getMatricula()))) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (pcoContext.getPassageiroCTR().verMotorista(Long.parseLong(matricula))) {\n" +
                            "                    motoristaBean = pcoContext.getPassageiroCTR().getMotorista(Long.parseLong(matricula));\n" +
                            "                    txtRetPassageiro.setText(motoristaBean.getMatricMoto() + \"\\n\" + motoristaBean.getNomeMoto());", getLocalClassName());
                    MotoristaBean motoristaBean = pcoContext.getViagemCTR().getMotorista(Long.parseLong(VerifDadosServ.getInstance().getMatricula()));
                    txtRetPassageiro.setText(motoristaBean.getMatricMoto() + "\n" + motoristaBean.getNomeMoto());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    progressBar = new ProgressDialog(MotoristaActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"PESQUISANDO MOTORISTA...\");\n" +
                            "                    progressBar.show();\n" +
                            "                    customHandler.postDelayed(updateTimerThread, 10000);\n" +
                            "                    pcoContext.getPassageiroCTR().verMotorista(matricula, MotoristaActivity.this, MotoristaActivity.class, progressBar);", getLocalClassName());

                    progressBar = new ProgressDialog(MotoristaActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("PESQUISANDO MOTORISTA...");
                    progressBar.show();

                    customHandler.postDelayed(updateTimerThread, 10000);
                    pcoContext.getViagemCTR().verMotorista(VerifDadosServ.getInstance().getMatricula(), MotoristaActivity.this, MotoristaActivity.class, progressBar);

                }
            }
        }

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            LogProcessoDAO.getInstance().insertLogProcesso("private Runnable updateTimerThread = new Runnable() {\n" +
                    "        public void run() {", getLocalClassName());
            if(VerifDadosServ.status < 3) {

                LogProcessoDAO.getInstance().insertLogProcesso("if(VerifDadosServ.status < 3) {\n" +
                        "                VerifDadosServ.getInstance().cancelVer();", getLocalClassName());
                VerifDadosServ.getInstance().cancelVer();

                if (progressBar.isShowing()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (progressBar.isShowing()) {\n" +
                            "                    progressBar.dismiss();", getLocalClassName());
                    progressBar.dismiss();
                }

                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(MotoristaActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"FALHA DE PESQUISA DE MOTORISTA! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.\");", getLocalClassName());

                AlertDialog.Builder alerta = new AlertDialog.Builder(MotoristaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA DE PESQUISA DE MOTORISTA! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });

                alerta.show();

            }

        }
    };

    public void onBackPressed() {
    }

}