package br.com.usinasantafe.pco.model.dao;

import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;

public class MotoristaDAO {

    public MotoristaDAO() {
    }

    public boolean hasElements(){
        MotoristaBean motoristaBean = new MotoristaBean();
        return motoristaBean.hasElements();
    }

    public boolean verMotorista(Long matricMoto){
        List motoristaList = motoristaList(matricMoto);
        boolean ret = motoristaList.size() > 0;
        motoristaList.clear();
        return ret;
    }

    public MotoristaBean getMotorista(Long matricMoto){
        List motoristaList = motoristaList(matricMoto);
        MotoristaBean motoristaBean = (MotoristaBean) motoristaList.get(0);
        motoristaList.clear();
        return motoristaBean;
    }

    private List motoristaList(Long matricMoto){
        MotoristaBean motoristaBean = new MotoristaBean();
        return motoristaBean.get("matricMoto", matricMoto);
    }

}
