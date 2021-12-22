package br.com.usinasantafe.pco.util;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pco.control.PassageiroCTR;
import br.com.usinasantafe.pco.model.dao.LogErroDAO;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pco.model.dao.PassageiroDAO;
import br.com.usinasantafe.pco.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pco.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pco.view.ActivityGeneric;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    public static int status; //1 - Existe Dados para Enviar; 2 - Enviando; 3 - Todos os Dados Foram Enviados;
    private int posEnvio;
    private Context context;

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
        status = 1;
        if(ActivityGeneric.connectNetwork) {
            LogProcessoDAO.getInstance().insertLogProcesso("ActivityGeneric.connectNetwork", activity);
            status = 2;
            if (verifDadosEnvio()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (verifCabecFechado()) {\n" +
                        "enviarCabecFechado(activity);", activity);
                enviarPassageiro(activity);
            } else {
                status = 3;
            }
        } else{
            status = 3;
        }
    }

    public boolean verifDadosEnvio() {
        PassageiroCTR passageiroCTR = new PassageiroCTR();
        return passageiroCTR.verPassageiroNEnviado();
    }


    ////////////////////////////////// ENVIAR DADOS ///////////////////////////////////////////////

    public void enviarPassageiro(String activity) {

        PassageiroCTR passageiroCTR = new PassageiroCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("envio(urlsConexaoHttp.getsInsertBolFechadoMMFert(), carregCTR.dadosEnvioCabecFechado(), activity);", activity);
        envio(urlsConexaoHttp.getsInserirPassageiro(), passageiroCTR.dadosEnvio(), activity);

    }

    public void envio(String url, String dados, String activity){

        String[] strings = {url, activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);
        LogProcessoDAO.getInstance().insertLogProcesso("postCadGenerico.execute('" + url + "'); - Dados de Envio = " + dados, activity);
        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(strings);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO RECEBIMENTO/////////////////////////////////////////

    public void recDados(String result, String activity){
        if(result.trim().startsWith("SALVOU")) {
            PassageiroCTR passageiroCTR = new PassageiroCTR();
            passageiroCTR.updatePassageiro(result, activity);
        }
        else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                    "            status = 1;", activity);
            status = 1;
            LogErroDAO.getInstance().insertLogErro(result);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public int getPosEnvio() {
        return posEnvio;
    }

    public void setPosEnvio(int posEnvio) {
        this.posEnvio = posEnvio;
    }
}
