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
	public String[] getPrimaryAttributes() {
		return new String[]{"customer_id"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "customer_id":
					ps.setString(index, keyValuePairs.getString("customer_id"));
					break;
				case "customer_name":
					ps.setString(index, keyValuePairs.getString("customer_name"));
					break;
				case "customer_age":
					ps.setInt(index, keyValuePairs.getInt("customer_age"));
					break;
				case "customer_paymentInformation":
					ps.setString(index, keyValuePairs.getString("customer_paymentInformation"));
					break;
				case "customer_phoneNumber":
					ps.setString(index, keyValuePairs.getString("customer_phoneNumber"));
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
