//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Table;
//import ca.ubc.cs304.modelInterface.TableHelper;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class RoomHelper extends TableHelper {
//
//	@Override
//	public String getTableName() {
//		return "room";
//	}
//
//	@Override
//	public String[] getPrimaryAttributes() {
//		return new String[] {"room_number", "room_floor"};
//	}
//
//	@Override
//	public void printTupleInfo(Table tuple) {
//		Room roomTuple = (Room) tuple;
//		// simplified output formatting; truncation may occur
//		System.out.printf("%-10.10s", roomTuple.getRoomNumber());
//		System.out.printf("%-20.20s", roomTuple.getRoomFloor());
//		if (roomTuple.getRoomType() == null) {
//			System.out.printf("%-20.20s", " ");
//		} else {
//			System.out.printf("%-20.20s", roomTuple.getRoomType());
//		}
//		if (roomTuple.getNeedsCleaning() == null) {
//			System.out.printf("%-15.15s", " ");
//		} else {
//			System.out.printf("%-15.15s", roomTuple.getNeedsCleaning());
//		}
//		System.out.printf("%-15.15s", roomTuple.getNumberOfBeds());
//		System.out.printf("%-20.20s", roomTuple.getHotelAddress());
//		System.out.println();
//	}
//
//	@Override
//	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject primaryKey) throws SQLException {
//
//	}
//}
