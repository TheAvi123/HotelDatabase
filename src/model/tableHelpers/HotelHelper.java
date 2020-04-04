package model.tableHelpers;

import model.TableHelper;
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
	public String[] getAttributes() {
		return new String[] {"hotelAddress", "hotelName", "capacity"};
	}

	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "hotelAddress":
					ps.setString(index, keyValuePairs.getString("hotelAddress"));
					break;
				case "hotelName":
					ps.setString(index, keyValuePairs.getString("hotelName"));
					break;
				case "capacity":
					ps.setInt(index, keyValuePairs.getInt("capacity"));
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
