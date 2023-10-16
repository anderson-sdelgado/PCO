package br.com.usinasantafe.pco.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pco.model.bean.AtualAplicBean;
import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
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
        VerifDadosServ.getInstance().verDados(dado, "Colab", telaAtual, telaProx, progressDialog);
    }


    public ColabBean recColab(JSONArray jsonArray) throws JSONException {

        JSONObject objeto = jsonArray.getJSONObject(0);
        Gson gson = new Gson();
        ColabBean colabBean = gson.fromJson(objeto.toString(), ColabBean.class);
        colabBean.insert();

        return colabBean;
    }


}
