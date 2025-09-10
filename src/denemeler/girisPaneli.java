package denemeler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class girisPaneli extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					girisPaneli frame = new girisPaneli();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public girisPaneli() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_1 = new JLabel("SAĞLIK YÖNETİM PANELİNE HOŞGELDİNİZ");
		lbl_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbl_1.setBounds(106, 59, 471, 39);
		contentPane.add(lbl_1);
		
		JButton btnNewButton = new JButton("Doktor Girişi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new doktorPaneli().setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnNewButton.setBounds(256, 143, 167, 29);
		contentPane.add(btnNewButton);
		
		JButton btnHastaGirii = new JButton("Hasta Girişi");
		btnHastaGirii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new hastaPanel().setVisible(true);
			}
		});
		btnHastaGirii.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnHastaGirii.setBounds(256, 202, 167, 29);
		contentPane.add(btnHastaGirii);
		
		JButton btnk = new JButton("Çıkış");
		btnk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnk.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnk.setBounds(256, 327, 167, 29);
		contentPane.add(btnk);
	}
}
