/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */



package Boundary;

import Entity.*;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;



public class RegistrationView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField usernameTextField;
	private JLabel usernameLabel;

	private JPasswordField passwordField;
	private JLabel passwordLabel;
	
	private JTextField emailTextField;
	private JLabel emailLabel;
	
	private JTextField fullNameTextField;
	private JLabel fullNameLabel;

	private JTextField cardNumberTextField;
	private JLabel cardNumberLabel;

	private JLabel registerButton;
	
	private JLabel backButton;
	

	public RegistrationView(JFrame frame, Login backend) {

		setLayout(null);

		backButton = new JLabel("Return to Login");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.setFont(new Font("Arial", Font.PLAIN, 15));

		//back button to go to login screen
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginView loginPanel = new LoginView(frame, backend);
				frame.setContentPane(loginPanel);
				frame.revalidate();
			}
		});
		
		backButton.setBounds(30, 30, 150, 50);
		
		add(backButton);

		cardNumberTextField = new JTextField();
		cardNumberTextField.setBounds(561, 463, 254, 28);
		cardNumberTextField.setToolTipText("Enter your card number.");
		cardNumberTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		cardNumberTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		cardNumberTextField.setForeground(Color.DARK_GRAY);
		cardNumberTextField.setBackground(Color.WHITE);
		cardNumberTextField.setOpaque(true);
		cardNumberTextField.setColumns(10);
		cardNumberTextField.setOpaque(true);
		add(cardNumberTextField);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(697, 307, 254, 28);
		usernameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		usernameTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		usernameTextField.setForeground(Color.DARK_GRAY);
		usernameTextField.setBackground(Color.WHITE);
		usernameTextField.setOpaque(true);
		usernameTextField.setColumns(10);
		usernameTextField.setOpaque(true);
		add(usernameTextField);

		passwordField = new JPasswordField();
		passwordField.setBounds(568, 359, 254, 28);
		passwordField.setToolTipText("Enter your password.");
		passwordField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		passwordField.setForeground(Color.BLACK);
		add(passwordField);

		emailTextField = new JTextField();
		emailTextField.setBounds(561, 411, 254, 28);
		emailTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		emailTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		emailTextField.setForeground(Color.DARK_GRAY);
		emailTextField.setBackground(Color.WHITE);
		emailTextField.setOpaque(true);
		emailTextField.setColumns(10);
		emailTextField.setOpaque(true);
		add(emailTextField);

		fullNameTextField = new JTextField();
		fullNameTextField.setBounds(433, 307, 254, 28);
		fullNameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		fullNameTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		fullNameTextField.setForeground(Color.DARK_GRAY);
		fullNameTextField.setBackground(Color.WHITE);
		fullNameTextField.setOpaque(true);
		fullNameTextField.setColumns(10);
		fullNameTextField.setOpaque(true);
		add(fullNameTextField);
		
		fullNameLabel = new JLabel("Full name");
		fullNameLabel.setBounds(522, 292, 77, 14);
		fullNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fullNameLabel.setForeground(Color.BLACK);
		fullNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		add(fullNameLabel);

		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(786, 292, 77, 14);
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setForeground(Color.BLACK);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		add(usernameLabel);


		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(660, 345, 77, 14);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		add(passwordLabel);
	
	
		emailLabel = new JLabel("Email");
		emailLabel.setBounds(667, 397, 50, 14);
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel.setForeground(Color.BLACK);
		emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		add(emailLabel);

		
		cardNumberLabel = new JLabel("Card Number");
		cardNumberLabel.setBounds(634, 449, 108, 14);
		cardNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cardNumberLabel.setForeground(Color.BLACK);
		cardNumberLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		add(cardNumberLabel);

	
	
		JLabel invalidUsernameErrorLabel = new JLabel("<html>"
				+ "The username you have entered already exists." + "</html>");
		invalidUsernameErrorLabel.setBounds(565, 236, 254, 45);
		invalidUsernameErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		invalidUsernameErrorLabel.setForeground(Color.BLACK);
		invalidUsernameErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		add(invalidUsernameErrorLabel);
		
		
		JLabel invalidCardErrorLabel = new JLabel("<html>"
				+ "Card information was declined by bank."+ "</html>");
		invalidCardErrorLabel.setBounds(565, 236, 254, 45);
		invalidCardErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		invalidCardErrorLabel.setForeground(Color.BLACK);
		invalidCardErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		invalidCardErrorLabel.setVisible(false);
		add(invalidCardErrorLabel);

		
		JLabel invalidNameErrorLabel = new JLabel(
				"<html>" + "Please enter your first AND last name." + "</html>");
		invalidNameErrorLabel.setBounds(565, 236, 254, 45);
		invalidNameErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		invalidNameErrorLabel.setForeground(Color.BLACK);
		invalidNameErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		invalidNameErrorLabel.setVisible(false);
		invalidUsernameErrorLabel.setVisible(false);
		add(invalidNameErrorLabel);

		// submit button
		registerButton = new JLabel("Register");
		registerButton.setToolTipText("Login");
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton.setFont(new Font("Arial", Font.BOLD, 20));
		
		 // Upon clicking register button, data from all the text field is verified
		 // if the information provided is correct, it stores all the information in the database 
		 // and goes back to the login screen
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fullname = fullNameTextField.getText();
				String[] name = fullname.split(" ");
				String user = usernameTextField.getText();
				String pass = String.valueOf(passwordField.getPassword());
				String email = emailTextField.getText();
				String cardNum = cardNumberTextField.getText();
			
	
			

				// removing all error messeges
				invalidCardErrorLabel.setVisible(false);
				invalidNameErrorLabel.setVisible(false);
				invalidUsernameErrorLabel.setVisible(false);				

				// Checking username: make sure it doesn't already exist
				if (!backend.check_if_user_is_existing(user)) {
					
						if (name.length == 2) {							
							if(backend.getDataController().getInst().CardInfo_verification(fullname, cardNum)) {
								backend.user_registration(user, pass, name[0], name[1], email, fullname,  cardNum);
								LoginView loginPanel = new LoginView(frame, backend);
								frame.setContentPane(loginPanel);
							}
							else {
								invalidCardErrorLabel.setVisible(true);
							}							
						} else {
							invalidNameErrorLabel.setVisible(true);
						}
					
				} else {
					invalidUsernameErrorLabel.setVisible(true);
				}
				frame.revalidate();
			}
		});
		registerButton.setBounds(650, 500, 90, 120);
		add(registerButton);

		// set bg image
		JLabel registerBackground = new JLabel("");
		registerBackground.setBounds(-2, -1, 1366, 768);
		registerBackground.setIcon(new ImageIcon(RegistrationView.class.getResource("/bg.png")));
		add(registerBackground);

	}
}