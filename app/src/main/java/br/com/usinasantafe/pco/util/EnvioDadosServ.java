package br.com.usinasantafe.pco.util;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pco.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pco.util.connHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;

    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
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

    public void enviarDadosSoqueira() {

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {

//            ConfigCTR configCTR = new ConfigCTR();
//            String dados = configCTR.dadosEnvioSoqueira();
            String dados = "";
//
//            Log.i("PMM", "BOLETIM SOQUEIRA = " + dados);

            UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

            String[] url = {urlsConexaoHttp.getsInserirSoqueira()};
            Map<String, Object> parametrosPost = new HashMap<String, Object>();
            parametrosPost.put("dado", dados);

            PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
            conHttpPostGenerico.setParametrosPost(parametrosPost);
            conHttpPostGenerico.execute(url);

        }
        else{
            posEnvio = -1;
        }

    }

    public boolean verifDados() {
//        ConfigCTR configCTR = new ConfigCTR();
//        configCTR.verifDadosRuricola();
        return false;
    }

    public void envioDados(Context context) {
        this.context = context;
        if (verifDados()) {
            posEnvio = 1;
//            enviarDadosRuricola();
        }
        else{
            posEnvio = 0;
        }
    }

    public int getStatusEnvio() {
        if (verifDados()) {
            statusEnvio = 1;
        } else {
            statusEnvio = 2;
        }
        return statusEnvio;
    }

    ////////////////////////////////////MECANISMO RECEBIMENTO/////////////////////////////////////////

    public void recDados(String result){
//        if (result.trim().contains("GRAVOU-SOQUEIRA")) {
//            SoqueiraCTR soqueiraCTR = new SoqueiraCTR();
//            soqueiraCTR.updateDados(result);
//            posEnvio = 5 ;
//        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }

    public int getPosEnvio() {
        return posEnvio;
    }

    public void setPosEnvio(int posEnvio) {
        this.posEnvio = posEnvio;
    }
}
