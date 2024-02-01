package videoteka1;

import java.awt.EventQueue;
import videoteka1.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import videoteka1.Login;
import videoteka1.Registracija;


public class GlavniIzbornik {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniIzbornik window = new GlavniIzbornik();
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
	public GlavniIzbornik() {
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
		
		JLabel lblNewLabel = new JLabel("Videoteka");
		lblNewLabel.setBounds(171, 34, 90, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lo = new Login();
				lo.showWindow();
			}
		});
		btnNewButton.setBounds(133, 78, 128, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registracija");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registracija re= new Registracija();
				re.showWindow();
			}
		});
		btnNewButton_1.setBounds(133, 131, 128, 21);
		frame.getContentPane().add(btnNewButton_1);
	}
	public void showWindow(){		
		frame.setVisible(true);
	}

}
