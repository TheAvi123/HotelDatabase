package model.tableHelpers;

import model.tables.BranchModel;
import model.Table;
import model.TableHelper;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BranchModelHelper extends TableHelper {

	@Override
	public String getTableName() {
		return "branch";
	}

	@Override
	public String[] getPrimaryAttributes() {
		return new String[] {"branch_id"};
	}

	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "branch_id":
					ps.setInt(index, keyValuePairs.getInt("branch_id"));
					break;
				case "branch_name":
					ps.setString(index, keyValuePairs.getString("branch_name"));
					break;
				case "branch_addr":
					ps.setString(index, keyValuePairs.getString("branch_addr"));
					break;
				case "branch_city":
					ps.setString(index, keyValuePairs.getString("branch_city"));
					break;
				case "branch_phone":
					ps.setInt(index, keyValuePairs.getInt("branch_phone"));
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
