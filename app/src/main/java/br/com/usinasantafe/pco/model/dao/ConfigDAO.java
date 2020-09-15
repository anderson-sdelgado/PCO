package br.com.usinasantafe.pco.model.dao;

import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.EquipBean;
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
        List configList = configList();
        ConfigBean configBean = (ConfigBean) configList.get(0);
        configList.clear();
        return configBean;
    }

    public boolean getConfig(String senha){
        List configList = configList(senha);
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

    public void salvarConfig(String senha){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setSenhaConfig(senha);
        configBean.setMatricMotoConfig(0L);
        configBean.setIdTurnoConfig(0L);
        configBean.setDtrhViagemConfig("");
        configBean.setDthrServConfig("");
        configBean.setDifDthrConfig(0L);
        configBean.insert();
        configBean.commit();
    }

    public void setEquipConfig(EquipBean equipBean){
        ConfigBean configBean = getConfig();
        configBean.setIdEquipConfig(equipBean.getIdEquip());
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
        configBean.setDtrhViagemConfig(Tempo.getInstance().dataComHora());
        configBean.update();
    }

    public void clearDtrhViagemConfig(){
        ConfigBean configBean = getConfig();
        configBean.setDtrhViagemConfig("");
        configBean.update();
    }

    public void setDthrServConfig(String dthrServConfig){
        ConfigBean configBean = getConfig();
        configBean.setDthrServConfig(dthrServConfig);
        configBean.update();
    }

}
