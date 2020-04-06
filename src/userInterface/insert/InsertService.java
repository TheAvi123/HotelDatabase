package userInterface.insert;

import controller.HotelController;
import model.tables.Service;
import userInterface.chooseMenu.ChooseMenuRoomCost;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertService extends JPanel {
    private JLabel titleLabel;
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

    public InsertService(HotelController controller) {

        //construct components
        titleLabel = new JLabel ("Insert new SERVICE");
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
        add (titleLabel);
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
        titleLabel.setBounds (145, 65, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        serviceIDLabel.setBounds (100, 115, 105, 25);
        serviceIDField.setBounds (200, 115, 105, 25);
        minTierLevelLabel.setBounds (100, 150, 105, 25);
        minTierLevelField.setBounds (200, 150, 105, 25);
        serviceCostLabel.setBounds (100, 185, 105, 25);
        serviceCostField.setBounds (200, 185, 105, 25);
        hotelAddressLabel.setBounds (100, 220, 105, 25);
        hotelAddressField.setBounds (200, 220, 105, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on pressing the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Service roomToInsert = new Service(serviceIDField.getText(),
                        minTierLevelField.getText(),
                        Double.valueOf(serviceCostField.getText()),
                        hotelAddressField.getText());
                controller.insertTuple(roomToInsert);
            }
        });

        // on pressing cancelButton
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ChooseMenuRoomCost(controller));
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
