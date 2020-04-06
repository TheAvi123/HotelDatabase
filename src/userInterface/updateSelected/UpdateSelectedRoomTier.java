package userInterface.updateSelected;

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

public class UpdateSelectedRoomTier extends JPanel {
    private JLabel updateLabel;
    private JButton cancelButton;
    private JLabel tierLevelLabel;
    private JTextField tierLevelField;
    private JButton submitButton;

    public UpdateSelectedRoomTier(HotelController controller, JFrame frame, JSONObject whereKeys) {
        //construct preComponents

        //construct components
        updateLabel = new JLabel ("Update the selected ROOM TIER");
        cancelButton = new JButton ("Cancel");
        tierLevelLabel = new JLabel ("Tier Level");
        tierLevelField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (updateLabel);
        add (cancelButton);
        add (tierLevelLabel);
        add (tierLevelField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        updateLabel.setBounds (115, 70, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        tierLevelLabel.setBounds (100, 150, 107, 25);
        tierLevelField.setBounds (200, 150, 107, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on clicking the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the fields for the room to update
//                String newServiceID = String.valueOf(serviceIDField.getText());
                String newTierLevel = String.valueOf(tierLevelField.getText());

                RoomTierHelper helper = new RoomTierHelper();
                JSONObject setKeys = new JSONObject();
                try {
                    setKeys.put("tierLevel", newTierLevel);
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
                frame.getContentPane().add(new ChooseMenuRoomTier(controller, frame));
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

