package br.com.usinasantafe.pco.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;

public class LogBaseDadoActivity extends ActivityGeneric {

    private PCOContext pcoContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_base_dado);


        pcoContext = (PCOContext) getApplication();

        Button buttonAvancaLogBaseDado = findViewById(R.id.buttonAvancaLogBaseDado);
        Button buttonRetLogBaseDado = findViewById(R.id.buttonRetLogBaseDado);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listViewLogBaseDado = findViewById(R.id.listViewLogBaseDado);\n" +
                "        AdapterListBaseDado adapterListBaseDado = new AdapterListBaseDado(this, pcoContext.getConfigCTR().logBaseDadoList());\n" +
                "        listViewLogBaseDado.setAdapter(adapterListBaseDado);", getLocalClassName());
        ListView listViewLogBaseDado = findViewById(R.id.listViewLogBaseDado);
        AdapterListBaseDado adapterListBaseDado = new AdapterListBaseDado(this, pcoContext.getConfigCTR().logBaseDadoList());
        listViewLogBaseDado.setAdapter(adapterListBaseDado);

        buttonAvancaLogBaseDado.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaLogProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "Intent it = new Intent(LogBaseDadoActivity.this, LogErroActivity.class);", getLocalClassName());
                Intent it = new Intent(LogBaseDadoActivity.this, LogErroActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonRetLogBaseDado.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetLogBaseDado.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "Intent it = new Intent(LogBaseDadoActivity.this, LogProcessoActivity.class);", getLocalClassName());
                Intent it = new Intent(LogBaseDadoActivity.this, LogProcessoActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed() {
    }

}