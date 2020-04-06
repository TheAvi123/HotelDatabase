package userInterface.chooseMenu;

import controller.HotelController;
import userInterface.WelcomeScreen;
import userInterface.delete.DeleteBooking;
import userInterface.insert.InsertBooking;
import userInterface.showAll.ShowAllBookings;
import userInterface.updatePrompt.UpdatePromptBooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseMenuBooking extends JPanel {
    private JLabel titleLabel;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton showButton;
    private JButton backButton;

    public ChooseMenuBooking(HotelController controller) {
        //construct components
        titleLabel = new JLabel ("What do you want to do with BOOKING?");
        insertButton = new JButton ("Insert Booking");
        deleteButton = new JButton ("Delete Booking");
        updateButton = new JButton ("Update Booking");
        showButton = new JButton ("Show Bookings");
        backButton = new JButton ("Back to Welcome Screen");

        //set components properties
        insertButton.setToolTipText ("Inserts booking into SQL table");
        deleteButton.setToolTipText ("Removes booking from SQL table");
        updateButton.setToolTipText ("Updates booking in the SQL table");
        showButton.setToolTipText ("Shows all bookings in the SQL table");

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
                JFrame frame = new JFrame ("Insert Booking");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new InsertBooking(controller));
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
                frame.getContentPane().add (new UpdatePromptBooking(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the deleteButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Delete Booking");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new DeleteBooking(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the showButton
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Show all bookings");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ShowAllBookings(controller));
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
