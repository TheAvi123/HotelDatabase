package ca.ubc.cs304.model;

public class Cleaned_By {

	private final char[] janitorStaffID;
	private final int roomFloor;
	private final int roomNumber;

	public Cleaned_By(char[] janitorStaffID, int roomFloor, int roomNumber) {
		this.janitorStaffID = janitorStaffID;
		this.roomFloor = roomFloor;
		this.roomNumber = roomNumber;
	}

	public char[] getJanitorStaffID() {
		return janitorStaffID;
	}

	public int getRoomFloor() {
		return roomFloor;
	}

	public int getRoomNumber() {
		return roomNumber;
	}
}
