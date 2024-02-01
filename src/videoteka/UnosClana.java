package videoteka1;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPasswordField;




public class UnosClana {

	private JFrame frame;
	private JTextField ime;
	private JTextField prezime;
	private JTextField brojMob;
	private JPasswordField lozinka;
	private JPasswordField ponLozinka;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosClana window = new UnosClana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UnosClana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 580, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Unos clana");
		lblNewLabel.setBounds(150, 10, 89, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ime");
		lblNewLabel_1.setBounds(34, 56, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prezime");
		lblNewLabel_2.setBounds(34, 93, 65, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Broj mobitela");
		lblNewLabel_3.setBounds(34, 127, 89, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		ime = new JTextField();
		ime.setBounds(150, 53, 96, 19);
		frame.getContentPane().add(ime);
		ime.setColumns(10);
		
		prezime = new JTextField();
		prezime.setBounds(150, 90, 96, 19);
		frame.getContentPane().add(prezime);
		prezime.setColumns(10);
		
		brojMob = new JTextField();
		brojMob.setBounds(150, 124, 96, 19);
		frame.getContentPane().add(brojMob);
		brojMob.setColumns(10);
		
		JButton btnNewButton = new JButton("Unesi clana");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String imes, prezimes, brojMobs, lozinkas, ponLozinkas;
				imes = ime.getText();
				prezimes = prezime.getText();
				brojMobs = brojMob.getText();
				lozinkas=new String (lozinka.getPassword());
				ponLozinkas=new String(ponLozinka.getPassword());				
				
				if(lozinkas.equals(ponLozinkas)) {			
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/pdukic?serverTimezone=UTC","pdukic","11");
					String insert="INSERT INTO registracija_videoteka (ime,prezime,brojMob, lozinka) VALUES (?,?,?,?)"; 
					PreparedStatement ps=con.prepareStatement(insert);
					ps.setString(1, imes);
					ps.setString(2,prezimes);
					ps.setString(3, brojMobs);	
					ps.setString(4, lozinkas);
					
					int ubacenoRedaka=ps.executeUpdate();
					
					if(ubacenoRedaka==1) {
						JOptionPane.showMessageDialog(null, "Novi clan uspjesno unesen");
					}
					else {
						JOptionPane.showMessageDialog(null, "Greska u unosu novog clana");
					}
				}
				
					
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					e1.printStackTrace();
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Lozinke nisu jednake");
				}
				
				
			}
				
		});
		btnNewButton.setBounds(150, 237, 124, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Izbornik");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Izbornik gi = new Izbornik();
				gi.showWindow();
			}
		});
		btnNewButton_1.setBounds(150, 268, 124, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Lozinka");
		lblNewLabel_4.setBounds(34, 163, 65, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ponovljena lozinka");
		lblNewLabel_5.setBounds(34, 203, 101, 13);
		frame.getContentPane().add(lblNewLabel_5);
		
		lozinka = new JPasswordField();
		lozinka.setBounds(150, 160, 96, 19);
		frame.getContentPane().add(lozinka);
		
		ponLozinka = new JPasswordField();
		ponLozinka.setBounds(150, 194, 96, 19);
		frame.getContentPane().add(ponLozinka);
	}
	public void showWindow(){		
		frame.setVisible(true);
	}
}
