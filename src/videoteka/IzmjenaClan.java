package videoteka;


import java.sql.*;
import java.awt.EventQueue;
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


public class IzmjenaClan{

	private JFrame frame;
	private JTable tablica;
	private JButton btnNewButton_1;
	private JTextField clan_id;
	private JTextField ime;
	private JTextField prezime;
	private JTextField brojMob;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmjenaClan window = new IzmjenaClan();
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
	public IzmjenaClan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 556, 327);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ispis clanova");
		lblNewLabel.setBounds(213, 10, 81, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Izbornik");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Izbornik iz = new Izbornik();
				iz.showWindow();
			}
		});
		btnNewButton.setBounds(358, 259, 131, 21);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 33, 326, 205);
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
					String upit="SELECT * FROM clan_videoteka";
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
		btnNewButton_1.setBounds(217, 259, 131, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Novi podaci");
		lblNewLabel_1.setBounds(20, 66, 81, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Unesi ID clana");
		lblNewLabel_2.setBounds(20, 10, 81, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		clan_id = new JTextField();
		clan_id.setBounds(20, 37, 96, 19);
		frame.getContentPane().add(clan_id);
		clan_id.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ime");
		lblNewLabel_3.setBounds(20, 89, 45, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		ime = new JTextField();
		ime.setBounds(20, 112, 96, 19);
		frame.getContentPane().add(ime);
		ime.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Prezime");
		lblNewLabel_4.setBounds(20, 141, 70, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		prezime = new JTextField();
		prezime.setBounds(20, 164, 96, 19);
		frame.getContentPane().add(prezime);
		prezime.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Broj mobitela");
		lblNewLabel_5.setBounds(20, 193, 96, 13);
		frame.getContentPane().add(lblNewLabel_5);
		
		brojMob = new JTextField();
		brojMob.setBounds(20, 216, 96, 19);
		frame.getContentPane().add(brojMob);
		brojMob.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Azuriraj podatke");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/pdukic?serverTimezone=UTC","pdukic","11");
					String update="UPDATE clan_videoteka SET ime='"+ime.getText()+"' , prezime='"+prezime.getText()+"' , brojMob='"+brojMob.getText()+"' WHERE clan_id='"+clan_id.getText()+"' "; 
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
		btnNewButton_2.setBounds(20, 259, 131, 21);
		frame.getContentPane().add(btnNewButton_2);
		
	
	}
	public void showWindow(){		
		frame.setVisible(true);
	}
}
