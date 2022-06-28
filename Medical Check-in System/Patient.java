import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.sql.*;

public class Patient extends StaffSwing implements ActionListener{
	Connection con = null;
	static JTextArea jta1, jta2;
	static JFrame addValues, removeValues, displayFrame;
	static JLabel idlabel, namelabel, agelabel, genderlabel, bloodlabel, phonelabel, removelabel;
	static JTextField id, name, age, gender, blood, phone, removeid;
	static JButton add, back1, back2, end1, end2, remove;
	
	String info = "";
	
	Patient(Connection con){
		this.con = con;
		
		addValues = new JFrame("Add Patient");
		addValues.setLayout(null);
		addValues.setSize(500, 500);
		addValues.setVisible(false);
		
		removeValues = new JFrame("Remove Patient");
		removeValues.setLayout(null);
		removeValues.setSize(500, 300);
		removeValues.setVisible(false);
		
		displayFrame = new JFrame("Current Records in the Database:");
		displayFrame.setLayout(null);
		displayFrame.setSize(500, 350);
		displayFrame.setVisible(false);
		
		jta1 = new JTextArea("");jta1.setBounds(0, 0, 500, 350);
		displayFrame.add(jta1);
		
		idlabel = new JLabel("Enter the Patient ID");idlabel.setBounds(40, 50, 200, 30);
		namelabel = new JLabel("Enter the Patient Name");namelabel.setBounds(40, 80, 200, 30);
		agelabel = new JLabel("Enter the Patient Age");agelabel.setBounds(40, 110, 200, 30);
		genderlabel = new JLabel("Enter the Patient Gender");genderlabel.setBounds(40, 140, 200, 30);
		bloodlabel = new JLabel("Enter the Patient Blood Group");bloodlabel.setBounds(40, 170, 200, 30);
		phonelabel = new JLabel("Enter the Patient Phone no.");phonelabel.setBounds(40, 200, 200, 30);
		removelabel = new JLabel("Enter the ID of the Patient to remove");removelabel.setBounds(40, 50, 200, 30);
		
		id = new JTextField("");id.setBounds(250, 55, 200, 20);
		name = new JTextField("");name.setBounds(250, 85, 200, 20);
		age = new JTextField("");age.setBounds(250, 115, 200, 20);
		gender = new JTextField("");gender.setBounds(250, 145, 200, 20);
		blood = new JTextField("");blood.setBounds(250, 175, 200, 20);
		phone = new JTextField("");phone.setBounds(250, 205, 200, 20);
		removeid = new JTextField("");removeid.setBounds(250, 55, 200, 20);
		
		add = new JButton("Add");add.setBounds(80, 300, 80, 30);
		back1 = new JButton("Back");back1.setBounds(170, 300, 80, 30);
		back2 = new JButton("Back");back2.setBounds(170, 170, 80, 30);
		end1 = new JButton("End"); end1.setBounds(260, 300, 80, 30);
		end2 = new JButton("End"); end2.setBounds(260, 170, 80, 30);
		remove = new JButton("Remove"); remove.setBounds(80,170,80,30);
		
		addValues.add(idlabel);addValues.add(namelabel);addValues.add(agelabel);
		addValues.add(genderlabel);addValues.add(bloodlabel);addValues.add(phonelabel);
		addValues.add(id);addValues.add(name);addValues.add(age);
		addValues.add(gender);addValues.add(blood);addValues.add(phone);
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
			staffFrame.setVisible(true);
			addValues.setVisible(false);
		}if(ae.getSource()==end1) {
			System.exit(0);
		}if(ae.getSource()==back2) {
			staffFrame.setVisible(true);
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
	
	public void addPatient() {
		Scanner scn = new Scanner(System.in);
		staffFrame.setVisible(false);
		addValues.setVisible(true);
	}
	public void addValues()throws SQLException, IOException, ClassNotFoundException {
		int pat_ID = Integer.parseInt(id.getText());
		String pat_Name = name.getText();
		String pat_Age = age.getText();
		String pat_Gender = gender.getText();
		String pat_Blood = blood.getText();
		String pat_Phone = phone.getText();
		
		PreparedStatement smt=con.prepareStatement("insert into patient values(?,?,?,?,?,?)");  
		smt.setInt(1, pat_ID);
		smt.setString(2, pat_Name);
		smt.setString(3, pat_Age);
		smt.setString(4, pat_Gender);
		smt.setString(5, pat_Blood);
		smt.setString(6, pat_Phone);
		int i = smt.executeUpdate(); 
		
		JOptionPane.showMessageDialog(addValues,"Patient added Successfully"); 
	}
	
	public void removePatient() {
		System.out.println("Current entries in the dataset are:");
		info = "CURRENT ENTRIES IN THE DATASET ARE:\n";
		staffFrame.setVisible(false);
		removeValues.setVisible(true);
		viewPatient();
	}
	
	public void deleteValues()throws SQLException, IOException, ClassNotFoundException {
		int del = Integer.parseInt(removeid.getText());
		PreparedStatement smt = con.prepareStatement("delete from patient where pat_Id = "+del);
		int i = smt.executeUpdate(); 
		JOptionPane.showMessageDialog(removeValues,"Patient deleted Successfully"); 
	}
	
	public void viewPatient() {
		try {
			display(con);
		}catch(Exception e) {
			System.out.println("The Error is: "+e);
		}  
	}
	public void display(Connection con) throws SQLException, ClassNotFoundException {
		info = "";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from patient");
		while(rs.next()) {
			System.out.println("\nPatient No: "+rs.getInt(1)+"\nPatient Name:"+rs.getString(2)+"\nPatient Age: "+rs.getString(3)+"\nPatient Gender: "+rs.getString(4)+"\nPatient Blood Group: "+rs.getString(5)+"\nPatient Phone no.: "+rs.getString(6));
			info = info + "Patient Number: "+ rs.getInt(1)+"\n"+"Patient Name: "+rs.getString(2)+"\n" +"Age: " +rs.getString(3)+"\n";	
			info = info + "Gender: "+ rs.getString(4)+"\n"+"Blood Group: "+rs.getString(5)+"\n"+"Phone no.: "+rs.getString(6)+"\n\n";
		}
		displayFrame.setVisible(true);
		jta1.setText(info);
	}
}
