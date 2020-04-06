package userInterface.updatePrompt;

import controller.HotelController;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuHotel;
import userInterface.chooseMenu.ChooseMenuRoom;
import userInterface.updateSelected.UpdateSelectedHotel;
import userInterface.updateSelected.UpdateSelectedRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePromptHotel extends JPanel {
    private JLabel whichHotelLabel;
    private JButton cancelButton;
    private JLabel hotelAddressLabel;
    private JTextField hotelAddressField;
    private JButton submitButton;

    public UpdatePromptHotel(HotelController controller, JFrame frame) {
        //construct components
        whichHotelLabel = new JLabel ("Which HOTEL to update?");
        cancelButton = new JButton ("Cancel");
        hotelAddressLabel = new JLabel ("Hotel Address");
        hotelAddressField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

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
                // saving the primary keys needed to find the particular room to update
                String hotelAddressToUpdate = String.valueOf(hotelAddressField.getText());

                JSONObject wherKeys = new JSONObject();
                try {
                    wherKeys.put("hotelAddress", hotelAddressToUpdate);
                } catch (JSONException error) {
                    System.out.println(error.getMessage());
                    error.printStackTrace();
                }

                // open the update room screen

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new UpdateSelectedHotel(controller, frame, wherKeys));
                frame.revalidate();
                frame.repaint();
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ChooseMenuHotel(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

//    public static void main (String[] args) {
//
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add (new UpdatePromptRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}
