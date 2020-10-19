package br.com.usinasantafe.pco;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.model.bean.variaveis.PassageiroBean;
import br.com.usinasantafe.pco.util.ConexaoWeb;
import br.com.usinasantafe.pco.util.EnvioDadosServ;
import br.com.usinasantafe.pco.util.Tempo;
import br.com.usinasantafe.pco.zxing.CaptureActivity;

public class ListaPassageiroActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private ListView passageiroListView;
    private List<PassageiroBean> passageiroList;
    private PCOContext pcoContext;
    private TextView textViewMotorista;
    private TextView textViewTurno;
    private AdapterListPassageiro adapterList;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_passageiro);

        pcoContext = (PCOContext) getApplication();
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

        textViewMotorista = (TextView) findViewById(R.id.textViewMotorista);
        textViewTurno = (TextView) findViewById(R.id.textViewTurno);
        Button buttonInserirPassageiro = (Button) findViewById(R.id.buttonInserirPassageiro);
        Button buttonFecharViagem = (Button) findViewById(R.id.buttonFecharViagem);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        customHandler.postDelayed(updateTimerThread, 0);

        MotoristaBean motoristaBean = pcoContext.getPassageiroCTR().getMotorista(pcoContext.getConfigCTR().getConfig().getMatricMotoConfig());
        textViewMotorista.setText(motoristaBean.getMatricMoto() + " - " + motoristaBean.getNomeMoto());

        String turno = "";
        if(pcoContext.getConfigCTR().getConfig().getIdTurnoConfig() == 1) {
            turno = "TURNO 1: 00:02 - 07:30";
        }
        else if(pcoContext.getConfigCTR().getConfig().getIdTurnoConfig() == 2) {
            turno = "TURNO 2: 07:31 - 15:54";
        }
        else{
            turno = "TURNO 3: 15:55 - 00:01";
        }
        textViewTurno.setText(turno);

        passageiroList = pcoContext.getPassageiroCTR().passageiroList();

        ArrayList<String> itens = new ArrayList<>();

        for(PassageiroBean passageiroBean : passageiroList){
            ColabBean colabBean = pcoContext.getPassageiroCTR().getColab(passageiroBean.getMatricColabPassageiro());
            itens.add(Tempo.getInstance().dataComHoraCTZ(passageiroBean.getDthrPassageiro()) + "\n"
                    + colabBean.getMatricColab() + " - " + colabBean.getNomeColab());
        }

        adapterList = new AdapterListPassageiro(this, itens);
        passageiroListView = (ListView) findViewById(R.id.passageiroListView);
        passageiroListView.setAdapter(adapterList);

        buttonInserirPassageiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaPassageiroActivity.this, CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonFecharViagem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pcoContext.getConfigCTR().clearDtrhViagemConfig();
                Intent it = new Intent(ListaPassageiroActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPassageiroActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaPassageiroActivity.this)) {

                            progressBar = new ProgressDialog(ListaPassageiroActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO MOTORISTA...");
                            progressBar.show();

                            pcoContext.getPassageiroCTR().atualDadosColab(ListaPassageiroActivity.this
                                    , ListaPassageiroActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPassageiroActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();

                        }
                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            String matricula = data.getStringExtra("SCAN_RESULT");
            if(matricula.length() == 8){
                matricula = matricula.substring(0,7);
                if (pcoContext.getPassageiroCTR().verColab(Long.parseLong(matricula))) {
                    if(pcoContext.getPassageiroCTR().verMatricColabViagem(Long.parseLong(matricula))){
                        pcoContext.getPassageiroCTR().salvarPassageiro(Long.parseLong(matricula));
                        ColabBean colabBean = pcoContext.getPassageiroCTR().getColab(Long.parseLong(matricula));
                        adapterList.addItem(Tempo.getInstance().dataComHoraCTZ() + "\n"
                                + colabBean.getMatricColab() + " - " + colabBean.getNomeColab());
                    }
                    else{
                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPassageiroActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FUNCIONÁRIO REPETIDO! FAVOR, INSERIR OUTRO FUNCIONÁRIO.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();
                    }

                }
                else{

                    progressBar = new ProgressDialog(ListaPassageiroActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO COLABORADOR...");
                    progressBar.show();

                    pcoContext.setVerTela(4);
                    pcoContext.getPassageiroCTR().verColab(matricula, ListaPassageiroActivity.this, ListaPassageiroActivity.class, progressBar);

                }
            }
        }

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            if (pcoContext.getConfigCTR().hasElements()) {
                if (EnvioDadosServ.getInstance().getStatusEnvio() == 1) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 2) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 3) {
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

}