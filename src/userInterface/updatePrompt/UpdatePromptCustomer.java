package userInterface.updatePrompt;

import controller.HotelController;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuCustomer;
import userInterface.chooseMenu.ChooseMenuEmployee;
import userInterface.updateSelected.UpdateSelectedCustomer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePromptCustomer extends JPanel {
    private JLabel whichCustomerLabel;
    private JButton cancelButton;
    private JLabel customerIDLabel;
    private JTextField customerIDField;
    private JButton submitButton;

    public UpdatePromptCustomer(HotelController controller, JFrame frame) {
        //construct components
        whichCustomerLabel = new JLabel ("Which CUSTOMER to update?");
        cancelButton = new JButton ("Cancel");
        customerIDLabel = new JLabel ("Customer ID");
        customerIDField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (whichCustomerLabel);
        add (cancelButton);
        add (customerIDLabel);
        add (customerIDField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        whichCustomerLabel.setBounds (130, 75, 300, 30);
        cancelButton.setBounds (240, 150, 102, 25);
        customerIDLabel.setBounds (100, 115, 100, 25);
        customerIDField.setBounds (200, 115, 145, 25);
        submitButton.setBounds (100, 150, 100, 25);

        // on clicking the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the primary keys needed to find the particular room to update
                String customerIDToUpdate = customerIDField.getText();

                JSONObject wherKeys = new JSONObject();
                try {
                    wherKeys.put("customerID", customerIDToUpdate);
                } catch (JSONException error) {
                    System.out.println(error.getMessage());
                    error.printStackTrace();
                }

                // open the update room screen

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new UpdateSelectedCustomer(controller, frame, wherKeys));
                frame.revalidate();
                frame.repaint();
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ChooseMenuCustomer(controller, frame));
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
