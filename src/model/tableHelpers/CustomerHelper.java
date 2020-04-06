package model.tableHelpers;

import model.TableHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerHelper extends TableHelper {

	@Override
	public String getTableName() {
		return "customer";
	}

	@Override
	public String[] getAttributes() {
		return new String[] {"customerID", "customerName", "customerAge", "paymentInformation", "phoneNumber"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "customerID":
					ps.setString(index, keyValuePairs.getString("customerID"));
					break;
				case "customerName":
					ps.setString(index, keyValuePairs.getString("customerName"));
					break;
				case "customerAge":
					ps.setInt(index, keyValuePairs.getInt("customerAge"));
					break;
				case "paymentInformation":
					ps.setString(index, keyValuePairs.getString("paymentInformation"));
					break;
				case "phoneNumber":
					ps.setString(index, keyValuePairs.getString("phoneNumber"));
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
