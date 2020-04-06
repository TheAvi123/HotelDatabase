package userInterface.delete;

import controller.HotelController;
import model.tableHelpers.HotelHelper;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuRoomCost;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteHotel extends JPanel {
    private JLabel whichHotelLabel;
    private JButton cancelButton;
    private JLabel hotelAddressLabel;
    private JTextField hotelAddressField;
    private JButton submitButton;

    public DeleteHotel(HotelController controller) {
        //construct components
        whichHotelLabel = new JLabel ("Which HOTEL to delete?");
        cancelButton = new JButton ("Cancel");
        hotelAddressLabel = new JLabel ("Hotel Address");
        hotelAddressField = new JTextField (5);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");
        hotelAddressLabel.setToolTipText ("enter an integer");
        hotelAddressField.setToolTipText ("only integers, please");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (whichHotelLabel);
        add (cancelButton);
        add (hotelAddressLabel);
        add (hotelAddressField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        whichHotelLabel.setBounds (130, 75, 300, 30);
        cancelButton.setBounds (200, 210, 102, 25);
        hotelAddressLabel.setBounds (100, 115, 100, 25);
        hotelAddressField.setBounds (200, 115, 100, 25);
        submitButton.setBounds (100, 210, 100, 25);

        // on clicking the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the primary keys needed to find the particular hotel to delete
                String hotelAddressToDelete = String.valueOf(hotelAddressField.getText());
                HotelHelper helper = new HotelHelper();
                JSONObject primaryKey = new JSONObject();
                try {
                    primaryKey.put("hotelAddress", hotelAddressToDelete);
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
                frame.getContentPane().add (new ChooseMenuRoomCost(controller));
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
