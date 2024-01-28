package videoteka;

import java.awt.EventQueue;


import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class IzmjenaFilm {

	private JFrame frame;
	private JTable tablica;
	private JButton btnNewButton_1;
	private JTextField film_id;
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
					IzmjenaFilm window = new IzmjenaFilm();
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
	public IzmjenaFilm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 698, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ispis filmova");
		lblNewLabel.setBounds(323, 10, 81, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Izbornik");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Izbornik iz = new Izbornik();
				iz.showWindow();
			}
		});
		btnNewButton.setBounds(419, 251, 128, 21);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(189, 33, 445, 208);
		frame.getContentPane().add(scrollPane);
		
		tablica = new JTable();
		scrollPane.setViewportView(tablica);
		tablica.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID filma", "Naziv", "Godina izdanja", "Redatelj"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		btnNewButton_1 = new JButton("Ispis filmova");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/pdukic?serverTimezone=UTC","pdukic","11");
					String upit="SELECT * FROM film_videoteka";
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(upit);

					DefaultTableModel model = (DefaultTableModel) tablica.getModel();
					model.setRowCount(0);
					
					while (rs.next()){
						int film_id=rs.getInt(1);
						String naziv=rs.getString(2);
						String redatelj=rs.getString(3);
						String godIzd=rs.getString(4);
						model.addRow(new Object[] {film_id, naziv, redatelj, godIzd});
						}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Greška servera!");
					
				}
				
			}
		});
		btnNewButton_1.setBounds(239, 251, 128, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Unesi ID filma");
		lblNewLabel_1.setBounds(20, 10, 91, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		film_id = new JTextField();
		film_id.setBounds(20, 37, 96, 19);
		frame.getContentPane().add(film_id);
		film_id.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Novi podaci");
		lblNewLabel_2.setBounds(20, 66, 91, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Naziv filma");
		lblNewLabel_3.setBounds(20, 89, 91, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		naziv = new JTextField();
		naziv.setBounds(20, 112, 96, 19);
		frame.getContentPane().add(naziv);
		naziv.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Godina izdanja");
		lblNewLabel_4.setBounds(20, 141, 91, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		godIzd = new JTextField();
		godIzd.setBounds(20, 164, 96, 19);
		frame.getContentPane().add(godIzd);
		godIzd.setColumns(10);
		
		JLabel Redatelj = new JLabel("Redatelj");
		Redatelj.setBounds(20, 193, 91, 13);
		frame.getContentPane().add(Redatelj);
		
		redatelj = new JTextField();
		redatelj.setBounds(20, 216, 96, 19);
		frame.getContentPane().add(redatelj);
		redatelj.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Azuriraj podatke");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/pdukic?serverTimezone=UTC","pdukic","11");
					String update="UPDATE film_videoteka SET naziv='"+naziv.getText()+"' , god_izdanja='"+godIzd.getText()+"' , redatelj='"+redatelj.getText()+"' WHERE film_id='"+film_id.getText()+"' "; 
					PreparedStatement ps=con.prepareStatement(update);
				
					
					int ubacenoRedaka=ps.executeUpdate();
					
					if(ubacenoRedaka==1) {
						JOptionPane.showMessageDialog(null, "Podaci uspjesno azurirani!");
					}
					else {
						JOptionPane.showMessageDialog(null, "Greska u azuriranju podataka");
					}
				}
					
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(20, 251, 128, 21);
		frame.getContentPane().add(btnNewButton_2);
		
	
	}
	public void showWindow(){		
		frame.setVisible(true);
	}
}
