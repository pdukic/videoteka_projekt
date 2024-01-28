package videoteka;

import java.sql.*;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registracija {

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
					Registracija window = new Registracija();
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
	public Registracija() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registracija");
		lblNewLabel.setBounds(185, 10, 79, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ime");
		lblNewLabel_1.setBounds(35, 51, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prezime");
		lblNewLabel_2.setBounds(35, 88, 79, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Broj mobitela");
		lblNewLabel_3.setBounds(35, 134, 79, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Lozinka");
		lblNewLabel_4.setBounds(35, 173, 45, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ponovljena lozinka");
		lblNewLabel_5.setBounds(35, 204, 103, 13);
		frame.getContentPane().add(lblNewLabel_5);
		
		ime = new JTextField();
		ime.setBounds(168, 48, 96, 19);
		frame.getContentPane().add(ime);
		ime.setColumns(10);
		
		prezime = new JTextField();
		prezime.setBounds(168, 85, 96, 19);
		frame.getContentPane().add(prezime);
		prezime.setColumns(10);
		
		brojMob = new JTextField();
		brojMob.setBounds(168, 131, 96, 19);
		frame.getContentPane().add(brojMob);
		brojMob.setColumns(10);
		
		lozinka = new JPasswordField();
		lozinka.setBounds(168, 170, 96, 19);
		frame.getContentPane().add(lozinka);
		
		ponLozinka = new JPasswordField();
		ponLozinka.setBounds(168, 201, 96, 19);
		frame.getContentPane().add(ponLozinka);
		
		JButton btnNewButton = new JButton("Registriraj se");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
String imes, prezimes, brojMobs, lozinkas,  ponLozinkas;
				
				imes=ime.getText();
				prezimes=prezime.getText();
				brojMobs=brojMob.getText();
				
				lozinkas=new String (lozinka.getPassword());
				ponLozinkas=new String(ponLozinka.getPassword());				
				
				if(lozinkas.equals(ponLozinkas)) {					
					
					
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/pdukic?serverTimezone=UTC","pdukic","11");
						String upit="SELECT * FROM registracija_videoteka WHERE brojMob=?";

						PreparedStatement ps=con.prepareStatement(upit);
						ps.setString(1, brojMobs);							
						ResultSet rs=ps.executeQuery();
						
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Registracija nije moguca, korisnik sa tim brojem mobitela vec postoji");
						}
						else {
						String insert="INSERT INTO registracija_videoteka (ime,prezime,brojMob,lozinka) VALUES (?,?,?,?)";  
						PreparedStatement psInsert=con.prepareStatement(insert);
						psInsert.setString(1, imes);
						psInsert.setString(2, prezimes);
						psInsert.setString(3, brojMobs);
						psInsert.setString(4, lozinkas);
						
						
						int ubacenoRedaka=psInsert.executeUpdate();
							if(ubacenoRedaka==1) {
								JOptionPane.showMessageDialog(null, "Registracija uspjesna");
								Izbornik gi = new Izbornik();
								gi.showWindow();
								
							}
							else {
								JOptionPane.showMessageDialog(null, "Registracija neuspjesna");
							}
						}
						
						
						
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Greska sa povezivanjem servera");
						e1.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Lozinke nisu jednake");
				}
				
				
			}
		});
		btnNewButton.setBounds(150, 230, 114, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Izbornik");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GlavniIzbornik gi = new GlavniIzbornik();
				gi.showWindow();
				
			}
		});
		btnNewButton_1.setBounds(326, 130, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
	}
	public void showWindow(){		
		frame.setVisible(true);
	}
}
