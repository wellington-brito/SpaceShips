package spaceships.appswb.com.spaceships.old.files;
/*
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import spaceships.appswb.com.spaceships.entity.Ship;
import spaceships.appswb.com.spaceships.entity.VisualisedShips;

@Database(entities = {Ship.class, VisualisedShips.class}, version = 1)
public abstract class ShipsRoomDatabase extends android.arch.persistence.room.RoomDatabase {

    private static volatile ShipsRoomDatabase INSTANCE;

    public abstract ShipDao shipDao();
    public abstract VisualisedShipsDao visualisedShipsDao();

    public static ShipsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ShipsRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ShipsRoomDatabase.class, "db_starships")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onCreate (@NonNull SupportSQLiteDatabase db){
                    super.onCreate(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ShipDao mDao;

        private PopulateDbAsync(ShipsRoomDatabase db) {
            mDao = db.shipDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }
}
*/