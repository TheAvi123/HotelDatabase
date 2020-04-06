package userInterface.showAll;

import controller.HotelController;
import database.DatabaseConnectionHandler;
import model.Table;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuService;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowAllServices extends JPanel {
    private JLabel showServicesLabel;
    private DatabaseConnectionHandler dbHandler;
    ArrayList<JSONObject> services;
    TableModel model;
    JTable table;

    private JButton backButton;

    public ShowAllServices(HotelController controller, JFrame frame) {

        dbHandler = new DatabaseConnectionHandler(controller);
        services = dbHandler.getTableTuples("service");
        model = new DynamicTableModel(services);


        //construct components
        showServicesLabel = new JLabel ("Showing All Services");
        table = new JTable(model);
        backButton = new JButton ("Back");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (showServicesLabel);
        add (table);
        add (backButton);
        //set component bounds (only needed by Absolute Positioning)
        showServicesLabel.setBounds (55, 55, 130, 15);
        table.setBounds (55, 95, 626, 300);
        backButton.setBounds (55, 400, 100, 25);

        // on clicking the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ChooseMenuService(controller, frame));
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

