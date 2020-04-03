package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JPanel {
    private JLabel welcomeLabel;
    private JLabel chooseLabel;
    private JComboBox entityChooser;
    private JButton submitEntityButton;

    public WelcomeScreen() {
        //construct preComponents
        String[] entityChooserItems = {"Booking", "Customer", "Employee", "Hotel", "Manager", "Room", "RoomClassification", "RoomCost", "Service"};

        //construct components
        welcomeLabel = new JLabel ("WELCOME TO THE NOT-SO-GRAND BUDAPEST HOTEL DATABASE");
        chooseLabel = new JLabel ("Which entity do you want to work with right now?");
        entityChooser = new JComboBox (entityChooserItems);
        submitEntityButton = new JButton ("Submit");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (welcomeLabel);
        add (chooseLabel);
        add (entityChooser);
        add (submitEntityButton);

        //set component bounds (only needed by Absolute Positioning)
        welcomeLabel.setBounds (175, 45, 375, 15);
        chooseLabel.setBounds (225, 85, 310, 30);
        entityChooser.setBounds (225, 120, 280, 25);
        submitEntityButton.setBounds (405, 160, 100, 25);

        // on clicking submitEntryButton
        submitEntityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEntity = String.valueOf(entityChooser.getSelectedItem());
                // TODO: add functionality for other entities once they're implemented in the backend
                if (selectedEntity == "Room") {
                    JFrame frame = new JFrame ("Choose Menu for Room");
                    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add (new userInterface.chooseMenu.ChooseMenuRoom());
                    frame.pack();
                    frame.setVisible (true);
                }
            }
        });
    }
}

