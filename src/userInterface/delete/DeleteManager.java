package userInterface.delete;

import controller.HotelController;
import model.tableHelpers.ManagerHelper;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteManager extends JPanel {
    private JLabel whichManagerLabel;
    private JButton cancelButton;
    private JLabel managerStaffIDLabel;
    private JTextField managerStaffIDField;
    private JButton submitButton;

    public DeleteManager(HotelController controller) {
        //construct components
        whichManagerLabel = new JLabel ("Which MANAGER to delete?");
        cancelButton = new JButton ("Cancel");
        managerStaffIDLabel = new JLabel ("Service ID");
        managerStaffIDField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (whichManagerLabel);
        add (cancelButton);
        add (managerStaffIDLabel);
        add (managerStaffIDField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        whichManagerLabel.setBounds (130, 75, 300, 30);
        cancelButton.setBounds (200, 210, 102, 25);
        managerStaffIDLabel.setBounds (100, 115, 130, 25);
        managerStaffIDField.setBounds (200, 115, 130, 25);
        submitButton.setBounds (100, 210, 100, 25);

        // on clicking the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the primary keys needed to find the particular hotel to delete
                String managerStaffIDToDelete = String.valueOf(managerStaffIDField.getText());
                ManagerHelper helper = new ManagerHelper();
                JSONObject primaryKey = new JSONObject();
                try {
                    primaryKey.put("managerStaffID", managerStaffIDToDelete);
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
                frame.getContentPane().add (new ChooseMenuManager(controller));
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
