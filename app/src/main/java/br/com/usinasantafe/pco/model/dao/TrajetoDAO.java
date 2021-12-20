package br.com.usinasantafe.pco.model.dao;

import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.TrajetoBean;
import br.com.usinasantafe.pco.model.bean.estaticas.TurnoBean;

public class TrajetoDAO {

    public TrajetoDAO() {
    }

    public List<TrajetoBean> trajetoList(){
        TrajetoBean trajetoBean = new TrajetoBean();
        return trajetoBean.all();
    }

}
