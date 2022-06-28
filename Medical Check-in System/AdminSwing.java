import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class AdminSwing implements ActionListener{
	Connection con = null;
	Staff s;
	static JFrame adminFrame;
	static JButton addStaff, removeStaff, viewStaff, back, end;
	
	AdminSwing(){}			//Constructor 
	
	AdminSwing(Connection con){
		this.con = con;
		s = new Staff(con);
		
		adminFrame = new JFrame("Options");
		adminFrame.setLayout(null);
		adminFrame.setSize(600, 600);
		adminFrame.setVisible(true);
		
		addStaff = new JButton("Add Staff");addStaff.setBounds(80, 50, 200, 30);
		removeStaff = new JButton("Remove Staff");removeStaff.setBounds(80, 100, 200, 30);
		viewStaff = new JButton("View Staff");viewStaff.setBounds(80, 150, 200, 30);
		back = new JButton("Back");back.setBounds(80, 200, 200, 30);
		end = new JButton("End");end.setBounds(80, 250, 200, 30);
		
		adminFrame.add(addStaff);adminFrame.add(removeStaff);adminFrame.add(viewStaff);
		adminFrame.add(back);adminFrame.add(end);
		
		
		
		addStaff.addActionListener(this);removeStaff.addActionListener(this);
		viewStaff.addActionListener(this);back.addActionListener(this);
		end.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==addStaff) {
			s.addStaff();
		}
		if(ae.getSource()==removeStaff) {
			s.removeStaff();
		}
		if(ae.getSource()==viewStaff) {
			 s.viewStaff();
		}
		if(ae.getSource()==back) {
			adminFrame.setVisible(false);
			LoginSwing.loginFrame.setVisible(true);
		}
		if(ae.getSource()==end) {
			System.exit(0);
		}
	} 
	
	
}
