package userInterface.showAll;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RoomCostTableModel extends AbstractTableModel {
    private ArrayList<JSONObject> roomCosts;
    private String[] columns ;

    public RoomCostTableModel(ArrayList<JSONObject> roomArr){
        super();
        roomCosts = roomArr ;
        columns = new String[]{"Room No.", "Room Floor", "Room Cost"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return roomCosts.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        JSONObject roomCost = this.roomCosts.get(row);
        switch(col) {
            case 0:
                try {
                    return roomCost.get("roomNumber");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 1:
                try {
                    return roomCost.get("roomFloor");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 2:
                try {
                    return roomCost.get("roomCost");
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