//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Assists extends Table {
//
//	private final String customer_id;
//	private final String bellhopStaffID;
//
//	public Assists(String customer_id, String bellhopStaffID) {
//		this.customer_id = customer_id;
//		this.bellhopStaffID = bellhopStaffID;
//	}
//
//	public String getCustomerID() {
//		return customer_id;
//	}
//
//	public String getBellhopStaffID() {
//		return bellhopStaffID;
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
