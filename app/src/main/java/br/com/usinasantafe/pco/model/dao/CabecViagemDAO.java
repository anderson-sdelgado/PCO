package br.com.usinasantafe.pco.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.variaveis.CabecViagemBean;
import br.com.usinasantafe.pco.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pco.util.Tempo;

public class CabecViagemDAO {

    public CabecViagemDAO() {
    }

    public boolean verCabecViagemAberto(){
        List<CabecViagemBean> cabecViagemList = cabecAbertoList();
        boolean ret = cabecViagemList.size() > 0;
        cabecViagemList.clear();
        return ret;
    }

    public boolean verCabecViagemFechado(){
        List<CabecViagemBean> cabecViagemList = cabecFechadoList();
        boolean ret = cabecViagemList.size() > 0;
        cabecViagemList.clear();
        return ret;
    }

    public CabecViagemBean getCabecViagemAberto(){
        List<CabecViagemBean> cabecViagemList = cabecAbertoList();
        CabecViagemBean cabecViagemBean = cabecViagemList.get(0);
        cabecViagemList.clear();
        return cabecViagemBean;
    }

    public List<CabecViagemBean> cabecViagemList(){
        CabecViagemBean cabecViagemBean = new CabecViagemBean();
        return cabecViagemBean.orderBy("idCabecViagem", false);
    }

    public List<CabecViagemBean> cabecAbertoList(){
        CabecViagemBean cabecViagemBean = new CabecViagemBean();
        return cabecViagemBean.get("statusCabecViagem", 1L);
    }

    public List<CabecViagemBean> cabecFechadoList(){
        CabecViagemBean cabecViagemBean = new CabecViagemBean();
        return cabecViagemBean.get("statusCabecViagem", 2L);
    }

    public String dadosEnvioCabecAberto(){
        return dadosCabec(cabecAbertoList());
    }

    public String dadosEnvioCabecFechado(){
        return dadosCabec(cabecFechadoList());
    }

    private String dadosCabec(List<CabecViagemBean> cabecViagemList){

        JsonArray cabecJsonArray = new JsonArray();

        for (CabecViagemBean cabecViagemBean : cabecViagemList) {
            Gson cabecGson = new Gson();
            cabecJsonArray.add(cabecGson.toJsonTree(cabecViagemBean, CabecViagemBean.class));
        }

        cabecViagemList.clear();

        JsonObject cabecJsonObj = new JsonObject();
        cabecJsonObj.add("cabecalho", cabecJsonArray);

        return cabecJsonObj.toString();
    }

    public void updateCabecFechado(String objeto) throws Exception {

        JSONObject cabecJsonObj = new JSONObject(objeto);
        JSONArray cabecJsonArray = cabecJsonObj.getJSONArray("cabecalho");

        for (int i = 0; i < cabecJsonArray.length(); i++) {

            JSONObject cabecObj = cabecJsonArray.getJSONObject(i);
            Gson cabecGson = new Gson();
            CabecViagemBean cabecViagemBean = cabecGson.fromJson(cabecObj.toString(), CabecViagemBean.class);

            CabecViagemBean cabecViagemBeanBD = new CabecViagemBean();
            List<CabecViagemBean> cabecViagemList = cabecViagemBeanBD.get("idCabecViagem", cabecViagemBean.getIdCabecViagem());
            cabecViagemBeanBD = cabecViagemList.get(0);
            cabecViagemList.clear();

            cabecViagemBeanBD.setStatusCabecViagem(3L);
            cabecViagemBeanBD.update();

        }
    }

    public ArrayList<Long> idCabecArrayList(List<CabecViagemBean> cabecList){
        ArrayList<Long> idCabecList = new ArrayList<Long>();
        for (CabecViagemBean cabecViagemBean : cabecList) {
            idCabecList.add(cabecViagemBean.getIdCabecViagem());
        }
        cabecList.clear();
        return idCabecList;
    }

    public ArrayList<CabecViagemBean> cabecExcluirArrayList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusEnviado());

        CabecViagemBean cabecViagemBean = new CabecViagemBean();
        List<CabecViagemBean> cabecViagemList =  cabecViagemBean.get(pesqArrayList);
        pesqArrayList.clear();

        ArrayList<CabecViagemBean> cabecViagemArrayList = new ArrayList<>();
        for (CabecViagemBean cabecViagemBeanBD : cabecViagemList) {
            if(cabecViagemBeanBD.getDthrInicialLongCabecViagem() < Tempo.getInstance().dthrLongDiaMenos(3)) {
                cabecViagemArrayList.add(cabecViagemBeanBD);
            }
        }

        cabecViagemList.clear();
        return cabecViagemArrayList;

    }

    private String dadosCabec(CabecViagemBean cabecViagemBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(cabecViagemBean, CabecViagemBean.class).toString();
    }

    public ArrayList<String> cabecViagemArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("CABEC VIAGEM");
        List<CabecViagemBean> cabecViagemList = cabecViagemList();
        for(CabecViagemBean cabecViagemBean : cabecViagemList){
            dadosArrayList.add(dadosCabec(cabecViagemBean));
        }
        cabecViagemList.clear();
        return dadosArrayList;
    }

    public void deleteCabec(CabecViagemBean cabecViagemBean){
        cabecViagemBean.delete();
    }

    public void abrirCabec(CabecViagemBean cabecViagemBean){
        Long dthr = Tempo.getInstance().dthr();
        cabecViagemBean.setDthrInicialCabecViagem(Tempo.getInstance().dthr(dthr));
        cabecViagemBean.setDthrInicialLongCabecViagem(dthr);
        cabecViagemBean.setStatusCabecViagem(1L);
        cabecViagemBean.insert();
    }

    public void fecharCabec(Double horimetro){
        Long dthr = Tempo.getInstance().dthr();
        CabecViagemBean cabecViagemBean = getCabecViagemAberto();
        cabecViagemBean.setDthrFinalCabecViagem(Tempo.getInstance().dthr(dthr));
        cabecViagemBean.setDthrFinalLongCabecViagem(dthr);
        cabecViagemBean.setHodometroFinalCabecViagem(horimetro);
        cabecViagemBean.setStatusCabecViagem(2L);
        cabecViagemBean.update();
    }

    private EspecificaPesquisa getPesqStatusEnviado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabecViagem");
        pesquisa.setValor(3L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
