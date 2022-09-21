package br.com.usinasantafe.pco;

import android.app.Application;

import br.com.usinasantafe.pco.control.ConfigCTR;
import br.com.usinasantafe.pco.control.ViagemCTR;

public class PCOContext extends Application {

    public static String versaoWS = "2.00";
    private ViagemCTR viagemCTR;
    private ConfigCTR configCTR;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ViagemCTR getViagemCTR() {
        if (viagemCTR == null)
            viagemCTR = new ViagemCTR();
        return viagemCTR;
    }

    public ConfigCTR getConfigCTR() {
        if (configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

}
