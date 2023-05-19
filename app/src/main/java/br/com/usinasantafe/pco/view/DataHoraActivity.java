package br.com.usinasantafe.pco.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pco.util.Tempo;

public class DataHoraActivity extends ActivityGeneric {

    private PCOContext pcoContext;
    private EditText editPadrao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_hora);

        pcoContext = (PCOContext) getApplication();

        Button buttonOkDataHora = findViewById(R.id.buttonOkPadrao);
        Button buttonCancDataHora = findViewById(R.id.buttonCancPadrao);
        TextView textViewDataHora = findViewById(R.id.textViewPadrao);

        switch (pcoContext.getConfigCTR().getContDataHora()) {
            case 1:
                textViewDataHora.setText("DIA:");
                break;
            case 2:
                textViewDataHora.setText("MÊS:");
                break;
            case 3:
                textViewDataHora.setText("ANO:");
                break;
            case 4:
                textViewDataHora.setText("HORA:");
                break;
            case 5:
                textViewDataHora.setText("MINUTOS:");
                break;
        }

        editPadrao = findViewById(R.id.editTextPadrao);

        editPadrao.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                switch (pcoContext.getConfigCTR().getContDataHora()) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                        editPadrao.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
                        break;
                    case 3:
                        editPadrao.setFilters(new InputFilter[] { new InputFilter.LengthFilter(4) });
                        break;
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        buttonOkDataHora.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkDataHora.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if (!editTextPadrao.getText().toString().equals("")) {
                int valor = Integer.parseInt(editTextPadrao.getText().toString());
                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                    int valor = Integer.parseInt(" + editTextPadrao.getText().toString() + ");\n" +
                        "                    Intent it;\n" +
                        "                    switch (" + pcoContext.getConfigCTR().getContDataHora() + ") {", getLocalClassName());
                Intent it;
                switch (pcoContext.getConfigCTR().getContDataHora()) {
                    case 1:
                        LogProcessoDAO.getInstance().insertLogProcesso("case 1:", getLocalClassName());
                        if((valor <= 31)){
                            LogProcessoDAO.getInstance().insertLogProcesso("if((valor <= 31)){\n" +
                                    "                                pcoContext.getConfigCTR().setDia(" + valor + ");\n" +
                                    "                                pcoContext.getConfigCTR().setContDataHora(pcoContext.getConfigCTR().getContDataHora() + 1);\n" +
                                    "                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
                            pcoContext.getConfigCTR().setDia(valor);
                            pcoContext.getConfigCTR().setContDataHora(pcoContext.getConfigCTR().getContDataHora() + 1);
                            it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"DIA INCORRETO! FAVOR VERIFICAR.\");\n" +
                                    "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    }\n" +
                                    "                                });\n" +
                                    "                                alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("DIA INCORRETO! FAVOR VERIFICAR.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();
                        }
                        break;
                    case 2:
                        LogProcessoDAO.getInstance().insertLogProcesso("case 2:", getLocalClassName());
                        if((valor <= 12)){
                            LogProcessoDAO.getInstance().insertLogProcesso("if((valor <= 12)){\n" +
                                    "                                pcoContext.getConfigCTR().setMes(" + valor + ");\n" +
                                    "                                pcoContext.getConfigCTR().setContDataHora(pcoContext.getConfigCTR().getContDataHora() + 1);\n" +
                                    "                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
                            pcoContext.getConfigCTR().setMes(valor);
                            pcoContext.getConfigCTR().setContDataHora(pcoContext.getConfigCTR().getContDataHora() + 1);
                            it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"MÊS INCORRETO! FAVOR VERIFICAR.\");\n" +
                                    "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    }\n" +
                                    "                                });\n" +
                                    "                                alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("MÊS INCORRETO! FAVOR VERIFICAR.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();
                        }
                        break;
                    case 3:
                        LogProcessoDAO.getInstance().insertLogProcesso("case 3:", getLocalClassName());
                        if((valor >= 2020) && (valor <= 3000)){
                            LogProcessoDAO.getInstance().insertLogProcesso("if((valor >= 2020) && (valor <= 3000)){\n" +
                                    "                                pcoContext.getConfigCTR().setAno(" + valor + ");\n" +
                                    "                                pcoContext.getConfigCTR().setContDataHora(pcoContext.getConfigCTR().getContDataHora() + 1);\n" +
                                    "                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
                            pcoContext.getConfigCTR().setAno(valor);
                            pcoContext.getConfigCTR().setContDataHora(pcoContext.getConfigCTR().getContDataHora() + 1);
                            it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"ANO INCORRETO! FAVOR VERIFICAR.\");\n" +
                                    "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    }\n" +
                                    "                                });\n" +
                                    "                                alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("ANO INCORRETO! FAVOR VERIFICAR.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();
                        }
                        break;
                    case 4:
                        LogProcessoDAO.getInstance().insertLogProcesso("case 4:", getLocalClassName());
                        if(valor <= 23){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(valor <= 23){\n" +
                                    "                                pcoContext.getConfigCTR().setHora(" + valor + ");\n" +
                                    "                                pcoContext.getConfigCTR().setContDataHora(pcoContext.getConfigCTR().getContDataHora() + 1);\n" +
                                    "                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
                            pcoContext.getConfigCTR().setHora(valor);
                            pcoContext.getConfigCTR().setContDataHora(pcoContext.getConfigCTR().getContDataHora() + 1);
                            it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"HORA INCORRETA! FAVOR VERIFICAR.\");\n" +
                                    "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    }\n" +
                                    "                                });\n" +
                                    "                                alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("HORA INCORRETA! FAVOR VERIFICAR.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();
                        }

                        break;
                    case 5:
                        LogProcessoDAO.getInstance().insertLogProcesso("case 5:", getLocalClassName());
                        if(valor <= 59){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(valor <= 59){\n" +
                                    " pcoContext.getConfigCTR().setMinuto(valor);\n" +
                                    " pcoContext.getConfigCTR().setDifDthrConfig(Tempo.getInstance().difDthr(" + pcoContext.getConfigCTR().getDia() + ", " + pcoContext.getConfigCTR().getMes() + ", " + pcoContext.getConfigCTR().getAno() + "\n" +
                                    " , " + pcoContext.getConfigCTR().getHora() + ", " + pcoContext.getConfigCTR().getMinuto() + "));", getLocalClassName());
                            pcoContext.getConfigCTR().setMinuto(valor);
                            pcoContext.getConfigCTR().setDifDthrConfig(Tempo.getInstance().difDthr(pcoContext.getConfigCTR().getDia(), pcoContext.getConfigCTR().getMes(), pcoContext.getConfigCTR().getAno()
                                    , pcoContext.getConfigCTR().getHora(), pcoContext.getConfigCTR().getMinuto()));
                            if(pcoContext.getViagemCTR().verCabecViagemAberto()){
                                LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getViagemCTR().verCabecViagemAberto()){" +
                                        "            Intent it = new Intent(DataHoraActivity.this, ListaPassageiroActivity.class);", getLocalClassName());
                                it = new Intent(DataHoraActivity.this, ListaPassageiroActivity.class);
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                        "            Intent it = new Intent(DataHoraActivity.this, MenuInicialActivity.class);", getLocalClassName());
                                it = new Intent(DataHoraActivity.this, MenuInicialActivity.class);
                            }
                            startActivity(it);
                            finish();
                        }
                        else{
                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"MINUTO INCORRETO! FAVOR VERIFICAR.\");\n" +
                                    "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    }\n" +
                                    "                                });\n" +
                                    "                                alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("MINUTO INCORRETO! FAVOR VERIFICAR.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();
                        }
                        break;
                }

            }

        });

        buttonCancDataHora.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancDataHora.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {" +
                    "if (editTextPadrao.getText().toString().length() > 0) {\n" +
                    "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                    "                }", getLocalClassName());
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {", getLocalClassName());
        if(pcoContext.getConfigCTR().getContDataHora() > 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcoContext.getConfigCTR().getContDataHora() > 1){\n" +
                    "            pcoContext.getConfigCTR().setContDataHora(pcoContext.getConfigCTR().getContDataHora() - 1);\n" +
                    "            Intent it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
            pcoContext.getConfigCTR().setContDataHora(pcoContext.getConfigCTR().getContDataHora() - 1);
            Intent it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
            startActivity(it);
            finish();
        }
    }

}