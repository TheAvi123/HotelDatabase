package userInterface.insert;

import controller.HotelController;
import model.tables.RoomCost;
import userInterface.chooseMenu.ChooseMenuHotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertRoomCost extends JPanel {
    private JLabel titleLabel;
    private JButton cancelButton;
    private JLabel roomNumberLabel;
    private JTextField roomNumberField;
    private JLabel roomFloorLabel;
    private JTextField roomFloorField;
    private JLabel roomCostLabel;
    private JTextField roomCostField;
    private JButton submitButton;

    public InsertRoomCost(HotelController controller) {

        //construct components
        titleLabel = new JLabel ("Insert new ROOM COST");
        cancelButton = new JButton ("Cancel");
        roomNumberLabel = new JLabel ("Room Number");
        roomNumberField = new JTextField (1);
        roomFloorLabel = new JLabel ("Room Floor");
        roomFloorField = new JTextField (1);
        roomCostLabel = new JLabel ("Room Cost (in CAD)");
        roomCostField = new JTextField (1);
        submitButton = new JButton ("Select");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");
        roomNumberLabel.setToolTipText ("enter an integer");
        roomNumberField.setToolTipText ("only integers, please");
        roomFloorLabel.setToolTipText ("Integers only please");
        roomFloorField.setToolTipText ("integers only please");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
        add (cancelButton);
        add (roomNumberLabel);
        add (roomNumberField);
        add (roomFloorLabel);
        add (roomFloorField);
        add (roomCostLabel);
        add (roomCostField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (145, 65, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        roomNumberLabel.setBounds (100, 115, 100, 25);
        roomNumberField.setBounds (200, 115, 100, 25);
        roomFloorLabel.setBounds (100, 150, 100, 25);
        roomFloorField.setBounds (200, 150, 100, 25);
        roomCostLabel.setBounds (100, 185, 100, 25);
        roomCostField.setBounds (200, 185, 100, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on pressing the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomCost roomCostToInsert = new RoomCost(Integer.parseInt(roomNumberField.getText()),
                        Integer.parseInt(roomFloorField.getText()),
                        Double.valueOf(roomCostField.getText()));
                controller.insertTuple(roomCostToInsert);
            }
        });

        // on pressing cancelButton
        cancelButton.addActionListener(new ActionListener() {
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
//        JFrame frame = new JFrame ("Insert Room");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new InsertRoom(new HotelController()));
//        frame.pack();
//        frame.setVisible (true);
//    }
}
