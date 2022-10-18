package br.com.usinasantafe.pco.model.dao;

import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.TurnoBean;

public class TurnoDAO {

    public TurnoDAO() {
    }

    public List<TurnoBean> turnoList(){
        TurnoBean turnoBean = new TurnoBean();
        return turnoBean.orderBy("nroTurno", true);
    }

}
