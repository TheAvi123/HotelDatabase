package userInterface.delete;

import controller.HotelController;
import model.tableHelpers.BookingHelper;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuBooking;
import userInterface.chooseMenu.ChooseMenuRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBooking extends JPanel {
    private JLabel whichBookingLabel;
    private JButton cancelButton;
    private JLabel bookingIDLabel;
    private JTextField bookingIDField;
    private JButton submitButton;


    public DeleteBooking(HotelController controller) {
        //construct components
        whichBookingLabel = new JLabel ("Which BOOKING to delete?");
        cancelButton = new JButton ("Cancel");
        bookingIDLabel = new JLabel ("Booking ID");
        bookingIDField = new JTextField (1);
        submitButton = new JButton ("Submit");


        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (whichBookingLabel);
        add (cancelButton);
        add (bookingIDLabel);
        add (bookingIDField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        whichBookingLabel.setBounds (130, 75, 300, 30);
        cancelButton.setBounds (200, 210, 102, 25);
        bookingIDLabel.setBounds (100, 115, 100, 25);
        bookingIDField.setBounds (200, 115, 100, 25);
        submitButton.setBounds (100, 210, 100, 25);

        // on clicking the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the primary keys needed to find the particular room to update
                String bookingIDFieldText = bookingIDField.getText();
                BookingHelper helper = new BookingHelper();
                JSONObject primaryKey = new JSONObject();
                try {
                    primaryKey.put("bookingID", bookingIDFieldText);
                } catch (JSONException error) {
                    System.out.println(error.getMessage());
                    error.printStackTrace();
                }
                controller.deleteTuple(helper, primaryKey);
            }
        });

        // on clicking the cancel button
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
//        JFrame frame = new JFrame ("Delete Room");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new DeleteRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}
