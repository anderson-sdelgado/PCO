package br.com.usinasantafe.pco.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pco.model.bean.variaveis.PassageiroBean;
import br.com.usinasantafe.pco.model.dao.ColabDAO;
import br.com.usinasantafe.pco.model.dao.MotoristaDAO;
import br.com.usinasantafe.pco.model.dao.PassageiroDAO;
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

    public boolean verColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.verColab(matricColab);
    }

    public boolean verMatricColabViagem(Long matricColab){
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        return passageiroDAO.verMatricColabViagem(matricColab);
    }

    public MotoristaBean getMotorista(Long matricMoto){
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        return motoristaDAO.getMotorista(matricMoto);
    }

    public TurnoBean getTurno(Long idTurno){
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.getTurno(idTurno);
    }

    public List getTurnoList(){
        ConfigCTR configCTR = new ConfigCTR();
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.getTurnoList(configCTR.getEquip().getCodTurno());
    }

    public ColabBean getColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getColab(matricColab);
    }

    public List<PassageiroBean> passageiroList(String dthr){
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        return passageiroDAO.passageiroViagemList(dthr);
    }

    public void atualDadosTurno(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList turnoArrayList = new ArrayList();
        turnoArrayList.add("TurnoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, turnoArrayList);
    }

    public boolean verPassageiroNEnviado(){
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        return passageiroDAO.verPassageiroNEnviado();
    }

    public boolean verPassageiroViagemList(String dthr){
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        return passageiroDAO.verPassageiroViagemList(dthr);
    }

    public void salvarPassageiro(Long matricColab){
        ConfigCTR configCTR = new ConfigCTR();
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        passageiroDAO.salvarPassageiro(configCTR.getConfig(), matricColab);
    }

    public String dadosEnvio(){
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        return passageiroDAO.dadosEnvio();
    }

    public void updatePassageiro(String retorno) {
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        passageiroDAO.updatePassageiro(retorno);
    }

    public void delPassageiro(){
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        passageiroDAO.delPassageiro();
    }

}
