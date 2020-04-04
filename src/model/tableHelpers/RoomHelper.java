package model.tableHelpers;

import model.tables.Room;
import model.Table;
import model.TableHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomHelper extends TableHelper {

	@Override
	public String getTableName() {
		return "room";
	}

	@Override
	public String[] getAttributes() {
		return new String[] {"roomNumber", "roomFloor", "roomType", "numberOfBeds", "hotelAddress"};
	}

	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "roomNumber":
					ps.setInt(index, keyValuePairs.getInt("roomNumber"));
					break;
				case "roomFloor":
					ps.setInt(index, keyValuePairs.getInt("roomFloor"));
					break;
				case "roomType":
					ps.setString(index, keyValuePairs.getString("roomType"));
					break;
				case "numberOfBeds":
					ps.setInt(index, keyValuePairs.getInt("numberOfBeds"));
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
