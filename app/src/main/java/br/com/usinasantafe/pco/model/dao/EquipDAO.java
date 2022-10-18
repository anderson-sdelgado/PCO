package br.com.usinasantafe.pco.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pco.model.pst.EspecificaPesquisa;

public class EquipDAO {

    public boolean verEquipNro(Long nroEquip){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNro(nroEquip));

        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.get(pesqArrayList);
        boolean ret = equipList.size() > 0;
        equipList.clear();
        pesqArrayList.clear();
        return ret;
    }

    private EspecificaPesquisa getPesqNro(Long nroEquip){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("nroEquip");
        pesquisa.setValor(nroEquip);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
