package ca.ubc.cs304.model;

public class Manager {

	private final char[] staffID;
	private final char[] managerName;
	private final char[] hotelAddress;

	public Manager(char[] staffID, char[] managerName, char[] hotelAddress) {
		this.staffID = staffID;
		this.managerName = managerName;
		this.hotelAddress = hotelAddress;
	}

	public char[] getStaffID() {
		return staffID;
	}

	public char[] getManagerName() {
		return managerName;
	}

	public char[] getHotelAddress() {
		return hotelAddress;
	}
}
