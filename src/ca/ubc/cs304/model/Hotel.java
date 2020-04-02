//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Hotel extends Table {
//
//	private final char[] address;
//	private final char[] name;
//	private final int capacity;
//
//	public Hotel(char[] address, char[] name, int capacity) {
//		this.address = address;
//		this.name = name;
//		this.capacity = capacity;
//	}
//
//	public char[] getAddress() {
//		return address;
//	}
//
//	public char[] getName() {
//		return name;
//	}
//
//	public int getCapacity() {
//		return capacity;
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
