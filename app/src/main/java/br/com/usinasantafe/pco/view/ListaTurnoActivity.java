package br.com.usinasantafe.pco.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;

public class ListaTurnoActivity extends ActivityGeneric {

    private ListView turnoListView;
    private PCOContext pcoContext;
    private List<TurnoBean> turnoBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        pcoContext = (PCOContext) getApplication();

        Button buttonRetTurno = findViewById(R.id.buttonRetTurno);

        LogProcessoDAO.getInstance().insertLogProcesso("turnoBeanList = pcoContext.getConfigCTR().turnoList();\n" +
                "        ArrayList<String> itens = new ArrayList<String>();\n" +
                "        for(TurnoBean turnoBean : turnoBeanList){\n" +
                "            itens.add(turnoBean.getDescTurno());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        turnoListView = (ListView) findViewById(R.id.listaTurno);\n" +
                "        turnoListView.setAdapter(adapterList);", getLocalClassName());

        turnoBeanList = pcoContext.getConfigCTR().turnoList();
        ArrayList<String> itens = new ArrayList<String>();

        for(TurnoBean turnoBean : turnoBeanList){
            itens.add(turnoBean.getDescTurno());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        turnoListView = findViewById(R.id.listaTurno);
        turnoListView.setAdapter(adapterList);
        turnoListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("turnoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                pcoContext.getViagemCTR().getCabecViagemBean().setIdTurnoCabecViagem(turnoBeanList.get(position).getIdTurno());\n" +
                    "                Intent it = new Intent(ListaTurnoActivity.this, ListaTrajetoActivity.class);", getLocalClassName());
            pcoContext.getViagemCTR().getCabecViagemBean().setIdTurnoCabecViagem(turnoBeanList.get(position).getIdTurno());
            Intent it = new Intent(ListaTurnoActivity.this, ListaTrajetoActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetTurno.setOnClickListener(v -> {
            if(pcoContext.getConfigCTR().getConfig().getTipoEquipConfig() == 1L){
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetTurno.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                if(pcoContext.getConfigCTR().getConfig().getTipoEquipConfig() == 1L){\n" +
                        "                    Intent it = new Intent(ListaTurnoActivity.this, MotoristaActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaTurnoActivity.this, MotoristaActivity.class);
                startActivity(it);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                    Intent it = new Intent(ListaTurnoActivity.this, EquipActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaTurnoActivity.this, EquipActivity.class);
                startActivity(it);
            }
            finish();

        });

    }

    public void onBackPressed() {
    }
}