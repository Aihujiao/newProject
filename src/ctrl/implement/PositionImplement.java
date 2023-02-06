package ctrl.implement;

import ctrl.dao.PositionDao;
import ctrl.db.ExecuteDB;
import model.Position;

import java.util.List;

public class PositionImplement extends ExecuteDB implements PositionDao {
    @Override
    public Position registerPositon(Position position) {
        return null;
    }

    @Override
    public Position getPositionById(int positionId) {
        return null;
    }

    @Override
    public Position updatePosition(Position position) {
        return null;
    }

    @Override
    public String getPositionNameById(int positionId) {
        return null;
    }

    @Override
    public boolean deletePositionById(int positionId) {
        return false;
    }

    @Override
    public List<Position> getAllPositions() {
        return null;
    }

    @Override
    public List<Position> getAllLikePositionsName() {
        return null;
    }


}
