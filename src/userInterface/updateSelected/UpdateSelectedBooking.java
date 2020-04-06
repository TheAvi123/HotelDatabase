package userInterface.updateSelected;

import controller.HotelController;
import userInterface.chooseMenu.ChooseMenuBooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class UpdateSelectedBooking extends JPanel {
    private JLabel updateBookingLabel;
    private JButton cancelButton;
    private JLabel bookingIDLabel;
    private JTextField bookingIDField;
    private JLabel startDateLabel;
    private JTextField startDateField;
    private JLabel endDateLabel;
    private JTextField endDateField;
    private JLabel roomFloorLabel;
    private JLabel roomNumberLabel;
    private JTextField roomNumberField;
    private JLabel customerIDLabel;
    private JTextField customerIDField;
    private JButton submitButton;
    private JTextField roomFloorField;

    public UpdateSelectedBooking(HotelController controller) {

        //construct components
        updateBookingLabel = new JLabel ("Update the selected BOOKING");
        cancelButton = new JButton ("Cancel");
        bookingIDLabel = new JLabel ("Booking ID");
        bookingIDField = new JTextField (1);
        startDateLabel = new JLabel ("Start Date (yyyy-MM-dd)");
        startDateField = new JTextField (1);
        endDateLabel = new JLabel ("End Date (yyyy-MM-dd)");
        endDateField = new JTextField (1);
        roomFloorLabel = new JLabel ("Room Floor");
        roomNumberLabel = new JLabel ("Room Number");
        roomNumberField = new JTextField (1);
        customerIDLabel = new JLabel ("Customer ID");
        customerIDField = new JTextField (1);
        submitButton = new JButton ("Submit");
        roomFloorField = new JTextField (1);


        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");
        startDateField.setToolTipText ("please follow date format");
        endDateField.setToolTipText ("please follow date format");
        roomNumberLabel.setToolTipText ("integer only please");
        roomNumberField.setToolTipText ("integers only please");
        roomFloorField.setToolTipText ("integers only");


        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (updateBookingLabel);
        add (cancelButton);
        add (bookingIDLabel);
        add (bookingIDField);
        add (startDateLabel);
        add (startDateField);
        add (endDateLabel);
        add (endDateField);
        add (roomFloorLabel);
        add (roomNumberLabel);
        add (roomNumberField);
        add (customerIDLabel);
        add (customerIDField);
        add (submitButton);
        add (roomFloorField);

        //set component bounds (only needed by Absolute Positioning)
        updateBookingLabel.setBounds (145, 70, 300, 30);
        cancelButton.setBounds (265, 330, 102, 25);
        bookingIDLabel.setBounds (100, 115, 100, 25);
        bookingIDField.setBounds (265, 115, 100, 25);
        startDateLabel.setBounds (100, 150, 145, 25);
        startDateField.setBounds (265, 150, 100, 25);
        endDateLabel.setBounds (100, 185, 135, 25);
        endDateField.setBounds (265, 185, 100, 25);
        roomFloorLabel.setBounds (100, 220, 100, 25);
        roomNumberLabel.setBounds (100, 255, 100, 25);
        roomNumberField.setBounds (265, 255, 100, 25);
        customerIDLabel.setBounds (100, 290, 100, 25);
        customerIDField.setBounds (265, 290, 100, 25);
        submitButton.setBounds (100, 330, 100, 25);
        roomFloorField.setBounds (265, 220, 100, 25);

        // on clicking the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the fields for the booking to update
                int newRoomNumber = Integer.parseInt(roomNumberField.getText());
                int newRoomFloor = Integer.parseInt(roomFloorField.getText());
                String newBookingID = String.valueOf(bookingIDField.getText());
                String newCustomerID = customerIDField.getText();
                Date newStartDate = Date.valueOf(startDateField.getText());
                Date newEndDate = Date.valueOf(endDateField.getText());
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new ChooseMenuBooking(controller));
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

