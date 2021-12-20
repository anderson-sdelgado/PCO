package br.com.usinasantafe.pco.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pco.view.MenuInicialActivity;
import br.com.usinasantafe.pco.control.ConfigCTR;
import br.com.usinasantafe.pco.model.bean.AtualAplicBean;
import br.com.usinasantafe.pco.model.dao.ColabDAO;
import br.com.usinasantafe.pco.model.dao.MotoristaDAO;
import br.com.usinasantafe.pco.model.pst.GenericRecordable;
import br.com.usinasantafe.pco.util.connHttp.PostVerGenerico;
import br.com.usinasantafe.pco.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pco.view.TelaInicialActivity;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private GenericRecordable genericRecordable;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private String variavel;
    private ProgressDialog progressDialog;
    private String dados;
    private String tipo;
    private AtualAplicBean atualAplicBean;
    private MenuInicialActivity menuInicialActivity;
    private boolean verTerm;
    private PostVerGenerico postVerGenerico;
    private String msgVerifColab;
    public static int status;
    private TelaInicialActivity telaInicialActivity;

    public VerifDadosServ() {
        //genericRecordable = new GenericRecordable();
    }

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result, String activity) {

        ConfigCTR configCTR = new ConfigCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("public void manipularDadosHttp(String result) {", activity);
        if (this.tipo.equals("Atualiza")) {
            LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"Atualiza\")) {\n" +
                    "            configCTR.recAtual(result.trim());\n" +
                    "            status = 3;", activity);
            configCTR.recAtual(result.trim());
            status = 3;
            LogProcessoDAO.getInstance().insertLogProcesso("this.menuInicialActivity.encerrarBarra();", activity);
            this.telaInicialActivity.goMenuInicial();
        }
        else if (this.tipo.equals("Moto")) {
            MotoristaDAO motoristaDAO = new MotoristaDAO();
            motoristaDAO.recDadosMotorista(result);
        }
        else if (this.tipo.equals("Colab")) {
            ColabDAO colabDAO = new ColabDAO();
            colabDAO.recDadosColab(result, activity);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            status = 1;", activity);
            status = 1;
        }
    }

    public String manipLocalClasse(String classe) {
        if (classe.contains("TO")) {
            classe = urlsConexaoHttp.localPSTEstatica + classe;
        }
        return classe;
    }

    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        verTerm = false;
        urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.dados = dado;
        this.tipo = tipo;

        envioDados();

    }

    public void envioDados() {

        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", String.valueOf(dados));

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

    public void verifAtualAplic(String dados, TelaInicialActivity telaInicialActivity, String activity) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.tipo = "Atualiza";
        this.dados = dados;
        this.telaInicialActivity = telaInicialActivity;

        envioVerif(activity);

    }

    public void envioVerif(String activity) {

        status = 2;
        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(tipo), activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", this.dados);

        LogProcessoDAO.getInstance().insertLogProcesso("postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(tipo) + "'); - Dados de Envio = " + this.dados, activity);
        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

    public void cancelVer() {
        verTerm = true;
        if (postVerGenerico.getStatus() == AsyncTask.Status.RUNNING) {
            postVerGenerico.cancel(true);
        }
    }

    public void pulaTelaSemTerm(){
        this.progressDialog.dismiss();
        Intent it = new Intent(telaAtual, telaProx);
        telaAtual.startActivity(it);
    }

    public void msgSemTerm(String texto){
        this.progressDialog.dismiss();
        AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
        alerta.setTitle("ATENÇÃO");
        alerta.setMessage(texto);
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alerta.show();
    }

    public void cancel() {
        status = 3;
        if (postVerGenerico.getStatus() == AsyncTask.Status.RUNNING) {
            postVerGenerico.cancel(true);
        }
    }

    public boolean isVerTerm() {
        return verTerm;
    }

    public void setVerTerm(boolean verTerm) {
        this.verTerm = verTerm;
    }

    public String getMsgVerifColab() {
        return msgVerifColab;
    }

    public void setMsgVerifColab(String msgVerifColab) {
        this.msgVerifColab = msgVerifColab;
    }
}
