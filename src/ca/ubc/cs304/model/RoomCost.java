package ca.ubc.cs304.model;


import ca.ubc.cs304.modelInterface.Entity;
import ca.ubc.cs304.modelInterface.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomCost extends Entity {

    private RoomCostHelper helper = null;

    private final int roomFloor;
	private final int roomNumber;
	private final double cost;

	public RoomCost(int roomFloor, int roomNumber, double cost) {
		this.roomFloor = roomFloor;
		this.roomNumber = roomNumber;
		this.cost = cost;
        this.helper = new RoomCostHelper();

    }

	public int getRoomFloor() {
		return roomFloor;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public double getCost() {
		return cost;
	}

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
        ps.setInt(1, this.getRoomFloor());
        ps.setInt(2, this.getRoomNumber());
        ps.setDouble(3, this.getCost());
    }
}
