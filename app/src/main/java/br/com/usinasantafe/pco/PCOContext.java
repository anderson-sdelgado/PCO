package br.com.usinasantafe.pco;

import android.app.Application;

import br.com.usinasantafe.pco.control.ConfigCTR;
import br.com.usinasantafe.pco.control.PassageiroCTR;

public class PCOContext extends Application {

    public static String versaoAplic = "1.02";
    private PassageiroCTR passageiroCTR;
    private ConfigCTR configCTR;
    private String matriculaPassageiro;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public PassageiroCTR getPassageiroCTR() {
        if (passageiroCTR == null)
            passageiroCTR = new PassageiroCTR();
        return passageiroCTR;
    }

    public ConfigCTR getConfigCTR() {
        if (configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

    public String getMatriculaPassageiro() {
        return matriculaPassageiro;
    }

    public void setMatriculaPassageiro(String matriculaPassageiro) {
        this.matriculaPassageiro = matriculaPassageiro;
    }

}
