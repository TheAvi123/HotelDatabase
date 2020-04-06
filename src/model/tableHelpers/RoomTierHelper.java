package model.tableHelpers;

import model.TableHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomTierHelper extends TableHelper {

	@Override
	public String getTableName() {
		return "roomTier";
	}

	@Override
	public String[] getAttributes() {
		return new String[]{"roomTier_roomType"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "roomTier_roomType":
					ps.setString(index, keyValuePairs.getString("roomTier_roomType"));
					break;
				case "roomTier_tierLevel":
					ps.setString(index, keyValuePairs.getString("roomTier_tierLevel"));
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
