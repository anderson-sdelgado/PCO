package br.com.usinasantafe.pco.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pco.PCOContext;
import br.com.usinasantafe.pco.R;
import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;

public class HorimetroActivity extends ActivityGeneric {

    private PCOContext pcoContext;
    private Double horimetroNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horimetro);

        pcoContext = (PCOContext) getApplication();

        Button buttonOkHorimetro = findViewById(R.id.buttonOkPadrao);
        Button buttonCancHorimetro = findViewById(R.id.buttonCancPadrao);
        TextView textViewHorimetro = findViewById(R.id.textViewPadrao);

        if(pcoContext.getConfigCTR().getConfig().getPosicaoTela() == 1){
            textViewHorimetro.setText("MEDIÇÃO INICIAL");
        }
        else{
            textViewHorimetro.setText("MEDIÇÃO FINAL");
        }

        buttonOkHorimetro.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkHorimetro.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                    \n" +
                        "                    String horimetro = editTextPadrao.getText().toString();\n" +
                        "                    horimetroNum = Double.valueOf(horimetro.replace(\",\", \".\"));", getLocalClassName());
                String horimetro = editTextPadrao.getText().toString();
                horimetroNum = Double.valueOf(horimetro.replace(",", "."));

                if(pcoContext.getConfigCTR().getConfig().getPosicaoTela() == 1){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcoContext.getConfigCTR().getConfig().getPosicaoTela() == 1){\n" +
                            "                        pcoContext.getConfigCTR().setHodometroInicialConfig(horimetroNum);\n" +
                            "                        pcoContext.getViagemCTR().getCabecViagemBean().setHodometroInicialConfig(horimetroNum);\n" +
                            "                        pcoContext.getViagemCTR().salvarCabecViagem();\n" +
                            "                        Intent it = new Intent(HorimetroActivity.this, ListaPassageiroActivity.class);", getLocalClassName());

                    pcoContext.getConfigCTR().setHodometroInicialConfig(horimetroNum);
                    pcoContext.getViagemCTR().getCabecViagemBean().setHodometroInicialCabecViagem(horimetroNum);
                    pcoContext.getViagemCTR().abrirCabecViagem();
                    Intent it = new Intent(HorimetroActivity.this, ListaPassageiroActivity.class);
                    startActivity(it);

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                        pcoContext.getConfigCTR().setHodometroInicialConfig(horimetroNum);\n" +
                            "                        pcoContext.getViagemCTR().fecharCabec(horimetroNum, getLocalClassName());\n" +
                            "                        Intent it = new Intent(HorimetroActivity.this, MenuInicialActivity.class);", getLocalClassName());
                    pcoContext.getConfigCTR().setHodometroInicialConfig(horimetroNum);
                    pcoContext.getViagemCTR().fecharCabec(horimetroNum, getLocalClassName());
                    Intent it = new Intent(HorimetroActivity.this, MenuInicialActivity.class);
                    startActivity(it);

                }
                finish();

            }

        });

        buttonCancHorimetro.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancHorimetro.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                if (editTextPadrao.getText().toString().length() > 0) {\n" +
                    "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                    "                }", getLocalClassName());
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {", getLocalClassName());
        if (pcoContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pcoContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {\n" +
                    "            Intent it = new Intent(HorimetroActivity.this, LotacaoMaxActivity.class);", getLocalClassName());
            Intent it = new Intent(HorimetroActivity.this, LotacaoMaxActivity.class);
            startActivity(it);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            Intent it = new Intent(HorimetroActivity.this, ListaPassageiroActivity.class);", getLocalClassName());
            Intent it = new Intent(HorimetroActivity.this, ListaPassageiroActivity.class);
            startActivity(it);
        }
        finish();
    }

}