package br.com.usinasantafe.pco.model.dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pco.model.bean.variaveis.PassageiroBean;
import br.com.usinasantafe.pco.model.pst.EspecificaPesquisa;
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

    public boolean verMatricColabViagem(Long matricColab, String dthr){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqDthrViagem(dthr));
        pesqArrayList.add(getPesqMatricColab(matricColab));

        PassageiroBean passageiroBean = new PassageiroBean();
        List<PassageiroBean> passageiroList = passageiroBean.get(pesqArrayList);
        boolean ret = (passageiroList.size() == 0);
        passageiroList.clear();
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

    public List<PassageiroBean> passageiroViagemList(String dthr, Long matricMoto, Long idturno){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqDthrViagem(dthr));
        pesqArrayList.add(getPesqMatricMoto(matricMoto));
        pesqArrayList.add(getPesqTurno(idturno));

        PassageiroBean passageiroBean = new PassageiroBean();
        return passageiroBean.getAndOrderBy(pesqArrayList, "idPassageiro", false);
    }

    public List<PassageiroBean> passageiroList(){
        PassageiroBean passageiroBean = new PassageiroBean();
        return passageiroBean.orderBy("idPassageiro", false);
    }

    public List<PassageiroBean> passageiroViagemList(String dthr){
        PassageiroBean passageiroBean = new PassageiroBean();
        return passageiroBean.get("dthrViagemPassageiro", dthr);
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

    private EspecificaPesquisa getPesqDthrViagem(String dthrViagemPassageiro){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("dthrViagemPassageiro");
        pesquisa.setValor(dthrViagemPassageiro);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqMatricMoto(Long matricMoto){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("matricMotoPassageiro");
        pesquisa.setValor(matricMoto);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqTurno(Long idTurno){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idTurnoPassageiro");
        pesquisa.setValor(idTurno);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqMatricColab(Long matricColab){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("matricColabPassageiro");
        pesquisa.setValor(matricColab);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
