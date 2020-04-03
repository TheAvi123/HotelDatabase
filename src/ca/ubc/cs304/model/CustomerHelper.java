package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.TableHelper;
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
		return new String[]{"customer_customerID"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "customer_customerID":
					ps.setString(index, keyValuePairs.getString("customer_customerID"));
					break;
				case "customer_customerName":
					ps.setString(index, keyValuePairs.getString("customer_customerName"));
					break;
				case "customer_customerAge":
					ps.setInt(index, keyValuePairs.getInt("customer_customerAge"));
					break;
				case "customer_creditCardNumber":
					ps.setString(index, keyValuePairs.getString("customer_creditCardNumber"));
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
