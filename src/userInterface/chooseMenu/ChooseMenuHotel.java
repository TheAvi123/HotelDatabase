package userInterface.chooseMenu;

import controller.HotelController;
import userInterface.WelcomeScreen;
import userInterface.aggregationQuery.AggregationQuery;
import userInterface.delete.DeleteHotel;
import userInterface.delete.DeleteRoomCost;
import userInterface.insert.InsertHotel;
import userInterface.insert.InsertRoomCost;
import userInterface.projectionQuery.ProjectionQuery;
import userInterface.showAll.ShowAllHotels;
import userInterface.showAll.ShowAllRoomCosts;
import userInterface.updatePrompt.UpdatePromptHotel;
import userInterface.updatePrompt.UpdatePromptRoomCost;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseMenuHotel extends JPanel {
    private JLabel titleLabel;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton showButton;
    private JButton backButton;
    private JButton projectionButton;
    private JButton aggregationButton;

    public ChooseMenuHotel(HotelController controller) {
        //construct components
        titleLabel = new JLabel ("What do you want to do with HOTEL?");
        insertButton = new JButton ("Insert");
        deleteButton = new JButton ("Delete");
        updateButton = new JButton ("Update");
        showButton = new JButton ("Show");
        backButton = new JButton ("Back to Welcome Screen");
        projectionButton = new JButton ("Projection");
        aggregationButton = new JButton ("Aggregation");

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
        add (projectionButton);
        add (aggregationButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (255, 65, 300, 30);
        insertButton.setBounds (305, 110, 103, 25);
        deleteButton.setBounds (305, 150, 103, 25);
        updateButton.setBounds (305, 190, 103, 25);
        showButton.setBounds (305, 230, 103, 25);
        backButton.setBounds (485, 345, 180, 25);
        projectionButton.setBounds (305, 270, 103, 25);
        aggregationButton.setBounds (305, 310, 103, 25);

        // on clicking the insertButton
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Insert Hotel");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new InsertHotel(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the updateButton
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Update Prompt");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new UpdatePromptHotel(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the deleteButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Delete Hotel");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new DeleteHotel(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the showButton
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Show all hotels");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ShowAllHotels(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the projectionButton
        projectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Selection Query");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ProjectionQuery(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the aggregationButton
        aggregationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Aggregation Query");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new AggregationQuery(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new WelcomeScreen(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("Choose Menu");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new ca.ubc.cs304.ui.chooseMenu.ChooseMenuRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}
