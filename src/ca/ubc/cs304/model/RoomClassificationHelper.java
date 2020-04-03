//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.TableHelper;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class RoomClassificationHelper extends TableHelper {
//
//	@Override
//	public String getTableName() {
//		return "roomClassification";
//	}
//
//	// TODO no idea what this table's primary key is, this table should not exist, should just be in Room
////	@Override
////	public String[] getPrimaryAttributes() {
////		return new String[]{"roomClassification_roomType"};
////	}
//
//
//	@Override
//	public void setStatementParameter(PreparedStatement ps, int index, String key, JSONObject keyValuePairs) throws SQLException {
//		try {
//			switch (key) {
//				case "roomClassification_roomType":
//					ps.setString(index, keyValuePairs.getString("roomClassification_roomType"));
//					break;
//				case "roomClassification_tierLevel":
//					ps.setString(index, keyValuePairs.getString("roomClassification_tierLevel"));
//					break;
//				default:
//					throw new Error("Invalid Key!");
//			}
//		} catch (JSONException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//	}
//}
