//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class RoomClassification extends Table {
//
//	private final char[] roomType;
//	private final char[] tierLevel;
//
//	public RoomClassification(char[] roomType, char[] tierLevel) {
//		this.roomType = roomType;
//		this.tierLevel = tierLevel;
//	}
//
//	public char[] getRoomType() {
//		return roomType;
//	}
//
//	public char[] getTierLevel() {
//		return tierLevel;
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
