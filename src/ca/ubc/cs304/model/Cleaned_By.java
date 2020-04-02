//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Cleaned_By extends Table {
//
//	private final char[] janitorStaffID;
//	private final int roomFloor;
//	private final int roomNumber;
//
//	public Cleaned_By(char[] janitorStaffID, int roomFloor, int roomNumber) {
//		this.janitorStaffID = janitorStaffID;
//		this.roomFloor = roomFloor;
//		this.roomNumber = roomNumber;
//	}
//
//	public char[] getJanitorStaffID() {
//		return janitorStaffID;
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
