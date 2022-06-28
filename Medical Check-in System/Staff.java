import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.sql.*;

public class Staff extends AdminSwing implements ActionListener{
	Connection con = null;
	static JTextArea jta1, jta2;
	static JFrame addValues, removeValues, displayFrame;
	static JLabel idlabel, namelabel, wardlabel, removelabel;
	static JTextField id, name, ward, removeid;
	static JButton add, back1, back2, end1, end2, remove;
	
	String info = "";
	
	Staff(Connection con){
		this.con = con;
		
		addValues = new JFrame("Add Staff");
		addValues.setLayout(null);
		addValues.setSize(500, 500);
		addValues.setVisible(false);
		
		removeValues = new JFrame("Remove Staff");
		removeValues.setLayout(null);
		removeValues.setSize(500, 300);
		removeValues.setVisible(false);
		
		displayFrame = new JFrame("Current Records in the Database:");
		displayFrame.setLayout(null);
		displayFrame.setSize(500, 350);
		displayFrame.setVisible(false);
		
		jta1 = new JTextArea("");jta1.setBounds(0, 0, 500, 350);
		displayFrame.add(jta1);
		
		idlabel = new JLabel("Enter the Staff ID");idlabel.setBounds(40, 50, 200, 30);
		namelabel = new JLabel("Enter the Staff Name");namelabel.setBounds(40, 80, 200, 30);
		wardlabel = new JLabel("Enter the Staff Ward");wardlabel.setBounds(40, 110, 200, 30);
		removelabel = new JLabel("Enter the ID of the staff to remove");removelabel.setBounds(40, 50, 200, 30);
		
		id = new JTextField("");id.setBounds(250, 55, 200, 20);
		name = new JTextField("");name.setBounds(250, 85, 200, 20);
		ward = new JTextField("");ward.setBounds(250, 115, 200, 20);
		removeid = new JTextField("");removeid.setBounds(250, 55, 200, 20);
		
		add = new JButton("Add");add.setBounds(80, 170, 80, 30);
		back1 = new JButton("Back");back1.setBounds(170, 170, 80, 30);
		back2 = new JButton("Back");back2.setBounds(170, 170, 80, 30);
		end1 = new JButton("End"); end1.setBounds(260, 170, 80, 30);
		end2 = new JButton("End"); end2.setBounds(260, 170, 80, 30);
		remove = new JButton("Remove"); remove.setBounds(80,170,80,30);
		
		addValues.add(idlabel);addValues.add(namelabel);addValues.add(wardlabel);
		addValues.add(id);addValues.add(name);addValues.add(ward);
		addValues.add(add);addValues.add(back1); addValues.add(end1);
		
		removeValues.add(removelabel);removeValues.add(removeid);removeValues.add(remove);
		removeValues.add(back2);removeValues.add(end2);
		
		add.addActionListener(this); back1.addActionListener(this);end1.addActionListener(this);
		remove.addActionListener(this);back2.addActionListener(this);end2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==add) {
			try {
				addValues();
			}catch(Exception e) {
				System.out.println(e);
			}
		}if(ae.getSource()==back1) {
			adminFrame.setVisible(true);
			addValues.setVisible(false);
		}if(ae.getSource()==end1) {
			System.exit(0);
		}if(ae.getSource()==back2) {
			adminFrame.setVisible(true);
			removeValues.setVisible(false);
		}if(ae.getSource()==end2) {
			System.exit(0);
		}
		if(ae.getSource()==remove) {
			try {
				deleteValues();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void addStaff() {
		Scanner scn = new Scanner(System.in);
		adminFrame.setVisible(false);
		addValues.setVisible(true);
	}
	public void addValues()throws SQLException, IOException, ClassNotFoundException {
		int staff_ID = Integer.parseInt(id.getText());
		String staff_Name = name.getText();
		String staff_Ward = ward.getText();
		
		PreparedStatement smt=con.prepareStatement("insert into staff values(?,?,?)");  
		smt.setInt(1, staff_ID);
		smt.setString(2, staff_Name);
		smt.setString(3, staff_Ward);
		int i = smt.executeUpdate(); 
		
		JOptionPane.showMessageDialog(addValues,"Staff added Successfully"); 
	}
	
	public void removeStaff() {
		System.out.println("Current entries in the dataset are:");
		info = "CURRENT ENTRIES IN THE DATASET ARE:\n";
		adminFrame.setVisible(false);
		removeValues.setVisible(true);
		viewStaff();
	}
	
	public void deleteValues()throws SQLException, IOException, ClassNotFoundException {
		int del = Integer.parseInt(removeid.getText());
		PreparedStatement smt = con.prepareStatement("delete from staff where staffNo = "+del);
		int i = smt.executeUpdate(); 
		JOptionPane.showMessageDialog(removeValues,"Staff deleted Successfully"); 
	}
	
	public void viewStaff() {
		try {
			display(con);
		}catch(Exception e) {
			System.out.println("The Error is: "+e);
		}  
	}
	public void display(Connection con) throws SQLException, ClassNotFoundException {
		info = "";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from staff");
		while(rs.next()) {
			System.out.println("\nStaffNo: "+rs.getInt(1)+"\nStaff Name:"+rs.getString(2)+"\nWard: "+rs.getString(3));
			info = info + "Staff Number: "+ rs.getInt(1)+"\n"+"Staff Name: "+rs.getString(2)+"\n" +"Ward: " +rs.getString(3)+"\n\n";	
		}
		displayFrame.setVisible(true);
		jta1.setText(info);
	}
}
