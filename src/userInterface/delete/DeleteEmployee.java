package userInterface.delete;

import controller.HotelController;
import model.tableHelpers.EmployeeHelper;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuEmployee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteEmployee extends JPanel {
    private JLabel whichEmployeeLabel;
    private JButton cancelButton;
    private JLabel employeeStaffIDLabel;
    private JTextField employeeStaffIDField;
    private JButton submitButton;

    public DeleteEmployee(HotelController controller) {
        //construct components
        whichEmployeeLabel = new JLabel ("Which EMPLOYEE to delete?");
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
                // saving the primary keys needed to find the particular hotel to delete
                String employeeIDToDelete = String.valueOf(employeeStaffIDField.getText());
                EmployeeHelper helper = new EmployeeHelper();
                JSONObject primaryKey = new JSONObject();
                try {
                    primaryKey.put("employeeStaffID", employeeIDToDelete);
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
                frame.getContentPane().add (new ChooseMenuEmployee(controller));
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
