package userInterface.divisionQuery;


import controller.HotelController;
import database.DatabaseConnectionHandler;

import org.json.JSONObject;
import userInterface.WelcomeScreen;
import userInterface.aggregationQuery.AggregationResult;
import userInterface.showAll.DynamicTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DivisionQuery extends JPanel {
    private DatabaseConnectionHandler dbHandler;
    TableModel model;
    ArrayList<JSONObject> arrayOfTuples;
    JTable table;

    private JLabel showRoomsLabel;
    private JLabel titleLabel;
    private JLabel table1Label;
    private JButton submitButton;
    private JButton backButton;
    private JLabel table2Label;
    private JComboBox table1Field;
    private JComboBox table2Field;


    public DivisionQuery(HotelController controller, JFrame frame) {
        dbHandler = new DatabaseConnectionHandler(controller);

        //construct preComponents
        String[] table1FieldItems = {"Booking", "Customer", "Employee", "Hotel", "Manager", "RoomCost", "Room", "Service"};
        String[] table2FieldItems = {"Booking", "Customer", "Employee", "Hotel", "Manager", "RoomCost", "Room", "Service"};

        //construct components
        titleLabel = new JLabel ("DIVISION QUERY");
        table1Label = new JLabel ("SELECT TABLE 1");
        submitButton = new JButton ("Submit Query");
        backButton = new JButton ("Back to Menu");
        table2Label = new JLabel ("SELECT TABLE 2");
        table1Field = new JComboBox (table1FieldItems);
        table2Field = new JComboBox (table2FieldItems);

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
        add (table1Label);
        add (submitButton);
        add (backButton);
        add (table2Label);
        add (table1Field);
        add (table2Field);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (325, 90, 175, 30);
        table1Label.setBounds (95, 155, 130, 20);
        submitButton.setBounds (520, 425, 120, 20);
        backButton.setBounds (520, 45, 115, 25);
        table2Label.setBounds (525, 155, 100, 25);
        table1Field.setBounds (70, 200, 145, 25);
        table2Field.setBounds (500, 200, 145, 25);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // works between customer and room
                String table1Selected = (String) table1Field.getSelectedItem();
                String table2Selected = (String) table2Field.getSelectedItem();
                if (!(table1Selected.equals("Customer") && table2Selected.equals("Room"))) {
                    JOptionPane.showMessageDialog(new JFrame(), "The query selected is: Return customers that have booked all rooms," +
                                    "thus, you must pick the first field as Customer and the second field as Room.", "Instruction",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    arrayOfTuples = dbHandler.division(table1Selected, table2Selected);
                    model = new DynamicTableModel(arrayOfTuples);


                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new DivisionResult(model, controller, frame));
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
                frame.getContentPane().add (new WelcomeScreen(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

//    public static void main (String[] args) {
//
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add (new SelectionQuery(controller, frame));
//        frame.pack();
//        frame.setVisible (true);
//    }
}

