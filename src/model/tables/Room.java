package model.tables;

import model.tableHelpers.RoomHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Room extends Table {

	private RoomHelper helper;

	private final int number;
	private final int floor;
	private final String type;
	private final int numberOfBeds;
	private final String hotelAddress;

	public Room(int number, int floor, String type, int numberOfBeds, String hotelAddress) {
		this.number = number;
		this.floor = floor;
		this.type = type;
		this.numberOfBeds = numberOfBeds;
		this.hotelAddress = hotelAddress;

		this.helper = new RoomHelper();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getNumber() {
		return number;
	}

	public int getFloor() {
		return floor;
	}

	public String getType() {
		return type;
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
		ps.setInt(1, this.getNumber());
		ps.setInt(2, this.getFloor());
		ps.setString(3, this.getType());
		if (this.getNumberOfBeds() == 0) {
			ps.setNull(4, java.sql.Types.INTEGER);
		} else {
			ps.setInt(4, this.getNumberOfBeds());
		}
		ps.setString(5, this.getHotelAddress());
	}
}
