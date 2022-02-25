package br.com.usinasantafe.pco.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;

public class LogProcessoActivity extends ActivityGeneric {

    private PCOContext pcoContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_processo);

        pcoContext = (PCOContext) getApplication();

        Button buttonAvancaLogProcesso = findViewById(R.id.buttonAvancaLogProcesso);
        Button buttonRetLogProcesso = findViewById(R.id.buttonRetLogProcesso);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listViewLogProcesso = findViewById(R.id.listViewLogProcesso);\n" +
                "        AdapterListProcesso adapterListProcesso = new AdapterListProcesso(this, pcbContext.getConfigCTR().logProcessoList());\n" +
                "        listViewLogProcesso.setAdapter(adapterListProcesso);", getLocalClassName());
        ListView listViewLogProcesso = findViewById(R.id.listViewLogProcesso);
        AdapterListProcesso adapterListProcesso = new AdapterListProcesso(this, pcoContext.getConfigCTR().logProcessoList());
        listViewLogProcesso.setAdapter(adapterListProcesso);

        buttonAvancaLogProcesso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaLogProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "Intent it = new Intent(LogProcessoActivity.this, LogBaseDadoActivity.class);", getLocalClassName());
                Intent it = new Intent(LogProcessoActivity.this, LogBaseDadoActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonRetLogProcesso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetLogProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (pcoContext.getConfigCTR().getConfig().getPosicaoTela() == 8L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 3L){\n" +
                            "Intent it = new Intent(LogProcessoActivity.this, TelaInicialActivity.class);", getLocalClassName());
                    Intent it = new Intent(LogProcessoActivity.this, TelaInicialActivity.class);
                    startActivity(it);
                    finish();
                }
                else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "Intent it = new Intent(LogProcessoActivity.this, ListaPassageiroActivity.class);", getLocalClassName());
                    Intent it = new Intent(LogProcessoActivity.this, ListaPassageiroActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

    }

    public void onBackPressed() {
    }

}