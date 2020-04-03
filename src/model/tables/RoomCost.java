package model.tables;

import model.tableHelpers.RoomCostHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomCost extends Table {

    private RoomCostHelper helper;

    private final int roomNumber;
    private final int roomFloor;
	private final double cost;

	public RoomCost(int roomNumber, int roomFloor, double cost) {
        this.roomNumber = roomNumber;
        this.roomFloor = roomFloor;
		this.cost = cost;

        this.helper = new RoomCostHelper();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getRoomFloor() {
		return roomFloor;
	}

	public double getCost() {
		return cost;
	}

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getAttributeCount() {
        return 3;
    }

    @Override
    public TableHelper getTableHelper() {
        return this.helper;
	}

    @Override
    public void setTupleParametersToStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, this.getRoomNumber());
        ps.setInt(2, this.getRoomFloor());
        ps.setDouble(3, this.getCost());
    }
}
