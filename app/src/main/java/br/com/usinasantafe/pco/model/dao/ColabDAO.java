package br.com.usinasantafe.pco.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pco.control.PassageiroCTR;
import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pco.util.Tempo;
import br.com.usinasantafe.pco.util.VerifDadosServ;

public class ColabDAO {

    public ColabDAO() {
    }

    public boolean verColab(Long matricColab){
        List<ColabBean> colabList = colabList(matricColab);
        boolean ret = colabList.size() > 0;
        colabList.clear();
        return ret;
    }

    public ColabBean getColab(Long matricColab){
        List<ColabBean> colabList = colabList(matricColab);
        ColabBean colabBean = (ColabBean) colabList.get(0);
        colabList.clear();
        return colabBean;
    }

    private List<ColabBean> colabList(Long matricColab){
        ColabBean colabBean = new ColabBean();
        return colabBean.get("matricColab", matricColab);
    }

    public void verColab(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(true);
        VerifDadosServ.getInstance().verDados(dado, "Colab", telaAtual, telaProx, progressDialog);
    }

    public void recDadosColab(String result, String activity){

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

                JSONObject objeto = jsonArray.getJSONObject(0);
                Gson gson = new Gson();
                ColabBean colabBean = gson.fromJson(objeto.toString(), ColabBean.class);
                colabBean.insert();

                PassageiroCTR passageiroCTR = new PassageiroCTR();
                passageiroCTR.salvarPassageiro(colabBean.getMatricColab(), activity);

                VerifDadosServ.getInstance().setMsgVerifColab(Tempo.getInstance().dthr() + "\n" +
                        + colabBean.getMatricColab() + " - " + colabBean.getNomeColab());
                VerifDadosServ.getInstance().pulaTelaSemTerm();

            } else {
                VerifDadosServ.getInstance().setMsgVerifColab("COLABORADOR INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
                VerifDadosServ.getInstance().pulaTelaSemTerm();
            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().setMsgVerifColab("FALHA DE PESQUISA DE COLABORADOR! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
            VerifDadosServ.getInstance().pulaTelaSemTerm();
        }
    }

}
