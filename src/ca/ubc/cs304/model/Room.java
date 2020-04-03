package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.Entity;
import ca.ubc.cs304.modelInterface.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Room extends Entity {

	private RoomHelper helper = null;

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
		ps.setInt(4, this.getNumberOfBeds());
		ps.setString(5, this.getHotelAddress());
	}
}
