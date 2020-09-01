package br.com.usinasantafe.pco;

import android.app.Application;

import br.com.usinasantafe.pco.control.ConfigCTR;
import br.com.usinasantafe.pco.control.PassageiroCTR;

public class PCOContext extends Application {

    public static String versaoAplic = "1.00";
    private PassageiroCTR passageiroCTR;
    private ConfigCTR configCTR;

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

}
