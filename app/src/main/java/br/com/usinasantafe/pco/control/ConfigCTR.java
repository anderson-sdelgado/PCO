package br.com.usinasantafe.pco.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pco.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pco.model.dao.ConfigDAO;
import br.com.usinasantafe.pco.model.dao.EquipDAO;
import br.com.usinasantafe.pco.model.dao.MotoristaDAO;
import br.com.usinasantafe.pco.util.AtualDadosServ;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public boolean getConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig(senha);
    }

    public EquipBean getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip(getConfig().getIdEquipConfig());
    }

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    public void salvarConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(senha);
    }

    public void verEquipConfig(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.verEquip(dado, telaAtual, telaProx, progressDialog);
    }

    public void setEquipConfig(EquipBean equipBean){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setEquipConfig(equipBean);
    }

    public void setMotoConfig(MotoristaBean motoristaBean){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setMotoConfig(motoristaBean);
    }

    public void setTurnoConfig(TurnoBean turnoBean){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setTurnoConfig(turnoBean);
    }

    public void setDthrServConfig(String dthrServConfig){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDthrServConfig(dthrServConfig);
    }

    public void clearDtrhViagemConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.clearDtrhViagemConfig();
    }

    public MotoristaBean getMotoConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        return motoristaDAO.getMotorista(configDAO.getConfig().getMatricMotoConfig());
    }

}
