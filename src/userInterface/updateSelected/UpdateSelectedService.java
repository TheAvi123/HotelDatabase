package userInterface.updateSelected;

import controller.HotelController;
import userInterface.chooseMenu.ChooseMenuService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSelectedService extends JPanel {
    private JLabel updateLabel;
    private JButton cancelButton;
    private JLabel serviceIDLabel;
    private JTextField serviceIDField;
    private JLabel minTierLevelLabel;
    private JTextField minTierLevelField;
    private JLabel serviceCostLabel;
    private JTextField serviceCostField;
    private JLabel hotelAddressLabel;
    private JTextField hotelAddressField;
    private JButton submitButton;

    public UpdateSelectedService(HotelController controller) {
        //construct preComponents

        //construct components
        updateLabel = new JLabel ("Update the selected SERVICE");
        cancelButton = new JButton ("Cancel");
        serviceIDLabel = new JLabel ("Service ID");
        serviceIDField = new JTextField (1);
        minTierLevelLabel = new JLabel ("Min Tier Lvl");
        minTierLevelField = new JTextField (1);
        serviceCostLabel = new JLabel ("Service Cost");
        serviceCostField = new JTextField (1);
        hotelAddressLabel = new JLabel ("Hotel Address");
        hotelAddressField = new JTextField (1);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (updateLabel);
        add (cancelButton);
        add (serviceIDLabel);
        add (serviceIDField);
        add (minTierLevelLabel);
        add (minTierLevelField);
        add (serviceCostLabel);
        add (serviceCostField);
        add (hotelAddressLabel);
        add (hotelAddressField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        updateLabel.setBounds (115, 70, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        serviceIDLabel.setBounds (100, 115, 107, 25);
        serviceIDField.setBounds (200, 115, 107, 25);
        minTierLevelLabel.setBounds (100, 150, 107, 25);
        minTierLevelField.setBounds (200, 150, 107, 25);
        serviceCostLabel.setBounds (100, 185, 107, 25);
        serviceCostField.setBounds (200, 185, 107, 25);
        hotelAddressLabel.setBounds (100, 220, 107, 25);
        hotelAddressField.setBounds (200, 220, 107, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on clicking the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the fields for the room to update
                String newServiceID = String.valueOf(serviceIDField.getText());
                String newMinTierLevel = String.valueOf(minTierLevelField.getText());
                Double newServiceCost = Double.valueOf(serviceCostField.getText());
                String newHotelAddress = String.valueOf(hotelAddressField.getText());
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new ChooseMenuService(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("Update Selected Room");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new UpdateSelectedRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}

