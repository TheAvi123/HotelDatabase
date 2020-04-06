package userInterface.updateSelected;

import controller.HotelController;
import userInterface.chooseMenu.ChooseMenuHotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectedRoomCost extends JPanel {
    private JLabel updateRoomLabel;
    private JButton cancelButton;
    private JLabel roomNumberLabel;
    private JTextField roomNumberField;
    private JLabel roomFloorLabel;
    private JTextField roomFloorField;
    private JLabel roomCostLabel;
    private JTextField roomCostField;
    private JButton submitButton;

    public UpdateSelectedRoomCost(HotelController controller) {
        //construct components
        updateRoomLabel = new JLabel ("Update the selected ROOM COST");
        cancelButton = new JButton ("Cancel");
        roomNumberLabel = new JLabel ("Room Number");
        roomNumberField = new JTextField (1);
        roomFloorLabel = new JLabel ("Room Floor");
        roomFloorField = new JTextField (1);
        roomCostLabel = new JLabel ("Room Cost");
        roomCostField = new JTextField (1);
        submitButton = new JButton ("Submit");

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
        add (updateRoomLabel);
        add (cancelButton);
        add (roomNumberLabel);
        add (roomNumberField);
        add (roomFloorLabel);
        add (roomFloorField);
        add (roomCostLabel);
        add (roomCostField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        updateRoomLabel.setBounds (115, 70, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        roomNumberLabel.setBounds (100, 115, 100, 25);
        roomNumberField.setBounds (200, 115, 100, 25);
        roomFloorLabel.setBounds (100, 150, 100, 25);
        roomFloorField.setBounds (200, 150, 100, 25);
        roomCostLabel.setBounds (100, 185, 100, 25);
        roomCostField.setBounds (200, 185, 100, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on clicking the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the fields for the room to update
                int newRoomNumber = Integer.parseInt(roomNumberField.getText());
                int newRoomFloor = Integer.parseInt(roomFloorField.getText());
                Double newRoomCost = Double.valueOf(roomCostField.getText());
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new ChooseMenuHotel(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("Update Selected Room");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new UpdateSelectedRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}

