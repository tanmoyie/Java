/* A simple login page to verify username and password
 * written in Java along with sqlite
 * Begineer level
 * Copyright @Tanmoy September 2017
 */
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;  // import necessary library for connection

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;

public class LoginPage {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblPassword;
	private JPasswordField passwordField_1;
	/**
	 * @wbp.nonvisual location=145,-11
	 */
	private final JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connectionVariable = null;
	
	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
		connectionVariable = SqliteConnection2.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 620, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBackground(Color.ORANGE);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserName.setBounds(110, 105, 105, 25);
		frame.getContentPane().add(lblUserName);


		
		textField = new JTextField();
		textField.setBounds(225, 105, 140, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query ="select * from LoginInfo where UserName=? AND Password=? "; 
					PreparedStatement pStatement =  connectionVariable.prepareStatement(query);
					pStatement.setString(1, textField.getText());
					pStatement.setString(2, passwordField_1.getText());
					
					ResultSet resultObject = pStatement.executeQuery();
					int count = 0;
					while(resultObject.next()) {
						count = count+1;
						
					}
					if(count == 1) {
						JOptionPane.showMessageDialog(null, "Username & Password is correct");
						frame.dispose();
						Grading2 grading = new Grading2();
						grading.mainFuncGrading2();
					//	grading.setVisible(true);
					}
					
					else {
						JOptionPane.showMessageDialog(null, "username and/or password are not correct, please try again");						
					}

					// sqlite has one database, close connection is necessary
					resultObject.close();
					pStatement.close();
				}
				catch(Exception exception){
					JOptionPane.showMessageDialog(null, exception);
				}

	}
		});
		btnNewButton.setBounds(225, 221, 140, 37);
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(326, 162, -95, 14);
		frame.getContentPane().add(passwordField);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPassword.setBounds(110, 162, 94, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setEchoChar('*');
		passwordField_1.setBounds(225, 156, 142, 25);
		frame.getContentPane().add(passwordField_1);
		
		JLabel lblHighSchool = new JLabel("Marjal K.M.B High School");
		lblHighSchool.setBounds(185, 44, 180, 14);
		frame.getContentPane().add(lblHighSchool);
	}
}
