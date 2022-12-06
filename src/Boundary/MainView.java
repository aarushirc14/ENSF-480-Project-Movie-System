/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Boundary;

import Entity.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.MatteBorder;


public class MainView extends JPanel {


	private JLabel logoutButton;

	private JLabel cartButton;

	private JLabel annualFeeButton;

	private JLabel cancelTicketButton;

	//  seat
	private JTextField rowTextField;

	//  seat
	private JTextField colTextField;

	//  seat
	private JLabel selectRowLabel;

	//  seat
	private JLabel selectColLabel;

	private JLabel invalidSeatErrorLabel;
	
	private JLabel takenSeatErrorLabel;
	
	private JLabel selectedSeatErrorLabel;

	private JLabel addedToCartLabel;

	private JTextArea seatGraphicLabel;

	private JLabel AddToCartButton;

	private JLabel selectSeatLabel;

	private JLabel selectShowtimeLabel;

	private JTextArea showtimeDetailsLabel;

	private JComboBox showtimeSelectComboBox;

	private JLabel selectTheatreLabel;

	JComboBox theatreSelectComboBox;

	private JLabel selectMovieLabel;
	
	private JTextArea movieDetailsLabel;
	
	private JTextArea movieSynopsisLabel;
	
	private JLabel posterCard;
	
	private JComboBox movieSelectComboBox;
	
	private JLabel userLabel;
	
	private JLabel homeBackground;
	
	private Movie currentMovie;

	private Theatre currentTheatre;

	private Showtime currentShowtime;

	public MainView(JFrame frame, Login login) {
		setLayout(null);

		rowTextField = new JTextField();
		rowTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		rowTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		rowTextField.setForeground(Color.DARK_GRAY);
		rowTextField.setBackground(Color.WHITE);
		rowTextField.setBounds(930, 320, 50, 28);
		rowTextField.setOpaque(true);
		rowTextField.setColumns(10);
		rowTextField.setVisible(false);
		add(rowTextField);

		selectRowLabel = new JLabel("Enter Row: ");
		selectRowLabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectRowLabel.setForeground(Color.BLACK);
		selectRowLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		selectRowLabel.setBounds(930, 280, 200, 45);
		selectRowLabel.setVisible(false);
		add(selectRowLabel);

		colTextField = new JTextField();
		colTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		colTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		colTextField.setForeground(Color.DARK_GRAY);
		colTextField.setBackground(Color.WHITE);
		colTextField.setBounds(930, 380, 50, 28);
		colTextField.setOpaque(true);
		colTextField.setColumns(10);
		colTextField.setVisible(false);
		add(colTextField);
		
		selectColLabel = new JLabel("Enter Seat: ");
		selectColLabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectColLabel.setForeground(Color.BLACK);
		selectColLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		selectColLabel.setBounds(930, 340, 200, 45);
		selectColLabel.setVisible(false);
		add(selectColLabel);

		invalidSeatErrorLabel = new JLabel("<html>" + "Invalid Row or Seat Number was Selected" + "</html>");
		invalidSeatErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		invalidSeatErrorLabel.setForeground(Color.RED);
		invalidSeatErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		invalidSeatErrorLabel.setBounds(1100, 500, 192, 45);
		invalidSeatErrorLabel.setVisible(false);
		add(invalidSeatErrorLabel);

		selectedSeatErrorLabel = new JLabel("<html>" + "The Seat Selected is already Selected" + "</html>");
		selectedSeatErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectedSeatErrorLabel.setForeground(Color.RED);
		selectedSeatErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		selectedSeatErrorLabel.setBounds(1100, 500, 192, 45);
		selectedSeatErrorLabel.setVisible(false);
		add(selectedSeatErrorLabel);
	
		takenSeatErrorLabel = new JLabel("<html>" + "The Seat Selected is already Booked" + "</html>");
		takenSeatErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		takenSeatErrorLabel.setForeground(Color.RED);
		takenSeatErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		takenSeatErrorLabel.setBounds(1100, 500, 192, 45);
		takenSeatErrorLabel.setVisible(false);
		add(takenSeatErrorLabel);

		
		addedToCartLabel = new JLabel("<html>" + "Added To Cart" + "</html>");
		addedToCartLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addedToCartLabel.setForeground(Color.RED);
		addedToCartLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		addedToCartLabel.setBounds(1000, 500, 192, 45);
		addedToCartLabel.setVisible(false);
		add(addedToCartLabel);

		seatGraphicLabel = new JTextArea("");
		AddToCartButton = new JLabel("Add To Cart");
		AddToCartButton.setForeground(Color.BLACK);
		AddToCartButton.setBackground(Color.GRAY);
		AddToCartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		AddToCartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((login.getCurrentUser().getreg_or_unreg_user().compareTo("Registered") == 0
						&& login.getCurrentRegisteredUser().isFeePayed()) || login.getCurrentUser().getreg_or_unreg_user().compareTo("Guest") == 0) {
					try {
					
						int userRow = Integer.parseInt(rowTextField.getText());
						int userCol = Integer.parseInt(colTextField.getText());

						invalidSeatErrorLabel.setVisible(false);
						takenSeatErrorLabel.setVisible(false);
						addedToCartLabel.setVisible(false);
						selectedSeatErrorLabel.setVisible(false);
						
						if (userRow < currentShowtime.getnumber_of_rows() && userCol < currentShowtime.getnumber_of_columns()) {
							boolean available = currentShowtime.getSeatAvaliability(userRow, userCol);
							if (available == false) {
								boolean selectFlag = false;
								
								for (int k = 0; k < login.getCurrentUser().getCart().getitems().size(); k++) {
									if (login.getCurrentUser().getCart().getitems().get(k).getseat_to_book()
											.getSelected_row() == userRow
											&& login.getCurrentUser().getCart().getitems().get(k)
													.getseat_to_book().getSelected_column() == userCol
											&& login.getCurrentUser().getCart().getitems().get(k)
													.getshowing_time()
													.getID_for_showtime() == currentShowtime.getID_for_showtime()) {
										selectedSeatErrorLabel.setVisible(true);
										selectFlag = true;
									}
								}
								if (!selectFlag) {
									if (currentShowtime.getMovie().getMovieAnnouncement().isPrivate()) {
										int numSelectedSeats = 0;
										for (int i = 0; i < login.getCurrentUser().getCart().getitems()
												.size(); i++) {
											if (currentMovie.gettitle_of_movie().compareTo(login.getCurrentUser().getCart()
													.getitems().get(i).getmovie_to_book().gettitle_of_movie()) == 0) {
												numSelectedSeats++;
											}
										}
										if ((currentShowtime.getnumer_of_seats_available()
												- (numSelectedSeats + 1) > (currentShowtime.getnumber_of_rows()
														* currentShowtime.getnumber_of_columns() * 0.9))) {
											login.getCurrentUser().getCart().addToCart(new Reservation(currentMovie,
													currentShowtime, currentShowtime.getSeats()[userRow][userCol]));
											addedToCartLabel.setVisible(true);
											createSeatGraphic(frame, login);
										} 
									} else {
										login.getCurrentUser().getCart().addToCart(new Reservation(currentMovie,
												currentShowtime, currentShowtime.getSeats()[userRow][userCol]));
										addedToCartLabel.setVisible(true);
										createSeatGraphic(frame, login);
									}
								}
							} else {
								
								takenSeatErrorLabel.setVisible(true);
							}
						} else {
					
							invalidSeatErrorLabel.setVisible(true);
						}
						frame.revalidate();
					} catch (NumberFormatException f) {
						System.out.println(f);
					}
				} else {
					JOptionPane.showMessageDialog(null, " You must pay the $20 Annual Fee for Registered Users.",
							(String) "Annual Fee Payment", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		AddToCartButton.setBounds(930, 425, 254, 50);
		AddToCartButton.setVisible(false);
	
		add(AddToCartButton);
		

		addedToCartLabel = new JLabel("<html>" + "Added To Cart" + "</html>");
		addedToCartLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addedToCartLabel.setForeground(Color.RED);
		addedToCartLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		addedToCartLabel.setBounds(930, 500, 192, 45);
		addedToCartLabel.setVisible(false);
		add(addedToCartLabel);
		
		selectSeatLabel = new JLabel("Screen is Here.");
		selectSeatLabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectSeatLabel.setForeground(Color.BLACK);
		selectSeatLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		selectSeatLabel.setBounds(600, 280, 254, 45);
		selectSeatLabel.setVisible(false);
		add(selectSeatLabel);

		// Graphic of Seat Avaliability, X if booked, O if
		// avaliable, - if Selected
		seatGraphicLabel.setForeground(Color.BLACK);
		seatGraphicLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
		seatGraphicLabel.setBounds(600, 310, 500, 500);
		seatGraphicLabel.setVisible(false);
		seatGraphicLabel.setLineWrap(true);
		seatGraphicLabel.setWrapStyleWord(true);
		seatGraphicLabel.setOpaque(false);
		seatGraphicLabel.setEditable(false);
		add(seatGraphicLabel);

		
		selectShowtimeLabel = new JLabel("Show Times");
		selectShowtimeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectShowtimeLabel.setForeground(Color.BLACK);
		selectShowtimeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		selectShowtimeLabel.setBounds(900, 140, 254, 45);
		selectShowtimeLabel.setVisible(false);
		add(selectShowtimeLabel);

		showtimeDetailsLabel = new JTextArea("");
		showtimeDetailsLabel.setForeground(Color.BLACK);
		showtimeDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		showtimeDetailsLabel.setBounds(900, 215, 254, 100);
		showtimeDetailsLabel.setVisible(false);
		showtimeDetailsLabel.setLineWrap(true);
		showtimeDetailsLabel.setWrapStyleWord(true);
		showtimeDetailsLabel.setOpaque(false);
		showtimeDetailsLabel.setEditable(false);
		add(showtimeDetailsLabel);

		showtimeSelectComboBox = new JComboBox(new String[0]);
		showtimeSelectComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
		showtimeSelectComboBox.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		showtimeSelectComboBox.setForeground(Color.DARK_GRAY);
		showtimeSelectComboBox.setBackground(Color.WHITE);
		showtimeSelectComboBox.setBounds(900, 180, 200, 28);
		showtimeSelectComboBox.setOpaque(true);
		showtimeSelectComboBox.setVisible(false);
	
		showtimeSelectComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tempString = (String) showtimeSelectComboBox.getSelectedItem();
				invalidSeatErrorLabel.setVisible(false);
				takenSeatErrorLabel.setVisible(false);
				addedToCartLabel.setVisible(false);
				selectedSeatErrorLabel.setVisible(false);
				if (tempString != null) {
					String[] tempStringArray = tempString.split("/");
					Date tempDate = new Date(Integer.parseInt(tempStringArray[0]), Integer.parseInt(tempStringArray[1]),
							Integer.parseInt(tempStringArray[2]));
					currentShowtime = login.getDataController().findShowtime(currentMovie, currentTheatre, tempDate);
					if (currentShowtime != null) {
						showtimeDetailsLabel
								.setText("Time: " + currentShowtime.gettime_in_hours() + ":" + currentShowtime.gettime_in_minutes());

						showtimeDetailsLabel.setVisible(true);
						rowTextField.setVisible(true);
						colTextField.setVisible(true);
						selectRowLabel.setVisible(true);
						selectColLabel.setVisible(true);
						AddToCartButton.setVisible(true);

						createSeatGraphic(frame, login);
						selectSeatLabel.setVisible(true);
					} else {
						showtimeDetailsLabel.setVisible(false);
					}
				} else {
					showtimeDetailsLabel.setVisible(false);
				}
			}
		});
		add(showtimeSelectComboBox);

		
		selectTheatreLabel = new JLabel("Theatres");
		selectTheatreLabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectTheatreLabel.setForeground(Color.BLACK);
		selectTheatreLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		selectTheatreLabel.setBounds(600, 140, 254, 45);
		selectTheatreLabel.setVisible(false);
		add(selectTheatreLabel);

		theatreSelectComboBox = new JComboBox(new String[0]);
		theatreSelectComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
		theatreSelectComboBox.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		theatreSelectComboBox.setForeground(Color.DARK_GRAY);
		theatreSelectComboBox.setBackground(Color.WHITE);
		theatreSelectComboBox.setBounds(600, 180, 200, 28);
		theatreSelectComboBox.setOpaque(true);
		theatreSelectComboBox.setVisible(false);
		
		theatreSelectComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentTheatre = login.getDataController()
						.findTheatre((String) theatreSelectComboBox.getSelectedItem());
				
				if (currentTheatre != null) {
					
					currentTheatre = login.getDataController()
							.findTheatre((String) theatreSelectComboBox.getSelectedItem());
					currentShowtime = null;
					if (currentTheatre != null) {
						

						DefaultComboBoxModel model2 = (DefaultComboBoxModel) showtimeSelectComboBox.getModel();
						model2.removeAllElements();
						ArrayList<Showtime> showtimeList = login.getDataController().findAllShowtime(currentMovie,
								currentTheatre);
						for (int i = 0; i < showtimeList.size(); i++) {
							model2.addElement(showtimeList.get(i).getDate_of_show().toString());
						}
						showtimeSelectComboBox.setModel(model2);
						showtimeSelectComboBox.setVisible(true);
						// noShowTimeSelectedLabel.setVisible(true);
						selectShowtimeLabel.setVisible(true);
					} 
				}
			}
		});
		add(theatreSelectComboBox);

		
		selectMovieLabel = new JLabel("Movies");
		selectMovieLabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectMovieLabel.setForeground(Color.BLACK);
		selectMovieLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		selectMovieLabel.setBounds(85, 140, 67, 32);
		add(selectMovieLabel);

		movieDetailsLabel = new JTextArea("");
		movieDetailsLabel.setForeground(Color.BLACK);
		movieDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		movieDetailsLabel.setBounds(273, 233, 123, 268);
		movieDetailsLabel.setVisible(false);
		movieDetailsLabel.setLineWrap(true);
		movieDetailsLabel.setWrapStyleWord(true);
		movieDetailsLabel.setOpaque(false);
		movieDetailsLabel.setEditable(false);
		add(movieDetailsLabel);

		movieSynopsisLabel = new JTextArea("");
		movieSynopsisLabel.setForeground(Color.BLACK);
		movieSynopsisLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		movieSynopsisLabel.setBounds(85, 525, 311, 134);
		movieSynopsisLabel.setVisible(false);
		movieSynopsisLabel.setLineWrap(true);
		movieSynopsisLabel.setWrapStyleWord(true);
		movieSynopsisLabel.setOpaque(false);
		movieSynopsisLabel.setEditable(false);
		add(movieSynopsisLabel);

		posterCard = new JLabel("");
		posterCard.setBounds(85, 235, 182, 268);
		posterCard.setVisible(true);
		add(posterCard);

		Vector<String> list_of_movies = new Vector<String>();
		for (int i = 0; i < login.getDataController().getlist_of_movies().size(); i++) {
			if (login.getCurrentRegisteredUser() != null) {
				if (!login.getDataController().getlist_of_movies().get(i).getMovieAnnouncement().getPrAnnounceDate()
						.is_before_CurrentDate())
					list_of_movies.add(login.getDataController().getlist_of_movies().get(i).gettitle_of_movie());
			} else {
				if (!login.getDataController().getlist_of_movies().get(i).getMovieAnnouncement().getPuAnnounceDate()
						.is_before_CurrentDate())
					list_of_movies.add(login.getDataController().getlist_of_movies().get(i).gettitle_of_movie());
			}
		}
		movieSelectComboBox = new JComboBox(list_of_movies);
		movieSelectComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
		movieSelectComboBox.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		movieSelectComboBox.setForeground(Color.DARK_GRAY);
		movieSelectComboBox.setBackground(Color.WHITE);
		movieSelectComboBox.setBounds(85, 180, 311, 28);
		movieSelectComboBox.setOpaque(true);
		
		movieSelectComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = (String) movieSelectComboBox.getSelectedItem();

				currentMovie = login.getDataController().findMovie(temp);
				currentTheatre = null;
				currentShowtime = null;

				movieDetailsLabel.setText(currentMovie.gettitle_of_movie() + "\n\n" + currentMovie.getgenre_of_movie());
				movieSynopsisLabel.setText(currentMovie.getSynopsis());

				posterCard.setIcon(new ImageIcon(MainView.class.getResource("/" + currentMovie.getPoster() + ".jpg")));

				movieDetailsLabel.setVisible(true);
				selectTheatreLabel.setVisible(true);
				posterCard.setVisible(true);
				movieSynopsisLabel.setVisible(true);

				DefaultComboBoxModel model = (DefaultComboBoxModel) theatreSelectComboBox.getModel();
				model.removeAllElements();
				ArrayList<Theatre> theatreList = login.getDataController().findTheatresPlayingMovie(currentMovie);
				for (int i = 0; i < theatreList.size(); i++) {
					model.addElement(theatreList.get(i).getname_of_theatre());
				}
				theatreSelectComboBox.setModel(model);
				theatreSelectComboBox.setVisible(true);
				frame.revalidate();
			}
		});
		add(movieSelectComboBox);

		
		userLabel = new JLabel("");
		if (login.getCurrentRegisteredUser() != null) {
			userLabel = new JLabel("User: " + login.getCurrentRegisteredUser().getfirst_name() + " "
					+ login.getCurrentRegisteredUser().getlast_name());
		} else {
			userLabel = new JLabel("User:Guest");
		}
		userLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userLabel.setForeground(Color.BLACK);
		userLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		userLabel.setBounds(140, 5, 571, 50);
		add(userLabel);

		
		cancelTicketButton = new JLabel("Cancel Ticket");
		cancelTicketButton.setForeground(Color.BLACK);
		cancelTicketButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelTicketButton.setFont(new Font("Arial", Font.BOLD, 15));
		
		cancelTicketButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CancelView cancelTicketPage = new CancelView(frame, login);
				frame.setContentPane(cancelTicketPage);
				frame.revalidate();

			}
		});
		cancelTicketButton.setBounds(1100, 14, 100, 32);
		cancelTicketButton.setVisible(true);
		
		add(cancelTicketButton);
		
		logoutButton = new JLabel("Logout");
		logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login.logout_user();
				LoginView loginPanel = new LoginView(frame, login);
				frame.setContentPane(loginPanel);
				frame.revalidate();
			}
		});
		logoutButton.setBounds(30, 5, 50, 50);
		
		add(logoutButton);

		
		if (login.getCurrentUser().getreg_or_unreg_user().compareTo("Registered") == 0) {
			annualFeeButton = new JLabel("Pay Annual Fee");
			annualFeeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			annualFeeButton.setFont(new Font("Arial", Font.BOLD, 15));
		
			annualFeeButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					
					if (!login.getCurrentRegisteredUser().getdate_of_feeDeposit().is_before_CurrentDate()) {
						
						String name = login.getCurrentRegisteredUser().getfirst_name() + " "
								+ login.getCurrentRegisteredUser().getlast_name();
						
						String cn = login.getCurrentRegisteredUser().getBankInfo().getpayment_card_number();
						
					
						if (login.getDataController().getInst().CardInfo_verification(name, cn)) {
							JOptionPane.showMessageDialog(null,
									"Annual Fee has been paid.",
									(String) "Annual Fee Payment", JOptionPane.INFORMATION_MESSAGE);
							LocalDate today = LocalDate.now();
							Date date = new Date(today.getDayOfMonth(),today.getMonthValue(),today.getYear() + 1);
							login.getCurrentRegisteredUser().setdate_of_feeDeposit(date);
						} else {
							JOptionPane.showMessageDialog(null, "Annual Fee payment failed.",
									(String) "Annual Fee Payment", JOptionPane.INFORMATION_MESSAGE);
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Annual Fee is already payed.",
								(String) "Annual Fee Payment", JOptionPane.INFORMATION_MESSAGE);
					}

					frame.revalidate();
				}
			});
			annualFeeButton.setBounds(915, 14, 160, 32);
			add(annualFeeButton);
			
		}

	
		cartButton = new JLabel("View Cart");
		cartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cartButton.setFont(new Font("Arial", Font.BOLD, 15));
		

		cartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CartView cartPanel = new CartView(frame, login);
				frame.setContentPane(cartPanel);
				frame.revalidate();
			}
		});
		cartButton.setBounds(1250, 14, 180, 32);
		add(cartButton);
		

		
		// Set bg image
		homeBackground = new JLabel("");
		homeBackground.setBounds(-2, -1, 1366, 768);
		homeBackground.setIcon(new ImageIcon(MainView.class.getResource("/bg.png")));
		add(homeBackground);
	}

	public void createSeatGraphic(JFrame frame, Login login) {
		String tempGraphic = "";
		for (int i = 0; i < currentShowtime.getnumber_of_rows(); i++) {
			if (i == 0) {
				tempGraphic += "     ";
				for (int j = 0; j < currentShowtime.getnumber_of_columns(); j++) {
					tempGraphic += j + "  ";
				}
				tempGraphic += "Seat";
				tempGraphic += "\n   ..";
				for (int j = 0; j < currentShowtime.getnumber_of_columns(); j++) {
					tempGraphic += "...";
				}
				tempGraphic += "\n";
			}
			for (int j = 0; j < currentShowtime.getnumber_of_columns(); j++) {
				if (j == 0) {
					tempGraphic += i + "  . ";
				}
				if (currentShowtime.getSeatAvaliability(i, j) == true) {
					tempGraphic += "X  ";
				} else if (login.getCurrentUser() != null) {
					boolean tempFlag = false;
					for (int k = 0; k < login.getCurrentUser().getCart().getitems().size(); k++) {
						if (login.getCurrentUser().getCart().getitems().get(k).getseat_to_book().getSelected_row() == i
								&& login.getCurrentUser().getCart().getitems().get(k).getseat_to_book()
										.getSelected_column() == j
								&& login.getCurrentUser().getCart().getitems().get(k).getshowing_time()
										.getID_for_showtime() == currentShowtime.getID_for_showtime()) {
							tempGraphic += "/  ";
							tempFlag = true;
						}
					}
					if (tempFlag == false) {
						tempGraphic += "✔️  ";
					}
				} else {
					tempGraphic += "✔️ ";
				}
			}
			tempGraphic += "\n";
		}
		tempGraphic += "Row\n✔️ = Free, / = Selected, X = Taken";
		seatGraphicLabel.setText(tempGraphic);
		seatGraphicLabel.setVisible(true);
	}

	private static final long serialVersionUID = 1L;
}
