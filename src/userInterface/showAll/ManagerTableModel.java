package userInterface.showAll;

import model.Table;
import model.tables.Manager;
import model.tables.Service;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ManagerTableModel extends AbstractTableModel {
    private ArrayList<Table> services;
    private String[] columns ;

    public ManagerTableModel(ArrayList<Table> serviceArr){
        super();
        services = serviceArr ;
        columns = new String[]{"Manager Staff ID", "Manager Name", "Hotel Address"};
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
        Manager manager = (Manager) services.get(row);
        switch(col) {
            case 0:
                return manager.getManagerStaffID();
            case 1:
                return manager.getManagerName();
            case 2:
                return manager.getHotelAddress();
            default:
                return null;
        }
    }
    public String getColumnName(int col) {
        return columns[col] ;
    }
}