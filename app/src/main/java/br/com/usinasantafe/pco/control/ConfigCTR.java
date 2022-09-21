package br.com.usinasantafe.pco.control;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.AtualAplicBean;
import br.com.usinasantafe.pco.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pco.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pco.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pco.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pco.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pco.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pco.model.dao.CabecViagemDAO;
import br.com.usinasantafe.pco.model.dao.ConfigDAO;
import br.com.usinasantafe.pco.model.dao.EquipDAO;
import br.com.usinasantafe.pco.model.dao.LogErroDAO;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pco.model.dao.PassageiroViagemDAO;
import br.com.usinasantafe.pco.model.dao.TurnoDAO;
import br.com.usinasantafe.pco.util.AtualDadosServ;
import br.com.usinasantafe.pco.util.VerifDadosServ;
import br.com.usinasantafe.pco.view.TelaInicialActivity;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElemConfig(){
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
        return equipDAO.getEquipId(getConfig().getIdEquipConfig());
    }

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    public void salvarConfig(String senha, Long nroAparelho, Long nroEquip){
        ConfigDAO configDAO = new ConfigDAO();
        EquipDAO equipDAO = new EquipDAO();
        configDAO.salvarConfig(senha, nroAparelho, equipDAO.getEquipNro(nroEquip).getIdEquip(), 1L);
    }

    public boolean verSenha(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.verSenha(senha);
    }

    public void salvarConfig(String senha, Long nroAparelho){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(senha, nroAparelho, 0L, 2L);
    }

    public boolean verEquipNro(Long nroEquip, Long tipoEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.verEquipNro(nroEquip, tipoEquip);
    }

    public void setEquipConfig(Long nroEquip){
        EquipDAO equipDAO = new EquipDAO();
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setEquipConfig(equipDAO.getEquipNro(nroEquip).getIdEquip());
    }

    public List<TurnoBean> turnoList(){
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.turnoList(getEquip().getIdJornada());
    }

    public void setLotacaoMaxConfig(Long lotacaoMax){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setLotacaoMaxConfig(lotacaoMax);
    }

    public void setHodometroInicialConfig(Double hodometroInicial){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setHodometroInicialConfig(hodometroInicial);
    }

    public void setPosicaoTela(Long posicaoTela){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setPosicaoTela(posicaoTela);
    }

    public AtualAplicBean recAtual(String result) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {
                ConfigDAO configDAO = new ConfigDAO();
                atualAplicBean = configDAO.recAtual(jsonArray);
            }

        } catch (Exception e) {
            VerifDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }
        return atualAplicBean;
    }

    public void verAtualAplic(String versaoAplic, TelaInicialActivity telaInicialActivity, String activity) {
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.getInstance().verifAtualAplic(atualAplicDAO.dadosVerAtualAplicBean(equipBean.getNroEquip(), equipBean.getIdCheckList(), versaoAplic)\n" +
                "                , telaInicialActivity, progressDialog);", activity);
        VerifDadosServ.getInstance().verifAtualAplic(atualAplicDAO.dadosVerAtualAplicBean(getConfig().getNroAparelhoConfig(), versaoAplic)
                , telaInicialActivity, activity);
    }


    /////////////////////////////////////////// LOG ///////////////////////////////////////////////

    public List<LogProcessoBean> logProcessoList(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        return logProcessoDAO.logProcessoList();
    }

    public ArrayList<String> logBaseDadoList(){
        ArrayList<String> dadosArrayList = new ArrayList<>();
        ConfigDAO configDAO = new ConfigDAO();
        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        PassageiroViagemDAO passageiroViagemDAO = new PassageiroViagemDAO();
        dadosArrayList = configDAO.configArrayList(dadosArrayList);
        dadosArrayList = cabecViagemDAO.cabecViagemArrayList(dadosArrayList);
        dadosArrayList = passageiroViagemDAO.passagViagemArrayList(dadosArrayList);
        return dadosArrayList;
    }

    public List<LogErroBean> logErroList(){
        LogErroDAO logErroDAO = new LogErroDAO();
        return logErroDAO.logErroBeanList();
    }

    public void deleteLogs(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        LogErroDAO logErroDAO = new LogErroDAO();
        logProcessoDAO.deleteLogProcesso();
        logErroDAO.deleteLogErro();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
