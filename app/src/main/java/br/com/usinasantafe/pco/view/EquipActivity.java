package br.com.usinasantafe.pco.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;

public class EquipActivity extends ActivityGeneric {

    private PCOContext pcoContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        pcoContext = (PCOContext) getApplication();

        Button buttonOkEquip = findViewById(R.id.buttonOkPadrao);
        Button buttonCancEquip = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);
        EditText editText = findViewById(R.id.editTextPadrao);

        if(pcoContext.getConfigCTR().getConfig().getNroEquipConfig() > 0L){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcoContext.getConfigCTR().getConfig().getIdEquipConfig() > 0L){\n" +
                    "            editText.setText(String.valueOf(pcoContext.getConfigCTR().getEquip().getNroEquip()));", getLocalClassName());
            editText.setText(String.valueOf(pcoContext.getConfigCTR().getConfig().getNroEquipConfig()));
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            editText.setText(\"\");", getLocalClassName());
            editText.setText("");
        }

        buttonAtualPadrao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder( EquipActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());

            AlertDialog.Builder alerta = new AlertDialog.Builder( EquipActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());

                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                            progressBar = new ProgressDialog(EquipActivity.this);\n" +
                            "                            progressBar.setCancelable(true);\n" +
                            "                            progressBar.setMessage(\"Atualizando Colaborador...\");\n" +
                            "                            progressBar.show();\n" +
                            "                            pcoContext.getPassageiroCTR().atualDados(EquipActivity.this\n" +
                            "                                    , EquipActivity.class, progressBar, \"Equip\");", getLocalClassName());

                    progressBar = new ProgressDialog(EquipActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Colaborador...");
                    progressBar.show();

                    pcoContext.getViagemCTR().atualDados(EquipActivity.this
                            , EquipActivity.class, progressBar, "Equip");

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                            AlertDialog.Builder alerta = new AlertDialog.Builder( EquipActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( EquipActivity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", (dialog1, which1) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                                @Override\n" +
                            "                                public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                    alerta1.show();

                }

            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });

        buttonOkEquip.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkEquip.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @SuppressWarnings(\"rawtypes\")\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                    pcoContext.getConfigCTR().setEquipConfig(Long.parseLong(editTextPadrao.getText().toString()));\n" +
                        "                    Intent it = new Intent(EquipActivity.this, ListaTurnoActivity.class);", getLocalClassName());
                pcoContext.getConfigCTR().setEquipConfig(Long.parseLong(editTextPadrao.getText().toString()));
                Intent it = new Intent(EquipActivity.this, ListaTurnoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonCancEquip.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancEquip.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (editTextPadrao.getText().toString().length() > 0) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(EquipActivity.this, MotoristaActivity.class);", getLocalClassName());
        Intent it = new Intent(EquipActivity.this, MotoristaActivity.class);
        startActivity(it);
        finish();
    }

}