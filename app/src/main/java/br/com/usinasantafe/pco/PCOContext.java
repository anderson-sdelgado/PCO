package br.com.usinasantafe.pco;

import android.app.Application;

import br.com.usinasantafe.pco.control.ConfigCTR;
import br.com.usinasantafe.pco.control.PassageiroCTR;

public class PCOContext extends Application {

    public static String versaoAplic = "1.02";
    private PassageiroCTR passageiroCTR;
    private ConfigCTR configCTR;
    private int verTela;
    // 1 - Tela Inicial
    // 2 - Tela Passageiro
    // 3 - Verificando Motorista
    // 4 - Verificando Colaborador

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

    public int getVerTela() {
        return verTela;
    }

    public void setVerTela(int verTela) {
        this.verTela = verTela;
    }
}
