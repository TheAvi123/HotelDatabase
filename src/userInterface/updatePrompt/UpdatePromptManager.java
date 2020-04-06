package userInterface.updatePrompt;

import controller.HotelController;
import userInterface.chooseMenu.ChooseMenuManager;
import userInterface.updateSelected.UpdateSelectedManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePromptManager extends JPanel {
    private JLabel whichManagerLabel;
    private JButton cancelButton;
    private JLabel staffManagerIDLabel;
    private JTextField staffManagerIDField;
    private JButton submitButton;

    public UpdatePromptManager(HotelController controller) {
        //construct components
        whichManagerLabel = new JLabel ("Which MANAGER to update?");
        cancelButton = new JButton ("Cancel");
        staffManagerIDLabel = new JLabel ("Manager Staff ID");
        staffManagerIDField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (whichManagerLabel);
        add (cancelButton);
        add (staffManagerIDLabel);
        add (staffManagerIDField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        whichManagerLabel.setBounds (130, 75, 300, 30);
        cancelButton.setBounds (200, 210, 102, 25);
        staffManagerIDLabel.setBounds (100, 115, 110, 25);
        staffManagerIDField.setBounds (200, 115, 110, 25);
        submitButton.setBounds (100, 210, 100, 25);

        // on clicking the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the primary keys needed to find the particular room to update
                String managerStaffIDtoUpdate = String.valueOf(staffManagerIDField.getText());

                // open the update room screen
                JFrame frame = new JFrame ("Update Manager" + managerStaffIDtoUpdate);
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new UpdateSelectedManager(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ChooseMenuManager(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }
}
