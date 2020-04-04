package model.tableHelpers;

import model.TableHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingHelper extends TableHelper {

	@Override
	public String getTableName() {
		return "booking";
	}

	@Override
	public String[] getAttributes() {
		return new String[] {"bookingID",
				"startDate",
				"endDate",
				"roomFloor",
				"roomNumber",
				"customerID"};
	}

	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "bookingID":
					ps.setString(index, keyValuePairs.getString("bookingID"));
					break;
				case "startDate":
					String dateStart = keyValuePairs.getString("startDate");
					SimpleDateFormat simpleStartDate = new SimpleDateFormat("yyyy-MM-dd");
					Date startDate = simpleStartDate.parse(dateStart);
					java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
					ps.setDate(index, sqlStartDate);
					break;
				case "endDate":
					String dateEnd = keyValuePairs.getString("endDate");
					SimpleDateFormat simpleEndDate = new SimpleDateFormat("yyyy-MM-dd");
					Date endDate = simpleEndDate.parse(dateEnd);
					java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
					ps.setDate(index, sqlEndDate);
					break;
				case "roomFloor":
					ps.setInt(index, keyValuePairs.getInt("roomFloor"));
					break;
				case "roomNumber":
					ps.setInt(index, keyValuePairs.getInt("roomNumber"));
					break;
				case "customerID":
					ps.setString(index, keyValuePairs.getString("customerID"));
					break;
				default:
					throw new Error("Invalid Key!");
			}
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
