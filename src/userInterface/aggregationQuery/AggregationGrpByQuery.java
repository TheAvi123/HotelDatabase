package userInterface.aggregationQuery;


import controller.HotelController;
import database.DatabaseConnectionHandler;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuRoomCost;
import userInterface.showAll.HotelTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AggregationGrpByQuery extends JPanel {
    private DatabaseConnectionHandler dbHandler;
    TableModel model;
    ArrayList<JSONObject> arrayOfTuples;
    JTable table;

    private JLabel showRoomsLabel;
    private JLabel titleLabel;
    private JLabel selectLabel;
    private JCheckBox hotelAddressSelField;
    private JCheckBox hotelNameSelField;
    private JCheckBox capacitySelField;
    private JCheckBox aggrSelField;
    private JLabel grpByLabel;
    private JCheckBox hotelAddressGrpByField;
    private JCheckBox hotelNameGrpByField;
    private JCheckBox capacityGrpByField;
    private JButton submitButton;
    private JButton backButton;

    public AggregationGrpByQuery(HotelController controller) {
        dbHandler = new DatabaseConnectionHandler(controller);

        //construct components
        titleLabel = new JLabel ("NESTED AGGREGATION WITH GROUP BY");
        selectLabel = new JLabel ("SELECT");
        hotelAddressSelField = new JCheckBox ("Hotel Address");
        hotelNameSelField = new JCheckBox ("Hotel Name");
        capacitySelField = new JCheckBox ("Capacity");
        aggrSelField = new JCheckBox ("Max Capacity");
        grpByLabel = new JLabel ("GROUP BY");
        hotelAddressGrpByField = new JCheckBox ("Hotel Address");
        hotelNameGrpByField = new JCheckBox ("Hotel Name");
        capacityGrpByField = new JCheckBox ("Capacity");
        submitButton = new JButton ("Submit Query");
        backButton = new JButton ("Back to Menu");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
        add (selectLabel);
        add (hotelAddressSelField);
        add (hotelNameSelField);
        add (capacitySelField);
        add (aggrSelField);
        add (grpByLabel);
        add (hotelAddressGrpByField);
        add (hotelNameGrpByField);
        add (capacityGrpByField);
        add (submitButton);
        add (backButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (245, 70, 260, 25);
        selectLabel.setBounds (75, 135, 100, 25);
        hotelAddressSelField.setBounds (45, 165, 130, 30);
        hotelNameSelField.setBounds (45, 200, 100, 25);
        capacitySelField.setBounds (45, 230, 100, 25);
        aggrSelField.setBounds (45, 260, 103, 25);
        grpByLabel.setBounds (275, 135, 100, 25);
        hotelAddressGrpByField.setBounds (250, 165, 111, 25);
        hotelNameGrpByField.setBounds (250, 195, 100, 25);
        capacityGrpByField.setBounds (250, 225, 100, 25);
        submitButton.setBounds (505, 170, 115, 25);
        backButton.setBounds (505, 210, 115, 25);

        Boolean[] chosenSelect = new Boolean[4];
        Boolean[] chosenGrpBy = new Boolean[3];
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hotelAddressSelField.isSelected()) {
                    chosenSelect[0] = true;
                } else {
                    chosenSelect[0] = false;
                }
                if (hotelNameSelField.isSelected()) {
                    chosenSelect[1] = true;
                } else {
                    chosenSelect[1] = false;
                }
                if (capacitySelField.isSelected()) {
                    chosenSelect[2] = true;
                } else {
                    chosenSelect[2] = false;
                }
                if (aggrSelField.isSelected()) {
                    chosenSelect[3] = true;
                } else {
                    chosenSelect[3] = false;
                }

                if (hotelAddressGrpByField.isSelected()) {
                    chosenGrpBy[0] = true;
                } else {
                    chosenGrpBy[0] = false;
                }
                if (hotelNameGrpByField.isSelected()) {
                    chosenGrpBy[1] = true;
                } else {
                    chosenGrpBy[1] = false;
                }
                if (capacityGrpByField.isSelected()) {
                    chosenGrpBy[2] = true;
                } else {
                    chosenGrpBy[2] = false;
                }

                if (chosenSelect[3] == true && chosenSelect[0] == true && chosenGrpBy[0] == false) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Eggs are not supposed to be green.");                }
                if (chosenSelect[3] == true && chosenSelect[1] == true && chosenGrpBy[1] == false) {
                    JFrame frame = new JFrame();
                    System.out.println("error, if aggregate function present, all non-aggregate values must be in Group-By");
                }
                if (chosenSelect[3] == true && chosenSelect[2] == true && chosenGrpBy[2] == false) {
                    JFrame frame = new JFrame();
                    System.out.println("error, if aggregate function present, all non-aggregate values must be in Group-By");
                }

                arrayOfTuples = dbHandler.aggregationGroupHotel(chosenSelect, chosenGrpBy);
                model = new HotelTableModel(arrayOfTuples);

                //construct components
                showRoomsLabel = new JLabel ("Showing Aggregation Group By Query");
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
                showRoomsLabel.setBounds (55, 343, 130, 15);
                table.setBounds (55, 350, 300, 145);
                backButton.setBounds (55, 260, 100, 25);

            }
        });

        // on pressing backButton
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
}

