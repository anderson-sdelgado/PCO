package br.com.usinasantafe.pco.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;

public class LogErroActivity extends ActivityGeneric {

    private PCOContext pcbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_erro);


        pcbContext = (PCOContext) getApplication();

        Button buttonRetLogErro = findViewById(R.id.buttonRetLogErro);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listViewLogErro = findViewById(R.id.listViewLogErro);\n" +
                "        AdapterListErro adapterListErro = new AdapterListErro(this, pcbContext.getConfigCTR().logErroList());\n" +
                "        listViewLogErro.setAdapter(adapterListErro);", getLocalClassName());
        ListView listViewLogErro = findViewById(R.id.listViewLogErro);
        AdapterListErro adapterListErro = new AdapterListErro(this, pcbContext.getConfigCTR().logErroList());
        listViewLogErro.setAdapter(adapterListErro);

        buttonRetLogErro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetLogProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "Intent it = new Intent(LogErroActivity.this, LogBaseDadoActivity.class);", getLocalClassName());
                Intent it = new Intent(LogErroActivity.this, LogBaseDadoActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed() {
    }

}