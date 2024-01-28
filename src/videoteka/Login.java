package videoteka;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField brojMob;
	private JPasswordField lozinka;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
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
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(178, 10, 45, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Broj mobitela");
		lblNewLabel_1.setBounds(46, 53, 77, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lozinka");
		lblNewLabel_2.setBounds(46, 97, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		brojMob = new JTextField();
		brojMob.setBounds(160, 50, 96, 19);
		frame.getContentPane().add(brojMob);
		brojMob.setColumns(10);
		
		lozinka = new JPasswordField();
		lozinka.setBounds(160, 94, 96, 19);
		frame.getContentPane().add(lozinka);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String brojMobs, lozinkas;
				brojMobs=brojMob.getText();
				lozinkas=new String (lozinka.getPassword());
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/pdukic?serverTimezone=UTC","pdukic","11");
					String upit="SELECT * FROM registracija_videoteka WHERE brojMob=? and lozinka=?";
					PreparedStatement ps=con.prepareStatement(upit);
					ps.setString(1, brojMobs);	
					ps.setString(2,lozinkas);
					ResultSet rs=ps.executeQuery();
					
					if (rs.next()) {
						Izbornik gi = new Izbornik();
						gi.showWindow();
					}
					else {
						JOptionPane.showMessageDialog(null, "Login nije moguć, podaci ne odgovaraju");
					}
					
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Greska servera");
					e1.printStackTrace();
					
				}
				
			}
		});
		btnNewButton.setBounds(160, 157, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Izbornik");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlavniIzbornik gi = new GlavniIzbornik();
				gi.showWindow();
			}
		});
		btnNewButton_1.setBounds(306, 119, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
	}
	public void showWindow(){		
		frame.setVisible(true);
	}

}
