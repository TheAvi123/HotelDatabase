package userInterface.chooseMenu;

import controller.HotelController;
import userInterface.WelcomeScreen;
import userInterface.delete.DeleteEmployee;
import userInterface.insert.InsertEmployee;
import userInterface.showAll.ShowAllEmployees;
import userInterface.updatePrompt.UpdatePromptEmployee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseMenuEmployee extends JPanel {
    private JLabel titleLabel;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton showButton;
    private JButton backButton;

    public ChooseMenuEmployee(HotelController controller) {
        //construct components
        titleLabel = new JLabel ("What do you want to do with EMPLOYEE?");
        insertButton = new JButton ("Insert Employee");
        deleteButton = new JButton ("Delete Employee");
        updateButton = new JButton ("Update Employee");
        showButton = new JButton ("Show Employees");
        backButton = new JButton ("Back to Welcome Screen");

        //set components properties
        insertButton.setToolTipText ("Inserts employee into SQL table");
        deleteButton.setToolTipText ("Removes employee from SQL table");
        updateButton.setToolTipText ("Updates employee in the SQL table");
        showButton.setToolTipText ("Shows all employees in the SQL table");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
        add (insertButton);
        add (deleteButton);
        add (updateButton);
        add (showButton);
        add (backButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (215, 65, 300, 30);
        insertButton.setBounds (305, 110, 170, 25);
        deleteButton.setBounds (305, 150, 170, 25);
        updateButton.setBounds (305, 190, 170, 25);
        showButton.setBounds (305, 230, 170, 25);
        backButton.setBounds (485, 345, 180, 25);

        // on clicking the insertButton
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Insert Employee");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new InsertEmployee(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the updateButton
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Update Prompt");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new UpdatePromptEmployee(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the deleteButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Delete Employee");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new DeleteEmployee(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the showButton
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Show all employees");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ShowAllEmployees(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new WelcomeScreen(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("Choose Menu");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new ca.ubc.cs304.ui.chooseMenu.ChooseMenuRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}
