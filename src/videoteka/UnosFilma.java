package videoteka1;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.JFrame;

public class UnosFilma {

	private JFrame frame;
	private JTextField naziv;
	private JTextField godIzd;
	private JTextField redatelj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosFilma window = new UnosFilma();
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
	public UnosFilma() {
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
		
		JLabel lblNewLabel = new JLabel("Unos filma");
		lblNewLabel.setBounds(159, 20, 93, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Naziv filma");
		lblNewLabel_1.setBounds(40, 77, 78, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Godina izdanja");
		lblNewLabel_2.setBounds(40, 120, 118, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Redatelj");
		lblNewLabel_3.setBounds(40, 157, 67, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		naziv = new JTextField();
		naziv.setBounds(188, 74, 96, 19);
		frame.getContentPane().add(naziv);
		naziv.setColumns(10);
		
		godIzd = new JTextField();
		godIzd.setBounds(188, 117, 96, 19);
		frame.getContentPane().add(godIzd);
		godIzd.setColumns(10);
		
		redatelj = new JTextField();
		redatelj.setBounds(188, 154, 96, 19);
		frame.getContentPane().add(redatelj);
		redatelj.setColumns(10);
		
		JButton btnNewButton = new JButton("Unesi film");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nazivs, redateljs, godIzds;
				nazivs=naziv.getText();
				godIzds=godIzd.getText();
				redateljs=redatelj.getText();
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/pdukic?serverTimezone=UTC","pdukic","11");
					String insert="INSERT INTO film_videoteka (naziv,god_izdanja,redatelj) VALUES (?,?,?)"; 
					PreparedStatement ps=con.prepareStatement(insert);
					ps.setString(1, nazivs);
					ps.setString(2, godIzds);
					ps.setString(3,redateljs);
					
					int ubacenoRedaka=ps.executeUpdate();
					
					if(ubacenoRedaka==1) {
						JOptionPane.showMessageDialog(null, "Novi film uspjesno unesen");
					}
					else {
						JOptionPane.showMessageDialog(null, "Greska u unosu novog filma");
					}
				}
					
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					e1.printStackTrace();
				}
			
				
				
			}
		});
		btnNewButton.setBounds(156, 202, 128, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Izbornik");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Izbornik gi = new Izbornik();
				gi.showWindow();
			}
		});
		btnNewButton_1.setBounds(321, 116, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
	}
	public void showWindow(){		
		frame.setVisible(true);
	}

}
