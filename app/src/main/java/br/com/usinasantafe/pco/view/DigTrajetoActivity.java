package br.com.usinasantafe.pco.view;

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

public class DigTrajetoActivity extends ActivityGeneric {

    private PCOContext pcoContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_trajeto);

        pcoContext = (PCOContext) getApplication();

        Button buttonRetTrajeto = findViewById(R.id.buttonRetTrajeto);
        Button buttonOkTrajeto = findViewById(R.id.buttonOkTrajeto);
        EditText editTextTrajeto = findViewById(R.id.editTextTrajeto);

        buttonRetTrajeto.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetTrajeto.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(DigTrajetoActivity.this, ListaTrajetoActivity.class);", getLocalClassName());
            Intent it = new Intent(DigTrajetoActivity.this, ListaTrajetoActivity.class);
            startActivity(it);
            finish();
        });

        buttonOkTrajeto.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkTrajeto.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!editTextTrajeto.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextTrajeto.getText().toString().equals(\"\")) {\n" +
                        "                    pcoContext.getViagemCTR().getCabecViagemBean().setIdTrajetoCabecViagem(0L);\n" +
                        "                    pcoContext.getViagemCTR().getCabecViagemBean().setDescrTrajetoCabecViagem(editTextTrajeto.getText().toString());\n" +
                        "                    Intent it = new Intent(DigTrajetoActivity.this, LotacaoMaxActivity.class);", getLocalClassName());
                pcoContext.getViagemCTR().getCabecViagemBean().setIdTrajetoCabecViagem(0L);
                pcoContext.getViagemCTR().getCabecViagemBean().setDescrTrajetoCabecViagem(editTextTrajeto.getText().toString());
                Intent it = new Intent(DigTrajetoActivity.this, LotacaoMaxActivity.class);
                startActivity(it);
                finish();

            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                    AlertDialog.Builder alerta = new AlertDialog.Builder( ComentarioActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"POR FAVOR! DIGITE OS TRAJETO DA VIAGEM.\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder( DigTrajetoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("POR FAVOR! DIGITE OS TRAJETO DA VIAGEM.");
                alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                alerta.show();
            }

        });
    }

    public void onBackPressed() {
    }

}