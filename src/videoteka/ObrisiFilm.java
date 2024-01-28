package videoteka;

import java.awt.EventQueue;

import java.sql.*;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ObrisiFilm {

	private JFrame frame;
	private JTable tablica;
	private JButton btnNewButton_1;
	private JTextField film_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObrisiFilm window = new ObrisiFilm();
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
	public ObrisiFilm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 697, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ispis filmova");
		lblNewLabel.setBounds(213, 10, 81, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Izbornik");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Izbornik iz = new Izbornik();
				iz.showWindow();
			}
		});
		btnNewButton.setBounds(439, 248, 128, 21);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 33, 401, 205);
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
		btnNewButton_1.setBounds(264, 248, 128, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("ID filma za brisanje");
		lblNewLabel_1.setBounds(25, 40, 128, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		film_id = new JTextField();
		film_id.setBounds(25, 63, 96, 19);
		frame.getContentPane().add(film_id);
		film_id.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Obrisi");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/pdukic?serverTimezone=UTC","pdukic","11");
					
					String upit="DELETE FROM film_videoteka WHERE film_id='"+film_id.getText()+"'  ";
					
					PreparedStatement ps=con.prepareStatement(upit);
					
					int deleteRedaka=ps.executeUpdate();
					
					if (deleteRedaka==1)
					{
						System.out.println("Film je obrisan");
					}
					else
					{
						System.out.println("Doslo je do greske");
					}
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
		});
		btnNewButton_2.setBounds(25, 150, 85, 21);
		frame.getContentPane().add(btnNewButton_2);
		
	
	}
	public void showWindow(){		
		frame.setVisible(true);
	}
}
