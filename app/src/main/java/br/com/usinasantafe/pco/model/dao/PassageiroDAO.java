package br.com.usinasantafe.pco.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
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
        List<PassageiroBean> passageiroList = passageiroNEnviadoList();
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

    public void salvarPassageiro(ConfigBean configBean, Long matricColab){
        Long dthr = Tempo.getInstance().dthr();
        PassageiroBean passageiroBean = new PassageiroBean();
        passageiroBean.setDthrPassageiro(Tempo.getInstance().dthr(dthr));
        passageiroBean.setDthrLongPassageiro(dthr);
        passageiroBean.setDthrViagemPassageiro(configBean.getDtrhViagemConfig());
        passageiroBean.setDthrLongViagemPassageiro(configBean.getDtrhLongViagemConfig());
        passageiroBean.setIdEquipPassageiro(configBean.getIdEquipConfig());
        passageiroBean.setMatricMotoPassageiro(configBean.getMatricMotoConfig());
        passageiroBean.setIdTurnoPassageiro(configBean.getIdTurnoConfig());
        passageiroBean.setMatricColabPassageiro(matricColab);
        passageiroBean.setIdTrajetoPassageiro(configBean.getIdTrajetoConfig());
        passageiroBean.setStatusPassageiro(1L);
        passageiroBean.insert();
    }

    public String dadosEnvio(){

        List<PassageiroBean> passageiroList = passageiroNEnviadoList();

        JsonArray jsonArrayPassageiro = new JsonArray();

        for (int i = 0; i < passageiroList.size(); i++) {
            PassageiroBean passageiroBean = passageiroList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayPassageiro.add(gsonCabec.toJsonTree(passageiroBean, passageiroBean.getClass()));
        }

        passageiroList.clear();

        JsonObject jsonPassageiro = new JsonObject();
        jsonPassageiro.add("passageiro", jsonArrayPassageiro);

        return jsonPassageiro.toString();

    }

    public void updatePassageiro(String objPrinc) throws JSONException {

        JSONObject passageiroJsonObj = new JSONObject(objPrinc);
        JSONArray passageiroJsonArray = passageiroJsonObj.getJSONArray("passageiro");

        if (passageiroJsonArray.length() > 0) {

            ArrayList<Long> rList = new ArrayList<>();
            PassageiroBean passageiroBean = new PassageiroBean();

            for (int i = 0; i < passageiroJsonArray.length(); i++) {

                JSONObject objPassageiro = passageiroJsonArray.getJSONObject(i);
                Gson gsonPassageiro = new Gson();
                passageiroBean = gsonPassageiro.fromJson(objPassageiro.toString(), PassageiroBean.class);

                rList.add(passageiroBean.getIdPassageiro());

            }

            List<PassageiroBean> passageiroList = passageiroBean.in("idPassageiro", rList);
            for (PassageiroBean passageiro : passageiroList) {
                passageiro.setStatusPassageiro(2L);
                passageiro.update();
            }
            rList.clear();

        }

    }

    public void delPassageiro(){
        List<PassageiroBean> passageiroList = passageiroEnviadoList();
        for(PassageiroBean passageiroBean : passageiroList){
            if(passageiroBean.getDthrLongViagemPassageiro() < Tempo.getInstance().dthrLongDia1Menos()) {
                passageiroBean.delete();
            }
        }
        passageiroList.clear();
    }

    public ArrayList<String> passageiroArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("PASSAGEIRO");
        List<PassageiroBean> passageiroList = passageiroList();
        for(PassageiroBean passageiroBean : passageiroList){
            dadosArrayList.add(dadosPassageiro(passageiroBean));
        }
        passageiroList.clear();
        return dadosArrayList;
    }

    private String dadosPassageiro(PassageiroBean passageiroBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(passageiroBean, passageiroBean.getClass()).toString();
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
