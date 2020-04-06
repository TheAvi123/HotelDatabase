package model.tableHelpers;

import model.TableHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerHelper extends TableHelper {

	@Override
	public String getTableName() {
		return "manager";
	}

	@Override
	public String[] getAttributes() {
		return new String[]{"managerStaffID", "managerName", "hotelAddress"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "managerStaffID":
					ps.setString(index, keyValuePairs.getString("managerStaffID"));
					break;
				case "managerName":
					ps.setString(index, keyValuePairs.getString("managerName"));
					break;
				case "hotelAddress":
					ps.setString(index, keyValuePairs.getString("hotelAddress"));
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
