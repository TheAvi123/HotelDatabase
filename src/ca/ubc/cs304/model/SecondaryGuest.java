//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class SecondaryGuest extends Table {
//
//	private final char[] customerID;
//	private final char[] guestName;
//	private final int guestAge;
//
//	public SecondaryGuest(char[] customerID, char[] guestName, int guestAge) {
//		this.customerID = customerID;
//		this.guestName = guestName;
//		this.guestAge = guestAge;
//	}
//
//	public char[] getCustomerID() {
//		return customerID;
//	}
//
//	public char[] getGuestName() {
//		return guestName;
//	}
//
//	public int getGuestAge() {
//		return guestAge;
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
