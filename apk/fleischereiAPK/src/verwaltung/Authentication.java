package verwaltung;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Authentication {
	private static JFrame authenticationFrame;
	private static PrintWriter ausauth;
	private static BufferedReader inauth;

	public static void main(final String[] args) throws IOException {	
		
		File daten1=new File("daten");
		daten1.mkdir();
		final File authenticationFile = new File("daten/Authentication.txt");
		System.out.println(authenticationFile.getAbsolutePath());
		System.out.println(authenticationFile.isFile());
		ausauth = new PrintWriter(new FileWriter("daten/Authentication.txt", true));
		inauth = new BufferedReader(new FileReader("daten/Authentication.txt"));

		String line = "";

		if ((line = inauth.readLine()) != null) {
			if (passCheck(line)) {

				inauth.close();
				ausauth.close();
				new VerwaltungsGui(true);

			}else {
				PrintWriter writer = new PrintWriter("daten/Authentication.txt");
				writer.print("");
				writer.close();
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							final Authentication window = new Authentication();
							window.authenticationFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		} else {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						final Authentication window = new Authentication();
						window.authenticationFrame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}
	}

	public Authentication() {
		this.initialize();
	}

	private void initialize() {
		(this.authenticationFrame = new JFrame()).setTitle("Authentifizierung");
		this.authenticationFrame.setBounds(500, 500, 350, 200);
		authenticationFrame.setLocationRelativeTo(null);
		this.authenticationFrame.setDefaultCloseOperation(3);
		this.authenticationFrame.getContentPane().setLayout(null);
		final JLabel passwordtext = new JLabel("Passwort eingeben:");
		passwordtext.setBounds(85, 11, 205, 15);
		final JTextField userInput = new JTextField(10);
		final JButton complete = new JButton("Bestätigen");
		final JLabel passwordInfo = new JLabel("Password stimmt nicht!");
		complete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				System.out.println(userInput.getText());

				if (passCheck(userInput.getText())) {
					ausauth.write(userInput.getText());
					ausauth.close();
					try {
						inauth.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					authenticationFrame.setVisible(false);
					new VerwaltungsGui(true);
				} else {
					passwordInfo.setVisible(true);
					userInput.setText("");
				}
			}
		});
		complete.setBackground(Color.LIGHT_GRAY);
		complete.setBounds(85, 60, 152, 30);
		userInput.setBackground(Color.WHITE);
		userInput.setBounds(85, 30, 153, 25);
		passwordInfo.setBackground(Color.BLACK);
		passwordInfo.setBounds(85, 90, 153, 35);
		passwordInfo.setVisible(false);
		this.authenticationFrame.getContentPane().add(complete);
		this.authenticationFrame.getContentPane().add(userInput);
		this.authenticationFrame.getContentPane().add(passwordtext);
		this.authenticationFrame.getContentPane().add(passwordInfo);

	}

	public Authentication(final boolean callback) {
		if (callback) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						final Authentication window = new Authentication();
						window.authenticationFrame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	public static boolean passCheck(String pass) {

		if (pass.equals("16546526879843")) {
			return true;
		}
		if (pass.equals("19689798746541")) {
			System.out.println(java.time.LocalDate.now());
			LocalDate date = java.time.LocalDate.now();
			if (date.getYear() > 2023) {
				return false;
			}
			if (date.getMonthValue() == 4) {
				return false;
			}
//			if (date.getDayOfMonth() > 7) {
//				return false;
//			}
			return true;
		}
		if (pass.equals("16549879876543")) {
			System.out.println(java.time.LocalDate.now());
			LocalDate date = java.time.LocalDate.now();
			if (date.getYear() > 2021) {
				return false;
			}
			if (date.getMonthValue() != 12) {
				return false;
			}
			if (date.getDayOfMonth() > 12) {
				return false;
			}
			return true;
		}
		if (pass.equals("545798494131654")) {
			System.out.println(java.time.LocalDate.now());
			LocalDate date = java.time.LocalDate.now();
			if (date.getYear() > 2021) {
				return false;
			}
			if (date.getMonthValue() != 12) {
				return false;
			}
			if (date.getDayOfMonth() > 1) {
				return false;
			}
			return true;
		}
		System.out.println("bin hier");
		return false;

	}

}
