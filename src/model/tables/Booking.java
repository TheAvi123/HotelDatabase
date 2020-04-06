package model.tables;

import model.tableHelpers.BookingHelper;
import model.Table;
import model.TableHelper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Booking extends Table {

	private BookingHelper helper;

	private final String bookingID;
	private final Date startDate;
	private final Date endDate;
	private final int roomFloor;
	private final int roomNumber;
	private final String customerID;

	public Booking(String bookingID, Date startDate, Date endDate, int roomFloor, int roomNumber, String customerID) {
		this.bookingID = bookingID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.roomFloor = roomFloor;
		this.roomNumber = roomNumber;
		this.customerID = customerID;

		this.helper = new BookingHelper();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String getBookingID() {
		return bookingID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
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

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

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
		ps.setString(1, this.getBookingID());
		ps.setDate(2, this.getStartDate());
		ps.setDate(3, this.getEndDate());
		ps.setInt(4, this.getRoomFloor());
		ps.setInt(5, this.getRoomNumber());
		ps.setString(6, this.getCustomerID());
	}
}
