package br.com.usinasantafe.pco.util.connHttp;

import br.com.usinasantafe.pco.PCOContext;

public class UrlsConexaoHttp {

    public static String versao = "versao_" + PCOContext.versaoWS.replace(".", "_");

    public static String url = "https://www.usinasantafe.com.br/pcodev/view/";
//    public static String url = "https://www.usinasantafe.com.br/pcoqa/view/";
//    public static String url = "https://www.usinasantafe.com.br/pcoprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pco.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pco.util.connHttp.UrlsConexaoHttp";

    public static String ColabBean = url + "colab.php";
    public static String EquipBean = url + "equip.php";
    public static String MotoristaBean = url + "moto.php";
    public static String TrajetoBean = url + "trajeto.php";
    public static String TurnoBean = url + "turno.php";

    public UrlsConexaoHttp() {
    }

    public String getsInsertCabecFechado() {
        return url + "inserircabecfechado.php";
    }

    public String getsInsertCabecAberto() {
        return url + "inserircabecaberto.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Atualiza")) {
            retorno = url + "atualaplic.php";
        } else if (classe.equals("Moto")) {
            retorno = url + "atualmoto.php";
        } else if (classe.equals("Colab")) {
            retorno = url + "atualcolab.php";
        }
        return retorno;
    }

}
