package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.Entity;
import ca.ubc.cs304.modelInterface.TableHelper;
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
	public void printTupleInfo(Entity tuple) {
		BranchModel branchTuple = (BranchModel) tuple;
		// simplified output formatting; truncation may occur
		System.out.printf("%-10.10s", branchTuple.getId());
		System.out.printf("%-20.20s", branchTuple.getName());
		if (branchTuple.getAddress() == null) {
			System.out.printf("%-20.20s", " ");
		} else {
			System.out.printf("%-20.20s", branchTuple.getAddress());
		}
		System.out.printf("%-15.15s", branchTuple.getCity());
		if (branchTuple.getPhoneNumber() == 0) {
			System.out.printf("%-15.15s", " ");
		} else {
			System.out.printf("%-15.15s", branchTuple.getPhoneNumber());
		}
		System.out.println();
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
