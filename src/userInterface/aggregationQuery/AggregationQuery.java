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

public class AggregationQuery extends JPanel {
    private DatabaseConnectionHandler dbHandler;
    TableModel model;
    ArrayList<JSONObject> arrayOfTuples;
    JTable table;
    private JLabel showRoomsLabel;

    private JLabel titleLabel;
    private JLabel attrLabel;
    private JLabel aggregationLabel;
    private JComboBox attrField;
    private JComboBox aggregationField;
    private JButton submitButton;
    private JButton backButton;

    public AggregationQuery(HotelController controller) {
        dbHandler = new DatabaseConnectionHandler(controller);

        //construct preComponents
        String[] attrFieldItems = {"Hotel Address", "Hotel Name", "Capacity"};
        String[] aggregationFieldItems = {"Min", "Max", "Average", "Count"};

        //construct components
        titleLabel = new JLabel ("AGGREGATION QUERY");
        attrLabel = new JLabel ("ATTRIBUTE");
        aggregationLabel = new JLabel ("AGGREGATION");
        attrField = new JComboBox (attrFieldItems);
        aggregationField = new JComboBox (aggregationFieldItems);
        submitButton = new JButton ("Submit Query");
        backButton = new JButton ("Back to Menu");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
        add (attrLabel);
        add (aggregationLabel);
        add (attrField);
        add (aggregationField);
        add (submitButton);
        add (backButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (300, 95, 175, 30);
        attrLabel.setBounds (125, 150, 100, 25);
        aggregationLabel.setBounds (320, 150, 100, 25);
        attrField.setBounds (95, 190, 130, 25);
        aggregationField.setBounds (300, 190, 115, 25);
        submitButton.setBounds (515, 195, 120, 20);
        backButton.setBounds (590, 35, 115, 25);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object attributeSelected = attrField.getSelectedItem();
                Object aggregationSelected = aggregationField.getSelectedItem();
                Boolean[] maxCapacity = {false, false, true};
                arrayOfTuples = dbHandler.aggregationMaxHotel(maxCapacity);
                model = new HotelTableModel(arrayOfTuples);

                //construct components
                showRoomsLabel = new JLabel ("Showing Aggregation Query");
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

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("MyPanel");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new SelectionQuery(controller));
//        frame.pack();
//        frame.setVisible (true);
//    }
}

