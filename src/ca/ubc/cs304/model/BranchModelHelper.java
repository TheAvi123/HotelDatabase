package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.Table;
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
	public void printTupleInfo(Table tuple) {
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
	public void setPrimaryStatementParameters(PreparedStatement ps, JSONObject primaryKey) throws SQLException {
		try {
			ps.setInt(1, primaryKey.getInt("branch_id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
