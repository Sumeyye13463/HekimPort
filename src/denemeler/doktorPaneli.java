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

public class doktorPaneli extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doktorPaneli frame = new doktorPaneli();
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
	public doktorPaneli() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_1 = new JLabel("DOKTOR PANELİNE HOŞGELDİNİZ");
		lbl_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbl_1.setBounds(116, 48, 360, 39);
		contentPane.add(lbl_1);
		
		JButton btnKaytYap = new JButton("Kayıt Yap");
		btnKaytYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new doktorKayitPaneli().setVisible(true);
			}
		});
		btnKaytYap.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnKaytYap.setBounds(206, 139, 167, 29);
		contentPane.add(btnKaytYap);
		
		JButton btnGiriYap = new JButton("Giriş Yap");
		btnGiriYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new doktorGirisPaneli().setVisible(true);
			}
		});
		btnGiriYap.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnGiriYap.setBounds(206, 196, 167, 29);
		contentPane.add(btnGiriYap);
		
		JButton btnGeriDn = new JButton("Geri Dön");
		btnGeriDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new girisPaneli().setVisible(true);
			}
		});
		btnGeriDn.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnGeriDn.setBounds(31, 315, 167, 29);
		contentPane.add(btnGeriDn);
	}
}
