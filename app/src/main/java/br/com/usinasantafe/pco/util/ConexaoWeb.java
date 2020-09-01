package br.com.usinasantafe.pco.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

public class ConexaoWeb {

	public ConexaoWeb() {
	}
	
	public  boolean verificaConexao(Context context) {
	    boolean conectado;  
	    ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    if (cm.getActiveNetworkInfo() != null  
	            && cm.getActiveNetworkInfo().isAvailable()  
	            && cm.getActiveNetworkInfo().isConnected()) {  
	        conectado = true;
	        Log.i("PCO", "CONECTA");
	    } else {  
	        conectado = false;  
	        Log.i("PCO", "NAO CONECTA");
	    }  
	    return conectado;  
	} 
	
}
