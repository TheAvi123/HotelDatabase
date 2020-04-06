package userInterface.showAll;

import controller.HotelController;
import database.DatabaseConnectionHandler;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuRoomCost;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowAllHotels extends JPanel {
    private JLabel showHotelsLabel;
    private DatabaseConnectionHandler dbHandler;
    ArrayList<JSONObject> hotels;
    TableModel model;
    JTable table;

    private JButton backButton;

    public ShowAllHotels(HotelController controller) {

        dbHandler = new DatabaseConnectionHandler(controller);
        hotels = dbHandler.getTableTuples("hotel");
        model = new HotelTableModel(hotels);

        //construct components
        showHotelsLabel = new JLabel ("Showing All Hotels");
        table = new JTable(model);
        backButton = new JButton ("Back");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (showHotelsLabel);
        add (table);
        add (backButton);
        //set component bounds (only needed by Absolute Positioning)
        showHotelsLabel.setBounds (55, 55, 130, 15);
        table.setBounds (55, 95, 300, 145);
        backButton.setBounds (55, 260, 100, 25);

        // on clicking the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ChooseMenuRoomCost(controller));
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

