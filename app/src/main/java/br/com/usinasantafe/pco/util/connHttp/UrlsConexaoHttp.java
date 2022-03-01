package br.com.usinasantafe.pco.util.connHttp;

import br.com.usinasantafe.pco.PCOContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "https://www.usinasantafe.com.br/pcoqa/view/";
    public static String urlPrincEnvio = "https://www.usinasantafe.com.br/pcoqa/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pco.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pco.util.connHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + PCOContext.versaoAplic.replace(".", "_");

    public static String ColabBean = urlPrincipal + "colab.php" + put;
    public static String EquipBean = urlPrincipal + "equip.php" + put;
    public static String MotoristaBean = urlPrincipal + "moto.php" + put;
    public static String TrajetoBean = urlPrincipal + "trajeto.php" + put;
    public static String TurnoBean = urlPrincipal + "turno.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInsertCabecFechado() {
        return urlPrincEnvio + "inserircabecfechado.php" + put;
    }

    public String getsInsertCabecAberto() {
        return urlPrincEnvio + "inserircabecaberto.php" + put;
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualaplic.php" + put;
        } else if (classe.equals("Moto")) {
            retorno = urlPrincEnvio + "atualmoto.php" + put;
        } else if (classe.equals("Colab")) {
            retorno = urlPrincEnvio + "atualcolab.php" + put;
        }
        return retorno;
    }

}
