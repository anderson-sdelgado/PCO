package br.com.usinasantafe.pco.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pco.control.ConfigCTR;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.util.VerifDadosServ;

public class MotoristaDAO {

    public MotoristaDAO() {
    }

    public boolean hasElements(){
        MotoristaBean motoristaBean = new MotoristaBean();
        return motoristaBean.hasElements();
    }

    public boolean verMotorista(Long matricMoto){
        List<MotoristaBean> motoristaList = motoristaList(matricMoto);
        boolean ret = motoristaList.size() > 0;
        motoristaList.clear();
        return ret;
    }

    public MotoristaBean getMotorista(Long matricMoto){
        List<MotoristaBean> motoristaList = motoristaList(matricMoto);
        MotoristaBean motoristaBean = motoristaList.get(0);
        motoristaList.clear();
        return motoristaBean;
    }

    private List<MotoristaBean> motoristaList(Long matricMoto){
        MotoristaBean motoristaBean = new MotoristaBean();
        return motoristaBean.get("matricMoto", matricMoto);
    }

    public void verMotorista(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().verDados(dado, "Moto", telaAtual, telaProx, progressDialog);
    }

}
