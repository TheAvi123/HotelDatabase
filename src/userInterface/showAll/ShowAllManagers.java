package userInterface.showAll;

import controller.HotelController;
import database.DatabaseConnectionHandler;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuManager;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowAllManagers extends JPanel {
    private JLabel showManagersLabel;
    private DatabaseConnectionHandler dbHandler;
    ArrayList<JSONObject> managers;
    TableModel model;
    JTable table;

    private JButton backButton;

    public ShowAllManagers(HotelController controller) {

        dbHandler = new DatabaseConnectionHandler(controller);
        managers = dbHandler.getTableTuples("manager");
        model = new ManagerTableModel(managers);

        //construct components
        showManagersLabel = new JLabel ("Showing All Managers");
        table = new JTable(model);
        backButton = new JButton ("Back");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (showManagersLabel);
        add (table);
        add (backButton);
        //set component bounds (only needed by Absolute Positioning)
        showManagersLabel.setBounds (55, 55, 140, 15);
        table.setBounds (55, 95, 300, 145);
        backButton.setBounds (55, 260, 100, 25);

        // on clicking the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ChooseMenuManager(controller));
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

