package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.TableHelper;
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
	public String[] getPrimaryAttributes() {
		return new String[] {"booking_bookingNumber"};
	}


	@Override
	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
		try {
			switch (key) {
				case "booking_bookingNumber":
					ps.setString(index, keyValuePairs.getString("booking_bookingNumber"));
					break;
				case "booking_startDate":
					String dateStart = keyValuePairs.getString("booking_startDate");
					SimpleDateFormat simpleStartDate = new SimpleDateFormat("yyyy-MM-dd");
					Date startDate = simpleStartDate.parse(dateStart);
					java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
					ps.setDate(index, sqlStartDate);
					break;
				case "booking_endDate":
					String dateEnd = keyValuePairs.getString("booking_startDate");
					SimpleDateFormat simpleEndDate = new SimpleDateFormat("yyyy-MM-dd");
					Date endDate = simpleEndDate.parse(dateEnd);
					java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
					ps.setDate(index, sqlEndDate);
					break;
				case "booking_roomFloor":
					ps.setInt(index, keyValuePairs.getInt("booking_roomFloor"));
					break;
				case "booking_roomNumber":
					ps.setInt(index, keyValuePairs.getInt("booking_roomNumber"));
					break;
				case "booking_customerID":
					ps.setString(index, keyValuePairs.getString("booking_customerID"));
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
