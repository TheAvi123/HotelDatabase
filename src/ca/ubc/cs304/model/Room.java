package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.Table;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Room extends Table {

	private final int roomNumber;
	private final int roomFloor;
	private final String roomType;
	private final String needsCleaning;
	private final int numberOfBeds;
	private final String hotelAddress;

	public Room(int roomNumber, int roomFloor, String roomType, String needsCleaning, int numberOfBeds, String hotelAddress) {
		this.roomNumber = roomNumber;
		this.roomFloor = roomFloor;
		this.roomType = roomType;
		this.needsCleaning = needsCleaning;
		this.numberOfBeds = numberOfBeds;
		this.hotelAddress = hotelAddress;
	}

	public String getRoomType() {
		return roomType;
	}

	public String getNeedsCleaning() {
		return needsCleaning;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public int getRoomFloor() {
		return roomFloor;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	@Override
	public int getAttributeCount() {
		return 6;
	}

	@Override
	public void setAllStatementParameters(PreparedStatement ps) throws SQLException {
		ps.setInt(1, this.getRoomNumber());
		ps.setInt(2, this.getRoomFloor());
		ps.setString(3, this.getRoomType());
		ps.setString(4, this.getNeedsCleaning());
		ps.setInt(5, this.getNumberOfBeds());
		ps.setString(6, this.getHotelAddress());
	}
}
