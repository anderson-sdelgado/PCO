package br.com.usinasantafe.pco.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pco.control.ViagemCTR;
import br.com.usinasantafe.pco.model.dao.LogErroDAO;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pco.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pco.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pco.view.ActivityGeneric;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    public static int status; //1 - Existe Dados para Enviar; 2 - Enviando; 3 - Todos os Dados Foram Enviados;

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    public void envioDados(String activity) {
        LogProcessoDAO.getInstance().insertLogProcesso("public void envioDados(String activity) {\n" +
                "        status = 1;", activity);
        status = 1;
        if(ActivityGeneric.connectNetwork) {
            LogProcessoDAO.getInstance().insertLogProcesso("if(ActivityGeneric.connectNetwork) {\n" +
                    "            status = 2;", activity);
            status = 2;
            if (verifCabecFechado()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (verifCabecFechado()) {\n" +
                        "enviarCabecFechado(activity);", activity);
                enviarCabecFechado(activity);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {", activity);
                if(verifPassagNEnviado()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(verifPassagNEnviado()){\n" +
                            "                    enviarCabecAberto(activity);", activity);
                    enviarCabecAberto(activity);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    status = 3;", activity);
                    status = 3;
                }
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "                    status = 3;", activity);
            status = 3;
        }
    }

    public boolean verifDadosEnvio() {
        if ((!verifPassagNEnviado())
                && (!verifCabecFechado())){
            return false;
        } else {
            return true;
        }
    }

    ////////////////////////////// VERIFICAR ENVIO DADOS //////////////////////////////////////////

    public Boolean verifPassagNEnviado() {
        ViagemCTR viagemCTR = new ViagemCTR();
        return viagemCTR.verPassageiroNEnviado();
    }

    public Boolean verifCabecFechado() {
        ViagemCTR viagemCTR = new ViagemCTR();
        return viagemCTR.verCabecViagemFechado();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// ENVIAR DADOS ///////////////////////////////////////////////

    public void enviarCabecFechado(String activity) {

        ViagemCTR viagemCTR = new ViagemCTR();

        LogProcessoDAO.getInstance().insertLogProcesso("motoMecFertCTR.dadosEnvioBolFechadoMMFert()", activity);
        envio(urlsConexaoHttp.getsInsertCabecFechado(), viagemCTR.dadosEnvioCabecFechado(), activity);

    }

    public void enviarCabecAberto(String activity) {

        ViagemCTR viagemCTR = new ViagemCTR();

        LogProcessoDAO.getInstance().insertLogProcesso("motoMecFertCTR.dadosEnvioBolAbertoMMFert()", activity);
        envio(urlsConexaoHttp.getsInsertCabecAberto(), viagemCTR.dadosEnvioCabecAberto(), activity);

    }

    public void envio(String url, String dados, String activity){

        String[] strings = {url, activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);
        Log.i("PCO", "postCadGenerico.execute('" + url + "'); - Dados de Envio = " + dados);
        LogProcessoDAO.getInstance().insertLogProcesso("postCadGenerico.execute('" + url + "'); - Dados de Envio = " + dados, activity);
        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(strings);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO RECEBIMENTO/////////////////////////////////////////

    public void recDados(String result, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("public void recDados(" + result + ", activity){", activity);
        if(result.trim().startsWith("CABECABERTO_")) {
            ViagemCTR viagemCTR = new ViagemCTR();
            viagemCTR.updateCabecAberto(result, activity);
            LogProcessoDAO.getInstance().insertLogProcesso("if(result.trim().startsWith(\"CABECABERTO_\")) {\n" +
                    "            ViagemCTR viagemCTR = new ViagemCTR();\n" +
                    "            viagemCTR.updateCabecAberto(result, activity);", activity);
        }
        else if(result.trim().startsWith("CABECFECHADO_")) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if(result.trim().startsWith(\"CABECFECHADO_\")) {\n" +
                    "            ViagemCTR viagemCTR = new ViagemCTR();\n" +
                    "            viagemCTR.updateCabecFechado(result, activity);", activity);
            ViagemCTR viagemCTR = new ViagemCTR();
            viagemCTR.updateCabecFechado(result, activity);
        }
        else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                    "            status = 1;", activity);
            status = 1;
            LogErroDAO.getInstance().insertLogErro(result);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
