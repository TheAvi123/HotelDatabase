package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.TableHelper;
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
	public String[] getPrimaryAttributes() {
		return new String[]{"service_serviceID"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "service_serviceID":
					ps.setInt(index, keyValuePairs.getInt("service_serviceID"));
					break;
				case "service_minTierLevel":
					ps.setInt(index, keyValuePairs.getInt("service_minTierLevel"));
					break;
				case "service_cost":
					ps.setDouble(index, keyValuePairs.getDouble("service_cost"));
					break;
				case "service_hotelAddress":
					ps.setDouble(index, keyValuePairs.getDouble("service_hotelAddress"));
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
