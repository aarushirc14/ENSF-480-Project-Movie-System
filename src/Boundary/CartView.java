
/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Boundary;

import Entity.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import java.util.ArrayList;


public class CartView extends JPanel {
	

	private JLabel backButton;
	
	private JLabel checkoutButton;

	public CartView(JFrame frame, Login backend) {
		setLayout(null);

		
		JLabel userLabel = new JLabel("");
		if (backend.getCurrentRegisteredUser() != null) {
			userLabel = new JLabel("User: " + backend.getCurrentRegisteredUser().getfirst_name() + " "
					+ backend.getCurrentRegisteredUser().getlast_name());
		} else {
			userLabel = new JLabel("User: Guest");
		}
		userLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userLabel.setForeground(Color.BLACK);
		userLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		userLabel.setBounds(140, 5, 571, 50);
		add(userLabel);

		
		JLabel cartTotalLabel = new JLabel("");
		cartTotalLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cartTotalLabel.setForeground(Color.BLACK);
		cartTotalLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		cartTotalLabel.setBounds(26, 679, 571, 50);
		cartTotalLabel.setText("Total: $" + String.format("%.2f",backend.getCurrentUser().getCart().calculatetotalCost()));
		add(cartTotalLabel);

		
		checkoutButton = new JLabel("Checkout");
		checkoutButton.setFont(new Font("Arial", Font.PLAIN, 25));
		checkoutButton.setBounds(1082, 679, 254, 50);
		checkoutButton.setToolTipText("Checkout");
		checkoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//Go to CheckoutView 
		checkoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				CheckoutView paymentPanel = new CheckoutView(frame, backend);
				frame.setContentPane(paymentPanel);
				frame.revalidate();
				
			}
		});
		if (backend.getCurrentUser().getCart().getitems().size() == 0){
			checkoutButton.setVisible(false);
		} else{
			checkoutButton.setVisible(true);
		}
		add(checkoutButton);

		// Show list of tickets in cart
		JPanel imagePanels = new JPanel();
		imagePanels.setPreferredSize(new Dimension(1304, 1800));

		ArrayList<Reservation> cartItems = backend.getCurrentUser().getCart().getitems();
		JLabel[] ticketCards = new JLabel[cartItems.size()];
		JLabel[] ticketDetails = new JLabel[cartItems.size()];
		for (int i = 0; i < cartItems.size(); i++) {
			

			ticketDetails[i] = new JLabel("");
			ticketDetails[i].setHorizontalAlignment(SwingConstants.LEFT);
			ticketDetails[i].setText(cartItems.get(i).getshowing_time() + "        " + cartItems.get(i).getseat_to_book()
					+ "        " + cartItems.get(i).getmovie_to_book());
			ticketDetails[i].setForeground(Color.BLACK);
			ticketDetails[i].setFont(new Font("Arial", Font.PLAIN, 20));
			ticketDetails[i].setBounds(60, 70 + (170 * i), 1000, 32);
			imagePanels.add(ticketDetails[i]);

			ticketCards[i] = new JLabel("");
			ticketCards[i].setVerticalAlignment(SwingConstants.TOP);
			ticketCards[i].setHorizontalAlignment(SwingConstants.LEFT);
			ticketCards[i].setBackground(Color.GRAY);
			ticketCards[i].setBounds(0, 5 + (170 * i), 1285, 164);
			
			imagePanels.add(ticketCards[i]);
		}
		add(imagePanels);
		imagePanels.setOpaque(false);

		JScrollPane scrollPane = new JScrollPane(imagePanels);
		imagePanels.setLayout(null);
		scrollPane.setBounds(20, 87, 1304, 560);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		add(scrollPane);

		
		backButton = new JLabel("Go Back");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.setForeground(Color.BLACK);
		backButton.setFont(new Font("Arial", Font.BOLD, 15));


		//Go back to main view
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView homePanel = new MainView(frame, backend);
				frame.setContentPane(homePanel);
				frame.revalidate();
			}
		});
		backButton.setBounds(30, 30, 80, 50);
		add(backButton);

		

		// Set bg image
		JLabel homeBackground = new JLabel("");
		homeBackground.setBounds(-2, -1, 1366, 768);
		homeBackground.setIcon(new ImageIcon(MainView.class.getResource("/bg.png")));
		add(homeBackground);
	}

	private static final long serialVersionUID = 1L;
}
