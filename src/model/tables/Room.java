package model.tables;

import model.tableHelpers.RoomHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Room extends Table {

	private RoomHelper helper;

	private final int roomNumber;
	private final int roomFloor;
	private final String roomType;
	private final int numberOfBeds;
	private final String hotelAddress;

	public Room(int roomNumber, int roomFloor, String roomType, int numberOfBeds, String hotelAddress) {
		this.roomNumber = roomNumber;
		this.roomFloor = roomFloor;
		this.roomType = roomType;
		this.numberOfBeds = numberOfBeds;
		this.hotelAddress = hotelAddress;

		this.helper = new RoomHelper();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getRoomNumber() {
		return roomNumber;
	}

	public int getRoomFloor() {
		return roomFloor;
	}

	public String getRoomType() {
		return roomType;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public int getAttributeCount() {
		return 5;
	}

	@Override
	public TableHelper getTableHelper() {
		return this.helper;
	}

	@Override
	public void setTupleParametersToStatement(PreparedStatement ps) throws SQLException {
		ps.setInt(1, this.getRoomNumber());
		ps.setInt(2, this.getRoomFloor());
		ps.setString(3, this.getRoomType());
		if (this.getNumberOfBeds() == 0) {
			ps.setNull(4, java.sql.Types.INTEGER);
		} else {
			ps.setInt(4, this.getNumberOfBeds());
		}
		ps.setString(5, this.getHotelAddress());
	}
}
