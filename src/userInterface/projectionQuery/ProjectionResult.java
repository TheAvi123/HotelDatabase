package userInterface.projectionQuery;

import controller.HotelController;
import userInterface.chooseMenu.ChooseMenuHotel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectionResult extends JPanel {
    private JLabel titleLabel;
    private JButton backButton;
    private JTable table;

    public ProjectionResult(TableModel model, HotelController controller, JFrame frame) {
        //construct components
        titleLabel = new JLabel ("PROJECTION QUERY RESULT");
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
