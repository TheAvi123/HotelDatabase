//package ca.ubc.cs304.model;
//
//import ca.ubc.cs304.modelInterface.Entity;
//import ca.ubc.cs304.modelInterface.TableHelper;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class RoomClassification extends Entity {
//
//    private RoomClassificationHelper helper = null;
//
//    private final String roomType;
//	private final String tierLevel;
//
//	public RoomClassification(String roomType, String tierLevel) {
//		this.roomType = roomType;
//		this.tierLevel = tierLevel;
//        this.helper = new RoomClassificationHelper();
//    }
//
//	public String getRoomType() {
//		return roomType;
//	}
//
//	public String getTierLevel() {
//		return tierLevel;
//	}
//
//
//    @Override
//    public int getAttributeCount() {
//        return 2;
//    }
//
//    @Override
//    public TableHelper getTableHelper() {
//        return this.helper;
//	}
//
//    @Override
//    public void setTupleParametersToStatement(PreparedStatement ps) throws SQLException {
//        ps.setString(1, this.getRoomType());
//        ps.setString(2, this.getTierLevel());
//    }
//}
