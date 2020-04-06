package userInterface.insert;

import controller.HotelController;
import model.tables.RoomCost;
import model.tables.RoomTier;
import userInterface.chooseMenu.ChooseMenuRoomCost;
import userInterface.chooseMenu.ChooseMenuRoomTier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertRoomTier extends JPanel {
    private JLabel titleLabel;
    private JButton cancelButton;
    private JLabel roomTypeLabel;
    private JTextField roomTypeField;
    private JLabel tierLevelLabel;
    private JTextField tierLevelField;
    private JButton submitButton;

    public InsertRoomTier(HotelController controller, JFrame frame) {

        //construct components
        titleLabel = new JLabel ("Insert new ROOM TIER");
        cancelButton = new JButton ("Cancel");
        roomTypeLabel = new JLabel ("Room Type");
        roomTypeField = new JTextField (1);
        tierLevelLabel = new JLabel ("Tier Level");
        tierLevelField = new JTextField (1);
        submitButton = new JButton ("Select");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
        add (cancelButton);
        add (roomTypeLabel);
        add (roomTypeField);
        add (tierLevelLabel);
        add (tierLevelField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (145, 65, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        roomTypeLabel.setBounds (100, 115, 100, 25);
        roomTypeField.setBounds (200, 115, 100, 25);
        tierLevelLabel.setBounds (100, 150, 100, 25);
        tierLevelField.setBounds (200, 150, 100, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on pressing the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomTier roomTierToInsert = new RoomTier(roomTypeField.getText(),
                        tierLevelField.getText());
                controller.insertTuple(roomTierToInsert);
            }
        });

        // on pressing cancelButton
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ChooseMenuRoomTier(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

//    public static void main (String[] args) {
//
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add (new InsertRoom(new HotelController()));
//        frame.pack();
//        frame.setVisible (true);
//    }
}
