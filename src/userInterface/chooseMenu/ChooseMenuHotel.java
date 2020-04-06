package userInterface.chooseMenu;

import controller.HotelController;
import userInterface.WelcomeScreen;
import userInterface.aggregationQuery.AggregationGrpByQuery;
import userInterface.aggregationQuery.AggregationQuery;
import userInterface.delete.DeleteHotel;
import userInterface.insert.InsertHotel;
import userInterface.projectionQuery.ProjectionQuery;
import userInterface.showAll.ShowAllHotels;
import userInterface.updatePrompt.UpdatePromptHotel;

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
    private JButton selectButton;
    private JButton aggrGrpByButton;
    private JButton aggrButton;

    public ChooseMenuHotel(HotelController controller, JFrame frame) {
        //construct components
        titleLabel = new JLabel ("What do you want to do with Hotel?");
        insertButton = new JButton ("Insert");
        deleteButton = new JButton ("Delete");
        updateButton = new JButton ("Update");
        showButton = new JButton ("Show");
        backButton = new JButton ("Back to Welcome Screen");
        selectButton = new JButton ("Projection");
        aggrGrpByButton = new JButton ("Aggr + GrpBy");
        aggrButton = new JButton ("Aggregation");

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
        add (aggrButton);


        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (250, 65, 300, 30);
        insertButton.setBounds (305, 110, 110, 25);
        deleteButton.setBounds (305, 150, 110, 25);
        updateButton.setBounds (305, 190, 110, 25);
        showButton.setBounds (305, 230, 110, 25);
        backButton.setBounds (485, 360, 180, 25);
        selectButton.setBounds (305, 270, 110, 25);
        aggrGrpByButton.setBounds (305, 310, 110, 25);
        aggrButton.setBounds (305, 350, 110, 25);


        // on clicking the insertButton
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add (new InsertHotel(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        // on clicking the updateButton
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add (new UpdatePromptHotel(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        // on clicking the deleteButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add (new DeleteHotel(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        // on clicking the showButton
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ShowAllHotels(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        // on clicking the selectButton
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ProjectionQuery(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        // on clicking the aggrGrpByButton
        aggrGrpByButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add (new AggregationGrpByQuery(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        // on clicking the aggrGrpByButton
        aggrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add (new AggregationQuery(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        // on clicking the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add (new WelcomeScreen(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

//    public static void main (String[] args) {
//
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add (new ca.ubc.cs304.ui.chooseMenu.ChooseMenuRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}
