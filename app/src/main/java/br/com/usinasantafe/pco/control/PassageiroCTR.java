package br.com.usinasantafe.pco.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.model.dao.MotoristaDAO;
import br.com.usinasantafe.pco.model.dao.TurnoDAO;
import br.com.usinasantafe.pco.util.AtualDadosServ;

public class PassageiroCTR {

    public PassageiroCTR() {
    }

    public boolean hasElementsMotorista(){
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        return motoristaDAO.hasElements();
    }

    public boolean verMotorista(Long matricMoto){
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        return motoristaDAO.verMotorista(matricMoto);
    }

    public MotoristaBean getMotorista(Long matricMoto){
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        return motoristaDAO.getMotorista(matricMoto);
    }

    public List getTurnoList(){
        ConfigCTR configCTR = new ConfigCTR();
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.getTurnoList(configCTR.getEquip().getCodTurno());
    }

    public void atualDadosTurno(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList turnoArrayList = new ArrayList();
        turnoArrayList.add("TurnoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, turnoArrayList);
    }

}
