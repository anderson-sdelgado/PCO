package br.com.usinasantafe.pco.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.AtualAplicBean;
import br.com.usinasantafe.pco.model.bean.variaveis.ConfigBean;

public class ConfigDAO {

    public ConfigDAO() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public ConfigBean getConfig(){
        List<ConfigBean> configList = configList();
        ConfigBean configBean = configList.get(0);
        configList.clear();
        return configBean;
    }

    public boolean getConfig(String senha){
        List<ConfigBean> configList = configListSenha(senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    public boolean verSenha(String senha){
        ConfigBean configBean = new ConfigBean();
        List<ConfigBean> configList = configBean.get("senhaConfig", senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    public boolean verNroAparelho(Long nroAparelho){
        ConfigBean configBean = new ConfigBean();
        List<ConfigBean> configList = configBean.get("nroAparelhoConfig", nroAparelho);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    private List<ConfigBean> configList(){
        ConfigBean configBean = new ConfigBean();
        return configBean.all();
    }

    private List<ConfigBean> configListSenha(String senha){
        ConfigBean configBean = new ConfigBean();
        return configBean.get("senhaConfig", senha);
    }

    public void salvarConfig(Long nroAparelho){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setTipoEquipConfig(0L);
        configBean.setNroEquipConfig(0L);
        configBean.setSenhaConfig("");
        configBean.setNroAparelhoConfig(nroAparelho);
        configBean.setDthrServConfig("");
        configBean.setDifDthrConfig(0L);
        configBean.setLotacaoMaxConfig(0L);
        configBean.insert();
        configBean.commit();
    }

    public void salvarConfig(String senha, Long nroAparelho, Long nroEquip, Long tipo){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setTipoEquipConfig(tipo);
        configBean.setNroEquipConfig(nroEquip);
        configBean.setSenhaConfig(senha);
        configBean.setNroAparelhoConfig(nroAparelho);
        configBean.setDthrServConfig("");
        configBean.setDifDthrConfig(0L);
        configBean.setLotacaoMaxConfig(0L);
        configBean.insert();
        configBean.commit();
    }

    public void setEquipConfig(Long nroEquip){
        ConfigBean configBean = getConfig();
        configBean.setNroEquipConfig(nroEquip);
        configBean.update();
    }

    public void setLotacaoMaxConfig(Long lotacaoMax){
        ConfigBean configBean = getConfig();
        configBean.setLotacaoMaxConfig(lotacaoMax);
        configBean.update();
    }

    public void setHodometroInicialConfig(Double hodometroInicial){
        ConfigBean configBean = getConfig();
        configBean.setHodometroConfig(hodometroInicial);
        configBean.update();
    }

    public void setPosicaoTela(Long posicaoTela){
        ConfigBean configBean = getConfig();
        configBean.setPosicaoTela(posicaoTela);
        configBean.update();
    }

    public void setDifDthrConfig(Long difDthr){
        ConfigBean configBean = getConfig();
        configBean.setDifDthrConfig(difDthr);
        configBean.update();
    }

    public AtualAplicBean recAtual(JSONArray jsonArray) throws JSONException {

        JSONObject objeto = jsonArray.getJSONObject(0);
        Gson gson = new Gson();
        AtualAplicBean atualAplicBean = gson.fromJson(objeto.toString(), AtualAplicBean.class);

        ConfigBean configBean = getConfig();
        configBean.setDthrServConfig(atualAplicBean.getDthr());
        configBean.update();

        return atualAplicBean;

    }

    public ArrayList<String> configArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("CONFIG");
        dadosArrayList.add(dadosConfig(getConfig()));
        return dadosArrayList;
    }

    private String dadosConfig(ConfigBean configBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(configBean, configBean.getClass()).toString();
    }


    public AtualAplicBean recAparelho(JSONArray jsonArray) throws JSONException {

        JSONObject objeto = jsonArray.getJSONObject(0);
        Gson gson = new Gson();
        AtualAplicBean atualAplicBean = gson.fromJson(objeto.toString(), AtualAplicBean.class);

        return atualAplicBean;

    }

}
