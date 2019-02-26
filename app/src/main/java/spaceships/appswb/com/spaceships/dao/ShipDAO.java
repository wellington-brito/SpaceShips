package spaceships.appswb.com.spaceships.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import spaceships.appswb.com.spaceships.entity.Ship;

import java.sql.SQLException;

public class ShipDAO extends BaseDaoImpl<Ship, Integer> {
    public ShipDAO(ConnectionSource connectionSource) throws SQLException {
        super(Ship.class);
        setConnectionSource(connectionSource);
        initialize();
    }

}
