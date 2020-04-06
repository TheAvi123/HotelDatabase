package userInterface.showAll;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HotelTableModel extends AbstractTableModel {
    private ArrayList<JSONObject> hotels;
    private String[] columns ;

    public HotelTableModel(ArrayList<JSONObject> roomArr){
        super();
        hotels = roomArr ;
        columns = new String[]{"Hotel Address", "Hotel Name", "Capacity"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return hotels.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        JSONObject hotel = hotels.get(row);
        switch(col) {
            case 0:
                try {
                    return hotel.get("hotelAddress");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 1:
                try {
                    return hotel.get("hotelName");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 2:
                try {
                    return hotel.get("capacity");
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