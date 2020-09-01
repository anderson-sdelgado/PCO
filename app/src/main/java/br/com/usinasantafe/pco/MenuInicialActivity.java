package br.com.usinasantafe.pco;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuInicialActivity extends ActivityGeneric {

    private PCOContext pcoContext;
    private ListView listView;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pcoContext = (PCOContext) getApplication();

        ArrayList<String> itens = new ArrayList<>();

        itens.add("INICIAR VIAGEM");
        itens.add("RELATÓRIO");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        listView = (ListView) findViewById(R.id.listaMenuInicial);
        listView.setAdapter(adapterList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("INICIAR VIAGEM")) {
                    if (pcoContext.getPassageiroCTR().hasElementsMotorista()
                            && pcoContext.getConfigCTR().hasElements()) {

                        Intent it = new Intent(MenuInicialActivity.this, MotoristaActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else if (text.equals("RELATÓRIO")) {



                } else if (text.equals("CONFIGURAÇÃO")) {
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("SAIR")) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }

        });

    }
}