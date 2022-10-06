package br.com.usinasantafe.pco.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pco.util.Tempo;
import br.com.usinasantafe.pco.util.VerifDadosServ;
import br.com.usinasantafe.pco.zxing.CaptureActivity;

public class MsgAddPassageiroActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCOContext pcoContext;
    private TextView textViewMsgPassageiro;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_add_passageiro);

        pcoContext = (PCOContext) getApplication();

        textViewMsgPassageiro = findViewById(R.id.textViewMsgPassageiro);
        Button buttonMsgPassageiroNao = findViewById(R.id.buttonMsgPassageiroNao);
        Button buttonMsgPassageiroSim = findViewById(R.id.buttonMsgPassageiroSim);

        LogProcessoDAO.getInstance().insertLogProcesso("msgPassageiro();", getLocalClassName());
        msgPassageiro();

        buttonMsgPassageiroNao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgPassageiroNao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(MsgAddPassageiroActivity.this, ListaPassageiroActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgAddPassageiroActivity.this, ListaPassageiroActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonMsgPassageiroSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgPassageiroSim.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(MsgAddPassageiroActivity.this, CaptureActivity.class);\n" +
                        "                startActivityForResult(it, REQUEST_CODE);", getLocalClassName());
                Intent it = new Intent(MsgAddPassageiroActivity.this, CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            LogProcessoDAO.getInstance().insertLogProcesso("public void onActivityResult(int requestCode, int resultCode, Intent data){\n" +
                    "        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){\n" +
                    "            String matriculaPassageiro = data.getStringExtra(\"SCAN_RESULT\");\n" +
                    "            pcoContext.setMatriculaPassageiro(matriculaPassageiro);\n" +
                    "            VerifDadosServ.getInstance().setMsgVerifColab(\"\");\n" +
                    "            msgPassageiro();", getLocalClassName());
            String matriculaPassageiro = data.getStringExtra("SCAN_RESULT");
            VerifDadosServ.getInstance().setMatricula(matriculaPassageiro);
            VerifDadosServ.getInstance().setMsgVerifColab("");
            msgPassageiro();
        }

    }

    public void msgPassageiro(){

        LogProcessoDAO.getInstance().insertLogProcesso("public void msgPassageiro(){\n" +
                "        String msgBag = \"\";", getLocalClassName());
        String msgBag = "";

        if(VerifDadosServ.getInstance().getMsgVerifColab().equals("")){
            LogProcessoDAO.getInstance().insertLogProcesso("if(VerifDadosServ.getInstance().getMsgVerifColab().equals(\"\")){", getLocalClassName());
            if(VerifDadosServ.getInstance().getMatricula().length() == 8){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pcoContext.getMatriculaPassageiro().length() == 8){\n" +
                        "                pcoContext.setMatriculaPassageiro(pcoContext.getMatriculaPassageiro().substring(0,7));", getLocalClassName());
                VerifDadosServ.getInstance().setMatricula(VerifDadosServ.getInstance().getMatricula().substring(0,7));
                if (pcoContext.getViagemCTR().verColab(Long.parseLong(VerifDadosServ.getInstance().getMatricula()))) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pcoContext.getPassageiroCTR().verColab(Long.parseLong(pcoContext.getMatriculaPassageiro()))) {", getLocalClassName());
                    if(pcoContext.getViagemCTR().verMatricColabViagem(Long.parseLong(VerifDadosServ.getInstance().getMatricula()))) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcoContext.getPassageiroCTR().verMatricColabViagem(Long.parseLong(pcoContext.getMatriculaPassageiro()))) {\n" +
                                "                        pcoContext.getPassageiroCTR().salvarPassageiro(Long.parseLong(pcoContext.getMatriculaPassageiro()), getLocalClassName());\n" +
                                "                        ColabBean colabBean = pcoContext.getPassageiroCTR().getColab(Long.parseLong(pcoContext.getMatriculaPassageiro()));\n" +
                                "                        msgBag = msgBag + Tempo.getInstance().dthr(Tempo.getInstance().dthr()) + \"\\n\" +\n" +
                                "                                + colabBean.getMatricColab() + \" - \" + colabBean.getNomeColab();", getLocalClassName());
                        pcoContext.getViagemCTR().salvarPassageiro(Long.parseLong(VerifDadosServ.getInstance().getMatricula()), 1L, getLocalClassName());
                        ColabBean colabBean = pcoContext.getViagemCTR().getColab(Long.parseLong(VerifDadosServ.getInstance().getMatricula()));
                        msgBag = msgBag + Tempo.getInstance().dthr(Tempo.getInstance().dthr()) + "\n" +
                                + colabBean.getMatricColab() + " - " + colabBean.getNomeColab();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        msgBag = msgBag + \"FUNCIONÁRIO REPETIDO! POR FAVOR, INSIRA OUTRO FUNCIONÁRIO.\";", getLocalClassName());
                        msgBag = msgBag + "FUNCIONÁRIO REPETIDO! POR FAVOR, INSIRA OUTRO FUNCIONÁRIO.";
                    }
                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    progressBar = new ProgressDialog(MsgAddPassageiroActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO COLABORADOR...\");\n" +
                            "                    progressBar.show();\n" +
                            "                    pcoContext.getConfigCTR().setPosicaoTela(4L);\n" +
                            "                    pcoContext.getPassageiroCTR().verColab(pcoContext.getMatriculaPassageiro(), MsgAddPassageiroActivity.this, MsgAddPassageiroActivity.class, progressBar);", getLocalClassName());

                    progressBar = new ProgressDialog(MsgAddPassageiroActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO COLABORADOR...");
                    progressBar.show();

                    pcoContext.getConfigCTR().setPosicaoTela(4L);
                    pcoContext.getViagemCTR().verColab(VerifDadosServ.getInstance().getMatricula(), MsgAddPassageiroActivity.this, MsgAddPassageiroActivity.class, progressBar);
                }
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            msgBag = msgBag + VerifDadosServ.getInstance().getMsgVerifColab();", getLocalClassName());
            msgBag = msgBag + VerifDadosServ.getInstance().getMsgVerifColab();
        }

        if(pcoContext.getViagemCTR().qtdePassageiroPorLotacao() == 0){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcoContext.getPassageiroCTR().qtdePassageiroPorLotacao() == 0){\n" +
                    "            msgBag = msgBag + \"\\nÔNIBUS ESTA COM A LOTAÇÃO MÁXIMA!\";", getLocalClassName());
            msgBag = msgBag + "\nÔNIBUS ESTA COM A LOTAÇÃO MÁXIMA!";
        } else if(pcoContext.getViagemCTR().qtdePassageiroPorLotacao() > 0){
            LogProcessoDAO.getInstance().insertLogProcesso("} else if(pcoContext.getPassageiroCTR().qtdePassageiroPorLotacao() > 0){\n" +
                    "            msgBag = msgBag + \"\\nFALTA \" + pcoContext.getPassageiroCTR().qtdePassageiroPorLotacao() + \" PASSAGEIRO(S) PARA LOTAÇÃO MÁXIMA!\";", getLocalClassName());
            msgBag = msgBag + "\nFALTA " + pcoContext.getViagemCTR().qtdePassageiroPorLotacao() + " PASSAGEIRO(S) PARA LOTAÇÃO MÁXIMA!";
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            msgBag = msgBag + \"\\nO ÔNIBUS ESTA COM \" + (pcoContext.getPassageiroCTR().qtdePassageiroPorLotacao() * -1) + \" PASSAGEIRO(S) ACIMA DA LOTAÇÃO MÁXIMA!\";", getLocalClassName());
            msgBag = msgBag + "\nO ÔNIBUS ESTA COM " + (pcoContext.getViagemCTR().qtdePassageiroPorLotacao() * -1) + " PASSAGEIRO(S) ACIMA DA LOTAÇÃO MÁXIMA!";
        }

        LogProcessoDAO.getInstance().insertLogProcesso("\nDESEJA INSERIR OUTRO PASSAGEIRO NA VIAGEM?", getLocalClassName());
        msgBag = msgBag + "\nDESEJA INSERIR OUTRO PASSAGEIRO NA VIAGEM?";

        textViewMsgPassageiro.setText(msgBag);

    }

}