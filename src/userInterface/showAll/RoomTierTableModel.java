package userInterface.showAll;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RoomTierTableModel extends AbstractTableModel {
    private ArrayList<JSONObject> roomTiers;
    private String[] columns ;

    public RoomTierTableModel(ArrayList<JSONObject> roomArr){
        super();
        roomTiers = roomArr ;
        columns = new String[]{"Room Type", "Tier Level"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return roomTiers.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        JSONObject roomTier = this.roomTiers.get(row);
        switch(col) {
            case 0:
                try {
                    return roomTier.get("roomType");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 1:
                try {
                    return roomTier.get("tierLevel");
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