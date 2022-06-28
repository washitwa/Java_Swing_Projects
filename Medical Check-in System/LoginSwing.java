import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class LoginSwing implements ActionListener {
	Connection con = null;
	static JFrame loginFrame;
	static JButton adminlogin, stafflogin, end;
	LoginSwing(Connection con){
		this.con = con;
		
		loginFrame = new JFrame("Login");
		loginFrame.setLayout(null);;
		loginFrame.setSize(400, 250);
		loginFrame.setVisible(true);
		
		adminlogin = new JButton("Admin Login");adminlogin.setBounds(80, 50, 200, 30);
		stafflogin = new JButton("Staff Login");stafflogin.setBounds(80, 100, 200, 30);
		end = new JButton("End");end.setBounds(80, 150, 200, 30);
		
		loginFrame.add(adminlogin);
		loginFrame.add(stafflogin);
		loginFrame.add(end);
		
		adminlogin.addActionListener(this);
		stafflogin.addActionListener(this);
		end.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==adminlogin) {
			String pass = JOptionPane.showInputDialog(loginFrame, "Enter the Password");
			if(pass.equals("Admin")) {
				JOptionPane.showMessageDialog(loginFrame,"Logged in Successfully");
				loginFrame.setVisible(false);
				AdminSwing as = new AdminSwing(con);
			}else {
				JOptionPane.showMessageDialog(loginFrame,"Username or Password Error!"); 
			}
		}if(ae.getSource()==stafflogin) {
			String pass = JOptionPane.showInputDialog(loginFrame, "Enter the Password");
			if(pass.equals("Staff")) {
				JOptionPane.showMessageDialog(loginFrame,"Logged in Successfully");
				loginFrame.setVisible(false);
				StaffSwing as = new StaffSwing(con);
			}else {
				JOptionPane.showMessageDialog(loginFrame,"Username or Password Error!"); 
			}
		}if(ae.getSource()==end) {
			System.exit(0);
		}
	}
}
