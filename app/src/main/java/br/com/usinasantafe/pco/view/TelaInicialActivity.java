package br.com.usinasantafe.pco.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pco.util.ConnectNetwork;
import br.com.usinasantafe.pco.util.EnvioDadosServ;
import br.com.usinasantafe.pco.util.Tempo;
import br.com.usinasantafe.pco.util.VerifDadosServ;

public class TelaInicialActivity extends ActivityGeneric {

    private PCOContext pcoContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        pcoContext = (PCOContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("        if (!checkPermission(Manifest.permission.CAMERA)) {\n" +
                "            String[] PERMISSIONS = {Manifest.permission.CAMERA};\n" +
                "            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);\n" +
                "        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {\n" +
                "            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};\n" +
                "            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);\n" +
                "        }\n" +
                "customHandler.postDelayed(excluirBDThread, 0);", getLocalClassName());
        customHandler.postDelayed(excluirBDThread, 0);

    }

    public void onBackPressed() {
    }

    public void goMenuInicial(){

        LogProcessoDAO.getInstance().insertLogProcesso("public void goMenuInicial(){\n" +
                "        customHandler.removeCallbacks(encerraAtualThread);", getLocalClassName());
        customHandler.removeCallbacks(encerraAtualThread);
        if(pcoContext.getConfigCTR().hasElemConfig()){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getConfigCTR().hasElemConfig()){", getLocalClassName());
            if(Tempo.getInstance().verDthrServ(pcoContext.getConfigCTR().getConfig().getDthrServConfig())){
                LogProcessoDAO.getInstance().insertLogProcesso("if(Tempo.getInstance().verDthrServ(pcoContext.getConfigCTR().getConfig().getDthrServConfig())){", getLocalClassName());
                if(pcoContext.getViagemCTR().verCabecViagemAberto()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getViagemCTR().verCabecViagemAberto()){" +
                            "            Intent it = new Intent(TelaInicialActivity.this, ListaPassageiroActivity.class);", getLocalClassName());
                    Intent it = new Intent(TelaInicialActivity.this, ListaPassageiroActivity.class);
                    startActivity(it);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "            Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);", getLocalClassName());
                    Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                }
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "pmmContext.getConfigCTR().setContDataHora(1);", getLocalClassName());
                pcoContext.getConfigCTR().setContDataHora(1);
                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(TelaInicialActivity.this, MsgDataHoraActivity.class);", getLocalClassName());
                Intent it = new Intent(TelaInicialActivity.this, MsgDataHoraActivity.class);
                startActivity(it);
                finish();
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);
            startActivity(it);
        }
        finish();
    }

    public void atualizarAplic(){
        LogProcessoDAO.getInstance().insertLogProcesso("public void atualizarAplic(){", getLocalClassName());
        if(ConnectNetwork.isConnected(this)){
            LogProcessoDAO.getInstance().insertLogProcesso("if(ConnectNetwork.isConnected(this)){\n" +
                    "            connectNetwork = true;", getLocalClassName());
            connectNetwork = true;
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            connectNetwork = false;", getLocalClassName());
            connectNetwork = false;
        }
        if (connectNetwork) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {", getLocalClassName());
            if (pcoContext.getConfigCTR().hasElemConfig()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getConfigCTR().hasElemConfig()) {", getLocalClassName());
                if(!pcoContext.getConfigCTR().getConfig().getSenhaConfig().equals("")){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(!pcbContext.getConfigCTR().getConfig().getSenhaConfig().equals(\"\")){\n" +
                            "                customHandler.postDelayed(updateTimerThread, 10000);\n" +
                            "pmmContext.getConfigCTR().verAtualAplic(pmmContext.versaoAplic, this, getLocalClassName());", getLocalClassName());
                    customHandler.postDelayed(encerraAtualThread, 10000);
                    pcoContext.getConfigCTR().verAtualAplic(pcoContext.versaoWS, this, getLocalClassName());
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                VerifDadosServ.status = 3;\n" +
                            "goMenuInicial();", getLocalClassName());
                    VerifDadosServ.status = 3;
                    goMenuInicial();
                }
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "                VerifDadosServ.status = 3;\n" +
                        "goMenuInicial();", getLocalClassName());
                VerifDadosServ.status = 3;
                goMenuInicial();
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "                VerifDadosServ.status = 3;\n" +
                    "goMenuInicial();", getLocalClassName());
            VerifDadosServ.status = 3;
            goMenuInicial();
        }
    }

    public void clearBD() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void clearBD() {\n" +
                "        pcbContext.getConfigCTR().deleteLogs();\n" +
                "        pcbContext.getPassageiroCTR().delPassageiro();", getLocalClassName());
        pcoContext.getConfigCTR().deleteLogs();
        pcoContext.getViagemCTR().deleteViagemEnviada();
    }

    private Runnable encerraAtualThread = () -> {
        LogProcessoDAO.getInstance().insertLogProcesso("    private Runnable updateTimerThread = new Runnable() {\n" +
                "        public void run() {", getLocalClassName());
        LogProcessoDAO.getInstance().insertLogProcesso("verifEnvio();", getLocalClassName());
        if(VerifDadosServ.status < 3) {
            LogProcessoDAO.getInstance().insertLogProcesso("if(VerifDadosServ.status < 3) {\n" +
                    "VerifDadosServ.getInstance().cancel();", getLocalClassName());
            VerifDadosServ.getInstance().cancel();
        }
        LogProcessoDAO.getInstance().insertLogProcesso("goMenuInicial();", getLocalClassName());
        goMenuInicial();
    };

    private Runnable excluirBDThread = () -> {

        LogProcessoDAO.getInstance().insertLogProcesso("clearBD();", getLocalClassName());
        clearBD();

        if(EnvioDadosServ.getInstance().verifDadosEnvio()){
            LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().verifDadosEnvio()", getLocalClassName());
            if(connectNetwork){
                LogProcessoDAO.getInstance().insertLogProcesso("if(connectNetwork){\n" +
                        "EnvioDadosServ.getInstance().envioDados()", getLocalClassName());
                EnvioDadosServ.getInstance().envioDados(getLocalClassName());
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "                EnvioDadosServ.status = 1;", getLocalClassName());
                EnvioDadosServ.status = 1;
            }
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            EnvioDadosServ.status = 3;", getLocalClassName());
            EnvioDadosServ.status = 3;
        }

        LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.status = 3;\n" +
                "            atualizarAplic();", getLocalClassName());
        VerifDadosServ.status = 3;
        atualizarAplic();

    };
}