package userInterface.showAll;

import controller.HotelController;
import database.DatabaseConnectionHandler;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuRoomCost;
import userInterface.chooseMenu.ChooseMenuRoomTier;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowAllRoomTiers extends JPanel {
    private JLabel showRoomTiersLabel;
    private DatabaseConnectionHandler dbHandler;
    ArrayList<JSONObject> roomTiers;
    TableModel model;
    JTable table;

    private JButton backButton;

    public ShowAllRoomTiers(HotelController controller, JFrame frame) {

        dbHandler = new DatabaseConnectionHandler(controller);
        roomTiers = dbHandler.getTableTuples("roomTier");
        model = new DynamicTableModel(roomTiers);

        //construct components
        showRoomTiersLabel = new JLabel ("Showing All Room Tiers");
        table = new JTable(model);
        backButton = new JButton ("Back");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (showRoomTiersLabel);
        add (table);
        add (backButton);
        //set component bounds (only needed by Absolute Positioning)
        showRoomTiersLabel.setBounds (55, 55, 200, 15);
        table.setBounds (55, 95, 626, 300);
        backButton.setBounds (55, 400, 100, 25);

        // on clicking the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ChooseMenuRoomTier(controller, frame));
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

