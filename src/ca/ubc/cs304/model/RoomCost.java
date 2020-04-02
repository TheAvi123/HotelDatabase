//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class RoomCost extends Table {
//
//	private final int roomFloor;
//	private final int roomNumber;
//	private final double cost;
//
//	public RoomCost(int roomFloor, int roomNumber, double cost) {
//		this.roomFloor = roomFloor;
//		this.roomNumber = roomNumber;
//		this.cost = cost;
//	}
//
//	public int getRoomFloor() {
//		return roomFloor;
//	}
//
//	public int getRoomNumber() {
//		return roomNumber;
//	}
//
//	public double getCost() {
//		return cost;
//	}
//
//	@Override
//	public String getTableName() {
//		return null;
//	}
//
//	@Override
//	public String[] getPrimaryAttributes() {
//		return new String[0];
//	}
//
//	@Override
//	public void printTupleInfo(Table tuple) {
//
//	}
//
//	@Override
//	public void setAllStatementParameters(PreparedStatement ps) throws SQLException {
//
//	}
//}
