import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class GradingInput {

	private JFrame frmMarkingInEnglish;
	private JTable table;
	private JFrame frame;
	private JTextField textField;
	private JTable table_1;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradingInput window = new GradingInput();
					window.frmMarkingInEnglish.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connectionVariable = null; // connect to database
	private JTextField nameVar;
	private JTextField rollVar;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	/**
	 * Create the application.
	 */
	public GradingInput() {
		initialize();

		connectionVariable = SqliteConnection2.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMarkingInEnglish = new JFrame();
		frmMarkingInEnglish.setTitle("Marking in English");
		frmMarkingInEnglish.setBounds(100, 100, 737, 422);
		frmMarkingInEnglish.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMarkingInEnglish.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(486, 37, 161, 267);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Student's Name", "Roll No.", "Mark (1st Semester)"
			}
		));
		frmMarkingInEnglish.getContentPane().add(table);
		
		JLabel lblMarkingInEnglish = new JLabel("Enter Marking in English");
		lblMarkingInEnglish.setBounds(50, 11, 161, 14);
		frmMarkingInEnglish.getContentPane().add(lblMarkingInEnglish);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{
				// Generate Grading based on input (Mark)  
					int n1 = Integer.parseInt(textField.getText());
					int n2 = Integer.parseInt(textField_1.getText());
					int n3 = Integer.parseInt(textField_2.getText());
					
					// Create an array with room for 100 integers
					int[] nums = {n1, n2, n3};
					System.out.println(nums);
				//	for (nums = nums[0]; n <= length(nums); n = n + 1) {
					if(n1<40)
						textField_5.setText("F");

					if(n2<50)
						textField_6.setText("D");

					if(n3<60)
						textField_7.setText("C");
		//			}
				}
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				model.addRow(new Object[] {
						textField.getText(),
						textField_1.getText(),
						textField_2.getText(),
						
						
				});
				
				try {
					String query = "insert into MarkAndGradeTable values (?,?,?,?,?,?,?,?)"; // add info into the database
					PreparedStatement pStatement =  connectionVariable.prepareStatement(query);
					pStatement.setString(1, nameVar.getText());
					pStatement.setInt(2, Integer.parseInt(rollVar.getText()));
					
					pStatement.setInt(3, Integer.parseInt(textField.getText()));
					pStatement.setString(4, textField_5.getText());
					pStatement.setInt(5, Integer.parseInt(textField_1.getText()));
					pStatement.setString(6, textField_6.getText());
					pStatement.setInt(7, Integer.parseInt(textField_2.getText()));
					pStatement.setString(8, textField_7.getText());
						
					pStatement.execute();
					JOptionPane.showMessageDialog(null, "Data has been stored in the database");
				}
				catch(Exception exception){
					JOptionPane.showMessageDialog(null, exception);
				}
			}
		});
		btnSave.setBounds(63, 331, 89, 23);
		frmMarkingInEnglish.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Reset");
		btnEdit.setBounds(308, 331, 89, 23);
		frmMarkingInEnglish.getContentPane().add(btnEdit);
		
		JButton btnEdit_1 = new JButton("Edit");
		btnEdit_1.setBounds(190, 331, 89, 23);
		frmMarkingInEnglish.getContentPane().add(btnEdit_1);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(438, 331, 89, 23);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame = new JFrame("Exit");
					if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to EXIT", "Mark entry page",
							JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
						System.exit(0);
					}
			}
		});
		frmMarkingInEnglish.getContentPane().add(btnExit);
		
		textField = new JTextField();
		textField.setBounds(222, 95, 86, 20);
		frmMarkingInEnglish.getContentPane().add(textField);
		textField.setColumns(10);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table_1.setBounds(10, 37, 161, 267);
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		frmMarkingInEnglish.getContentPane().add(table_1);
		
		JLabel lblFinalMarkingIn = new JLabel("Final Marking in English");
		lblFinalMarkingIn.setBounds(485, 11, 136, 14);
		frmMarkingInEnglish.getContentPane().add(lblFinalMarkingIn);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(222, 126, 86, 20);
		frmMarkingInEnglish.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(222, 157, 86, 20);
		frmMarkingInEnglish.getContentPane().add(textField_2);
		
		nameVar = new JTextField();
		nameVar.setBounds(222, 34, 86, 20);
		frmMarkingInEnglish.getContentPane().add(nameVar);
		nameVar.setColumns(10);
		
		rollVar = new JTextField();
		rollVar.setColumns(10);
		rollVar.setBounds(343, 34, 86, 20);
		frmMarkingInEnglish.getContentPane().add(rollVar);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(343, 95, 86, 20);
		frmMarkingInEnglish.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(343, 126, 86, 20);
		frmMarkingInEnglish.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(343, 157, 86, 20);
		frmMarkingInEnglish.getContentPane().add(textField_7);
		
		JLabel lblMark = new JLabel("Mark");
		lblMark.setBounds(222, 77, 46, 14);
		frmMarkingInEnglish.getContentPane().add(lblMark);
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setBounds(343, 77, 46, 14);
		frmMarkingInEnglish.getContentPane().add(lblGrade);
	}
}
