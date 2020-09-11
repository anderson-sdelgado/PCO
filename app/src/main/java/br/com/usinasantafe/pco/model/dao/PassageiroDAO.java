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
import br.com.usinasantafe.pco.util.EnvioDadosServ;
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

    public boolean verPassageiroViagemList(String dthr){
        List passageiroList = passageiroViagemList(dthr);
        boolean ret = passageiroList.size() > 0;
        passageiroList.clear();
        return ret;
    }

    public boolean verMatricColabViagem(Long matricColab){
        List<PassageiroBean> passageiroList = passageiroList();
        boolean ret = true;
        if(passageiroList.size() > 0) {
            PassageiroBean passageiroBean = passageiroList.get(0);
            if (passageiroBean.getMatricColabPassageiro() == matricColab) {
                ret = false;
            }
        }
        return ret;
    }

    public List<PassageiroBean> passageiroEnviadoList(){
        PassageiroBean passageiroBean = new PassageiroBean();
        return passageiroBean.get("statusPassageiro", 2L);
    }

    public List<PassageiroBean> passageiroNEnviadoList(){
        PassageiroBean passageiroBean = new PassageiroBean();
        return passageiroBean.get("statusPassageiro", 1L);
    }

    public List<PassageiroBean> passageiroViagemList(String dthr){
        PassageiroBean passageiroBean = new PassageiroBean();
        return passageiroBean.getAndOrderBy("dthrPassageiro", dthr, "idPassageiro", false);
    }

    public List<PassageiroBean> passageiroList(){
        PassageiroBean passageiroBean = new PassageiroBean();
        return passageiroBean.orderBy("idPassageiro", false);
    }

    public void salvarPassageiro(ConfigBean configBean, Long matricColab){
        PassageiroBean passageiroBean = new PassageiroBean();
        passageiroBean.setDthrPassageiro(Tempo.getInstance().dataComHora());
        passageiroBean.setDthrViagemPassageiro(configBean.getDtrhViagemConfig());
        passageiroBean.setIdEquipPassageiro(configBean.getIdEquipConfig());
        passageiroBean.setMatricMotoPassageiro(configBean.getMatricMotoConfig());
        passageiroBean.setIdTurnoPassageiro(configBean.getIdTurnoConfig());
        passageiroBean.setMatricColabPassageiro(matricColab);
        passageiroBean.setStatusPassageiro(1L);
        passageiroBean.insert();

        EnvioDadosServ.getInstance().setStatusEnvio(2);

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

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjPassageiro = new JSONObject(objPrinc);
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

            EnvioDadosServ.getInstance().setStatusEnvio(3);

        }
        catch(Exception e){
        }

    }

    public void delPassageiro(){

        for(PassageiroBean passageiroBean : passageiroEnviadoList()){

            if(Tempo.getInstance().timeDataHora(passageiroBean.getDthrViagemPassageiro()) <= Tempo.getInstance().timeMenos1Mes()){
                passageiroBean.delete();
            }

        }

    }

}
