package userInterface.showAll;

import model.Table;
import model.tables.Room;
import model.tables.Service;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ServiceTableModel extends AbstractTableModel {
    private ArrayList<Table> services;
    private String[] columns ;

    public ServiceTableModel(ArrayList<Table> serviceArr){
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
        Service service = (Service) services.get(row);
        switch(col) {
            case 0:
                return service.getServiceID();
            case 1:
                return service.getMinTierLevel();
            case 2:
                return service.getServiceCost();
            case 3:
                return service.getHotelAddress();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }
}