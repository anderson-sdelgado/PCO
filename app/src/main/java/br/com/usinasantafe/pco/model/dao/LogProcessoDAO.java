package br.com.usinasantafe.pco.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pco.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pco.util.Tempo;

public class LogProcessoDAO {

    private static LogProcessoDAO instance = null;

    public static LogProcessoDAO getInstance() {
        if (instance == null)
            instance = new LogProcessoDAO();
        return instance;
    }

    public void insertLogProcesso(String processo, String activity){
        Long dthrLong = Tempo.getInstance().dthrAtualLong();
        LogProcessoBean logProcessoBean = new LogProcessoBean();
        logProcessoBean.setProcesso(processo);
        logProcessoBean.setActivity(activity);
        logProcessoBean.setDthr(Tempo.getInstance().dthrLongToString(dthrLong));
        logProcessoBean.setDthrLong(dthrLong);
        logProcessoBean.insert();
    }

    public List<LogProcessoBean> logProcessoList(){
        LogProcessoBean logProcessoBean = new LogProcessoBean();
        return logProcessoBean.orderBy("idLogProcesso", false);
    }

    public void deleteLogProcesso(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqDtrhLongDia1Menos(Tempo.getInstance().dthrLongDiaMenos(3)));

        LogProcessoBean logProcessoBean = new LogProcessoBean();
        logProcessoBean.deleteGet(pesqArrayList);
    }

    private EspecificaPesquisa getPesqDtrhLongDia1Menos(Long dtrhLongDia1Menos){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("dthrLong");
        pesquisa.setValor(dtrhLongDia1Menos);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
