package br.com.usinasantafe.pco.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pco.control.ViagemCTR;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pco.control.ConfigCTR;
import br.com.usinasantafe.pco.util.connHttp.PostVerGenerico;
import br.com.usinasantafe.pco.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pco.view.TelaInicialActivity;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;
    private String dados;
    private String classe;
    private PostVerGenerico postVerGenerico;
    private String msgVerifColab;
    private String nroMatricula;
    public static int status; //1 - Existe Dados para Enviar; 2 - Enviando; 3 - Todos os Dados Foram Enviados;
    private TelaInicialActivity telaInicialActivity;

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result, String activity) {

        ConfigCTR configCTR = new ConfigCTR();
        ViagemCTR viagemCTR = new ViagemCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("public void manipularDadosHttp(String result) {", activity);
        if (this.classe.equals("Atualiza")) {
            LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"Atualiza\")) {\n" +
                    "            configCTR.recAtual(result.trim());\n" +
                    "            status = 3;", activity);
            configCTR.recAtual(result.trim());
            status = 3;
            LogProcessoDAO.getInstance().insertLogProcesso("this.menuInicialActivity.encerrarBarra();", activity);
            this.telaInicialActivity.goMenuInicial();
        } else if (this.classe.equals("Moto")) {
            viagemCTR.recDadosMotorista(result);
        } else if (this.classe.equals("Colab")) {
            viagemCTR.recDadosColab(result, activity);
        } else if(this.classe.equals("Token")) {
            configCTR.recToken(result.trim(), this.telaAtual, this.progressDialog, activity);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            status = 1;", activity);
            status = 1;
        }
    }

    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        status = 2;
        urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.dados = dado;
        this.classe = tipo;

        envioVerif(telaAtual.getPackageName());

    }

    public void verifAtualAplic(String dados, TelaInicialActivity telaInicialActivity, String activity) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.classe = "Atualiza";
        this.dados = dados;
        this.telaInicialActivity = telaInicialActivity;

        envioVerif(activity);

    }

    public void salvarToken(String dados, Context telaAtual, ProgressDialog progressDialog, String activity) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.classe = "Token";
        this.telaAtual = telaAtual;
        this.progressDialog = progressDialog;
        this.dados = dados;

        envioVerif(activity);

    }

    public void envioVerif(String activity) {

        status = 2;
        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(classe), activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", this.dados);

        Log.i("PCO", "postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(classe) + "'); - Dados de Envio = " + this.dados);
        LogProcessoDAO.getInstance().insertLogProcesso("postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(classe) + "'); - Dados de Envio = " + this.dados, activity);
        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

    public void cancelVer() {
        status = 3;
        if (postVerGenerico.getStatus() == AsyncTask.Status.RUNNING) {
            postVerGenerico.cancel(true);
        }
    }

    public void pulaTela(){
        if(status < 3) {
            status = 3;
            this.progressDialog.dismiss();
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);
        }
    }

    public void msgSemTerm(String texto){
        this.progressDialog.dismiss();
        AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
        alerta.setTitle("ATENÇÃO");
        alerta.setMessage(texto);
        alerta.setPositiveButton("OK", (dialog, which) -> {
        });
        alerta.show();
    }

    public void cancel() {
        status = 3;
        if (postVerGenerico.getStatus() == AsyncTask.Status.RUNNING) {
            postVerGenerico.cancel(true);
        }
    }

    public String getMsgVerifColab() {
        return msgVerifColab;
    }

    public void setMsgVerifColab(String msgVerifColab) {
        this.msgVerifColab = msgVerifColab;
    }

    public String getNroMatricula() {
        return nroMatricula;
    }

    public void setNroMatricula(String nroMatricula) {
        this.nroMatricula = nroMatricula;
    }
}
