package br.com.usinasantafe.pco.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.AtualAplicBean;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pco.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pco.util.Tempo;

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
        List<ConfigBean> configList = configList(senha);
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

    private List configList(){
        ConfigBean configBean = new ConfigBean();
        return configBean.all();
    }

    private List configList(String senha){
        ConfigBean configBean = new ConfigBean();
        return configBean.get("senhaConfig", senha);
    }

    public void salvarConfig(String senha, Long nroAparelho, Long idEquip, Long tipoEquip){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setTipoEquipConfig(tipoEquip);
        configBean.setIdEquipConfig(idEquip);
        configBean.setSenhaConfig(senha);
        configBean.setNroAparelhoConfig(nroAparelho);
        configBean.setMatricMotoConfig(0L);
        configBean.setIdTurnoConfig(0L);
        configBean.setDtrhViagemConfig("");
        configBean.setDtrhLongViagemConfig(0L);
        configBean.setDthrServConfig("");
        configBean.setDifDthrConfig(0L);
        configBean.setIdTrajetoConfig(0L);
        configBean.setLotacaoMaxConfig(0L);
        configBean.insert();
        configBean.commit();
    }

    public void setEquipConfig(Long idEquip){
        ConfigBean configBean = getConfig();
        configBean.setIdEquipConfig(idEquip);
        configBean.update();
    }

    public void setMotoConfig(MotoristaBean motoristaBean){
        ConfigBean configBean = getConfig();
        configBean.setMatricMotoConfig(motoristaBean.getMatricMoto());
        configBean.update();
    }

    public void setTurnoConfig(TurnoBean turnoBean){
        ConfigBean configBean = getConfig();
        configBean.setIdTurnoConfig(turnoBean.getIdTurno());
        configBean.update();
    }

    public void clearDthrViagemConfig(){
        ConfigBean configBean = getConfig();
        configBean.setDtrhViagemConfig("");
        configBean.setDtrhLongViagemConfig(0L);
        configBean.update();
    }

    public void setTrajetoConfig(Long idTrajeto){
        ConfigBean configBean = getConfig();
        configBean.setIdTrajetoConfig(idTrajeto);
        configBean.update();
    }

    public void setLotacaoMaxConfig(Long lotacaoMax){
        Long dthr = Tempo.getInstance().dthr();
        ConfigBean configBean = getConfig();
        configBean.setLotacaoMaxConfig(lotacaoMax);
        configBean.setDtrhViagemConfig(Tempo.getInstance().dthr(dthr));
        configBean.setDtrhLongViagemConfig(dthr);
        configBean.update();
    }

    public void setPosicaoTela(Long posicaoTela){
        if(hasElements()){
            ConfigBean configBean = getConfig();
            configBean.setPosicaoTela(posicaoTela);
            configBean.update();
        }
        else{
            ConfigBean configBean = new ConfigBean();
            configBean.setPosicaoTela(posicaoTela);
            configBean.setSenhaConfig("");
            configBean.setTipoEquipConfig(0L);
            configBean.insert();
            configBean.commit();
        }
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

}
