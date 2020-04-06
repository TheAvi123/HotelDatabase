package userInterface.delete;

import controller.HotelController;
import model.tableHelpers.CustomerHelper;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuCustomer;
import userInterface.chooseMenu.ChooseMenuRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCustomer extends JPanel {
    private JLabel whichCustomerLabel;
    private JButton cancelButton;
    private JLabel customerIDLabel;
    private JTextField customerIDField;
    private JButton submitButton;


    public DeleteCustomer(HotelController controller) {
        //construct components
        whichCustomerLabel = new JLabel ("Which CUSTOMER to delete?");
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
        cancelButton.setBounds (200, 210, 102, 25);
        customerIDLabel.setBounds (100, 115, 100, 25);
        customerIDField.setBounds (200, 115, 100, 25);
        submitButton.setBounds (100, 210, 100, 25);

        // on clicking the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the primary keys needed to find the particular room to update
                String bookingIDFieldText = customerIDField.getText();
                CustomerHelper helper = new CustomerHelper();
                JSONObject primaryKey = new JSONObject();
                try {
                    primaryKey.put("customerID", bookingIDFieldText);
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
                frame.getContentPane().add (new ChooseMenuCustomer(controller));
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
