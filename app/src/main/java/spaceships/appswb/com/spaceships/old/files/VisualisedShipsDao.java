package spaceships.appswb.com.spaceships.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import spaceships.appswb.com.spaceships.entity.Ship;
import spaceships.appswb.com.spaceships.entity.VisualisedShips;

import java.util.List;

@Dao
public interface VisualisedShipsDao {
    @Insert
    void insert(VisualisedShips visualisedShips);

    @Query("DELETE FROM visualised_ships_table")
    void deleteAll();

    @Query("SELECT * from visualised_ships_table")
    List<Ship> getAllVisualisedShips();
}
