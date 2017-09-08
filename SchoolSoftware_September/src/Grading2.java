import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;  // import necessary library for connection

public class Grading2 {

	private JFrame frmStudentsGrading;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void mainFuncGrading2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grading2 window = new Grading2();
					window.frmStudentsGrading.setVisible(true);
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
	public Grading2() {
		initialize();
		connectionVariable = SqliteConnection2.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStudentsGrading = new JFrame();
		frmStudentsGrading.setTitle("Student's Grading");
		frmStudentsGrading.setBounds(100, 100, 687, 448);
		frmStudentsGrading.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentsGrading.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Input area", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(24, 22, 326, 190);
		frmStudentsGrading.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblStudentsName = new JLabel("Student's Name     ");
		lblStudentsName.setBounds(10, 22, 92, 19);
		lblStudentsName.setEnabled(false);
		panel.add(lblStudentsName);
		
		textField = new JTextField();
		textField.setBounds(119, 21, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 66, 86, 20);
		panel.add(textField_1);
		
		JLabel label = new JLabel("Roll Number");
		label.setEnabled(false);
		label.setBounds(10, 53, 92, 19);
		panel.add(label);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(119, 83, 86, 20);
		panel.add(textField_2);
		
		JLabel label_1 = new JLabel("Contact Mobile");
		label_1.setEnabled(false);
		label_1.setBounds(10, 84, 92, 19);
		panel.add(label_1);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setBounds(10, 114, 46, 14);
		panel.add(lblClass);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Class 6", "Class 7", "Class 8", "Class 9", "Class 10"}));
		comboBox.setBounds(119, 111, 86, 20);
		panel.add(comboBox);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into BasicInfoOfStudents values (?,?,?,?)"; // add info into the database
					PreparedStatement pStatement =  connectionVariable.prepareStatement(query);
					pStatement.setString(1, textField.getText());
					pStatement.setInt(2, Integer.parseInt(textField_1.getText()));
					pStatement.setString(3, textField_2.getText());
					pStatement.setString(4, comboBox.getSelectedItem().toString());
					
					pStatement.execute();
					JOptionPane.showMessageDialog(null, "Data has been stored in the database");
				}
				catch(Exception exception){
					JOptionPane.showMessageDialog(null, exception);
				}
			
		}
		});
		btnCreate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCreate.setBounds(119, 156, 89, 23);
		panel.add(btnCreate);
		
		
	}

	
}
