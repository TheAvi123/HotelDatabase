package userInterface.insert;

import controller.HotelController;
import model.tables.Employee;
import userInterface.chooseMenu.ChooseMenuEmployee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertEmployee extends JPanel {
    private JLabel titleLabel;
    private JButton cancelButton;
    private JLabel employeeStaffIDLabel;
    private JTextField employeeStaffIDField;
    private JLabel employeeNameLabel;
    private JTextField employeeNameField;
    private JLabel payrollAccountNumberLabel;
    private JTextField payrollAccountNumberField;
    private JLabel salaryLabel;
    private JTextField salaryField;
    private JLabel workShiftLabel;
    private JTextField workShiftField;
    private JLabel managerStaffIDLabel;
    private JTextField managerStaffIDField;
    private JButton submitButton;


    public InsertEmployee(HotelController controller) {

        //construct components
        titleLabel = new JLabel ("Insert new EMPLOYEE");
        cancelButton = new JButton ("Cancel");
        employeeStaffIDLabel = new JLabel ("Employee Staff ID");
        employeeStaffIDField = new JTextField (1);
        employeeNameLabel = new JLabel ("Employee Name");
        employeeNameField = new JTextField (1);
        payrollAccountNumberLabel = new JLabel ("Payroll Information");
        payrollAccountNumberField = new JTextField (1);
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
        add (titleLabel);
        add (cancelButton);
        add (employeeStaffIDLabel);
        add (employeeStaffIDField);
        add (employeeNameLabel);
        add (employeeNameField);
        add (payrollAccountNumberLabel);
        add (payrollAccountNumberField);
        add (salaryLabel);
        add (workShiftLabel);
        add (workShiftField);
        add (managerStaffIDLabel);
        add (managerStaffIDField);
        add (submitButton);
        add (salaryField);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (145, 65, 300, 30);
        cancelButton.setBounds (255, 330, 102, 25);
        employeeStaffIDLabel.setBounds (100, 115, 130, 25);
        employeeStaffIDField.setBounds (255, 115, 130, 25);
        employeeNameLabel.setBounds (100, 150, 130, 25);
        employeeNameField.setBounds (255, 150, 130, 25);
        payrollAccountNumberLabel.setBounds (100, 185, 130, 25);
        payrollAccountNumberField.setBounds (255, 185, 130, 25);
        salaryLabel.setBounds (100, 220, 130, 25);
        workShiftLabel.setBounds (100, 255, 130, 25);
        workShiftField.setBounds (255, 255, 130, 25);
        managerStaffIDLabel.setBounds (100, 290, 130, 25);
        managerStaffIDField.setBounds (255, 290, 130, 25);
        submitButton.setBounds (100, 330, 100, 25);
        salaryField.setBounds (255, 220, 100, 25);

        // on pressing the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee(employeeStaffIDField.getText(),
                        employeeNameField.getText(),
                        Integer.parseInt(payrollAccountNumberField.getText()),
                        Integer.parseInt(salaryField.getText()),
                        workShiftField.getText(),
                        managerStaffIDField.getText());
                controller.insertTuple(employee);
            }
        });

        // on pressing cancelButton
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
//        JFrame frame = new JFrame ("Insert Room");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new InsertRoom(new HotelController()));
//        frame.pack();
//        frame.setVisible (true);
//    }
}
