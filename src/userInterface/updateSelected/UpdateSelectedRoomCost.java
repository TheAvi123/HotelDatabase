package userInterface.updateSelected;

import controller.HotelController;
import model.tableHelpers.HotelHelper;
import model.tableHelpers.RoomCostHelper;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuRoom;
import userInterface.chooseMenu.ChooseMenuRoomCost;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectedRoomCost extends JPanel {
    private JLabel updateRoomLabel;
    private JButton cancelButton;
    private JLabel roomCostLabel;
    private JTextField roomCostField;
    private JButton submitButton;

    public UpdateSelectedRoomCost(HotelController controller, JFrame frame, JSONObject whereKeys) {
        //construct components
        updateRoomLabel = new JLabel ("Update the selected ROOM COST");
        cancelButton = new JButton ("Cancel");
        roomCostLabel = new JLabel ("Room Cost");
        roomCostField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (updateRoomLabel);
        add (cancelButton);
        add (roomCostLabel);
        add (roomCostField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        updateRoomLabel.setBounds (115, 70, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        roomCostLabel.setBounds (100, 185, 100, 25);
        roomCostField.setBounds (200, 185, 100, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on clicking the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the fields for the room to update
//                int newRoomNumber = Integer.parseInt(roomNumberField.getText());
//                int newRoomFloor = Integer.parseInt(roomFloorField.getText());
                Double newRoomCost = Double.valueOf(roomCostField.getText());


                RoomCostHelper helper = new RoomCostHelper();
                JSONObject setKeys = new JSONObject();

                try {
                    setKeys.put("roomCost", newRoomCost);
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
                frame.getContentPane().add(new ChooseMenuRoomCost(controller, frame));
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

