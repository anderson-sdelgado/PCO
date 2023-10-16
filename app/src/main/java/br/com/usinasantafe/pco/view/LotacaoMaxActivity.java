package br.com.usinasantafe.pco.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;

public class LotacaoMaxActivity extends ActivityGeneric {

    private PCOContext pcoContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotacao_max);

        pcoContext = (PCOContext) getApplication();

        Button buttonOkLotacao = findViewById(R.id.buttonOkPadrao);
        Button buttonCancLotacao = findViewById(R.id.buttonCancPadrao);
        EditText editText = findViewById(R.id.editTextPadrao);

        if(pcoContext.getConfigCTR().getConfig().getLotacaoMaxConfig() > 0L){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcoContext.getConfigCTR().getConfig().getLotacaoMaxConfig() > 0L){\n" +
                    "            editText.setText(String.valueOf(pcoContext.getConfigCTR().getConfig().getLotacaoMaxConfig()));", getLocalClassName());
            editText.setText(String.valueOf(pcoContext.getConfigCTR().getConfig().getLotacaoMaxConfig()));
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            editText.setText(\"\");", getLocalClassName());
            editText.setText("");
        }

        buttonOkLotacao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkLotacao.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {", getLocalClassName());

                if(Long.parseLong(editTextPadrao.getText().toString()) < 100L){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(Long.parseLong(editTextPadrao.getText().toString()) < 100L){\n" +
                            "                        pcoContext.getConfigCTR().setPosicaoTela(2L);\n" +
                            "                        pcoContext.getConfigCTR().setLotacaoMaxConfig(Long.parseLong(editTextPadrao.getText().toString()));\n" +
                            "                        Intent it = new Intent(LotacaoMaxActivity.this, ListaPassageiroActivity.class);", getLocalClassName());

                    pcoContext.getConfigCTR().setLotacaoMaxConfig(Long.parseLong(editTextPadrao.getText().toString()));
                    Intent it = new Intent(LotacaoMaxActivity.this, HorimetroActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(LotacaoMaxActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"QUANTIDADE DE LOTAÇÃO INCOMPATÍVEL! POR FAVOR, VERIFICAR A NUMERAÇÃO DIGITADA.\");", getLocalClassName());

                    AlertDialog.Builder alerta = new AlertDialog.Builder(LotacaoMaxActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("QUANTIDADE DE LOTAÇÃO INCOMPATÍVEL! POR FAVOR, VERIFICAR A NUMERAÇÃO DIGITADA.");
                    alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                    alerta.show();
                }

            }
        });

        buttonCancLotacao.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancLotacao.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (editTextPadrao.getText().toString().length() > 0) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {\n" +
                "        Intent it = new Intent(LotacaoMaxActivity.this, ListaTrajetoActivity.class);", getLocalClassName());
        Intent it = new Intent(LotacaoMaxActivity.this, ListaTrajetoActivity.class);
        startActivity(it);
        finish();
    }

}