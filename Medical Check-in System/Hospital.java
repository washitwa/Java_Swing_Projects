import java.sql.*;
import java.io.*;
public class Hospital {
	public static void main(String[] args)throws SQLException, IOException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Laptop@140");
		Statement smt = con.createStatement();
		System.out.println("Connected to database");
		LoginSwing sc = new LoginSwing(con);
	}
}
