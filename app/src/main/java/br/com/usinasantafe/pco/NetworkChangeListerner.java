package br.com.usinasantafe.pco;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.com.usinasantafe.pco.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pco.util.ConnectNetwork;
import br.com.usinasantafe.pco.util.EnvioDadosServ;
import br.com.usinasantafe.pco.util.VerifDadosServ;
import br.com.usinasantafe.pco.view.ActivityGeneric;

public class NetworkChangeListerner extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(ConnectNetwork.isConnected(context)){
			ActivityGeneric.connectNetwork = true;
			LogProcessoDAO.getInstance().insertLogProcesso("if(ConnectNetwork.isConnected(context)){\n" +
					"            ActivityGeneric.connectNetwork = true;\n" +
					"Tempo.getInstance().zerarDifTempo()\n" +
					"EnvioDadosServ.status " + EnvioDadosServ.status, context.getClass().getName());
			if (EnvioDadosServ.status == 1) {
				LogProcessoDAO.getInstance().insertLogProcesso("if (EnvioDadosServ.status == 1) {\n" +
						"EnvioDadosServ.getInstance().envioDados(context.getClass().getName());", context.getClass().getName());
				EnvioDadosServ.getInstance().envioDados(context.getClass().getName());
			}
		}
		else{
			LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
					"            ActivityGeneric.connectNetwork = false;", context.getClass().getName());
			ActivityGeneric.connectNetwork = false;
		}

	}

}