package br.com.usinasantafe.pco.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;

public class SenhaActivity extends ActivityGeneric {

    private EditText editTextSenha;
    private PCOContext pcoContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        pcoContext = (PCOContext) getApplication();

        editTextSenha = (EditText)  findViewById(R.id.editTextSenha);
        Button btOkSenha =  (Button) findViewById(R.id.buttonOkSenha);
        Button btCancSenha = (Button) findViewById(R.id.buttonCancSenha);

        btOkSenha.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("btOkSenha.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @SuppressWarnings(\"unchecked\")\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (pcoContext.getConfigCTR().getConfig().getPosicaoTela() == 7L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getConfigCTR().getConfig().getPosicaoTela() == 2L){", getLocalClassName());
                    if (pcoContext.getConfigCTR().getConfig().getSenhaConfig().equals("")) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (!pmmContext.getConfigCTR().hasElemConfig()) {\n" +
                                "Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else if(pcoContext.getConfigCTR().verSenha(editTextSenha.getText().toString())) {
                        LogProcessoDAO.getInstance().insertLogProcesso("else if(pcbContext.getConfigCTR().verSenha(editTextSenha.getText().toString())) {\n" +
                                "Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                        startActivity(it);
                        finish();

                    }
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                    if (editTextSenha.getText().toString().equals("fgbny946")) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (editTextSenha.getText().toString().equals(\"fgbny946\")) {\n" +
                                "Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        });

        btCancSenha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("btCancSenha.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (pcoContext.getConfigCTR().getConfig().getPosicaoTela() == 7L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getConfigCTR().getConfig().getPosicaoTela() == 2L) {\n" +
                            "                    Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);", getLocalClassName());
                    Intent it = new Intent(SenhaActivity.this, TelaInicialActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (pcoContext.getConfigCTR().getConfig().getPosicaoTela() == 8L){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if (pcbContext.getConfigCTR().getConfig().getPosicaoTela() == 3L){\n" +
                            "                        Intent it = new Intent(SenhaActivity.this, TelaInicialActivity.class);", getLocalClassName());
                    Intent it = new Intent(SenhaActivity.this, TelaInicialActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("}else{\n" +
                            "Intent it = new Intent(SenhaActivity.this, ListaPassageiroActivity.class);", getLocalClassName());
                    Intent it = new Intent(SenhaActivity.this, ListaPassageiroActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

    }

    public void onBackPressed() {
    }

}