package br.com.usinasantafe.pco.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.variaveis.PassageiroViagemBean;
import br.com.usinasantafe.pco.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pco.util.Tempo;

public class PassageiroViagemDAO {

    public PassageiroViagemDAO() {
    }

    public boolean verPassageiroNEnviado(){
        List<PassageiroViagemBean> passageiroList = passageiroNEnviadoList();
        boolean ret = passageiroList.size() > 0;
        passageiroList.clear();
        return ret;
    }

    public boolean verMatricColabViagem(Long matricColab, Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        pesqArrayList.add(getPesqMatricColab(matricColab));

        PassageiroViagemBean passageiroViagemBean = new PassageiroViagemBean();
        List<PassageiroViagemBean> passageiroViagemList = passageiroViagemBean.get(pesqArrayList);
        boolean ret = (passageiroViagemList.size() == 0);
        passageiroViagemList.clear();
        return ret;
    }

    public List<PassageiroViagemBean> passageiroEnviadoList(){
        PassageiroViagemBean passageiroViagemBean = new PassageiroViagemBean();
        return passageiroViagemBean.get("statusPassageiroViagem", 2L);
    }

    public List<PassageiroViagemBean> passageiroNEnviadoList(){
        PassageiroViagemBean passageiroViagemBean = new PassageiroViagemBean();
        return passageiroViagemBean.get("statusPassageiroViagem", 1L);
    }

    public List<PassageiroViagemBean> passageiroViagemList(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));

        PassageiroViagemBean passageiroViagemBean = new PassageiroViagemBean();
        return passageiroViagemBean.getAndOrderBy(pesqArrayList, "idPassageiroViagem", false);
    }

    public List<PassageiroViagemBean> passageiroViagemList(ArrayList<Long> idPassageiroViagemArrayList){
        PassageiroViagemBean passageiroViagemBean = new PassageiroViagemBean();
        return passageiroViagemBean.in("idPassageiroViagem", idPassageiroViagemArrayList);
    }

    public List<PassageiroViagemBean> passageiroViagemList(){
        PassageiroViagemBean passageiroViagemBean = new PassageiroViagemBean();
        return passageiroViagemBean.orderBy("idPassageiroViagem", false);
    }

    public void salvarPassageiro(Long idCabec, Long matricColab, Long tipo){
        Long dthr = Tempo.getInstance().dthr();
        PassageiroViagemBean passageiroViagemBean = new PassageiroViagemBean();
        passageiroViagemBean.setIdCabecPassageiroViagem(idCabec);
        passageiroViagemBean.setDthrLongPassageiroViagem(dthr);
        passageiroViagemBean.setDthrPassageiroViagem(Tempo.getInstance().dthr(dthr));
        passageiroViagemBean.setMatricColabPassageiroViagem(matricColab);
        passageiroViagemBean.setStatusPassageiroViagem(1L);
        passageiroViagemBean.setTipoPassageiroViagem(tipo);
        passageiroViagemBean.insert();
    }

    public List<PassageiroViagemBean> passageiroEnvioList(ArrayList<Long> idCabecList){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusApont());

        PassageiroViagemBean passageiroViagemBean = new PassageiroViagemBean();
        return passageiroViagemBean.inAndGetAndOrderBy("idCabecPassageiroViagem", idCabecList, pesqArrayList, "idPassageiroViagem", true);

    }

    public String dadosEnvioPassageiro(List<PassageiroViagemBean> passageiroViagemList){

        JsonArray jsonArrayApont = new JsonArray();

        for (PassageiroViagemBean passageiroViagemBean : passageiroViagemList) {
            Gson gsonItemImp = new Gson();
            jsonArrayApont.add(gsonItemImp.toJsonTree(passageiroViagemBean, passageiroViagemBean.getClass()));
        }

        passageiroViagemList.clear();

        JsonObject jsonPassageiro = new JsonObject();
        jsonPassageiro.add("passageiro", jsonArrayApont);

        return jsonPassageiro.toString();

    }

    public void updatePassagViagem(String objPrinc) throws JSONException {

        JSONObject passageiroJsonObj = new JSONObject(objPrinc);
        JSONArray passageiroJsonArray = passageiroJsonObj.getJSONArray("passageiro");

        if (passageiroJsonArray.length() > 0) {

            for (int i = 0; i < passageiroJsonArray.length(); i++) {

                JSONObject objPassageiro = passageiroJsonArray.getJSONObject(i);
                Gson gsonPassageiro = new Gson();
                PassageiroViagemBean passageiroViagemBean = gsonPassageiro.fromJson(objPassageiro.toString(), PassageiroViagemBean.class);

                ArrayList pesqArrayList = new ArrayList();
                pesqArrayList.add(getPesqIdPassageiro(passageiroViagemBean.getIdPassageiroViagem()));
                pesqArrayList.add(getPesqIdCabec(passageiroViagemBean.getIdCabecPassageiroViagem()));

                PassageiroViagemBean passageiroViagemBeanBD = new PassageiroViagemBean();
                List<PassageiroViagemBean> passageiroViagemList = passageiroViagemBeanBD.get(pesqArrayList);
                pesqArrayList.clear();

                passageiroViagemBeanBD = passageiroViagemList.get(0);
                passageiroViagemList.clear();

                passageiroViagemBeanBD.setStatusPassageiroViagem(2L);
                passageiroViagemBeanBD.update();

            }


        }

    }

    public ArrayList<Long> idPassageiroArrayList(List<PassageiroViagemBean> passageiroViagemList){
        ArrayList<Long> idPassageiroList = new ArrayList<Long>();
        for (PassageiroViagemBean passageiroViagemBean : passageiroViagemList) {
            idPassageiroList.add(passageiroViagemBean.getIdPassageiroViagem());
        }
        return idPassageiroList;
    }

    public ArrayList<String> passagViagemArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("PASSAGEIRO VIAGEM");
        List<PassageiroViagemBean> passageiroViagemList = passageiroViagemList();
        for(PassageiroViagemBean passageiroViagemBean : passageiroViagemList){
            dadosArrayList.add(dadosPassageiro(passageiroViagemBean));
        }
        passageiroViagemList.clear();
        return dadosArrayList;
    }

    private String dadosPassageiro(PassageiroViagemBean passageiroViagemBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(passageiroViagemBean, PassageiroViagemBean.class).toString();
    }

    public void deletePassageiroViagem(ArrayList<Long> idPassageiroViagemArrayList){

        List<PassageiroViagemBean> passageiroViagemList = passageiroViagemList(idPassageiroViagemArrayList);

        for (PassageiroViagemBean passageiroViagemBean : passageiroViagemList) {
            passageiroViagemBean.delete();
        }

        passageiroViagemList.clear();
        idPassageiroViagemArrayList.clear();

    }

    private EspecificaPesquisa getPesqStatusApont(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusPassageiroViagem");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecPassageiroViagem");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdPassageiro(Long idPassageiro){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idPassageiroViagem");
        pesquisa.setValor(idPassageiro);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqMatricColab(Long matricColab){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("matricColabPassageiroViagem");
        pesquisa.setValor(matricColab);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
