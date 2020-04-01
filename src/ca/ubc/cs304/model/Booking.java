package ca.ubc.cs304.model;

import oracle.sql.DATE;

public class Booking {

	private final char[] bookingNumber;
	private final DATE startDate;
	private final DATE endDate;
	private final int roomFloor;
	private final int roomNumber;
	private final char[] customerID;

	public Booking(char[] bookingNumber, DATE startDate, DATE endDate, int roomFloor, int roomNumber, char[] customerID) {
		this.bookingNumber = bookingNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.roomFloor = roomFloor;
		this.roomNumber = roomNumber;
		this.customerID = customerID;
	}

	public char[] getBookingNumber() {
		return bookingNumber;
	}

	public DATE getStartDate() {
		return startDate;
	}

	public DATE getEndDate() {
		return startDate;
	}

	public int getRoomFloor() {
		return roomFloor;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public char[] getCustomerID() {
		return customerID;
	}
}
