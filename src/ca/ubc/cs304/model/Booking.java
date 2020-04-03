package ca.ubc.cs304.model;

import ca.ubc.cs304.modelInterface.Entity;
import ca.ubc.cs304.modelInterface.TableHelper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Booking extends Entity {

	private BookingHelper helper = null;


	private final String bookingNumber;
	private final Date startDate;
	private final Date endDate;
	private final int roomFloor;
	private final int roomNumber;
	private final String customerID;

	public Booking(String bookingNumber, Date startDate, Date endDate, int roomFloor, int roomNumber, String customerID) {
		this.bookingNumber = bookingNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.roomFloor = roomFloor;
		this.roomNumber = roomNumber;
		this.customerID = customerID;
		this.helper = new BookingHelper();

	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return startDate;
	}

	public int getRoomFloor() {
		return roomFloor;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public String getCustomerID() {
		return customerID;
	}


	@Override
	public int getAttributeCount() {
		return 6;
	}

	@Override
	public TableHelper getTableHelper() {
		return this.helper;
	}

	@Override
	public void setTupleParametersToStatement(PreparedStatement ps) throws SQLException {
		ps.setString(1, this.getBookingNumber());
		ps.setDate(2, this.getStartDate());
		ps.setDate(3, this.getEndDate());
		ps.setInt(4, this.getRoomFloor());
		ps.setInt(5, this.getRoomNumber());
		ps.setString(6, this.getCustomerID());
	}
}
