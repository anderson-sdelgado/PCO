package br.com.usinasantafe.pco.model.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.com.usinasantafe.pco.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pco.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pco.model.bean.estaticas.MotoristaBean;
import br.com.usinasantafe.pco.model.bean.estaticas.TrajetoBean;
import br.com.usinasantafe.pco.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pco.model.bean.variaveis.CabecViagemBean;
import br.com.usinasantafe.pco.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pco.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pco.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pco.model.bean.variaveis.PassageiroViagemBean;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pco_db";
	public static final int FORCA_BD_VERSION = 4;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {

		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);;

		instance = this;
		
	}

	@Override
	public void close() {
		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		
		try{

			createTable(cs);

		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
                          ConnectionSource cs,
                          int oldVersion,
                          int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){
				dropTable(cs);
				createTable(cs);
			} else if(oldVersion == 2 && newVersion == 3){
				dropTable(cs);
				createTable(cs);
			} else if(oldVersion == 3 && newVersion == 4){
				dropTable(cs);
				createTable(cs);
			}

		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

	public void dropTable(ConnectionSource cs) throws SQLException {

		TableUtils.dropTable(cs, ColabBean.class, true);
		TableUtils.dropTable(cs, EquipBean.class, true);
		TableUtils.dropTable(cs, MotoristaBean.class, true);
		TableUtils.dropTable(cs, TrajetoBean.class, true);
		TableUtils.dropTable(cs, TurnoBean.class, true);

		TableUtils.dropTable(cs, ConfigBean.class, true);
		TableUtils.dropTable(cs, LogErroBean.class, true);
		TableUtils.dropTable(cs, LogProcessoBean.class, true);
		TableUtils.dropTable(cs, CabecViagemBean.class, true);
		TableUtils.dropTable(cs, PassageiroViagemBean.class, true);

	}

	public void createTable(ConnectionSource cs) throws SQLException {

		TableUtils.createTable(cs, ColabBean.class);
		TableUtils.createTable(cs, EquipBean.class);
		TableUtils.createTable(cs, MotoristaBean.class);
		TableUtils.createTable(cs, TrajetoBean.class);
		TableUtils.createTable(cs, TurnoBean.class);

		TableUtils.createTable(cs, ConfigBean.class);
		TableUtils.createTable(cs, LogErroBean.class);
		TableUtils.createTable(cs, LogProcessoBean.class);
		TableUtils.createTable(cs, CabecViagemBean.class);
		TableUtils.createTable(cs, PassageiroViagemBean.class);

	}

}
