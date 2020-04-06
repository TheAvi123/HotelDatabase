package userInterface;

import controller.HotelController;
import userInterface.chooseMenu.*;
import userInterface.divisionQuery.DivisionQuery;
import userInterface.joinQuery.JoinQuery;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WelcomeScreen extends JPanel {
    private JLabel welcomeLabel;
    private JLabel chooseLabel;
    private JComboBox entityChooser;
    private JButton submitEntityButton;
    private JLabel queryLabel;
    private JComboBox queryChooserField;

    public WelcomeScreen(HotelController controller) {
        //construct preComponents
        String[] entityChooserItems = {"Query - Select 1 Below", "Booking", "Customer", "Employee", "Hotel", "Manager", "Room", "RoomCost", "Service"};
        String[] queryChooserFieldItems = {"Join Query", "Division Query"};

        //construct components
        welcomeLabel = new JLabel ("WELCOME TO THE NOT-SO-GRAND BUDAPEST HOTEL DATABASE");
        chooseLabel = new JLabel ("Which entity do you want to work with right now?");
        entityChooser = new JComboBox (entityChooserItems);
        submitEntityButton = new JButton ("Submit");
        queryLabel = new JLabel ("Alternatively, choose a query:");
        queryChooserField = new JComboBox (queryChooserFieldItems);

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (welcomeLabel);
        add (chooseLabel);
        add (entityChooser);
        add (submitEntityButton);
        add (queryLabel);
        add (queryChooserField);

        //set component bounds (only needed by Absolute Positioning)
        welcomeLabel.setBounds (175, 45, 375, 15);
        chooseLabel.setBounds (225, 85, 310, 30);
        entityChooser.setBounds (225, 120, 280, 25);
        submitEntityButton.setBounds (405, 225, 100, 25);
        queryLabel.setBounds (225, 155, 280, 25);
        queryChooserField.setBounds (225, 180, 280, 24);

        // on clicking submitEntryButton
        submitEntityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEntity = String.valueOf(entityChooser.getSelectedItem());
                if (selectedEntity.equals("Room")) {
                    JFrame frame = new JFrame ("Choose Menu for Room");
                    frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add (new ChooseMenuRoom(controller));
                    frame.pack();
                    frame.setVisible (true);
                } else if (selectedEntity.equals("Booking")) {
                    JFrame frame = new JFrame ("Choose Menu for Booking");
                    frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add (new ChooseMenuBooking(controller));
                    frame.pack();
                    frame.setVisible (true);
                } else if (selectedEntity.equals("Customer")) {
                    JFrame frame = new JFrame ("Choose Menu for Customer");
                    frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add (new ChooseMenuCustomer(controller));
                    frame.pack();
                    frame.setVisible (true);
                } else if (selectedEntity.equals("Employee")) {
                    JFrame frame = new JFrame ("Choose Menu for Employee");
                    frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add (new ChooseMenuEmployee(controller));
                    frame.pack();
                    frame.setVisible (true);
                } else if (selectedEntity.equals("Hotel")) {
                    JFrame frame = new JFrame ("Choose Menu for Hotel");
                    frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add (new ChooseMenuHotel(controller));
                    frame.pack();
                    frame.setVisible (true);
                } else if (selectedEntity.equals("Manager")) {
                    JFrame frame = new JFrame ("Choose Menu for Manager");
                    frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add (new ChooseMenuManager(controller));
                    frame.pack();
                    frame.setVisible (true);
                } else if (selectedEntity.equals("RoomCost")) {
                    JFrame frame = new JFrame ("Choose Menu for RoomCost");
                    frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add (new ChooseMenuRoomCost(controller));
                    frame.pack();
                    frame.setVisible (true);
                } else if (selectedEntity.equals("Service")) {
                    JFrame frame = new JFrame ("Choose Menu for Service");
                    frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add (new ChooseMenuService(controller));
                    frame.pack();
                    frame.setVisible (true);
                }

                String selectedQuery = String.valueOf(queryChooserField.getSelectedItem());
                if (selectedQuery.equals("Join Query")) {
                    JFrame frame = new JFrame ("Join Query");
                    frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add (new JoinQuery(controller));
                    frame.pack();
                    frame.setVisible (true);
                } else if (selectedQuery.equals("Division Query")) {
                    JFrame frame = new JFrame("Division Query");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(new DivisionQuery(controller));
                    frame.pack();
                    frame.setVisible(true);
                    setVisible(false);
                }
            }
        });
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("Welcome Screen");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new WelcomeScreen(new HotelController()));
//        frame.pack();
//        frame.setVisible (true);
//    }
}

