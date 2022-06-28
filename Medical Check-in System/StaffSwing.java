import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class StaffSwing implements ActionListener{
	Connection con = null;
	Patient p;
	static JFrame staffFrame;
	static JButton addPatient, removePatient, viewPatient, back, end;
	
	StaffSwing(){}			//Constructor 
	
	StaffSwing(Connection con){
		this.con = con;
		p = new Patient(con);
		
		staffFrame = new JFrame("Options");
		staffFrame.setLayout(null);
		staffFrame.setSize(600, 600);
		staffFrame.setVisible(true);
		
		addPatient = new JButton("Add Patient");addPatient.setBounds(80, 50, 200, 30);
		removePatient = new JButton("Remove Patient");removePatient.setBounds(80, 100, 200, 30);
		viewPatient = new JButton("View Patient");viewPatient.setBounds(80, 150, 200, 30);
		back = new JButton("Back");back.setBounds(80, 200, 200, 30);
		end = new JButton("End");end.setBounds(80, 250, 200, 30);
		
		staffFrame.add(addPatient);staffFrame.add(removePatient);staffFrame.add(viewPatient);
		staffFrame.add(back);staffFrame.add(end);
		
		
		
		addPatient.addActionListener(this);removePatient.addActionListener(this);
		viewPatient.addActionListener(this);back.addActionListener(this);
		end.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==addPatient) {
			p.addPatient();
		}
		if(ae.getSource()==removePatient) {
			p.removePatient();
		}
		if(ae.getSource()==viewPatient) {
			 p.viewPatient();
		}
		if(ae.getSource()==back) {
			staffFrame.setVisible(false);
			LoginSwing.loginFrame.setVisible(true);
		}
		if(ae.getSource()==end) {
			System.exit(0);
		}
	} 
	
	
}
