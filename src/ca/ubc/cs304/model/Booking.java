//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//import oracle.sql.DATE;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Booking extends Table {
//
//	private final char[] bookingNumber;
//	private final DATE startDate;
//	private final DATE endDate;
//	private final int roomFloor;
//	private final int roomNumber;
//	private final char[] customerID;
//
//	public Booking(char[] bookingNumber, DATE startDate, DATE endDate, int roomFloor, int roomNumber, char[] customerID) {
//		this.bookingNumber = bookingNumber;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.roomFloor = roomFloor;
//		this.roomNumber = roomNumber;
//		this.customerID = customerID;
//	}
//
//	public char[] getBookingNumber() {
//		return bookingNumber;
//	}
//
//	public DATE getStartDate() {
//		return startDate;
//	}
//
//	public DATE getEndDate() {
//		return startDate;
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
//	public char[] getCustomerID() {
//		return customerID;
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
