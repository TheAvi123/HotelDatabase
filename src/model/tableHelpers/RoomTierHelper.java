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
		return new String[]{"roomType", "tierLevel"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "roomType":
					ps.setString(index, keyValuePairs.getString("roomType"));
					break;
				case "tierLevel":
					ps.setString(index, keyValuePairs.getString("tierLevel"));
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
