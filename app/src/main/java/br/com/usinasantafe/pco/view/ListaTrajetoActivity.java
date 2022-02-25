package br.com.usinasantafe.pco.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.bean.estaticas.TrajetoBean;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;

public class ListaTrajetoActivity extends ActivityGeneric {

    private ListView trajetoListView;
    private PCOContext pcoContext;
    private ProgressDialog progressBar;
    private List<TrajetoBean> trajetoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_trajeto);

        pcoContext = (PCOContext) getApplication();

        Button buttonAtualAtividade = findViewById(R.id.buttonAtualTrajeto);
        Button buttonRetAtividade = findViewById(R.id.buttonRetTrajeto);

        buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(v.getContext());\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO ATIVIDADES...\");\n" +
                            "                    progressBar.show();\n" +
                            "                    pcoContext.getPassageiroCTR().atualDados(ListaTrajetoActivity.this\n" +
                            "                            , ListaTrajetoActivity.class, progressBar, \"Trajeto\");", getLocalClassName());

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ATIVIDADES...");
                    progressBar.show();

                    pcoContext.getViagemCTR().atualDados(ListaTrajetoActivity.this
                            , ListaTrajetoActivity.class, progressBar, "Trajeto");

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaTrajetoActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaTrajetoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        }
                    });

                    alerta.show();

                }

            }
        });

        buttonRetAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetAtividade.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaTrajetoActivity.this, ListaTurnoActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaTrajetoActivity.this, ListaTurnoActivity.class);
                startActivity(it);
                finish();
            }
        });

        LogProcessoDAO.getInstance().insertLogProcesso("trajetoList = pcoContext.getPassageiroCTR().trajetoList();\n" +
                "        ArrayList<String> itens = new ArrayList<String>();\n" +
                "        for (int i = 0; i < trajetoList.size(); i++) {\n" +
                "            TrajetoBean trajetoBean = trajetoList.get(i);\n" +
                "            itens.add(trajetoBean.getDescrTrajeto());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        trajetoListView = (ListView) findViewById(R.id.trajetoList);\n" +
                "        trajetoListView.setAdapter(adapterList);", getLocalClassName());

        trajetoList = pcoContext.getViagemCTR().trajetoList();

        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < trajetoList.size(); i++) {
            TrajetoBean trajetoBean = trajetoList.get(i);
            itens.add(trajetoBean.getDescrTrajeto());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        trajetoListView = (ListView) findViewById(R.id.trajetoList);
        trajetoListView.setAdapter(adapterList);
        trajetoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("trajetoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                TrajetoBean trajetoBean = trajetoList.get(position);\n" +
                        "                pcoContext.getConfigCTR().setTrajetoConfig(trajetoBean.getIdTrajeto());\n" +
                        "                Intent it = new Intent(ListaTrajetoActivity.this, LotacaoMaxActivity.class);", getLocalClassName());
                TrajetoBean trajetoBean = trajetoList.get(position);
                pcoContext.getViagemCTR().getCabecViagemBean().setIdTrajetoCabecViagem(trajetoBean.getIdTrajeto());
                Intent it = new Intent(ListaTrajetoActivity.this, LotacaoMaxActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void onBackPressed()  {
    }

}