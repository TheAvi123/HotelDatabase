package userInterface.insert;

import controller.HotelController;
import model.tables.Hotel;
import model.tables.RoomCost;
import userInterface.chooseMenu.ChooseMenuHotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertHotel extends JPanel {
    private JLabel titleLabel;
    private JButton cancelButton;
    private JLabel hotelAddressLabel;
    private JTextField hotelAddressField;
    private JLabel hotelNameLabel;
    private JTextField hotelNameField;
    private JLabel capacityLabel;
    private JTextField capacityField;
    private JButton submitButton;

    public InsertHotel(HotelController controller, JFrame frame) {

        //construct components
        titleLabel = new JLabel ("Insert new HOTEL");
        cancelButton = new JButton ("Cancel");
        hotelAddressLabel = new JLabel ("Hotel Address");
        hotelAddressField = new JTextField (5);
        hotelNameLabel = new JLabel ("Hotel Name");
        hotelNameField = new JTextField (1);
        capacityLabel = new JLabel ("Capacity");
        capacityField = new JTextField (1);
        submitButton = new JButton ("Select");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");
        capacityField.setToolTipText ("enter an integer");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
        add (cancelButton);
        add (hotelAddressLabel);
        add (hotelAddressField);
        add (hotelNameLabel);
        add (hotelNameField);
        add (capacityLabel);
        add (capacityField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (145, 65, 300, 30);
        cancelButton.setBounds (200, 330, 102, 25);
        hotelAddressLabel.setBounds (100, 115, 100, 25);
        hotelAddressField.setBounds (200, 115, 100, 25);
        hotelNameLabel.setBounds (100, 150, 100, 25);
        hotelNameField.setBounds (200, 150, 100, 25);
        capacityLabel.setBounds (100, 185, 100, 25);
        capacityField.setBounds (200, 185, 100, 25);
        submitButton.setBounds (100, 330, 100, 25);

        // on pressing the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String i = String.valueOf(hotelAddressField.getText());
                    String i1 = String.valueOf(hotelNameField.getText());
                    int i2 = Integer.parseInt(capacityField.getText());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(new JFrame(), "one or more of the fields should be a number", "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                }
                Hotel hotel = new Hotel(String.valueOf(hotelAddressField.getText()),
                        String.valueOf(hotelNameField.getText()),
                        Integer.parseInt(capacityField.getText()));
                controller.insertTuple(hotel);
            }
        });

        // on pressing cancelButton
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ChooseMenuHotel(controller, frame));
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
