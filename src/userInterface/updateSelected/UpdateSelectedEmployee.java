package userInterface.updateSelected;

import controller.HotelController;
import userInterface.chooseMenu.ChooseMenuEmployee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectedEmployee extends JPanel {
    private JLabel updateLabel;
    private JButton cancelButton;
    private JLabel employeeStaffIDLabel;
    private JTextField employeeStaffIDField;
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

    public UpdateSelectedEmployee(HotelController controller) {

        //construct components
        updateLabel = new JLabel ("Update the selected EMPLOYEE");
        cancelButton = new JButton ("Cancel");
        employeeStaffIDLabel = new JLabel ("Employee Staff ID");
        employeeStaffIDField = new JTextField (1);
        employeeNameLabel = new JLabel ("Employee Name");
        employeeNameField = new JTextField (1);
        payrollInformationLabel = new JLabel ("Payroll Information");
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
        add (employeeStaffIDLabel);
        add (employeeStaffIDField);
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
        employeeStaffIDLabel.setBounds (100, 115, 100, 25);
        employeeStaffIDField.setBounds (265, 115, 100, 25);
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
                String newRoomNumber = String.valueOf(workShiftField.getText());
                int newRoomFloor = Integer.parseInt(salaryField.getText());
                String newEmployeeStaffID = String.valueOf(employeeStaffIDField.getText());
                String newManagerStaffID = managerStaffIDField.getText();
                String newEmployeeName = String.valueOf(employeeNameField.getText());
                int newPayrollInformation = Integer.parseInt(payrollInformationField.getText());
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new ChooseMenuEmployee(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }
}

