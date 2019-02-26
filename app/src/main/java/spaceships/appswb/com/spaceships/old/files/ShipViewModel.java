package spaceships.appswb.com.spaceships.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import spaceships.appswb.com.spaceships.entity.Ship;
import spaceships.appswb.com.spaceships.old.files.ShipsRepository;

import java.util.List;

public class ShipViewModel extends AndroidViewModel {
    private ShipsRepository mRepository;
    private LiveData<List<Ship>> mAllShips;

    public ShipViewModel(Application application) {
        super(application);
        mRepository = new ShipsRepository(application);
        mAllShips = mRepository.getAllShips();
    }

    LiveData<List<Ship>> getmAllShips() {
        return mAllShips;
    }

    public void insert(Ship ship) {
        mRepository.insert(ship);
    }
}
