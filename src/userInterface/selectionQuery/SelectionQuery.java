package userInterface.selectionQuery;


import controller.HotelController;
import model.tables.RoomCost;
import userInterface.chooseMenu.ChooseMenuRoomCost;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SelectionQuery extends JPanel {
    private JLabel titleLabel;
    private JLabel attrLabel;
    private JLabel condLabel;
    private JLabel valLabel;
    private JComboBox attrField;
    private JComboBox condField;
    private JTextField valField;
    private JLabel noteLabel;
    private JButton submitButton;
    private JButton backButton;

    public SelectionQuery(HotelController controller) {
        //construct preComponents
        String[] attrFieldItems = {"Room Number", "Room Floor", "Room Cost"};
        String[] condFieldItems = {"=", "!=", ">", "<", ">=", "<="};

        //construct components
        titleLabel = new JLabel ("SELECTION QUERY");
        attrLabel = new JLabel ("ATTRIBUTE");
        condLabel = new JLabel ("CONDITION");
        valLabel = new JLabel ("VALUE");
        attrField = new JComboBox (attrFieldItems);
        condField = new JComboBox (condFieldItems);
        valField = new JTextField (1);
        noteLabel = new JLabel ("NOTE: please make sure you use the right operators for the right data type. Thanks!");
        submitButton = new JButton ("Submit Query");
        backButton = new JButton ("Back to Menu");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
        add (attrLabel);
        add (condLabel);
        add (valLabel);
        add (attrField);
        add (condField);
        add (valField);
        add (noteLabel);
        add (submitButton);
        add (backButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (310, 95, 175, 30);
        attrLabel.setBounds (125, 150, 100, 25);
        condLabel.setBounds (330, 150, 100, 25);
        valLabel.setBounds (530, 150, 100, 25);
        attrField.setBounds (95, 190, 130, 25);
        condField.setBounds (315, 190, 100, 25);
        valField.setBounds (500, 190, 100, 25);
        noteLabel.setBounds (50, 435, 485, 40);
        submitButton.setBounds (580, 445, 120, 20);
        backButton.setBounds (590, 35, 115, 25);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object attributeSelected = attrField.getSelectedItem();
                Object conditionSelected = condField.getSelectedItem();
                int valueEntered = Integer.parseInt(valField.getText());
            }
        });

        // on pressing backButton
        backButton.addActionListener(new ActionListener() {
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
//        JFrame frame = new JFrame ("MyPanel");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new SelectionQuery(controller));
//        frame.pack();
//        frame.setVisible (true);
//    }
}

