/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Boundary;

import Entity.*;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LoginView extends JPanel {

	private JLabel titleLabel;
	private JLabel infoLabel;
	
	private JTextField usernameTextField;
	private JLabel usernameLabel;
	
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	
	JLabel submitLoginButton;
	private JLabel registerButton;
	private JLabel guestButton;

	
	public LoginView(JFrame frame, Login backend) {
		setLayout(null);

		
		titleLabel = new JLabel("MOVIE REGISTRATION APP");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setBounds(515, 200, 350, 30);
		add(titleLabel);

		infoLabel = new JLabel("Please sign in, sign up or use guest mode.");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setForeground(Color.BLACK);
		infoLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		infoLabel.setBounds(515, 580, 350, 30);
		add(infoLabel);
		
		registerButton = new JLabel("SIGN UP");
		registerButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Go to RegistrationView
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrationView registerPanel = new RegistrationView(frame, backend);
				frame.setContentPane(registerPanel);
				frame.revalidate();
			}
		});
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton.setForeground(Color.BLACK);
		registerButton.setFont(new Font("Arial", Font.BOLD, 15));
		registerButton.setBounds(585, 520, 200, 14);
		add(registerButton);


		guestButton = new JLabel("PROCEED AS GUEST");
		guestButton.setHorizontalAlignment(SwingConstants.CENTER);
		guestButton.setForeground(Color.BLACK);
		guestButton.setFont(new Font("Arial", Font.BOLD, 15));
		guestButton.setBounds(585, 550, 200, 14);
		guestButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//Go to MainView as guest
		guestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				backend.guestUser();
				MainView homePanel = new MainView(frame, backend);
				frame.setContentPane(homePanel);
				frame.revalidate();
			}
		});
		add(guestButton);

		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		usernameTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		usernameTextField.setForeground(Color.DARK_GRAY);
		usernameTextField.setBackground(Color.WHITE);
		usernameTextField.setBounds(564, 314, 254, 28);
		usernameTextField.setOpaque(true);
		usernameTextField.setColumns(10);
		usernameTextField.setOpaque(true);
		add(usernameTextField);

		
		passwordField = new JPasswordField();
		passwordField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		passwordField.setForeground(Color.BLACK);
		passwordField.setBounds(564, 387, 254, 28);
		add(passwordField);

		
		usernameLabel = new JLabel("Username");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setForeground(Color.BLACK);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		usernameLabel.setBounds(653, 299, 77, 14);
		add(usernameLabel);

	
		passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordLabel.setBounds(653, 372, 77, 14);
		add(passwordLabel);

	
		JLabel invalidLoginErrorLabel = new JLabel("<html>"+ "The login info does not match \r\na registered account in the database."+ "</html>");
		invalidLoginErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		invalidLoginErrorLabel.setForeground(Color.RED);
		invalidLoginErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		invalidLoginErrorLabel.setBounds(564, 235, 254, 45);
		invalidLoginErrorLabel.setVisible(false);
		add(invalidLoginErrorLabel);

		
		submitLoginButton = new JLabel("Login");
		submitLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		submitLoginButton.setHorizontalAlignment(SwingConstants.CENTER);
		submitLoginButton.setFont(new Font("Arial", Font.BOLD, 18));
		
		//Login, go to MainView ONLY IF credentials match stored values in DB
		//Other give error message
		submitLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = usernameTextField.getText().toLowerCase();
				String pass = String.valueOf(passwordField.getPassword());
				if (backend.login_verification(user, pass) != null) {
					MainView homePanel = new MainView(frame, backend);
					frame.setContentPane(homePanel);
				} else {
					invalidLoginErrorLabel.setVisible(true);
				}
				frame.revalidate();
			}
		});
		submitLoginButton.setBounds(653, 410, 77, 50);
		add(submitLoginButton);

		
		JLabel loginBackground = new JLabel("");
		loginBackground.setBounds(-2, -1, 1366, 768);
		loginBackground.setIcon(new ImageIcon(LoginView.class.getResource("/bg.png")));
		add(loginBackground);
	}

	private static final long serialVersionUID = 1L;
}
