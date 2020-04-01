package ca.ubc.cs304.model;

import oracle.sql.DATE;

/**
 * The intent for this class is to update/store information about a single branch
 *

 */

public class Room {
	private final int roomNumber;
	private final int roomFloor;
	private final char[] roomType;
	private final char[] needsCleaning;
	private final int numberOfBeds;
	private final char[] hotelAddress;

	public Room(int roomNumber, int roomFloor, char[] roomType, char[] needsCleaning, int numberOfBeds, char[] hotelAddress) {
		this.roomType = roomType;
		this.needsCleaning = needsCleaning;
		this.numberOfBeds = numberOfBeds;
		this.roomFloor = roomFloor;
		this.roomNumber = roomNumber;
		this.hotelAddress = hotelAddress;
	}

	public char[] getRoomType() {
		return roomType;
	}

	public char[] getNeedsCleaning() {
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

	public char[] getHotelAddress() {
		return hotelAddress;
	}
}
