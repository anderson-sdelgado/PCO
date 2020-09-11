package br.com.usinasantafe.pco;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pco.util.ConexaoWeb;

public class ListaTurnoActivity extends ActivityGeneric {

    private ListView turnoListView;
    private PCOContext pcoContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        pcoContext = (PCOContext) getApplication();

        Button buttonRetTurno = (Button) findViewById(R.id.buttonRetTurno);

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

                Long turno = Long.valueOf(position + 1);
                TurnoBean turnoBean = new TurnoBean();
                turnoBean.setIdTurno(turno);
                turnoBean.setCodTurno(turno);
                pcoContext.getConfigCTR().setTurnoConfig(turnoBean);

                Intent it = new Intent(ListaTurnoActivity.this, ListaPassageiroActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaTurnoActivity.this, MotoristaActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}