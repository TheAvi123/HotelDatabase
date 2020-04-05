package model.tableHelpers;

import model.TableHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeHelper extends TableHelper {

	@Override
	public String getTableName() {
		return "employee";
	}

	@Override
	public String[] getAttributes() {
		return new String[]{"employeeStaffID", "employeeName", "payrollAccountNumber", "salary", "workShift", "managerStaffID"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "employeeStaffID":
					ps.setString(index, keyValuePairs.getString("employeeStaffID"));
					break;
				case "employeeName":
					ps.setString(index, keyValuePairs.getString("employeeName"));
					break;
				case "payrollAccountNumber":
					ps.setInt(index, keyValuePairs.getInt("payrollAccountNumber"));
					break;
				case "salary":
					ps.setFloat(index, (float) keyValuePairs.getDouble("salary"));
					break;
				case "workShift":
					ps.setString(index, keyValuePairs.getString("workShift"));
					break;
				case "managerStaffID":
					ps.setString(index, keyValuePairs.getString("managerStaffID"));
					break;
				default:
					throw new Error("Invalid Key!");
			}
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
