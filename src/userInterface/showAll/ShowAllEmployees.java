package userInterface.showAll;

import controller.HotelController;
import database.DatabaseConnectionHandler;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuService;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowAllEmployees extends JPanel {
    private JLabel showEmployeesLabel;
    private DatabaseConnectionHandler dbHandler;
    ArrayList<JSONObject> employees;
    TableModel model;
    JTable table;

    private JButton backButton;

    public ShowAllEmployees(HotelController controller) {

        dbHandler = new DatabaseConnectionHandler(controller);
        employees = dbHandler.getTableTuples("employee");
        model = new ManagerTableModel(employees);

        //construct components
        showEmployeesLabel = new JLabel ("Showing All Services");
        table = new JTable(model);
        backButton = new JButton ("Back");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (showEmployeesLabel);
        add (table);
        add (backButton);
        //set component bounds (only needed by Absolute Positioning)
        showEmployeesLabel.setBounds (55, 55, 140, 15);
        table.setBounds (55, 95, 300, 145);
        backButton.setBounds (55, 260, 100, 25);

        // on clicking the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ChooseMenuService(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("Show all rooms");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new ShowAllRooms());
//        frame.pack();
//        frame.setVisible (true);
//    }
}

