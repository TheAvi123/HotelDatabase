package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.TableHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HotelHelper extends TableHelper {

	@Override
	public String getTableName() {
		return "hotel";
	}

	@Override
	public String[] getPrimaryAttributes() {
		return new String[]{"hotel_address"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "hotel_address":
					ps.setString(index, keyValuePairs.getString("hotel_address"));
					break;
				case "hotel_name":
					ps.setString(index, keyValuePairs.getString("hotel_name"));
					break;
				case "hotel_capacity":
					ps.setInt(index, keyValuePairs.getInt("hotel_capacity"));
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
