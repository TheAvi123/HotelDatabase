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
	public String[] getPrimaryAttributes() {
		return new String[]{"manager_staffID"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "manager_staffID":
					ps.setString(index, keyValuePairs.getString("manager_staffID"));
					break;
				case "manager_name":
					ps.setString(index, keyValuePairs.getString("manager_name"));
					break;
				case "manager_hotelAddress":
					ps.setString(index, keyValuePairs.getString("manager_hotelAddress"));
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
