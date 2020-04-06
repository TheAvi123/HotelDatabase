package userInterface;

import controller.HotelController;

import javax.swing.*;

/**
 * The class is only responsible for handling terminal text inputs. 
 */
public class TransactionsHandler {
	
	private HotelController controller;

	public TransactionsHandler(HotelController controller) {
		this.controller = controller;
	}

	public void showMainMenu() {
		JFrame frame = new JFrame("Welcome Screen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new WelcomeScreen(controller));
		frame.pack();
		frame.setVisible (true);
	}
}
