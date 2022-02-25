package br.com.usinasantafe.pco.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;

public class ListaTurnoActivity extends ActivityGeneric {

    private ListView turnoListView;
    private PCOContext pcoContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        pcoContext = (PCOContext) getApplication();

        Button buttonRetTurno = findViewById(R.id.buttonRetTurno);

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<String>();\n" +
                "        itens.add(\"TURNO 1: 00:02 - 07:30\");\n" +
                "        itens.add(\"TURNO 2: 07:31 - 15:54\");\n" +
                "        itens.add(\"TURNO 3: 15:55 - 00:01\");\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        turnoListView = (ListView) findViewById(R.id.listaTurno);\n" +
                "        turnoListView.setAdapter(adapterList);", getLocalClassName());

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("TURNO 1: 00:02 - 07:30");
        itens.add("TURNO 2: 07:31 - 15:54");
        itens.add("TURNO 3: 15:55 - 00:01");

        AdapterList adapterList = new AdapterList(this, itens);
        turnoListView = (ListView) findViewById(R.id.listaTurno);
        turnoListView.setAdapter(adapterList);
        turnoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("turnoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                Long turno = Long.valueOf(position + 1);\n" +
                        "                TurnoBean turnoBean = new TurnoBean();\n" +
                        "                turnoBean.setIdTurno(turno);\n" +
                        "                turnoBean.setCodTurno(turno);\n" +
                        "                pcoContext.getConfigCTR().setTurnoConfig(turnoBean);\n" +
                        "                \n" +
                        "                Intent it = new Intent(ListaTurnoActivity.this, ListaTrajetoActivity.class);", getLocalClassName());

                Long turno = Long.valueOf(position + 1);
                pcoContext.getViagemCTR().getCabecViagemBean().setIdTurnoCabecViagem(turno);
                Intent it = new Intent(ListaTurnoActivity.this, ListaTrajetoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pcoContext.getConfigCTR().getConfig().getTipoEquipConfig() == 1L){
                    LogProcessoDAO.getInstance().insertLogProcesso("buttonRetTurno.setOnClickListener(new View.OnClickListener() {\n" +
                            "            @Override\n" +
                            "            public void onClick(View v) {\n" +
                            "                if(pcoContext.getConfigCTR().getConfig().getTipoEquipConfig() == 1L){\n" +
                            "                    Intent it = new Intent(ListaTurnoActivity.this, MotoristaActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaTurnoActivity.this, MotoristaActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(ListaTurnoActivity.this, EquipActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaTurnoActivity.this, EquipActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }

    public void onBackPressed() {
    }
}