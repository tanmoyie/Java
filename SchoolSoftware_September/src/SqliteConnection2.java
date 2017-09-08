import java.sql.*;  // import necessary library
import javax.swing.*;
public class SqliteConnection2 {
	Connection connectionVar = null;

	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connectionVar = DriverManager.getConnection("jdbc:sqlite:D:\\Dropbox\\IT\\Java\\SchoolSoftware_September\\Marjal K.M.B High School.sqlite");
		//	JOptionPane.showMessageDialog(null, "Connection is successful");

			return connectionVar;
		}
		catch(Exception exception){
			JOptionPane.showMessageDialog(null, exception);
			return null;
		}
	}

}
