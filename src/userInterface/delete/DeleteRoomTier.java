package userInterface.delete;

import controller.HotelController;
import model.tableHelpers.RoomTierHelper;
import model.tableHelpers.ServiceHelper;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuRoomTier;
import userInterface.chooseMenu.ChooseMenuService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteRoomTier extends JPanel {
    private JLabel whichRoomTierLabel;
    private JButton cancelButton;
    private JLabel roomTypeLabel;
    private JTextField roomTypeField;
    private JButton submitButton;

    public DeleteRoomTier(HotelController controller, JFrame frame) {
        //construct components
        whichRoomTierLabel = new JLabel ("Which ROOM TIER to delete?");
        cancelButton = new JButton ("Cancel");
        roomTypeLabel = new JLabel ("Room Type");
        roomTypeField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (whichRoomTierLabel);
        add (cancelButton);
        add (roomTypeLabel);
        add (roomTypeField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        whichRoomTierLabel.setBounds (130, 75, 300, 30);
        cancelButton.setBounds (200, 210, 102, 25);
        roomTypeLabel.setBounds (100, 115, 100, 25);
        roomTypeField.setBounds (200, 115, 100, 25);
        submitButton.setBounds (100, 210, 100, 25);

        // on clicking the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the primary keys needed to find the particular hotel to delete
                String roomTypeToDelete = String.valueOf(roomTypeField.getText());
                RoomTierHelper helper = new RoomTierHelper();
                JSONObject primaryKey = new JSONObject();
                try {
                    primaryKey.put("roomType", roomTypeToDelete);
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

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ChooseMenuRoomTier(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

//    public static void main (String[] args) {
//
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add (new DeleteRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}
