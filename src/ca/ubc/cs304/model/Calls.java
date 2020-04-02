//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Calls extends Table {
//
//	private final char[] receptionist_staff_id;
//	private final char[] customer_id;
//
//	public Calls(char[] receptionist_staff_id, char[] customer_id) {
//		this.receptionist_staff_id = receptionist_staff_id;
//		this.customer_id = customer_id;
//	}
//
//	public char[] getReceptionistStaffID() {
//		return receptionist_staff_id;
//	}
//
//	public char[] getCustomerID() {
//		return customer_id;
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
