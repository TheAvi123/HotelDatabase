package userInterface.updatePrompt;

import controller.HotelController;
import userInterface.chooseMenu.ChooseMenuRoom;
import userInterface.updateSelected.UpdateSelectedRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePromptRoom extends JPanel {
    private JLabel whichRoomLabel;
    private JButton cancelButton;
    private JLabel roomNumberLabel;
    private JTextField roomNumberField;
    private JLabel roomFloorLabel;
    private JTextField roomFloorField;
    private JButton submitButton;

    public UpdatePromptRoom(HotelController controller) {
        //construct components
        whichRoomLabel = new JLabel ("Which ROOM to update?");
        cancelButton = new JButton ("Cancel");
        roomNumberLabel = new JLabel ("Room Number");
        roomNumberField = new JTextField (1);
        roomFloorLabel = new JLabel ("Room Floor");
        roomFloorField = new JTextField (1);
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
        add (whichRoomLabel);
        add (cancelButton);
        add (roomNumberLabel);
        add (roomNumberField);
        add (roomFloorLabel);
        add (roomFloorField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        whichRoomLabel.setBounds (130, 75, 300, 30);
        cancelButton.setBounds (200, 210, 102, 25);
        roomNumberLabel.setBounds (100, 115, 100, 25);
        roomNumberField.setBounds (200, 115, 100, 25);
        roomFloorLabel.setBounds (100, 150, 100, 25);
        roomFloorField.setBounds (200, 150, 100, 25);
        submitButton.setBounds (100, 210, 100, 25);

        // on clicking the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the primary keys needed to find the particular room to update
                int roomNumberToUpdate = Integer.parseInt(roomNumberField.getText());
                int roomFloorToUpdate = Integer.parseInt(roomFloorField.getText());

                // open the update room screen
                JFrame frame = new JFrame ("Update Room" + roomNumberToUpdate + " on floor " + roomFloorToUpdate);
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new UpdateSelectedRoom(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ChooseMenuRoom(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("Update Prompt");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new UpdatePromptRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}
