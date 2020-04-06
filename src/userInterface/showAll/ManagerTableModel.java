package userInterface.showAll;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ManagerTableModel extends AbstractTableModel {
    private ArrayList<JSONObject> managers;
    private String[] columns ;

    public ManagerTableModel(ArrayList<JSONObject> managerArr){
        super();
        managers = managerArr ;
        columns = new String[]{"Manager Staff ID", "Manager Name", "Hotel Address"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return managers.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        JSONObject managers = this.managers.get(row);
        switch(col) {
            case 0:
                try {
                    return managers.get("managerStaffID");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 1:
                try {
                    return managers.get("managerName");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 2:
                try {
                    return managers.get("hotelAddress");
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