package br.com.usinasantafe.pco.control;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.model.bean.estaticas.TrajetoBean;
import br.com.usinasantafe.pco.model.bean.variaveis.CabecViagemBean;
import br.com.usinasantafe.pco.model.bean.variaveis.PassageiroViagemBean;
import br.com.usinasantafe.pco.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pco.model.dao.CabecViagemDAO;
import br.com.usinasantafe.pco.model.dao.ColabDAO;
import br.com.usinasantafe.pco.model.dao.LogErroDAO;
import br.com.usinasantafe.pco.model.dao.MotoristaDAO;
import br.com.usinasantafe.pco.model.dao.PassageiroViagemDAO;
import br.com.usinasantafe.pco.model.dao.TrajetoDAO;
import br.com.usinasantafe.pco.util.AtualDadosServ;
import br.com.usinasantafe.pco.util.EnvioDadosServ;
import br.com.usinasantafe.pco.util.Tempo;
import br.com.usinasantafe.pco.util.VerifDadosServ;

public class ViagemCTR {

    private CabecViagemBean cabecViagemBean;

    public ViagemCTR() {
    }

    public CabecViagemBean getCabecViagemBean() {
        return cabecViagemBean;
    }

    public void setCabecViagemBean() {
        this.cabecViagemBean = new CabecViagemBean();
    }

    public CabecViagemBean getCabecViagemAberto(){
        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        return cabecViagemDAO.getCabecViagemAberto();
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
        PassageiroViagemDAO passageiroViagemDAO = new PassageiroViagemDAO();
        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        return passageiroViagemDAO.verMatricColabViagem(matricColab, cabecViagemDAO.getCabecViagemAberto().getIdCabecViagem());
    }

    public MotoristaBean getMotorista(Long matricMoto){
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        return motoristaDAO.getMotorista(matricMoto);
    }

    public ColabBean getColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getColab(matricColab);
    }

    public List<PassageiroViagemBean> passageiroList(){
        PassageiroViagemDAO passageiroViagemDAO = new PassageiroViagemDAO();
        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        return passageiroViagemDAO.passageiroViagemList(cabecViagemDAO.getCabecViagemAberto().getIdCabecViagem());
    }

    public boolean verPassageiroNEnviado(){
        PassageiroViagemDAO passageiroViagemDAO = new PassageiroViagemDAO();
        return passageiroViagemDAO.verPassageiroNEnviado();
    }

    public boolean verCabecViagemAberto(){
        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        return cabecViagemDAO.verCabecViagemAberto();
    }

    public boolean verCabecViagemFechado(){
        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        return cabecViagemDAO.verCabecViagemFechado();
    }

    public void abrirCabecViagem(){
        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        ConfigCTR configCTR = new ConfigCTR();
        cabecViagemBean.setNroEquipCabecViagem(configCTR.getConfig().getNroEquipConfig());
        cabecViagemDAO.abrirCabec(cabecViagemBean);
    }

    public void fecharCabec(Double horimetroNum, String activity){
        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        cabecViagemDAO.fecharCabec(horimetroNum);
        EnvioDadosServ.getInstance().envioDados(activity);
    }

    public void salvarPassageiro(Long matricColab, Long tipo , String activity){
        PassageiroViagemDAO passageiroViagemDAO = new PassageiroViagemDAO();
        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        passageiroViagemDAO.salvarPassageiro(cabecViagemDAO.getCabecViagemAberto().getIdCabecViagem(), matricColab, tipo);
        EnvioDadosServ.getInstance().envioDados(activity);
    }

    public String dadosEnvioCabecAberto(){

        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        String dadosEnvioCabec = cabecViagemDAO.dadosEnvioCabecAberto();

        PassageiroViagemDAO passageiroViagemDAO = new PassageiroViagemDAO();
        String dadosEnvioPassag = passageiroViagemDAO.dadosEnvioPassageiro(passageiroViagemDAO.passageiroEnvioList(cabecViagemDAO.idCabecArrayList(cabecViagemDAO.cabecAbertoList())));

        return dadosEnvioCabec + "_" + dadosEnvioPassag;
    }

    public String dadosEnvioCabecFechado(){

        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        String dadosEnvioCabec = cabecViagemDAO.dadosEnvioCabecFechado();

        PassageiroViagemDAO passageiroViagemDAO = new PassageiroViagemDAO();
        String dadosEnvioPassag = passageiroViagemDAO.dadosEnvioPassageiro(passageiroViagemDAO.passageiroEnvioList(cabecViagemDAO.idCabecArrayList(cabecViagemDAO.cabecFechadoList())));

        return dadosEnvioCabec + "_" + dadosEnvioPassag;
    }

    public void updateCabecAberto(String retorno, String activity) {

        try {

            int pos1 = retorno.indexOf("_") + 1;

            String objPrinc  = retorno.substring(pos1);

            PassageiroViagemDAO passageiroViagemDAO = new PassageiroViagemDAO();
            passageiroViagemDAO.updatePassagViagem(objPrinc);

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void updateCabecFechado(String retorno, String activity){

        try {

            int pos1 = retorno.indexOf("_") + 1;
            int pos2 = retorno.indexOf("|") + 1;

            String objPrinc = retorno.substring(pos1, pos2);
            String objSeg = retorno.substring(pos2);

            CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
            cabecViagemDAO.updateCabecFechado(objPrinc);

            PassageiroViagemDAO passageiroViagemDAO = new PassageiroViagemDAO();
            passageiroViagemDAO.updatePassagViagem(objSeg);

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void deleteViagemEnviada(){

        CabecViagemDAO cabecViagemDAO = new CabecViagemDAO();
        ArrayList<CabecViagemBean> cabecViagemArrayList = cabecViagemDAO.cabecExcluirArrayList();

        for (CabecViagemBean cabecViagemBean : cabecViagemArrayList) {

            PassageiroViagemDAO passageiroViagemDAO = new PassageiroViagemDAO();
            List<PassageiroViagemBean> passageiroViagemList = passageiroViagemDAO.passageiroViagemList(cabecViagemBean.getIdCabecViagem());
            ArrayList<Long> idPassageiroArrayList = passageiroViagemDAO.idPassageiroArrayList(passageiroViagemList);
            passageiroViagemDAO.deletePassageiroViagem(idPassageiroArrayList);

            cabecViagemDAO.deleteCabec(cabecViagemBean);

        }

        cabecViagemArrayList.clear();


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

    public void verMotorista(Long nroMatricula, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        motoristaDAO.verMotorista(atualAplicDAO.getPesqNroMatricula(nroMatricula), telaAtual, telaProx, progressDialog);
    }

    public void verColab(Long nroMatricula, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ColabDAO colabDAO = new ColabDAO();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        colabDAO.verColab(atualAplicDAO.getPesqNroMatricula(nroMatricula), telaAtual, telaProx, progressDialog);
    }

    public List<TrajetoBean> trajetoList(){
        TrajetoDAO trajetoDAO = new TrajetoDAO();
        return trajetoDAO.trajetoList();
    }

    public int qtdePassageiroPorLotacao(){
        List<PassageiroViagemBean> passageiroList = passageiroList();
        ConfigCTR configCTR = new ConfigCTR();
        int ret = (int) (configCTR.getConfig().getLotacaoMaxConfig() - passageiroList.size());
        return ret;
    }

    public void recDadosMotorista(String result){

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

                JSONObject objeto = jsonArray.getJSONObject(0);
                Gson gson = new Gson();
                MotoristaBean motoristaBean = gson.fromJson(objeto.toString(), MotoristaBean.class);
                motoristaBean.insert();

                VerifDadosServ.getInstance().pulaTela();

            } else {

                VerifDadosServ.getInstance().setNroMatricula("");
                VerifDadosServ.getInstance().msgSemTerm("MOTORISTA INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");

            }

        } catch (Exception e) {

            VerifDadosServ.getInstance().setNroMatricula("");
            VerifDadosServ.getInstance().msgSemTerm("FALHA DE PESQUISA DE MOTORISTA! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");

        }
    }


    public void recDadosColab(String result, String activity){

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

                ColabDAO colabDAO = new ColabDAO();
                ColabBean colabBean = colabDAO.recColab(jsonArray);

                salvarPassageiro(colabBean.getMatricColab(), 1L, activity);

                VerifDadosServ.getInstance().setMsgVerifColab(Tempo.getInstance().dthrAtualLong() + "\n" +
                        + colabBean.getMatricColab() + " - " + colabBean.getNomeColab());

            } else {
                VerifDadosServ.getInstance().setMsgVerifColab("COLABORADOR INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
            }
            VerifDadosServ.getInstance().pulaTela();

        } catch (Exception e) {
            VerifDadosServ.getInstance().setMsgVerifColab("FALHA DE PESQUISA DE COLABORADOR! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
            VerifDadosServ.getInstance().pulaTela();
        }
    }
}
