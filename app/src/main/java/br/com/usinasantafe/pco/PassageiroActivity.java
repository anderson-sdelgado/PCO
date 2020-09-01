package br.com.usinasantafe.pco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PassageiroActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCOContext pcoContext;
    private TextView txtRetPassageiro;
    private String matricula;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passageiro);

        pcoContext = (PCOContext) getApplication();

        txtRetPassageiro = (TextView) findViewById(R.id.txtRetPassageiro);
        Button buttonOkPassageiro = (Button) findViewById(R.id.buttonOkPassageiro);
        Button buttonCancPassageiro = (Button) findViewById(R.id.buttonCancPassageiro);
        Button buttonAcionarCamera = (Button) findViewById(R.id.buttonAcionarCamera);
        nome = null;

        txtRetPassageiro.setText("");

        buttonOkPassageiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(PassageiroActivity.this, ListaTurnoActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonCancPassageiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(PassageiroActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonAcionarCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(PassageiroActivity.this, br.com.usinasantafe.pco.zxing.CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            matricula = data.getStringExtra("SCAN_RESULT");
            if(matricula.length() == 8){
                matricula = matricula.substring(0,7);
                FuncTO funcTO = new FuncTO();
                List listFunc = funcTO.get("matriculaFunc", Long.parseLong(matricula));
                if (listFunc.size() > 0) {
                    funcTO = (FuncTO) listFunc.get(0);
                    nome = funcTO.getNomeFunc();
                    txtRetPassageiro.setText(matricula + "\n" + nome);
                }
                else{
                    txtRetPassageiro.setText("Funcionario Inexistente");
                }
            }
        }

    }

}