package userInterface.updateSelected;

import controller.HotelController;
import model.tableHelpers.HotelHelper;
import model.tableHelpers.RoomHelper;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.INTERNAL;
import userInterface.chooseMenu.ChooseMenuHotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectedHotel extends JPanel {
    private JLabel updateHotelLabel;
    private JButton cancelButton;
    private JLabel hotelNameLabel;
    private JTextField hotelNameField;
    private JLabel capacityLabel;
    private JTextField capacityField;
    private JButton submitButton;

    public UpdateSelectedHotel(HotelController controller, JFrame frame, JSONObject whereKeys) {
        //construct components
        updateHotelLabel = new JLabel ("Update the selected HOTEL");
        cancelButton = new JButton ("Cancel");
        hotelNameLabel = new JLabel ("Hotel Name");
        hotelNameField = new JTextField (1);
        capacityLabel = new JLabel ("Capacity");
        capacityField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (updateHotelLabel);
        add (cancelButton);
        add (hotelNameLabel);
        add (hotelNameField);
        add (capacityLabel);
        add (capacityField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        updateHotelLabel.setBounds (115, 70, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        hotelNameLabel.setBounds (100, 150, 100, 25);
        hotelNameField.setBounds (200, 150, 100, 25);
        capacityLabel.setBounds (100, 185, 100, 25);
        capacityField.setBounds (200, 185, 100, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on clicking the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the fields for the hotel to update
//                String newHotelAddress = String.valueOf(hotelAddressField.getText());
                String newHotelName = String.valueOf(hotelNameField.getText());
                Integer newCapacity = Integer.parseInt(capacityField.getText());
                HotelHelper helper = new HotelHelper();
                JSONObject setKeys = new JSONObject();

                try {
                    setKeys.put("hotelName", newHotelName);
                    setKeys.put("capacity", newCapacity);
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
                frame.getContentPane().add(new ChooseMenuHotel(controller, frame));
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

