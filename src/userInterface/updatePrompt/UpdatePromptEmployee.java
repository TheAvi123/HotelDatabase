package userInterface.updatePrompt;

import controller.HotelController;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuEmployee;
import userInterface.updateSelected.UpdateSelectedEmployee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePromptEmployee extends JPanel {
    private JLabel whichEmployeeLabel;
    private JButton cancelButton;
    private JLabel employeeStaffIDLabel;
    private JTextField employeeStaffIDField;
    private JButton submitButton;

    public UpdatePromptEmployee(HotelController controller, JFrame frame) {
        //construct components
        whichEmployeeLabel = new JLabel ("Which EMPLOYEE to update?");
        cancelButton = new JButton ("Cancel");
        employeeStaffIDLabel = new JLabel ("Employee Staff ID");
        employeeStaffIDField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (whichEmployeeLabel);
        add (cancelButton);
        add (employeeStaffIDLabel);
        add (employeeStaffIDField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        whichEmployeeLabel.setBounds (130, 75, 300, 30);
        cancelButton.setBounds (200, 210, 102, 25);
        employeeStaffIDLabel.setBounds (100, 115, 100, 25);
        employeeStaffIDField.setBounds (200, 115, 100, 25);
        submitButton.setBounds (100, 210, 100, 25);

        // on clicking the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the primary keys needed to find the particular room to update
                String employeeStaffIDToUpdate = String.valueOf(employeeStaffIDField.getText());

                JSONObject wherKeys = new JSONObject();
                try {
                    wherKeys.put("employeeStaffID", employeeStaffIDToUpdate);
                } catch (JSONException error) {
                    System.out.println(error.getMessage());
                    error.printStackTrace();
                }

                // open the update room screen

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new UpdateSelectedEmployee(controller, frame, wherKeys));
                frame.revalidate();
                frame.repaint();
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ChooseMenuEmployee(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}
