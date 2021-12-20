package br.com.usinasantafe.pco.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pco.model.pst.EspecificaPesquisa;

public class EquipDAO {

    public boolean verEquipNro(Long nroEquip, Long tipoEquip){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNro(nroEquip));
        pesqArrayList.add(getPesqTipo(tipoEquip));

        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.get(pesqArrayList);
        boolean ret = equipList.size() > 0;
        equipList.clear();
        pesqArrayList.clear();
        return ret;
    }

    public EquipBean getEquipId(Long idEquip){
        List<EquipBean> equipList = equipListId(idEquip);
        EquipBean equipBean = equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    public EquipBean getEquipNro(Long nroEquip){
        List<EquipBean> equipList = equipListNro(nroEquip);
        EquipBean equipBean = equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    private List<EquipBean> equipListId(Long idEquip){
        EquipBean equipBean = new EquipBean();
        return  equipBean.get("idEquip", idEquip);
    }

    private List<EquipBean> equipListNro(Long nroEquip){
        EquipBean equipBean = new EquipBean();
        return  equipBean.get("nroEquip", nroEquip);
    }

    private EspecificaPesquisa getPesqNro(Long nroEquip){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("nroEquip");
        pesquisa.setValor(nroEquip);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqTipo(Long tipoEquip){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("tipoEquip");
        pesquisa.setValor(tipoEquip);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
