package br.com.usinasantafe.pco;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pco.model.pst.DatabaseHelper;
import br.com.usinasantafe.pco.util.EnvioDadosServ;
import br.com.usinasantafe.pco.util.Tempo;


public class ReceberAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

//		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

			if (DatabaseHelper.getInstance() == null) {
				new DatabaseHelper(context);
			}

			Log.i("PCO", "DATA HORA = " + Tempo.getInstance().data());

			if (EnvioDadosServ.getInstance().verifDadosEnvio()) {
				Log.i("PMM", "ENVIANDO");
				EnvioDadosServ.getInstance().enviarDados(context);
			}

//		}
	}

}