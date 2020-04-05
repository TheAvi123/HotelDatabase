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
	public String[] getPrimaryAttributes() {
		return new String[]{"employee_staffID"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "employee_staffID":
					ps.setString(index, keyValuePairs.getString("employee_staffID"));
					break;
				case "employee_name":
					ps.setString(index, keyValuePairs.getString("employee_name"));
					break;
				case "employee_payrollAccountNumber":
					ps.setInt(index, keyValuePairs.getInt("employee_payrollAccountNumber"));
					break;
				case "employee_salary":
					ps.setInt(index, keyValuePairs.getInt("employee_salary"));
					break;
				case "employee_workShift":
					ps.setString(index, keyValuePairs.getString("employee_workShift"));
					break;
				case "employee_managerStaffID":
					ps.setString(index, keyValuePairs.getString("employee_managerStaffID"));
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
