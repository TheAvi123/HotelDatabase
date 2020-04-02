//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Chef extends Table {
//
//	private final char[] employee_staff_id;
//	private final char[] signature_dish;
//
//	public Chef(char[] employee_staff_id, char[] signature_dish) {
//		this.employee_staff_id = employee_staff_id;
//		this.signature_dish = signature_dish;
//	}
//
//	public char[] getEmployeeStaffID() {
//		return employee_staff_id;
//	}
//
//	public char[] getSignatureDish() {
//		return signature_dish;
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
