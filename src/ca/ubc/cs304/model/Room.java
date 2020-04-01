package ca.ubc.cs304.model;

public class Room {

	private final int roomNumber;
	private final int roomFloor;
	private final String roomType;
	private final String needsCleaning;
	private final int numberOfBeds;
	private final String hotelAddress;

	public Room(int roomNumber, int roomFloor, String roomType, String needsCleaning, int numberOfBeds, String hotelAddress) {
		this.roomType = roomType;
		this.needsCleaning = needsCleaning;
		this.numberOfBeds = numberOfBeds;
		this.roomFloor = roomFloor;
		this.roomNumber = roomNumber;
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
}
