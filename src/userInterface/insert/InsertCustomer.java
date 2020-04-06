package userInterface.insert;

import controller.HotelController;
import model.tables.Customer;
import userInterface.chooseMenu.ChooseMenuCustomer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertCustomer extends JPanel {
    private JLabel titleLabel;
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

    public InsertCustomer(HotelController controller) {

        //construct components
        titleLabel = new JLabel ("Insert new Customer");
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

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
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
        titleLabel.setBounds (145, 65, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        customerIDLabel.setBounds (100, 115, 150, 25);
        customerIDField.setBounds (200, 115, 150, 25);
        customerNameLabel.setBounds (100, 150, 150, 25);
        customerNameField.setBounds (200, 150, 150, 25);
        customerAgeLabel.setBounds (100, 185, 150, 25);
        customerAgeField.setBounds (200, 185, 150, 25);
        paymentInformationLabel.setBounds (100, 220, 150, 25);
        paymentInformationField.setBounds (200, 220, 150, 25);
        phoneNumberLabel.setBounds (100, 255, 150, 25);
        phoneNumberField.setBounds (200, 255, 150, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on pressing the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customerToInsert = new Customer(customerIDField.getText(),
                        customerNameField.getText(),
                        Integer.parseInt(customerAgeField.getText()),
                        paymentInformationField.getText(),
                        phoneNumberField.getText());
                controller.insertTuple(customerToInsert);
            }
        });

        // on pressing cancelButton
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ChooseMenuCustomer(controller));
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
