package br.com.usinasantafe.pco.view;

import androidx.appcompat.app.AppCompatActivity;

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
import br.com.usinasantafe.pco.util.VerifDadosServ;

public class DigMatricPassageiroActivity extends ActivityGeneric {

    private PCOContext pcoContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_matric_passageiro);

        pcoContext = (PCOContext) getApplication();

        Button buttonOkPassageiro = findViewById(R.id.buttonOkPadrao);
        Button buttonCancPassageiro = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(DigMatricPassageiroActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());

            AlertDialog.Builder alerta = new AlertDialog.Builder(DigMatricPassageiroActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());

                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                            progressBar = new ProgressDialog(DigMatricPassageiroActivity.this);\n" +
                            "                            progressBar.setCancelable(true);\n" +
                            "                            progressBar.setMessage(\"Atualizando Colaborador...\");\n" +
                            "                            progressBar.show();\n" +
                            "                            pcoContext.getViagemCTR().atualDados(DigMatricPassageiroActivity.this\n" +
                            "                                    , DigMatricPassageiroActivity.class, progressBar, \"Colab\");", getLocalClassName());
                    progressBar = new ProgressDialog(DigMatricPassageiroActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Colaborador...");
                    progressBar.show();

                    pcoContext.getViagemCTR().atualDados(DigMatricPassageiroActivity.this
                            , DigMatricPassageiroActivity.class, progressBar, "Colab");

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                            AlertDialog.Builder alerta = new AlertDialog.Builder(DigMatricPassageiroActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(DigMatricPassageiroActivity.this);
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

        buttonOkPassageiro.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkEquip.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @SuppressWarnings(\"rawtypes\")\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {", getLocalClassName());
                if (pcoContext.getViagemCTR().verColab(Long.parseLong(editTextPadrao.getText().toString()))) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pcoContext.getViagemCTR().verColab(Long.parseLong(editTextPadrao.getText().toString()))) {", getLocalClassName());
                    if(pcoContext.getViagemCTR().verMatricColabViagem(Long.parseLong(editTextPadrao.getText().toString()))) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcoContext.getViagemCTR().verMatricColabViagem(Long.parseLong(editTextPadrao.getText().toString()))) {\n" +
                                "                            pcoContext.getViagemCTR().salvarPassageiro(Long.parseLong(editTextPadrao.getText().toString()), getLocalClassName());\n" +
                                "                            Intent it = new Intent(DigMatricPassageiroActivity.this, ListaPassageiroActivity.class);", getLocalClassName());
                        pcoContext.getViagemCTR().salvarPassageiro(Long.parseLong(editTextPadrao.getText().toString()), 2L, getLocalClassName());
                        Intent it = new Intent(DigMatricPassageiroActivity.this, ListaPassageiroActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(EquipActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"FUNCIONÁRIO REPETIDO! POR FAVOR, INSIRA OUTRO FUNCIONÁRIO.\");", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(DigMatricPassageiroActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FUNCIONÁRIO REPETIDO! POR FAVOR, INSIRA OUTRO FUNCIONÁRIO.");
                        alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                        alerta.show();

                    }

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(EquipActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"MATRICULA DO PASSAGEIRO INEXISTENTE! FAVOR VERIFICA A MESMA E/OU ATUALIZAR BASE DE DADOS.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(DigMatricPassageiroActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("MATRICULA DO PASSAGEIRO INEXISTENTE! FAVOR VERIFICA A MESMA E/OU ATUALIZAR BASE DE DADOS.");
                    alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                    alerta.show();

                }
            }

        });

        buttonCancPassageiro.setOnClickListener(v -> {
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
                "        Intent it = new Intent(DigMatricPassageiroActivity.this, ListaPassageiroActivity.class);", getLocalClassName());
        Intent it = new Intent(DigMatricPassageiroActivity.this, ListaPassageiroActivity.class);
        startActivity(it);
        finish();
    }

}