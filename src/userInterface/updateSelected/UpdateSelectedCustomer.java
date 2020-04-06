package userInterface.updateSelected;

import controller.HotelController;
import userInterface.chooseMenu.ChooseMenuCustomer;
import userInterface.chooseMenu.ChooseMenuRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectedCustomer extends JPanel {
    private JLabel updateCustomerLabel;
    private JButton cancelButton;
    private JLabel customerIDLabel;
    private JTextField customerIDField;
    private JLabel customerNameLabel;
    private JTextField customerNameField;
    private JLabel customerAgeLabel;
    private JTextField customerAgeField;
    private JLabel paymentInformationLabel;
    private JTextField paymentInformationField;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberField;
    private JButton submitButton;

    public UpdateSelectedCustomer(HotelController controller) {

        //construct components
        updateCustomerLabel = new JLabel ("Update the selected Customer");
        cancelButton = new JButton ("Cancel");
        customerIDLabel = new JLabel ("Customer ID");
        customerIDField = new JTextField (1);
        customerNameLabel = new JLabel ("Customer Name");
        customerNameField = new JTextField (1);
        customerAgeLabel = new JLabel ("Customer Age");
        customerAgeField = new JTextField (1);
        paymentInformationLabel = new JLabel ("Payment Information");
        paymentInformationField = new JTextField (1);
        phoneNumberLabel = new JLabel ("Phone Number");
        phoneNumberField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");
        customerAgeField.setToolTipText ("Integers only please");
        customerAgeLabel.setToolTipText ("integers only please");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (updateCustomerLabel);
        add (cancelButton);
        add (customerIDLabel);
        add (customerIDField);
        add (customerNameLabel);
        add (customerNameField);
        add (customerAgeLabel);
        add (customerAgeField);
        add (paymentInformationLabel);
        add (paymentInformationField);
        add (phoneNumberLabel);
        add (phoneNumberField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        updateCustomerLabel.setBounds (115, 70, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        customerIDLabel.setBounds (100, 115, 100, 25);
        customerIDField.setBounds (200, 115, 100, 25);
        customerNameLabel.setBounds (100, 150, 100, 25);
        customerNameField.setBounds (200, 150, 100, 25);
        customerAgeLabel.setBounds (100, 185, 100, 25);
        customerAgeField.setBounds (200, 185, 100, 25);
        paymentInformationLabel.setBounds (100, 220, 100, 25);
        paymentInformationField.setBounds (200, 220, 100, 25);
        phoneNumberLabel.setBounds (100, 255, 100, 25);
        phoneNumberField.setBounds (200, 255, 100, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on clicking the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the fields for the room to update
                String newCustomerID = String.valueOf(customerIDField.getText());
                String newCustomerName = String.valueOf(customerNameField.getText());
                String newPaymentInformation = String.valueOf(paymentInformationField.getText());
                int newCustomerAge = Integer.parseInt(customerAgeField.getText());
                String newPhoneNumber = String.valueOf(phoneNumberField.getText());
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new ChooseMenuCustomer(controller));
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

