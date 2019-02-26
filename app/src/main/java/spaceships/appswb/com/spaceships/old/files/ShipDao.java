package spaceships.appswb.com.spaceships.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import spaceships.appswb.com.spaceships.entity.Ship;

import java.util.List;

@Dao
public interface ShipDao {

    @Insert
    void insert(Ship ship);

    @Query("DELETE FROM ships_table")
    void deleteAll();

    @Query("SELECT * from ships_table")
    LiveData<List<Ship>> getAllShips();
}
