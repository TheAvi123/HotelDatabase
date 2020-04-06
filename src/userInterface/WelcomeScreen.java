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
    private JButton joinButton;
    private JButton divisonButton;

    public WelcomeScreen(HotelController controller, JFrame frame) {

        //construct preComponents
        String[] entityChooserItems = {"Query - Select 1 Below", "Booking", "Customer", "Employee", "Hotel", "Manager", "Room", "RoomCost", "RoomTier", "Service"};

        //construct components
        welcomeLabel = new JLabel ("WELCOME TO THE NOT-SO-GRAND BUDAPEST HOTEL DATABASE");
        chooseLabel = new JLabel ("Which entity do you want to work with right now?");
        entityChooser = new JComboBox (entityChooserItems);
        submitEntityButton = new JButton ("Submit");
        queryLabel = new JLabel ("Alternatively, choose a query:");
        joinButton = new JButton ("Join Query");
        divisonButton = new JButton ("Division Query");


        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (welcomeLabel);
        add (chooseLabel);
        add (entityChooser);
        add (submitEntityButton);
        add (queryLabel);
        add(joinButton);
        add(divisonButton);


        //set component bounds (only needed by Absolute Positioning)
        welcomeLabel.setBounds (175, 45, 375, 15);
        chooseLabel.setBounds (225, 85, 310, 30);
        entityChooser.setBounds (225, 120, 280, 25);
        submitEntityButton.setBounds (405, 165, 100, 25);
        queryLabel.setBounds (225, 225, 280, 25);
        joinButton.setBounds (225, 250, 280, 24);
        divisonButton.setBounds (225, 280, 280, 24);

        // on clicking submitEntryButton
        submitEntityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEntity = String.valueOf(entityChooser.getSelectedItem());
                if (selectedEntity.equals("Room")) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add (new ChooseMenuRoom(controller, frame));
                } else if (selectedEntity.equals("Booking")) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add (new ChooseMenuBooking(controller, frame));
                } else if (selectedEntity.equals("Customer")) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add (new ChooseMenuCustomer(controller, frame));
                } else if (selectedEntity.equals("Employee")) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add (new ChooseMenuEmployee(controller, frame));
                } else if (selectedEntity.equals("Hotel")) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add (new ChooseMenuHotel(controller, frame));
                } else if (selectedEntity.equals("Manager")) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add (new ChooseMenuManager(controller, frame));
                } else if (selectedEntity.equals("RoomCost")) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add (new ChooseMenuRoomCost(controller, frame));
                } else if (selectedEntity.equals("Service")) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add (new ChooseMenuService(controller, frame));
                } else if (selectedEntity.equals("RoomTier")) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add (new ChooseMenuRoomTier(controller, frame));
                }  else {
                    //Throw Error Here
                }
                frame.revalidate();
                frame.repaint();
            }
        });


        // on pressing cancelButton
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add (new JoinQuery(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        // on pressing cancelButton
        divisonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add (new DivisionQuery(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

//    public static void main (String[] args) {
//
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add (new WelcomeScreen(new HotelController()));
//        frame.pack();
//        frame.setVisible (true);
//    }
}

