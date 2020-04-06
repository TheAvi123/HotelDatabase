package userInterface.showAll;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BookingTableModel extends AbstractTableModel {
    private ArrayList<JSONObject> bookings;
    private String[] columns;

    public BookingTableModel(ArrayList<JSONObject> bookingArr){
        super();
        bookings = bookingArr;
        columns = new String[]{"Booking ID", "Start Date", "End Date", "Room Floor", "Room Number", "Customer ID"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length;
    }

    // Number of row of your table
    public int getRowCount() {
        return bookings.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        JSONObject booking = bookings.get(row);
        switch(col) {
            case 0:
                try {
                    return booking.get("bookingID");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 1:
                try {
                    return booking.get("startDate");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 2:
                try {
                    return booking.get("endDate");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 3:
                try {
                    return booking.get("roomNumber");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 4:
                try {
                    return booking.get("roomFloor");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 5:
                try {
                    return booking.get("customerID");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }
}