package userInterface.aggregationQuery;

import controller.HotelController;
import userInterface.chooseMenu.ChooseMenuEmployee;
import userInterface.chooseMenu.ChooseMenuHotel;
import userInterface.showAll.HotelTableModel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;

public class AggregationResult extends JPanel {
    private JLabel titleLabel;
    private JButton backButton;
    private JTable table;

    public AggregationResult(TableModel model, HotelController controller, JFrame frame) {
        //construct components
        titleLabel = new JLabel ("AGGREGATION QUERY RESULT");
        backButton = new JButton ("Back");
        table = new JTable(model);

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (titleLabel);
        add (backButton);
        add (table);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (50, 60, 200, 25);
        backButton.setBounds (600, 55, 100, 25);
        table.setBounds (50, 115, 650, 355);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add (new ChooseMenuHotel(controller, frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}
