package userInterface.showAll;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ServiceTableModel extends AbstractTableModel {
    private ArrayList<JSONObject> services;
    private String[] columns ;

    public ServiceTableModel(ArrayList<JSONObject> serviceArr){
        super();
        services = serviceArr ;
        columns = new String[]{"Service ID", "Min Tier Level", "Service Cost", "Hotel Address"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return services.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        JSONObject service = services.get(row);
        switch(col) {
            case 0:
                try {
                    return service.get("serviceID");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 1:
                try {
                    return service.get("minTierLevel");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 2:
                try {
                    return service.get("serviceCost");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 3:
                try {
                    return service.get("hotelAddress");
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