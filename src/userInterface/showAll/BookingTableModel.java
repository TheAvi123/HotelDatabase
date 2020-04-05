package userInterface.showAll;

import model.Table;
import model.tables.Booking;
import model.tables.Room;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BookingTableModel extends AbstractTableModel {
    private ArrayList<Table> bookings;
    private String[] columns ;

    public BookingTableModel(ArrayList<Table> bookingArr){
        super();
        bookings = bookingArr ;
        columns = new String[]{"Booking ID", "Start Date", "End Date", "Room Floor", "Room Number", "Customer ID"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return bookings.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        Booking booking = (Booking) bookings.get(row);
        switch(col) {
            case 0:
                return booking.getBookingID();
            case 1:
                return booking.getStartDate();
            case 2:
                return booking.getEndDate();
            case 3:
                return booking.getRoomFloor();
            case 4:
                return booking.getRoomNumber();
            case 5:
                return booking.getCustomerID();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }
}