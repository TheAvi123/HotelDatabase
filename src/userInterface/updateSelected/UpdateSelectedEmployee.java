package userInterface.updateSelected;

import controller.HotelController;
import model.tableHelpers.EmployeeHelper;
import model.tableHelpers.RoomHelper;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuEmployee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectedEmployee extends JPanel {
    private JLabel updateLabel;
    private JButton cancelButton;
    private JLabel employeeNameLabel;
    private JTextField employeeNameField;
    private JLabel payrollInformationLabel;
    private JTextField payrollInformationField;
    private JLabel salaryLabel;
    private JTextField salaryField;
    private JLabel workShiftLabel;
    private JTextField workShiftField;
    private JLabel managerStaffIDLabel;
    private JTextField managerStaffIDField;
    private JButton submitButton;

    public UpdateSelectedEmployee(HotelController controller, JFrame frame, JSONObject whereKeys) {

        //construct components
        updateLabel = new JLabel ("Update the selected EMPLOYEE");
        cancelButton = new JButton ("Cancel");
        employeeNameLabel = new JLabel ("Employee Name");
        employeeNameField = new JTextField (1);
        payrollInformationLabel = new JLabel ("Payroll Account");
        payrollInformationField = new JTextField (1);
        salaryLabel = new JLabel ("Salary");
        salaryField = new JTextField (1);
        workShiftLabel = new JLabel ("Work Shift");
        workShiftField = new JTextField (1);
        managerStaffIDLabel = new JLabel ("Manager Staff ID");
        managerStaffIDField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (updateLabel);
        add (cancelButton);
        add (employeeNameLabel);
        add (employeeNameField);
        add (payrollInformationLabel);
        add (payrollInformationField);
        add (salaryLabel);
        add (workShiftLabel);
        add (workShiftField);
        add (managerStaffIDLabel);
        add (managerStaffIDField);
        add (submitButton);
        add (salaryField);

        //set component bounds (only needed by Absolute Positioning)
        updateLabel.setBounds (145, 70, 300, 30);
        cancelButton.setBounds (265, 330, 102, 25);
        employeeNameLabel.setBounds (100, 150, 145, 25);
        employeeNameField.setBounds (265, 150, 100, 25);
        payrollInformationLabel.setBounds (100, 185, 135, 25);
        payrollInformationField.setBounds (265, 185, 100, 25);
        salaryLabel.setBounds (100, 220, 100, 25);
        workShiftLabel.setBounds (100, 255, 100, 25);
        workShiftField.setBounds (265, 255, 100, 25);
        managerStaffIDLabel.setBounds (100, 290, 100, 25);
        managerStaffIDField.setBounds (265, 290, 100, 25);
        submitButton.setBounds (100, 330, 100, 25);
        salaryField.setBounds (265, 220, 100, 25);

        // on clicking the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the fields for the booking to update
//                String newEmployeeStaffID = String.valueOf(employeeStaffIDField.getText());
                String newEmployeeName = String.valueOf(employeeNameField.getText());
                int newPayrollInformation = Integer.parseInt(payrollInformationField.getText());
                Double newSalary = Double.parseDouble(salaryField.getText());
                String newWorkShift = String.valueOf(workShiftField.getText());
                String newManagerStaffID = managerStaffIDField.getText();

                EmployeeHelper helper = new EmployeeHelper();
                JSONObject setKeys = new JSONObject();
                try {
                    setKeys.put("employeeName", newEmployeeName);
                    setKeys.put("payrollAccountNumber", newPayrollInformation);
                    setKeys.put("salary", newSalary);
                    setKeys.put("workShift", newWorkShift);
                    setKeys.put("managerStaffID", newManagerStaffID);
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
                frame.getContentPane().add(new ChooseMenuEmployee(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}

