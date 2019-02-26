package spaceships.appswb.com.spaceships.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import spaceships.appswb.com.spaceships.entity.Ship;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // nomeGrupoMuscular do database para sua aplicacao
    private static final String DATABASE_NAME = "bd_starships";

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
            TableUtils.createTable(connectionSource, Ship.class);

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

