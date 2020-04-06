package userInterface.updateSelected;

import controller.HotelController;
import model.TableHelper;
import model.tableHelpers.RoomHelper;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectedRoom extends JPanel {
    private JLabel updateRoomLabel;
    private JButton cancelButton;
    private JLabel roomTypeLabel;
    private JTextField roomTypeField;
    private JLabel numberOfBedsLabel;
    private JTextField numberOfBedsField;
    private JLabel hotelAddressLabel;
    private JTextField hotelAddressField;
    private JButton submitButton;

    public UpdateSelectedRoom(HotelController controller, JFrame frame, JSONObject whereKeys) {
        //construct preComponents

        //construct components
        updateRoomLabel = new JLabel ("Update the selected ROOM");
        cancelButton = new JButton ("Cancel");
        roomTypeLabel = new JLabel ("Room Type");
        roomTypeField = new JTextField (1);
        numberOfBedsLabel = new JLabel ("No. of Beds");
        numberOfBedsField = new JTextField (1);
        hotelAddressLabel = new JLabel ("Hotel Address");
        hotelAddressField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");
        numberOfBedsLabel.setToolTipText ("integer only please");
        numberOfBedsField.setToolTipText ("integers only please");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (updateRoomLabel);
        add (cancelButton);
        add (roomTypeLabel);
        add (roomTypeField);
        add (numberOfBedsLabel);
        add (numberOfBedsField);
        add (hotelAddressLabel);
        add (hotelAddressField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        updateRoomLabel.setBounds (115, 70, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        roomTypeLabel.setBounds (100, 185, 100, 25);
        roomTypeField.setBounds (200, 185, 100, 25);
        numberOfBedsLabel.setBounds (100, 220, 100, 25);
        numberOfBedsField.setBounds (200, 220, 100, 25);
        hotelAddressLabel.setBounds (100, 255, 100, 25);
        hotelAddressField.setBounds (200, 255, 100, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on clicking the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the fields for the room to update
                //int newRoomNumber = Integer.parseInt(roomNumberField.getText());
                //int newRoomFloor = Integer.parseInt(roomFloorField.getText());
                String newRoomType = String.valueOf(roomTypeField.getText());
                int newNumberOfBeds = Integer.parseInt(numberOfBedsField.getText());
                String newHotelAddress = String.valueOf(hotelAddressField.getText());
                RoomHelper helper = new RoomHelper();
                JSONObject setKeys = new JSONObject();
                try {
                    setKeys.put("roomType", newRoomType);
                    setKeys.put("numberOfBeds", newNumberOfBeds);
                    setKeys.put("hotelAddress", newHotelAddress);
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
                frame.getContentPane().add(new ChooseMenuRoom(controller, frame));
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

