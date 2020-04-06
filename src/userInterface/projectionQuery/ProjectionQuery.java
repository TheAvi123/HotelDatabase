package userInterface.projectionQuery;


import controller.HotelController;
import database.DatabaseConnectionHandler;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuHotel;
import userInterface.showAll.HotelTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProjectionQuery extends JPanel {
    private DatabaseConnectionHandler dbHandler;
    TableModel model;
    ArrayList<JSONObject> arrayOfTuples;
    JTable table;
    private JLabel showRoomsLabel;

    private JLabel titleLabel;
    private JLabel attrLabel;
    private JButton submitButton;
    private JButton backButton;
    private JCheckBox hotelAddressCB;
    private JCheckBox hotelNameCB;
    private JCheckBox capacityCB;

    public ProjectionQuery(HotelController controller) {
        dbHandler = new DatabaseConnectionHandler(controller);

        //construct components
        titleLabel = new JLabel ("PROJECTION QUERY");
        attrLabel = new JLabel ("SELECT ATTRIBUTES");
        submitButton = new JButton ("Submit Query");
        backButton = new JButton ("Back to Menu");
        hotelAddressCB = new JCheckBox ("Hotel Address");
        hotelNameCB = new JCheckBox ("Hotel Name");
        capacityCB = new JCheckBox ("Capacity");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
        add (attrLabel);
        add (submitButton);
        add (backButton);
        add (hotelAddressCB);
        add (hotelNameCB);
        add (capacityCB);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (300, 95, 175, 30);
        attrLabel.setBounds (95, 155, 130, 20);
        submitButton.setBounds (515, 195, 120, 20);
        backButton.setBounds (520, 45, 115, 25);
        hotelAddressCB.setBounds (90, 185, 140, 25);
        hotelNameCB.setBounds (90, 210, 140, 25);
        capacityCB.setBounds (90, 235, 100, 25);

        Boolean[] attributes = new Boolean[3];
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hotelAddressCB.isSelected()) {
                    attributes[0] = true;
                } else {
                    attributes[0] = false;
                }
                if (hotelNameCB.isSelected()) {
                    attributes[1] = true;
                } else {
                    attributes[1] = false;
                }
                if (capacityCB.isSelected()) {
                    attributes[2] = true;
                } else {
                    attributes[2] = false;
                }
                arrayOfTuples = dbHandler.projectionHotel(attributes);
                model = new HotelTableModel(arrayOfTuples);

                //construct components
                showRoomsLabel = new JLabel ("Showing Projection Query");
                table = new JTable(model);
                backButton = new JButton ("Back");

                //adjust size and set layout
                setPreferredSize (new Dimension (736, 523));
                setLayout (null);

                //add components
                add (showRoomsLabel);
                add (table);
                add (backButton);
                //set component bounds (only needed by Absolute Positioning)
                showRoomsLabel.setBounds (97, 343, 130, 15);
                table.setBounds (100, 355, 300, 145);
                backButton.setBounds (55, 260, 100, 25);

            }
        });

        // on pressing backButton
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ChooseMenuHotel(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("MyPanel");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new SelectionQuery(controller));
//        frame.pack();
//        frame.setVisible (true);
//    }
}

