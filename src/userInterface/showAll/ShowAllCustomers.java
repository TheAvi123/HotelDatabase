package userInterface.showAll;

import controller.HotelController;
import database.DatabaseConnectionHandler;
import model.Table;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuCustomer;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowAllCustomers extends JPanel {
    private JLabel showCustomersLabel;
    private DatabaseConnectionHandler dbHandler;
    ArrayList<JSONObject> customers;
    TableModel model;
    JTable table;

    private JButton backButton;

    public ShowAllCustomers(HotelController controller, JFrame frame) {

        dbHandler = new DatabaseConnectionHandler(controller);
        customers = dbHandler.getTableTuples("customer");
        model = new DynamicTableModel(customers);

        //construct components
        showCustomersLabel = new JLabel ("Showing All Customers");
        table = new JTable(model);
        backButton = new JButton ("Back");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (showCustomersLabel);
        add (table);
        add (backButton);
        //set component bounds (only needed by Absolute Positioning)
        showCustomersLabel.setBounds (55, 55, 130, 15);
        table.setBounds (55, 95, 626, 300);
        backButton.setBounds (55, 400, 100, 25);

        // on clicking the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ChooseMenuCustomer(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

//    public static void main (String[] args) {
//
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add (new ShowAllRooms());
//        frame.pack();
//        frame.setVisible (true);
//    }
}

