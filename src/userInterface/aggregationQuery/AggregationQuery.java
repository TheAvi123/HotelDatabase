package userInterface.aggregationQuery;


import controller.HotelController;
import database.DatabaseConnectionHandler;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuHotel;
import userInterface.showAll.DynamicTableModel;

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

    private JLabel titleLabel;
    private JLabel selectLabel;
    private JCheckBox hotelAddressSelField;
    private JCheckBox hotelNameSelField;
    private JCheckBox capacitySelField;
    private JCheckBox aggrSelField;
    private JButton submitButton;
    private JButton backButton;

    public AggregationQuery(HotelController controller, JFrame frame) {
        dbHandler = new DatabaseConnectionHandler(controller);

        //construct components
        titleLabel = new JLabel ("AGGREGATION");
        selectLabel = new JLabel ("SELECT");
        hotelAddressSelField = new JCheckBox ("Hotel Address");
        hotelNameSelField = new JCheckBox ("Hotel Name");
        capacitySelField = new JCheckBox ("Capacity");
        aggrSelField = new JCheckBox ("Max Capacity");
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
        add (submitButton);
        add (backButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (245, 70, 260, 25);
        selectLabel.setBounds (75, 135, 100, 25);
        hotelAddressSelField.setBounds (45, 165, 130, 30);
        hotelNameSelField.setBounds (45, 200, 100, 25);
        capacitySelField.setBounds (45, 230, 100, 25);
        aggrSelField.setBounds (45, 260, 103, 25);
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

                if (!chosenSelect[3] || chosenSelect[0] || chosenSelect[1] || chosenSelect[2]) {
                    JOptionPane.showMessageDialog(new JFrame(), "must select the only aggregation possible", "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    arrayOfTuples = dbHandler.aggregationMaxHotel(chosenSelect);
                    model = new DynamicTableModel(arrayOfTuples);

                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new AggregationResult(model, controller, frame));
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });

        // on pressing backButton
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
}

