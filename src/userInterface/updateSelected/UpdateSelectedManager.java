package userInterface.updateSelected;

import controller.HotelController;
import userInterface.chooseMenu.ChooseMenuManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectedManager extends JPanel {
    private JLabel updateLabel;
    private JButton cancelButton;
    private JLabel managerStaffIDLabel;
    private JTextField managerStaffIDField;
    private JLabel managerNameLabel;
    private JTextField managerNameField;
    private JLabel hotelAddressLabel;
    private JTextField hotelAddressField;
    private JButton submitButton;

    public UpdateSelectedManager(HotelController controller) {
        //construct preComponents

        //construct components
        updateLabel = new JLabel ("Update the selected MANAGER");
        cancelButton = new JButton ("Cancel");
        managerStaffIDLabel = new JLabel ("Manager Staff ID");
        managerStaffIDField = new JTextField (1);
        managerNameLabel = new JLabel ("Manager Name");
        managerNameField = new JTextField (1);
        hotelAddressLabel = new JLabel ("Hotel Address");
        hotelAddressField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (updateLabel);
        add (cancelButton);
        add (managerStaffIDLabel);
        add (managerStaffIDField);
        add (managerNameLabel);
        add (managerNameField);
        add (hotelAddressLabel);
        add (hotelAddressField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        updateLabel.setBounds (115, 70, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        managerStaffIDLabel.setBounds (100, 115, 117, 25);
        managerStaffIDField.setBounds (200, 115, 117, 25);
        managerNameLabel.setBounds (100, 150, 117, 25);
        managerNameField.setBounds (200, 150, 117, 25);
        hotelAddressLabel.setBounds (100, 185, 117, 25);
        hotelAddressField.setBounds (200, 185, 117, 25);
        hotelAddressLabel.setBounds (100, 220, 117, 25);
        hotelAddressField.setBounds (200, 220, 117, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on clicking the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the fields for the room to update
                String newManagerStaffID = String.valueOf(managerStaffIDField.getText());
                String newManagerName = String.valueOf(managerNameField.getText());
                String newHotelAddress = String.valueOf(hotelAddressField.getText());
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new ChooseMenuManager(controller));
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

