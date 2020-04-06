package userInterface.showAll;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CustomerTableModel extends AbstractTableModel {
    private ArrayList<JSONObject> customers;
    private String[] columns ;

    public CustomerTableModel(ArrayList<JSONObject> customerArr) {
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
        JSONObject customer = customers.get(row);
        switch(col) {
            case 0:
                try {
                    return customer.get("customerID");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 1:
                try {
                    return customer.get("customerName");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 2:
                try {
                    return customer.get("customerAge");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 3:
                try {
                    return customer.get("paymentInformation");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 4:
                try {
                    return customer.get("phoneNumber");
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