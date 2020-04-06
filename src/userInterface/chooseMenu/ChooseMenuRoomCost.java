package userInterface.chooseMenu;

import controller.HotelController;
import userInterface.WelcomeScreen;
import userInterface.aggregationQuery.AggregationGrpByQuery;
import userInterface.delete.DeleteRoomCost;
import userInterface.insert.InsertRoomCost;
import userInterface.selectionQuery.SelectionQuery;
import userInterface.showAll.ShowAllRoomCosts;
import userInterface.updatePrompt.UpdatePromptRoomCost;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseMenuRoomCost extends JPanel {
    private JLabel titleLabel;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton showButton;
    private JButton backButton;
    private JButton selectButton;
    private JButton aggrGrpByButton;

    public ChooseMenuRoomCost(HotelController controller) {
        //construct components
        titleLabel = new JLabel ("What do you want to do with ROOM COST?");
        insertButton = new JButton ("Insert");
        deleteButton = new JButton ("Delete");
        updateButton = new JButton ("Update");
        showButton = new JButton ("Show");
        backButton = new JButton ("Back to Welcome Screen");
        selectButton = new JButton ("Select");
        aggrGrpByButton = new JButton ("Aggr + GrpBy");

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
        add (selectButton);
        add (aggrGrpByButton);


        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (250, 65, 300, 30);
        insertButton.setBounds (305, 110, 110, 25);
        deleteButton.setBounds (305, 150, 110, 25);
        updateButton.setBounds (305, 190, 110, 25);
        showButton.setBounds (305, 230, 110, 25);
        backButton.setBounds (485, 360, 180, 25);
        selectButton.setBounds (305, 270, 110, 25);
        aggrGrpByButton.setBounds (305, 310, 110, 25);

        // on clicking the insertButton
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Insert RoomCost");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new InsertRoomCost(controller));
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
                frame.getContentPane().add (new UpdatePromptRoomCost(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the deleteButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Delete Room Cost");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new DeleteRoomCost(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the showButton
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Show all room costs");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ShowAllRoomCosts(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the selectButton
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Selection Query");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new SelectionQuery(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });

        // on clicking the aggrGrpByButton
        aggrGrpByButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Selection Query");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new AggregationGrpByQuery(controller));
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
