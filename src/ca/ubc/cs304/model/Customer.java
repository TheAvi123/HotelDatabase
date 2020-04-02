//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Customer extends Table {
//
//	private final char[] customerID;
//	private final char[] customerName;
//	private final int customerAge;
//	private final char[] creditCardNumber;
//	private final char[] phoneNumber;
//
//	public Customer(char[] customerID, char[] customerName, int customerAge, char[] creditCardNumber, char[] phoneNumber) {
//		this.customerID = customerID;
//		this.customerName = customerName;
//		this.customerAge = customerAge;
//		this.creditCardNumber = creditCardNumber;
//		this.phoneNumber = phoneNumber;
//	}
//
//	public char[] getCustomerID() {
//		return customerID;
//	}
//
//	public char[] getCustomerName() {
//		return customerName;
//	}
//
//	public int getCustomerAge() {
//		return customerAge;
//	}
//
//	public char[] getCreditCardNumber() {
//		return creditCardNumber;
//	}
//
//	public char[] getPhoneNumber() {
//		return phoneNumber;
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
