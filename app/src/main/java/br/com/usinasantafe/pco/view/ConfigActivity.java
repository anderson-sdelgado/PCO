package br.com.usinasantafe.pco.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pco.BuildConfig;
import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private AdapterList adapterList;
    private TextView textViewTipoEquipConfig;
    private EditText editTextNroAparelhoConfig;
    private EditText editTextEquipConfig;
    private EditText editTextSenhaConfig;
    private PCOContext pcoContext;
    private ConfigBean configBean;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button buttonSalvarConfig = findViewById(R.id.buttonSalvarConfig);
        Button buttonCancConfig = findViewById(R.id.buttonCancConfig);
        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);
        textViewTipoEquipConfig = findViewById(R.id.textViewTipoEquipConfig);
        editTextEquipConfig = findViewById(R.id.editTextEquipConfig);
        editTextSenhaConfig = findViewById(R.id.editTextSenhaConfig);
        editTextNroAparelhoConfig = findViewById(R.id.editTextNroAparelhoConfig);

        pcoContext = (PCOContext) getApplication();

        configBean = new ConfigBean();

        if(!pcoContext.getConfigCTR().hasElemConfig()){

            LogProcessoDAO.getInstance().insertLogProcesso("if(!pcoContext.getConfigCTR().hasElemConfig()){\n" +
                    "            configBean.setTipoEquipConfig(0L);\n" +
                    "            editTextEquipConfig.setEnabled(false);\n" +
                    "            editTextEquipConfig.setText(\"\");\n" +
                    "            editTextNroAparelhoConfig.setText(\"\");\n" +
                    "            editTextSenhaConfig.setText(\"\");", getLocalClassName());
            configBean.setTipoEquipConfig(0L);
            editTextEquipConfig.setEnabled(false);
            editTextEquipConfig.setText("");
            editTextNroAparelhoConfig.setText("");
            editTextSenhaConfig.setText("");

        } else {

            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            configBean = pcoContext.getConfigCTR().getConfig();\n" +
                    "            editTextSenhaConfig.setText(configBean.getSenhaConfig());\n" +
                    "switch ((int) configBean.getTipoEquipConfig().longValue()) {", getLocalClassName());

            configBean = pcoContext.getConfigCTR().getConfig();
            editTextSenhaConfig.setText(configBean.getSenhaConfig());
            editTextNroAparelhoConfig.setText(String.valueOf(configBean.getNroAparelhoConfig()));

            switch ((int) configBean.getTipoEquipConfig().longValue()) {
                case 1: {
                    LogProcessoDAO.getInstance().insertLogProcesso("case 1:\n" +
                            "                    \n" +
                            "                    textViewTipoEquipConfig.setText(\"EQUIP. PRÓPRIO\");\n" +
                            "                    editTextEquipConfig.setEnabled(true);\n" +
                            "                    editTextEquipConfig.setText(String.valueOf(pcoContext.getConfigCTR().getEquip().getNroEquip()));\n" +
                            "                    break;", getLocalClassName());
                    textViewTipoEquipConfig.setText("EQUIP. PRÓPRIO");
                    editTextEquipConfig.setEnabled(true);
                    editTextEquipConfig.setText(String.valueOf(pcoContext.getConfigCTR().getConfig().getNroEquipConfig()));
                    break;
                }
                case 2: {
                    LogProcessoDAO.getInstance().insertLogProcesso("case 2:\n" +
                            "                    textViewTipoEquipConfig.setText(\"EQUIP. TERCEIRO\");\n" +
                            "                    editTextEquipConfig.setEnabled(false);\n" +
                            "                    editTextEquipConfig.setText(\"\");\n" +
                            "                    break;", getLocalClassName());
                    textViewTipoEquipConfig.setText("EQUIP. TERCEIRO");
                    editTextEquipConfig.setEnabled(false);
                    editTextEquipConfig.setText("");
                    break;
                }
            }

        }

        textViewTipoEquipConfig.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("textViewTipoEquipConfig.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                ArrayList<String> itens = new ArrayList<String>();\n" +
                    "                itens.add(\"1 - PRÓPRIO\");\n" +
                    "                itens.add(\"2 - TERCEIRO\");\n" +
                    "                adapterList = new AdapterList(ConfigActivity.this, itens);\n" +
                    "                AlertDialog.Builder builder = new AlertDialog.Builder(ConfigActivity.this);\n" +
                    "                builder.setTitle(\"TIPO EQUIP.:\");", getLocalClassName());

            ArrayList<String> itens = new ArrayList<String>();
            itens.add("1 - PRÓPRIO");
            itens.add("2 - TERCEIRO");

            adapterList = new AdapterList(ConfigActivity.this, itens);

            AlertDialog.Builder builder = new AlertDialog.Builder(ConfigActivity.this);
            builder.setTitle("TIPO EQUIP.:");
            builder.setSingleChoiceItems(adapterList, 0, (arg0, arg1) -> {
                LogProcessoDAO.getInstance().insertLogProcesso("builder.setSingleChoiceItems(adapterList, 0, new DialogInterface.OnClickListener() {\n" +
                        "                    public void onClick(DialogInterface arg0, int arg1) {", getLocalClassName());
                switch (arg1 + 1) {
                    case 1:
                        LogProcessoDAO.getInstance().insertLogProcesso("switch (arg1 + 1) {\n" +
                                "                            case 1:\n" +
                                "                                configBean.setTipoEquipConfig(1L);\n" +
                                "                                textViewTipoEquipConfig.setText(\"EQUIP. PRÓPRIO\");\n" +
                                "                                editTextEquipConfig.setEnabled(true);\n" +
                                "                                editTextEquipConfig.setText(\"\");\n" +
                                "                                break;", getLocalClassName());
                        configBean.setTipoEquipConfig(1L);
                        textViewTipoEquipConfig.setText("EQUIP. PRÓPRIO");
                        editTextEquipConfig.setEnabled(true);
                        editTextEquipConfig.setText("");
                        break;
                    case 2:
                        LogProcessoDAO.getInstance().insertLogProcesso("case 2:\n" +
                                "                                configBean.setTipoEquipConfig(2L);\n" +
                                "                                textViewTipoEquipConfig.setText(\"EQUIP. TERCEIRO\");\n" +
                                "                                editTextEquipConfig.setEnabled(false);\n" +
                                "                                editTextEquipConfig.setText(\"\");\n" +
                                "                                break;", getLocalClassName());
                        configBean.setTipoEquipConfig(2L);
                        textViewTipoEquipConfig.setText("EQUIP. TERCEIRO");
                        editTextEquipConfig.setEnabled(false);
                        editTextEquipConfig.setText("");
                        break;
                }

                alerta.dismiss();

            });

            LogProcessoDAO.getInstance().insertLogProcesso("alerta = builder.create();\n" +
                    "                alerta.show();", getLocalClassName());
            alerta = builder.create();
            alerta.show();

        });

        buttonSalvarConfig.setOnClickListener(v -> {

            if(!pcoContext.getConfigCTR().hasElemConfig()){

                if (!editTextNroAparelhoConfig.getText().toString().equals("")) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Pequisando o Equipamento...");
                    progressBar.show();
                    pcoContext.getConfigCTR().salvarToken(BuildConfig.VERSION_NAME, Long.valueOf(editTextNroAparelhoConfig.getText().toString()), ConfigActivity.this, progressBar, getLocalClassName());

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! DIGITE O NUMERO DA LINHA.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();

                }

            } else {

                if (!editTextNroAparelhoConfig.getText().toString().equals("")) {

                    if(!pcoContext.getConfigCTR().verNroAparelho(Long.parseLong(editTextNroAparelhoConfig.getText().toString()))) {

                        configBean.setTipoEquipConfig(0L);
                        editTextEquipConfig.setEnabled(false);
                        editTextEquipConfig.setText("");
                        editTextNroAparelhoConfig.setText("");
                        editTextSenhaConfig.setText("");

                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("Salvando Configurações Inicial...");
                        progressBar.show();
                        pcoContext.getConfigCTR().salvarToken(BuildConfig.VERSION_NAME, Long.valueOf(editTextNroAparelhoConfig.getText().toString()), ConfigActivity.this, progressBar, getLocalClassName());

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                        if(textViewTipoEquipConfig.getText().toString().equals("EQUIP. PRÓPRIO")){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(textViewTipoEquipConfig.getText().toString().equals(\"EQUIP. PRÓPRIO\")){", getLocalClassName());

                            if(!editTextEquipConfig.getText().toString().equals("") &&
                                    !editTextSenhaConfig.getText().toString().equals("")){
                                LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextEquipConfig.getText().toString().equals(\"\") &&\n" +
                                        "                            !editTextSenhaConfig.getText().toString().equals(\"\") &&\n" +
                                        "                            !editTextNroAparelhoConfig.getText().toString().equals(\"\")){", getLocalClassName());

                                if(pcoContext.getConfigCTR().verEquipNro(Long.parseLong(editTextEquipConfig.getText().toString()))){

                                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcoContext.getConfigCTR().verEquipNro(Long.parseLong(editTextEquipConfig.getText().toString()))){\n" +
                                            "                            pcoContext.getConfigCTR().salvarConfig(editTextSenhaConfig.getText().toString()\n" +
                                            "                                    , Long.parseLong(editTextNroAparelhoConfig.getText().toString())\n" +
                                            "                                    , Long.parseLong(editTextEquipConfig.getText().toString()));\n" +
                                            "                            Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);", getLocalClassName());

                                    pcoContext.getConfigCTR().salvarConfig(editTextSenhaConfig.getText().toString()
                                            , Long.parseLong(editTextNroAparelhoConfig.getText().toString())
                                            , Long.parseLong(editTextEquipConfig.getText().toString()));

                                    Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);
                                    startActivity(it);
                                    finish();

                                } else {

                                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                            "                            AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                            "                            alerta.setMessage(\"EQUIPAMENTO INEXISTENTE! POR FAVOR, VERIFIQUE O NÚMERO DE EQUIPAMENTO DIGITADO\");", getLocalClassName());
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                                    alerta.setTitle("ATENÇÃO");
                                    alerta.setMessage("EQUIPAMENTO INEXISTENTE! POR FAVOR, VERIFIQUE O NÚMERO DE EQUIPAMENTO DIGITADO");
                                    alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                            "                                @Override\n" +
                                            "                                public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                                    alerta.show();

                                }

                            } else{

                                LogProcessoDAO.getInstance().insertLogProcesso("} else{\n" +
                                        "                        AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                                        "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                        alerta.setMessage(\"POR FAVOR, PREENCHA TODOS OS CAMPOS PARA SALVAR OS DADOS DE CONFIGURAÇÕES.\");", getLocalClassName());
                                AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("POR FAVOR, PREENCHA TODOS OS CAMPOS PARA SALVAR OS DADOS DE CONFIGURAÇÕES.");
                                alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                            @Override\n" +
                                        "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                                alerta.show();

                            }

                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                            if(!editTextSenhaConfig.getText().toString().equals("")){

                                LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextSenhaConfig.getText().toString().equals(\"\") &&\n" +
                                        "                            !editTextNroAparelhoConfig.getText().toString().equals(\"\")){\n" +
                                        "                        pcoContext.getConfigCTR().salvarConfig(editTextSenhaConfig.getText().toString()\n" +
                                        "                                , Long.parseLong(editTextNroAparelhoConfig.getText().toString()));\n" +
                                        "                        Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);", getLocalClassName());

                                pcoContext.getConfigCTR().salvarConfig(editTextSenhaConfig.getText().toString()
                                        , Long.parseLong(editTextNroAparelhoConfig.getText().toString()));

                                Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);
                                startActivity(it);
                                finish();

                            } else {

                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                        AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                                        "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                        alerta.setMessage(\"POR FAVOR, PREENCHA TODOS OS CAMPOS PARA SALVAR OS DADOS DE CONFIGURAÇÕES.\");", getLocalClassName());
                                AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("POR FAVOR, PREENCHA TODOS OS CAMPOS PARA SALVAR OS DADOS DE CONFIGURAÇÕES.");
                                alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                            @Override\n" +
                                        "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                                alerta.show();

                            }
                        }

                    }

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! DIGITE O NUMERO DA LINHA.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();

                }

            }

        });

        buttonCancConfig.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancConfig.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();
        });

        buttonAtualizarBD.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualizarBD.setOnClickListener(v -> {", getLocalClassName());
            if(!pcoContext.getConfigCTR().hasElemConfig()){

                LogProcessoDAO.getInstance().insertLogProcesso("if(!pcoContext.getConfigCTR().hasElemConfig()){", getLocalClassName());
                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(v.getContext());\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                    progressBar.setProgress(0);\n" +
                            "                    progressBar.setMax(100);\n" +
                            "                    progressBar.show();\n" +
                            "                    pcoContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);", getLocalClassName());

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pcoContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                        @Override\n" +
                            "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                    alerta.show();
                }

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("POR FAVOR, ADICIONE O NÚMERO DO APARELHO ANTES DE ATUALIZAR O APLICATIVO.");
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