package userInterface.insert;

import controller.HotelController;
import model.tables.Booking;
import userInterface.chooseMenu.ChooseMenuBooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertBooking extends JPanel {
    private JLabel titleLabel;
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


    public InsertBooking(HotelController controller) {

        //construct components
        titleLabel = new JLabel ("Insert new BOOKING");
        cancelButton = new JButton ("Cancel");
        bookingIDLabel = new JLabel ("Booking ID");
        bookingIDField = new JTextField (1);
        startDateLabel = new JLabel ("Start Date (yyyy-MM-dd)");
        startDateField = new JTextField (1);
        endDateLabel = new JLabel ("End Date (yyyy-MM-dd)");
        endDateField = new JTextField (1);
        roomFloorLabel = new JLabel ("Room Floor *");
        roomNumberLabel = new JLabel ("Room Number *");
        roomNumberField = new JTextField (1);
        customerIDLabel = new JLabel ("Customer ID");
        customerIDField = new JTextField (1);
        submitButton = new JButton ("Submit");
        roomFloorField = new JTextField (5);

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");
        startDateLabel.setToolTipText ("please follow the date format given");
        roomFloorLabel.setToolTipText ("MUST enter an integer");
        roomNumberLabel.setToolTipText ("MUST enter an integer");
        roomNumberField.setToolTipText ("integers only please");
        customerIDLabel.setToolTipText ("must enter the ID");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
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
        titleLabel.setBounds (145, 65, 300, 30);
        cancelButton.setBounds (255, 330, 102, 25);
        bookingIDLabel.setBounds (100, 115, 100, 25);
        bookingIDField.setBounds (255, 115, 100, 25);
        startDateLabel.setBounds (100, 150, 145, 25);
        startDateField.setBounds (255, 150, 100, 25);
        endDateLabel.setBounds (100, 185, 140, 25);
        endDateField.setBounds (255, 185, 100, 25);
        roomFloorLabel.setBounds (100, 220, 100, 25);
        roomNumberLabel.setBounds (100, 255, 100, 25);
        roomNumberField.setBounds (255, 255, 100, 25);
        customerIDLabel.setBounds (100, 290, 100, 25);
        customerIDField.setBounds (255, 290, 100, 25);
        submitButton.setBounds (100, 330, 100, 25);
        roomFloorField.setBounds (255, 220, 100, 25);

        // on pressing the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Booking bookingToInsert = new Booking(bookingIDField.getText(),
                        java.sql.Date.valueOf(startDateField.getText()),
                        java.sql.Date.valueOf(endDateField.getText()),
                        Integer.parseInt(roomFloorField.getText()),
                        Integer.parseInt(roomNumberField.getText()),
                        customerIDField.getText());
                controller.insertTuple(bookingToInsert);
            }
        });

        // on pressing cancelButton
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ChooseMenuBooking(controller));
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
