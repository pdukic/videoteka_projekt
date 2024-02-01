package videoteka1;

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

public class ObrisiClan {

	private JFrame frame;
	private JTable tablica;
	private JButton btnNewButton_1;
	private JTextField clan_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObrisiClan window = new ObrisiClan();
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
	public ObrisiClan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 733, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ispis clanova");
		lblNewLabel.setBounds(328, 10, 81, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Izbornik");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Izbornik iz = new Izbornik();
				iz.showWindow();
			}
		});
		btnNewButton.setBounds(437, 248, 131, 21);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(199, 33, 456, 205);
		frame.getContentPane().add(scrollPane);
		
		tablica = new JTable();
		scrollPane.setViewportView(tablica);
		tablica.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID clana", "ime", "prezime", "Broj mobitela"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		btnNewButton_1 = new JButton("Ispis clanova");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/pdukic?serverTimezone=UTC","pdukic","11");
					String upit="SELECT * FROM registracija_videoteka";
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(upit);

					DefaultTableModel model = (DefaultTableModel) tablica.getModel();
					model.setRowCount(0);
					
					while (rs.next()){
						int clan_id=rs.getInt(1);
						String ime=rs.getString(2);
						String prezime=rs.getString(3);
						String brojMob=rs.getString(4);
						model.addRow(new Object[] {clan_id, ime, prezime, brojMob});
						}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Greška servera!");
					
				}
				
			}
		});
		btnNewButton_1.setBounds(241, 248, 131, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("ID clana za brisanje");
		lblNewLabel_1.setBounds(20, 40, 102, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		clan_id = new JTextField();
		clan_id.setBounds(20, 63, 96, 19);
		frame.getContentPane().add(clan_id);
		clan_id.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Obrisi");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/pdukic?serverTimezone=UTC","pdukic","11");
					
					String upit="DELETE FROM registracija_videoteka WHERE clan_id='"+clan_id.getText()+"'  ";
					
					PreparedStatement ps=con.prepareStatement(upit);
					
					int deleteRedaka=ps.executeUpdate();
					
					if (deleteRedaka==1)
					{
						JOptionPane.showMessageDialog(null, "Clan je obrisan");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Doslo je do greske");
					}
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
				
			}
		});
		btnNewButton_2.setBounds(20, 116, 85, 21);
		frame.getContentPane().add(btnNewButton_2);
		
	
	}
	public void showWindow(){		
		frame.setVisible(true);
	}
}
