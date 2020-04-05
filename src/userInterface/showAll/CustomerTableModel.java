package userInterface.showAll;

import model.Table;
import model.tables.Customer;
import model.tables.Room;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CustomerTableModel extends AbstractTableModel {
    private ArrayList<Table> customers;
    private String[] columns ;

    public CustomerTableModel(ArrayList<Table> customerArr){
        super();
        customers = customerArr ;
        columns = new String[]{"Customer ID", "Customer Name", "Customer Age", "Payment Information", "Phone Number"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return customers.size();
    }

    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        Customer customer = (Customer) customers.get(row);
        switch(col) {
            case 0:
                return customer.getCustomerID();
            case 1:
                return customer.getCustomerName();
            case 2:
                return customer.getCustomerAge();
            case 3:
                return customer.getPaymentInformation();
            case 4:
                return customer.getPhoneNumber();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }
}