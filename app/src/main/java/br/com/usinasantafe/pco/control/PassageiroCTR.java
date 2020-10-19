package br.com.usinasantafe.pco.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.model.bean.variaveis.PassageiroBean;
import br.com.usinasantafe.pco.model.dao.ColabDAO;
import br.com.usinasantafe.pco.model.dao.EquipDAO;
import br.com.usinasantafe.pco.model.dao.MotoristaDAO;
import br.com.usinasantafe.pco.model.dao.PassageiroDAO;
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
        ConfigCTR configCTR = new ConfigCTR();
        return passageiroDAO.verMatricColabViagem(matricColab, configCTR.getConfig().getDtrhViagemConfig());
    }

    public MotoristaBean getMotorista(Long matricMoto){
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        return motoristaDAO.getMotorista(matricMoto);
    }

    public ColabBean getColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getColab(matricColab);
    }

    public List<PassageiroBean> passageiroList(){
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        ConfigCTR configCTR = new ConfigCTR();
        return passageiroDAO.passageiroViagemList(configCTR.getConfig().getDtrhViagemConfig(), configCTR.getConfig().getMatricMotoConfig(), configCTR.getConfig().getIdTurnoConfig());
    }

    public boolean verPassageiroNEnviado(){
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        return passageiroDAO.verPassageiroNEnviado();
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

    public void atualDadosMotorista(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("MotoristaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosColab(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("ColabBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void verMotorista(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        motoristaDAO.verMotorista(dado, telaAtual, telaProx, progressDialog);
    }

    public void verColab(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ColabDAO colabDAO = new ColabDAO();
        colabDAO.verColab(dado, telaAtual, telaProx, progressDialog);
    }

}
