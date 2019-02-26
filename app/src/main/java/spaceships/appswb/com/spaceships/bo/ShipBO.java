package spaceships.appswb.com.spaceships.bo;

import android.content.Context;
import android.widget.Toast;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.*;
import spaceships.appswb.com.spaceships.dao.DatabaseHelper;
import spaceships.appswb.com.spaceships.dao.ShipDAO;
import spaceships.appswb.com.spaceships.entity.Ship;

import java.sql.SQLException;
import java.util.List;

public class ShipBO {

    public void salvar(Ship shipCOrrente, Context context) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(context);
        ShipDAO shipDAO = new ShipDAO(dh.getConnectionSource());
        shipDAO.create(shipCOrrente);
    }

    public List<Ship> searchShips(Context context) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(context);
        ShipDAO shipDAO = new ShipDAO(dh.getConnectionSource());
        List<Ship> ships_list = shipDAO.queryForAll();
        return ships_list;
    }

    public void atualizar(Ship shipCOrrente, Context context, Ship ship) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(context);
        ShipDAO shipDAO = new ShipDAO(dh.getConnectionSource());
        UpdateBuilder<Ship, Integer> updateBuilder;
        updateBuilder = configurarUpdateBuilder(shipCOrrente, shipDAO, ship);
        updateBuilder.update();
    }
    private UpdateBuilder<Ship, Integer> configurarUpdateBuilder(Ship shipCOrrente, ShipDAO shipDAO, Ship ship) throws SQLException {
        UpdateBuilder<Ship, Integer> updateBuilder = shipDAO.updateBuilder();
        updateBuilder.updateColumnValue("name",shipCOrrente.getName());
        updateBuilder.updateColumnValue("model",shipCOrrente.getModel());
        updateBuilder.updateColumnValue("cost_in_credits",shipCOrrente.getCost_in_credits());
        updateBuilder.updateColumnValue("length",shipCOrrente.getLength());
        updateBuilder.updateColumnValue("crew",shipCOrrente.getCrew());
        updateBuilder.updateColumnValue("passengers",shipCOrrente.getPassengers());
        updateBuilder.updateColumnValue("cargo_capacity",shipCOrrente.getCargo_capacity());
        updateBuilder.updateColumnValue("consumables",shipCOrrente.getConsumables());
        updateBuilder.updateColumnValue("starship_class",shipCOrrente.getStarship_class());
        updateBuilder.updateColumnValue("dataAlteracao",shipCOrrente.getDataAlteracao());
        updateBuilder.updateColumnValue("horaAlteracao",shipCOrrente.getHoraAlteracao());
        updateBuilder.where().eq("id", ship.getId());
        return updateBuilder;
    }

//    private static List<Ship> getShipsVisualized()  throws SQLException {
//        DatabaseHelper dh = new DatabaseHelper(Context context);
//        ShipDAO shipDAO = new ShipDAO(dh.getConnectionSource());
//        try {
//            QueryBuilder<Ship, Integer> queryBuilder = shipDAO.queryBuilder();
//            queryBuilder.where().eq("dataAlteracao", null);
//            List<MyFacePhoto> facePhotos = facePhotoQb.query();
//            List<Long> photoIds = new ArrayList<>();
//            for (MyFacePhoto fp : facePhotos) {
//                photoIds.add(fp.photoId);
//            }
//
//            return photoIds;
//        } catch (Exception e) {
//            Log.w(TAG, e.getMessage());
//            return null;
//        }
//    }



}
