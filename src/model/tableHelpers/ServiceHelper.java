package model.tableHelpers;

import model.TableHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServiceHelper extends TableHelper {

	@Override
	public String getTableName() {
		return "service";
	}

	@Override
	public String[] getAttributes() {
		return new String[]{"serviceID", "minTierLevel", "serviceCost", "hotelAddress"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "serviceID":
					ps.setString(index, keyValuePairs.getString("serviceID"));
					break;
				case "minTierLevel":
					ps.setString(index, keyValuePairs.getString("minTierLevel"));
					break;
				case "serviceCost":
					ps.setDouble(index, keyValuePairs.getDouble("serviceCost"));
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
