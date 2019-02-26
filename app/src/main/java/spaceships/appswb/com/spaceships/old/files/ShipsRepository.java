package spaceships.appswb.com.spaceships.old.files;
/*
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import spaceships.appswb.com.spaceships.dao.ShipsRoomDatabase;
import spaceships.appswb.com.spaceships.dao.ShipDao;
import spaceships.appswb.com.spaceships.entity.Ship;

import java.util.List;

public class ShipsRepository {

    private ShipDao mShipDao;
    private LiveData<List<Ship>> mAllShips;

    public ShipsRepository(Application application) {
        ShipsRoomDatabase db = ShipsRoomDatabase.getDatabase(application);
        mShipDao = db.shipDao();
        mAllShips = mShipDao.getAllShips();
    }

    public LiveData<List<Ship>> getAllShips() {
        return mAllShips;
    }

    public void insert (Ship ship) {
        new insertAsyncTask(mShipDao).execute(ship);
    }

    private static class insertAsyncTask extends AsyncTask<Ship, Void, Void> {

        private ShipDao mAsyncTaskDao;

        insertAsyncTask(ShipDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Ship... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
*/