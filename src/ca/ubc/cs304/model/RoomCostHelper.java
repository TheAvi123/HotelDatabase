package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.TableHelper;
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
	public String[] getPrimaryAttributes() {
		return new String[]{"roomCost_roomFloor", "roomCost_roomNumber"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "roomCost_roomFloor":
					ps.setInt(index, keyValuePairs.getInt("roomCost_roomFloor"));
					break;
				case "roomCost_roomNumber":
					ps.setInt(index, keyValuePairs.getInt("roomCost_roomNumber"));
					break;
				case "roomCost_cost":
					ps.setDouble(index, keyValuePairs.getDouble("roomCost_cost"));
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
