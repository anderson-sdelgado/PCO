package br.com.usinasantafe.pco.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.model.bean.variaveis.PassageiroViagemBean;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pco.util.EnvioDadosServ;
import br.com.usinasantafe.pco.util.VerifDadosServ;
import br.com.usinasantafe.pco.zxing.CaptureActivity;

public class ListaPassageiroActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private ListView passageiroListView;
    private List<PassageiroViagemBean> passageiroList;
    private PCOContext pcoContext;
    private TextView textViewMotorista;
    private TextView textViewTurno;
    private TextView textViewLotacao;
    private AdapterListPassageiro adapterList;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_passageiro);

        pcoContext = (PCOContext) getApplication();
        textViewProcesso = findViewById(R.id.textViewProcesso);

        textViewMotorista = findViewById(R.id.textViewMotorista);
        textViewTurno = findViewById(R.id.textViewTurno);
        textViewLotacao = findViewById(R.id.textViewLotacao);
        Button buttonInserirCapturarPassag = findViewById(R.id.buttonInserirCapturarPassag);
        Button buttonFecharViagem = findViewById(R.id.buttonFecharViagem);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);
        Button buttonInserirDigPassag = findViewById(R.id.buttonInserirDigPassag);

        LogProcessoDAO.getInstance().insertLogProcesso("customHandler.postDelayed(updateTimerThread, 0);\n" +
                "        MotoristaBean motoristaBean = pcoContext.getViagemCTR().getMotorista(pcoContext.getViagemCTR().getCabecViagemAberto().getMatricMotoCabecViagem());\n" +
                "        textViewMotorista.setText(motoristaBean.getMatricMoto() + \" - \" + motoristaBean.getNomeMoto());\n" +
                "        String turno = \"\";\n" +
                "        Long idTurno = pcoContext.getViagemCTR().getCabecViagemAberto().getIdTurnoCabecViagem();\n" +
                "        if(idTurno == 1) {\n" +
                "            turno = \"TURNO 1: 00:02 - 07:30\";\n" +
                "        }\n" +
                "        else if(idTurno == 2) {\n" +
                "            turno = \"TURNO 2: 07:31 - 15:54\";\n" +
                "        }\n" +
                "        else{\n" +
                "            turno = \"TURNO 3: 15:55 - 00:01\";\n" +
                "        }\n" +
                "        textViewTurno.setText(turno);\n" +
                "        passageiroList = pcoContext.getViagemCTR().passageiroList();\n" +
                "        textViewLotacao.setText(\"QTDE DE PASSAGEIRO: \" + passageiroList.size());\n" +
                "        ArrayList<String> itens = new ArrayList<>();\n" +
                "        for(PassageiroViagemBean passageiroViagemBean : passageiroList){\n" +
                "            ColabBean colabBean = pcoContext.getViagemCTR().getColab(passageiroViagemBean.getMatricColabPassageiroViagem());\n" +
                "            itens.add(passageiroViagemBean.getDthrPassageiroViagem() + \"\\n\"\n" +
                "                    + colabBean.getMatricColab() + \" - \" + colabBean.getNomeColab());\n" +
                "        }\n" +
                "        adapterList = new AdapterListPassageiro(this, itens);\n" +
                "        passageiroListView = (ListView) findViewById(R.id.passageiroListView);\n" +
                "        passageiroListView.setAdapter(adapterList);", getLocalClassName());

        customHandler.postDelayed(updateTimerThread, 0);

        MotoristaBean motoristaBean = pcoContext.getViagemCTR().getMotorista(pcoContext.getViagemCTR().getCabecViagemAberto().getMatricMotoCabecViagem());
        textViewMotorista.setText(motoristaBean.getMatricMoto() + " - " + motoristaBean.getNomeMoto());

        String turno = "";
        Long idTurno = pcoContext.getViagemCTR().getCabecViagemAberto().getIdTurnoCabecViagem();
        if(idTurno == 1) {
            turno = "TURNO 1: 00:02 - 07:30";
        }
        else if(idTurno == 2) {
            turno = "TURNO 2: 07:31 - 15:54";
        }
        else{
            turno = "TURNO 3: 15:55 - 00:01";
        }
        textViewTurno.setText(turno);

        passageiroList = pcoContext.getViagemCTR().passageiroList();
        textViewLotacao.setText("QTDE DE PASSAGEIRO: " + passageiroList.size());

        ArrayList<String> itens = new ArrayList<>();

        for(PassageiroViagemBean passageiroViagemBean : passageiroList){
            ColabBean colabBean = pcoContext.getViagemCTR().getColab(passageiroViagemBean.getMatricColabPassageiroViagem());
            itens.add(passageiroViagemBean.getDthrPassageiroViagem() + "\n"
                    + colabBean.getMatricColab() + " - " + colabBean.getNomeColab());
        }

        adapterList = new AdapterListPassageiro(this, itens);
        passageiroListView = findViewById(R.id.passageiroListView);
        passageiroListView.setAdapter(adapterList);

        buttonInserirCapturarPassag.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonInserirCapturarPassag.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ListaPassageiroActivity.this, CaptureActivity.class);\n" +
                    "                startActivityForResult(it, REQUEST_CODE);", getLocalClassName());
            Intent it = new Intent(ListaPassageiroActivity.this, CaptureActivity.class);
            startActivityForResult(it, REQUEST_CODE);
        });

        buttonFecharViagem.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonFecharViagem.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                pcoContext.getConfigCTR().setPosicaoTela(10L);\n" +
                    "                Intent it = new Intent(ListaPassageiroActivity.this, HorimetroActivity.class);", getLocalClassName());

            pcoContext.getConfigCTR().setPosicaoTela(10L);
            Intent it = new Intent(ListaPassageiroActivity.this, HorimetroActivity.class);
            startActivity(it);
            finish();
        });

        buttonInserirDigPassag.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonInserirDigPassag.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ListaPassageiroActivity.this, DigMatricPassageiroActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaPassageiroActivity.this, DigMatricPassageiroActivity.class);
            startActivity(it);
            finish();
        });

        textViewProcesso.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("textViewProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                pcoContext.getConfigCTR().setPosicaoTela(9L);\n" +
                    "                Intent it = new Intent(ListaPassageiroActivity.this, SenhaActivity.class);", getLocalClassName());
            pcoContext.getConfigCTR().setPosicaoTela(9L);
            Intent it = new Intent(ListaPassageiroActivity.this, SenhaActivity.class);
            startActivity(it);
            finish();
        });

        buttonAtualPadrao.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPassageiroActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPassageiroActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {
                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                            progressBar = new ProgressDialog(ListaPassageiroActivity.this);\n" +
                            "                            progressBar.setCancelable(true);\n" +
                            "                            progressBar.setMessage(\"ATUALIZANDO MOTORISTA...\");\n" +
                            "                            progressBar.show();\n" +
                            "                            pcoContext.getPassageiroCTR().atualDados(ListaPassageiroActivity.this\n" +
                            "                                    , ListaPassageiroActivity.class, progressBar, \"Colab\");", getLocalClassName());

                    progressBar = new ProgressDialog(ListaPassageiroActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO MOTORISTA...");
                    progressBar.show();

                    pcoContext.getViagemCTR().atualDados(ListaPassageiroActivity.this
                            , ListaPassageiroActivity.class, progressBar, "Colab");

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "\n" +
                            "                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPassageiroActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(ListaPassageiroActivity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        }
                    });

                    alerta1.show();

                }
            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

            if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
                LogProcessoDAO.getInstance().insertLogProcesso("public void onActivityResult(int requestCode, int resultCode, Intent data){\n" +
                        "            if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){\n" +
                        "                String matriculaPassageiro = data.getStringExtra(\"SCAN_RESULT\");\n" +
                        "                pcoContext.setMatriculaPassageiro(matriculaPassageiro);\n" +
                        "                VerifDadosServ.getInstance().setMsgVerifColab(\"\");\n" +
                        "                Intent it = new Intent(ListaPassageiroActivity.this, MsgAddPassageiroActivity.class);", getLocalClassName());
                String matriculaPassageiro = data.getStringExtra("SCAN_RESULT");
                VerifDadosServ.getInstance().setNroMatricula(matriculaPassageiro);
                VerifDadosServ.getInstance().setMsgVerifColab("");
                Intent it = new Intent(ListaPassageiroActivity.this, MsgAddPassageiroActivity.class);
                startActivity(it);
                finish();
            }

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            if (pcoContext.getConfigCTR().hasElemConfig()) {
                if (EnvioDadosServ.status == 1) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (EnvioDadosServ.status == 2) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (EnvioDadosServ.status == 3) {
                    textViewProcesso.setTextColor(Color.GREEN);
                    textViewProcesso.setText("Todos os Dados já foram Enviados");
                }
            } else {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Aparelho sem Equipamento");
            }
            customHandler.postDelayed(this, 10000);
        }
    };

    public void onBackPressed() {
    }

}