package userInterface.showAll;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RoomTableModel extends AbstractTableModel {
    private ArrayList<JSONObject> rooms;
    private String[] columns;

    public RoomTableModel(ArrayList<JSONObject> roomArr){
        super();
        rooms = roomArr;
        columns = new String[]{"Room No.", "Room Floor", "Room Type", "No. Of Beds", "Hotel Address"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length;
    }

    // Number of row of your table
    public int getRowCount() {
        return rooms.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        JSONObject room = rooms.get(row);
        switch(col) {
            case 0:
                try {
                    return room.get("roomNumber");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 1:
                try {
                    return room.get("roomFloor");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 2:
                try {
                    return room.get("roomType");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 3:
                try {
                    return room.get("numberOfBeds");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 4:
                try {
                    return room.get("hotelAddress");
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