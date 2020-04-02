//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Manager extends Table {
//
//	private final char[] staffID;
//	private final char[] managerName;
//	private final char[] hotelAddress;
//
//	public Manager(char[] staffID, char[] managerName, char[] hotelAddress) {
//		this.staffID = staffID;
//		this.managerName = managerName;
//		this.hotelAddress = hotelAddress;
//	}
//
//	public char[] getStaffID() {
//		return staffID;
//	}
//
//	public char[] getManagerName() {
//		return managerName;
//	}
//
//	public char[] getHotelAddress() {
//		return hotelAddress;
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
