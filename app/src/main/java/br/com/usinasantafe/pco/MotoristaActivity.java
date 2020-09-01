package br.com.usinasantafe.pco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.zxing.CaptureActivity;

public class MotoristaActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCOContext pcoContext;
    private TextView txtRetPassageiro;
    private MotoristaBean motoristaBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista);

        pcoContext = (PCOContext) getApplication();

        txtRetPassageiro = (TextView) findViewById(R.id.txtRetPassageiro);
        Button buttonOkPassageiro = (Button) findViewById(R.id.buttonOkPassageiro);
        Button buttonCancPassageiro = (Button) findViewById(R.id.buttonCancPassageiro);
        Button buttonAcionarCamera = (Button) findViewById(R.id.buttonAcionarCamera);

        motoristaBean.setMatricMoto(0L);
        motoristaBean.setNomeMoto("");

        txtRetPassageiro.setText(motoristaBean.getNomeMoto());

        buttonOkPassageiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(motoristaBean.getMatricMoto() > 0){
                    pcoContext.getConfigCTR().setMotoConfig(motoristaBean);
                    Intent it = new Intent(MotoristaActivity.this, ListaTurnoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonCancPassageiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(MotoristaActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonAcionarCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(MotoristaActivity.this, CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            String matricula = data.getStringExtra("SCAN_RESULT");
            if(matricula.length() == 8){
                matricula = matricula.substring(0,7);
                if (pcoContext.getPassageiroCTR().verMotorista(Long.parseLong(matricula))) {
                    motoristaBean = pcoContext.getPassageiroCTR().getMotorista(Long.parseLong(matricula));
                    txtRetPassageiro.setText(motoristaBean.getMatricMoto() + "\n" + motoristaBean.getNomeMoto());
                }
                else{
                    txtRetPassageiro.setText("Funcionario Inexistente");
                }
            }
        }

    }

}