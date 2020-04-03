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
	public String[] getPrimaryAttributes() {
		return new String[]{"service_id"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "service_id":
					ps.setInt(index, keyValuePairs.getInt("service_id"));
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
