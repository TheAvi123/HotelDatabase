package model.tableHelpers;

import model.TableHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomCostHelper extends TableHelper {

	@Override
	public String getTableName() {
		return "roomCost";
	}

	@Override
	public String[] getAttributes() {
		return new String[] {"roomNumber", "roomFloor", "roomCost"};
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
				case "roomCost":
					ps.setDouble(index, keyValuePairs.getDouble("roomCost"));
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
