package br.com.usinasantafe.pco.model.dao;

import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
public class ColabDAO {

    public ColabDAO() {
    }

    public boolean verColab(Long matricColab){
        List colabList = colabList(matricColab);
        boolean ret = colabList.size() > 0;
        colabList.clear();
        return ret;
    }

    public ColabBean getColab(Long matricColab){
        List colabList = colabList(matricColab);
        ColabBean colabBean = (ColabBean) colabList.get(0);
        colabList.clear();
        return colabBean;
    }

    private List colabList(Long matricColab){
        ColabBean colabBean = new ColabBean();
        return colabBean.get("matricColab", matricColab);
    }

}
