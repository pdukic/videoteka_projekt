package videoteka1;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Izbornik {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Izbornik window = new Izbornik();
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
	public Izbornik() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 704, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Izbornik");
		lblNewLabel.setBounds(183, 10, 64, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Unos filma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosFilma uf = new UnosFilma();
				uf.showWindow();
			}
		});
		btnNewButton.setBounds(35, 44, 158, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Unos clana");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosClana uc = new UnosClana();
				uc.showWindow();
			}
		});
		btnNewButton_1.setBounds(217, 44, 158, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Ispis svih filmova");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IspisFilm ifilm= new IspisFilm();
				ifilm.showWindow();
			}
		});
		btnNewButton_2.setBounds(35, 89, 158, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ispis svih clanova");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IspisClan iclan= new IspisClan();
				iclan.showWindow();
			}
		});
		btnNewButton_3.setBounds(217, 89, 158, 21);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Izmijeni podatke o filmu");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IzmjenaFilm ifilm= new IzmjenaFilm();
				ifilm.showWindow();
			}
		});
		btnNewButton_4.setBounds(35, 134, 158, 21);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Izmijeni podatke o clanu");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IzmjenaClan iclan = new IzmjenaClan();
				iclan.showWindow();
			}
		});
		btnNewButton_5.setBounds(217, 134, 158, 21);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Izbrisi film");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObrisiFilm ofilm= new ObrisiFilm();
				ofilm.showWindow();
			}
		});
		btnNewButton_6.setBounds(35, 177, 158, 21);
		frame.getContentPane().add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Izbrisi clana");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObrisiClan oclan = new ObrisiClan();
				oclan.showWindow();
			}
		});
		btnNewButton_7.setBounds(217, 177, 158, 21);
		frame.getContentPane().add(btnNewButton_7);
	}
	public void showWindow(){		
		frame.setVisible(true);
	}
}
