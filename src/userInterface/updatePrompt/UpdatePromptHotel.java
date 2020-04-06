package userInterface.updatePrompt;

import controller.HotelController;
import userInterface.chooseMenu.ChooseMenuRoomCost;
import userInterface.updateSelected.UpdateSelectedHotel;

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

    public UpdatePromptHotel(HotelController controller) {
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

                // open the update room screen
                JFrame frame = new JFrame ("Update Hotel" + hotelAddressToUpdate);
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new UpdateSelectedHotel(controller));
                frame.pack();
                frame.setVisible (true);
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
//        JFrame frame = new JFrame ("Update Prompt");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new UpdatePromptRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}
