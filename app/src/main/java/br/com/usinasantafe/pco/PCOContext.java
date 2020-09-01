package br.com.usinasantafe.pco;

import android.app.Application;

import br.com.usinasantafe.pco.control.AcessoCTR;

public class PCOContext extends Application {

    public static String versaoAplic = "1.00";
    private AcessoCTR acessoCTR;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AcessoCTR getAcessoCTR() {
        if (acessoCTR == null)
            acessoCTR = new AcessoCTR();
        return acessoCTR;
    }

}
