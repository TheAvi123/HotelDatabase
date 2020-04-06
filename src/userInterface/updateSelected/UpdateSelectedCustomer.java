package userInterface.updateSelected;

import controller.HotelController;
import model.tableHelpers.CustomerHelper;
import model.tableHelpers.RoomHelper;
import model.tables.Customer;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuCustomer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectedCustomer extends JPanel {
    private JLabel updateCustomerLabel;
    private JButton cancelButton;
    private JLabel customerNameLabel;
    private JTextField customerNameField;
    private JLabel customerAgeLabel;
    private JTextField customerAgeField;
    private JLabel paymentInformationLabel;
    private JTextField paymentInformationField;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberField;
    private JButton submitButton;

    public UpdateSelectedCustomer(HotelController controller, JFrame frame, JSONObject whereKeys) {

        //construct components
        updateCustomerLabel = new JLabel ("Update the selected Customer");
        cancelButton = new JButton ("Cancel");
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
                String newCustomerName = String.valueOf(customerNameField.getText());
                int newCustomerAge = Integer.parseInt(customerAgeField.getText());
                String newPaymentInformation = String.valueOf(paymentInformationField.getText());
                String newPhoneNumber = String.valueOf(phoneNumberField.getText());

                CustomerHelper helper = new CustomerHelper();
                JSONObject setKeys = new JSONObject();
                try {
                    setKeys.put("customerName", newCustomerName);
                    setKeys.put("customerAge", newCustomerAge);
                    setKeys.put("paymentInformation", newPaymentInformation);
                    setKeys.put("phoneNumber", newPhoneNumber);

                } catch (JSONException error) {
                    System.out.println(error.getMessage());
                    error.printStackTrace();
                }
                controller.updateTuples(helper, setKeys, whereKeys);
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add(new ChooseMenuCustomer(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

//    public static void main (String[] args) {
//
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add (new UpdateSelectedRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}

