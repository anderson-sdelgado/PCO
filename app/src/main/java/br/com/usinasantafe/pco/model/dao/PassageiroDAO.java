package br.com.usinasantafe.pco.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pco.model.bean.variaveis.PassageiroBean;
import br.com.usinasantafe.pco.util.Tempo;

public class PassageiroDAO {

    public PassageiroDAO() {
    }

    public boolean verPassageiroNEnviado(){
        List passageiroList = passageiroNEnviadoList();
        boolean ret = passageiroList.size() > 0;
        passageiroList.clear();
        return ret;
    }

    public List<PassageiroBean> passageiroNEnviadoList(){
        PassageiroBean passageiroBean = new PassageiroBean();
        return passageiroBean.get("statusPassageiro", 1L);
    }

    public List<PassageiroBean> passageiroNEnviadoList(String dthr){
        PassageiroBean passageiroBean = new PassageiroBean();
        return passageiroBean.getAndOrderBy("dthrPassageiro", dthr, "idPassageiro", false);
    }

    public void salvarPassageiro(ConfigBean configBean, Long matricColab){
        PassageiroBean passageiroBean = new PassageiroBean();
        passageiroBean.setDthrPassageiro(Tempo.getInstance().data());
        passageiroBean.setDthrViagemPassageiro(configBean.getDtrhViagemConfig());
        passageiroBean.setIdEquipPassageiro(configBean.getIdEquipConfig());
        passageiroBean.setMatricMotoPassageiro(configBean.getMatricMotoConfig());
        passageiroBean.setIdTurnoPassageiro(configBean.getIdTurnoConfig());
        passageiroBean.setMatricColabPassageiro(matricColab);
        passageiroBean.setStatusPassageiro(1L);
        passageiroBean.insert();
    }

    public String dadosEnvio(){

        List passageiroList = passageiroNEnviadoList();

        JsonArray jsonArrayPassageiro = new JsonArray();

        for (int i = 0; i < passageiroList.size(); i++) {

            PassageiroBean passageiroBean = (PassageiroBean) passageiroList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayPassageiro.add(gsonCabec.toJsonTree(passageiroBean, passageiroBean.getClass()));

        }

        passageiroList.clear();

        JsonObject jsonPassageiro = new JsonObject();
        jsonPassageiro.add("passageiro", jsonArrayPassageiro);

        return jsonPassageiro.toString();

    }

    public void updatePassageiro(String retorno) {

        try{

            JSONObject jObjPassageiro = new JSONObject(retorno);
            JSONArray jsonArrayPassageiro = jObjPassageiro.getJSONArray("passageiro");

            if (jsonArrayPassageiro.length() > 0) {

                ArrayList<Long> rList = new ArrayList<Long>();
                PassageiroBean passageiroBean = new PassageiroBean();

                for (int i = 0; i < jsonArrayPassageiro.length(); i++) {

                    JSONObject objPassageiro = jsonArrayPassageiro.getJSONObject(i);
                    Gson gsonPassageiro = new Gson();
                    passageiroBean = gsonPassageiro.fromJson(objPassageiro.toString(), PassageiroBean.class);

                    rList.add(passageiroBean.getIdPassageiro());

                }

                List passageiroList = passageiroBean.in("idPassageiro", rList);

                for (int i = 0; i < passageiroList.size(); i++) {

                    passageiroBean = (PassageiroBean) passageiroList.get(i);
                    passageiroBean.setStatusPassageiro(2L);
                    passageiroBean.update();

                }

                rList.clear();

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

}
