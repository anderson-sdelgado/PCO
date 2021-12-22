package br.com.usinasantafe.pco.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.model.bean.estaticas.TrajetoBean;
import br.com.usinasantafe.pco.model.bean.variaveis.PassageiroBean;
import br.com.usinasantafe.pco.model.dao.ColabDAO;
import br.com.usinasantafe.pco.model.dao.LogErroDAO;
import br.com.usinasantafe.pco.model.dao.MotoristaDAO;
import br.com.usinasantafe.pco.model.dao.PassageiroDAO;
import br.com.usinasantafe.pco.model.dao.TrajetoDAO;
import br.com.usinasantafe.pco.util.AtualDadosServ;
import br.com.usinasantafe.pco.util.EnvioDadosServ;

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

    public void salvarPassageiro(Long matricColab, String activity){
        ConfigCTR configCTR = new ConfigCTR();
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        passageiroDAO.salvarPassageiro(configCTR.getConfig(), matricColab);
        EnvioDadosServ.getInstance().envioDados(activity);
    }

    public String dadosEnvio(){
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        return passageiroDAO.dadosEnvio();
    }

    public void updatePassageiro(String retorno, String activity) {

        try {

            int pos1 = retorno.indexOf("_") + 1;

            String objPrinc  = retorno.substring(pos1);

            PassageiroDAO passageiroDAO = new PassageiroDAO();
            passageiroDAO.updatePassageiro(objPrinc);

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void delPassageiro(){
        PassageiroDAO passageiroDAO = new PassageiroDAO();
        passageiroDAO.delPassageiro();
    }

    public void atualDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String tipoAtual) {
        ArrayList classeArrayList = new ArrayList();
        switch (tipoAtual) {
            case "Motorista":
                classeArrayList.add("MotoristaBean");
                break;
            case "Colab":
                classeArrayList.add("ColabBean");
                break;
            case "Equip":
                classeArrayList.add("EquipBean");
                break;
            case "Trajeto":
                classeArrayList.add("TrajetoBean");
                break;
        }
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList);
    }

    public void verMotorista(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        motoristaDAO.verMotorista(dado, telaAtual, telaProx, progressDialog);
    }

    public void verColab(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ColabDAO colabDAO = new ColabDAO();
        colabDAO.verColab(dado, telaAtual, telaProx, progressDialog);
    }

    public List<TrajetoBean> trajetoList(){
        TrajetoDAO trajetoDAO = new TrajetoDAO();
        return trajetoDAO.trajetoList();
    }

    public int qtdePassageiroPorLotacao(){
        List<PassageiroBean> passageiroList = passageiroList();
        ConfigCTR configCTR = new ConfigCTR();
        int ret = (int) (configCTR.getConfig().getLotacaoMaxConfig() - passageiroList.size());
        return ret;
    }

}
