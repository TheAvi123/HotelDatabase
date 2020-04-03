package userInterface;

import javax.swing.*;
import java.awt.*;

public class ChooseMenu extends JPanel {
    private JLabel titleLabel;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton showButton;
    private JButton backButton;

    public ChooseMenu() {
        //construct components
        titleLabel = new JLabel ("What do you want to do with your selected entity?");
        insertButton = new JButton ("Insert");
        deleteButton = new JButton ("Delete");
        updateButton = new JButton ("Update");
        showButton = new JButton ("Show");
        backButton = new JButton ("Back to Welcome Screen");

        //set components properties
        insertButton.setToolTipText ("Inserts new tuple into SQL table");
        deleteButton.setToolTipText ("Removes tuple from SQL table");
        updateButton.setToolTipText ("Updates a tuple in SQL table");
        showButton.setToolTipText ("Shows all tuples in the SQL table");

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
        insertButton.setBounds (305, 110, 100, 25);
        deleteButton.setBounds (305, 150, 100, 25);
        updateButton.setBounds (305, 190, 100, 25);
        showButton.setBounds (305, 230, 100, 25);
        backButton.setBounds (485, 345, 180, 25);
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("Choose Menu");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new ChooseMenu());
//        frame.pack();
//        frame.setVisible (true);
//    }
}

