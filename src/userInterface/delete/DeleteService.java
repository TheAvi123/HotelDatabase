package userInterface.delete;

import controller.HotelController;
import model.tableHelpers.ServiceHelper;
import org.json.JSONException;
import org.json.JSONObject;
import userInterface.chooseMenu.ChooseMenuService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteService extends JPanel {
    private JLabel whichServiceLabel;
    private JButton cancelButton;
    private JLabel serviceIDLabel;
    private JTextField serviceIDField;
    private JButton submitButton;

    public DeleteService(HotelController controller) {
        //construct components
        whichServiceLabel = new JLabel ("Which SERVICE to delete?");
        cancelButton = new JButton ("Cancel");
        serviceIDLabel = new JLabel ("Service ID");
        serviceIDField = new JTextField (5);
        submitButton = new JButton ("Submit");

        //set components properties
        cancelButton.setToolTipText ("Goes back to the Welcome Screen");

        //adjust size and set layout
        setPreferredSize (new Dimension (736, 523));
        setLayout (null);

        //add components
        add (whichServiceLabel);
        add (cancelButton);
        add (serviceIDLabel);
        add (serviceIDField);
        add (submitButton);

        //set component bounds (only needed by Absolute Positioning)
        whichServiceLabel.setBounds (130, 75, 300, 30);
        cancelButton.setBounds (200, 210, 102, 25);
        serviceIDLabel.setBounds (100, 115, 100, 25);
        serviceIDField.setBounds (200, 115, 100, 25);
        submitButton.setBounds (100, 210, 100, 25);

        // on clicking the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saving the primary keys needed to find the particular hotel to delete
                String serviceIDToDelete = String.valueOf(serviceIDField.getText());
                ServiceHelper helper = new ServiceHelper();
                JSONObject primaryKey = new JSONObject();
                try {
                    primaryKey.put("serviceID", serviceIDToDelete);
                } catch (JSONException error) {
                    System.out.println(error.getMessage());
                    error.printStackTrace();
                }
                controller.deleteTuple(helper, primaryKey);
            }
        });

        // on clicking the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Welcome Screen");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new ChooseMenuService(controller));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("Delete Room");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new DeleteRoom());
//        frame.pack();
//        frame.setVisible (true);
//    }
}
