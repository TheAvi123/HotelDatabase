package userInterface.insert;

import controller.HotelController;
import model.tables.Manager;
import userInterface.chooseMenu.ChooseMenuManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertManager extends JPanel {
    private JLabel titleLabel;
    private JButton cancelButton;
    private JLabel managerStaffIDLabel;
    private JTextField managerStaffIDField;
    private JLabel managerNameLabel;
    private JTextField managerNameField;
    private JLabel hotelAddressLabel;
    private JTextField hotelAddressField;
    private JButton submitButton;

    public InsertManager(HotelController controller) {

        //construct components
        titleLabel = new JLabel ("Insert new MANAGER");
        cancelButton = new JButton ("Cancel");
        managerStaffIDLabel = new JLabel ("Manager Staff ID");
        managerStaffIDField = new JTextField (1);
        managerNameLabel = new JLabel ("Manage Name");
        managerNameField = new JTextField (1);
        hotelAddressLabel = new JLabel ("Hotel Address");
        hotelAddressField = new JTextField (1);
        submitButton = new JButton ("Select");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
        add (cancelButton);
        add (managerStaffIDLabel);
        add (managerStaffIDField);
        add (managerNameLabel);
        add (managerNameField);
        add (hotelAddressLabel);
        add (hotelAddressField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (145, 65, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        managerStaffIDLabel.setBounds (100, 115, 130, 25);
        managerStaffIDField.setBounds (200, 115, 130, 25);
        managerNameLabel.setBounds (100, 150, 130, 25);
        managerNameField.setBounds (200, 150, 130, 25);
        hotelAddressLabel.setBounds (100, 185, 130, 25);
        hotelAddressField.setBounds (200, 185, 130, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on pressing the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager managerToInsert = new Manager(managerStaffIDField.getText(),
                        managerNameField.getText(),
                        hotelAddressField.getText());
                controller.insertTuple(managerToInsert);
            }
        });

        // on pressing cancelButton
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ChooseMenuManager(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }

}
