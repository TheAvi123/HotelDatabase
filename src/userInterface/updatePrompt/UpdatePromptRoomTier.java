package userInterface.updatePrompt;

import controller.HotelController;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuRoomTier;
import userInterface.chooseMenu.ChooseMenuService;
import userInterface.updateSelected.UpdateSelectedRoomTier;
import userInterface.updateSelected.UpdateSelectedService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePromptRoomTier extends JPanel {
    private JLabel whichServiceLabel;
    private JButton cancelButton;
    private JLabel roomTypeLabel;
    private JTextField roomTypeField;
    private JButton submitButton;

    public UpdatePromptRoomTier(HotelController controller, JFrame frame) {
        //construct components
        whichServiceLabel = new JLabel ("Which ROOM TIER to update?");
        cancelButton = new JButton ("Cancel");
        roomTypeLabel = new JLabel ("Room Type");
        roomTypeField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (whichServiceLabel);
        add (cancelButton);
        add (roomTypeLabel);
        add (roomTypeField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        whichServiceLabel.setBounds (130, 75, 300, 30);
        cancelButton.setBounds (200, 210, 102, 25);
        roomTypeLabel.setBounds (100, 115, 100, 25);
        roomTypeField.setBounds (200, 115, 100, 25);
        submitButton.setBounds (100, 210, 100, 25);

        // on clicking the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the primary keys needed to find the particular room to update
                String roomTypeToUpdate = String.valueOf(roomTypeField.getText());

                JSONObject wherKeys = new JSONObject();
                try {
                    wherKeys.put("roomType", roomTypeToUpdate);
                } catch (JSONException error) {
                    System.out.println(error.getMessage());
                    error.printStackTrace();
                }

                // open the update room screen

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new UpdateSelectedRoomTier(controller, frame, wherKeys));
                frame.revalidate();
                frame.repaint();
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
}
