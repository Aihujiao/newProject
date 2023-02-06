package ctrl.dao;

import model.Position;

import java.util.List;

public interface PositionDao {
    public Position registerPositon(Position position);
    public Position getPositionById(int positionId);
    public Position updatePosition(Position position);
    public String getPositionNameById(int positionId);
    public boolean deletePositionById(int positionId);
    public List<Position> getAllPositions();
    public List<Position> getAllLikePositionsName();
}
