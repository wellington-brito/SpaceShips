package apps4you.academytraining2.persistencia;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;
import java.util.List;

import apps4you.academytraining2.entidade.Agua;
import apps4you.academytraining2.entidade.Alimento;
import apps4you.academytraining2.entidade.AlimentosConsumidos;
import apps4you.academytraining2.entidade.Carga;
import apps4you.academytraining2.entidade.Corpo;
import apps4you.academytraining2.entidade.Exercicio;
import apps4you.academytraining2.entidade.GrupoAlimentar;
import apps4you.academytraining2.entidade.GrupoMuscular;
import apps4you.academytraining2.entidade.LembretesStatus;
import apps4you.academytraining2.entidade.Planejamento;
import apps4you.academytraining2.entidade.Suplemento;
import apps4you.academytraining2.entidade.TempoGasto;
import apps4you.academytraining2.entidade.Treino;
import apps4you.academytraining2.entidade.Usuario;


/**
 * Created by Were on 05/04/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // nomeGrupoMuscular do database para sua aplicacao
    private static final String DATABASE_NAME = "bd_projeto";

    // sempre que voce mudar objetos em seu database incremente a versao
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

//    // the DAO utilizado para acessar os dados
//    private Dao<GrupoMuscular, Integer> grupoMuscularDao = null;
//    private Dao<Exercicio, Integer> exercicioDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, GrupoMuscular.class);
            TableUtils.createTable(connectionSource, Exercicio.class);
            TableUtils.createTable(connectionSource, Treino.class);
            TableUtils.createTable(connectionSource, Usuario.class);
            TableUtils.createTable(connectionSource, Planejamento.class);
            TableUtils.createTable(connectionSource, GrupoAlimentar.class);
            TableUtils.createTable(connectionSource, AlimentosConsumidos.class);
            TableUtils.createTable(connectionSource, Alimento.class);
            TableUtils.createTable(connectionSource, TempoGasto.class);
            TableUtils.createTable(connectionSource, LembretesStatus.class);
            TableUtils.createTable(connectionSource, Suplemento.class);
            TableUtils.createTable(connectionSource, Agua.class);
            TableUtils.createTable(connectionSource, Corpo.class);
            TableUtils.createTable(connectionSource, Carga.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            switch (oldVersion) {
                case 1:
                    updateFromVersion1(db, connectionSource, oldVersion, newVersion);
                    break;
                case 2:
                    updateFromVersion2(db, connectionSource, oldVersion, newVersion);
                    break;
                default:
                    ///fadfadfa
                    break;
            }

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "exception during onUpgrade",
                    e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }
        @Override
        public void close() {
            super.close();
        }



    private void updateFromVersion1(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // do some stuff here

        onUpgrade(database, connectionSource, oldVersion + 1, newVersion);
    }

    private void updateFromVersion2(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) throws java.sql.SQLException {
        // do some stuff here
        onUpgrade(database, connectionSource, oldVersion + 1, newVersion);
    }
}

