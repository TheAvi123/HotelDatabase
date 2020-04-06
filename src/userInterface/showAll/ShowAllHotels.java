package userInterface.showAll;

import controller.HotelController;
import database.DatabaseConnectionHandler;
import model.Table;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuHotel;

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

    public ShowAllHotels(HotelController controller, JFrame frame) {

        dbHandler = new DatabaseConnectionHandler(controller);
        hotels = dbHandler.getTableTuples("hotel");
        model = new DynamicTableModel(hotels);

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
        table.setBounds (55, 95, 626, 300);
        backButton.setBounds (55, 400, 100, 25);

        // on clicking the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ChooseMenuHotel(controller, frame));
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

