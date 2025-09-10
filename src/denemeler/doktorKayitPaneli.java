package denemeler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import java.awt.event.ActionEvent;

public class doktorKayitPaneli extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_doktorAd;
	private JTextField txt_doktorSoyad;
	private JTextField txt_Brans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doktorKayitPaneli frame = new doktorKayitPaneli();
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
	public doktorKayitPaneli() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_1 = new JLabel("DOKTOR KAYIT PANELİ");
		lbl_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbl_1.setBounds(181, 51, 253, 39);
		contentPane.add(lbl_1);
		
		JLabel lblNewLabel = new JLabel("Adınız:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(132, 140, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblSoyadnz = new JLabel("Soyadınız:");
		lblSoyadnz.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSoyadnz.setBounds(132, 179, 80, 16);
		contentPane.add(lblSoyadnz);
		
		JLabel lblBrannz = new JLabel("Branşınız:");
		lblBrannz.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblBrannz.setBounds(132, 220, 80, 16);
		contentPane.add(lblBrannz);
		
		txt_doktorAd = new JTextField();
		txt_doktorAd.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_doktorAd.setBounds(238, 135, 230, 26);
		contentPane.add(txt_doktorAd);
		txt_doktorAd.setColumns(10);
		
		txt_doktorSoyad = new JTextField();
		txt_doktorSoyad.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_doktorSoyad.setBounds(238, 174, 230, 26);
		contentPane.add(txt_doktorSoyad);
		txt_doktorSoyad.setColumns(10);
		
		txt_Brans = new JTextField();
		txt_Brans.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_Brans.setBounds(238, 215, 230, 26);
		contentPane.add(txt_Brans);
		txt_Brans.setColumns(10);
		
		JButton btnNewButton = new JButton("Kayıt Ol");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad = txt_doktorAd.getText();
				String soyad = txt_doktorSoyad.getText();
				String brans = txt_Brans.getText();

				if (ad.isEmpty() || soyad.isEmpty() || brans.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!");
				} else {
					try {
						
						Random random = new Random();
						int doktorNo = random.nextInt(900) + 100;
						
						// Bağlantı sınıfını çağır
						baglantiDB db = new baglantiDB();
						Connection conn = db.connection;

						// Veritabanına kayıt ekle
						String sql = "INSERT INTO doktorlar (doktorNo, Ad, Soyad, Brans) VALUES (?, ?, ?, ?)";
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setInt(1, doktorNo);
						stmt.setString(2, ad);
						stmt.setString(3, soyad);
						stmt.setString(4, brans);

						int result = stmt.executeUpdate();
						if (result > 0) {
							JOptionPane.showMessageDialog(null, "Kayıt başarılı! Doktor No: " + doktorNo);
							txt_doktorAd.setText("");
							txt_doktorSoyad.setText("");
							txt_Brans.setText("");
						}

						stmt.close();
						conn.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Hata: " + ex.getMessage());
					}
				
				}
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton.setBounds(258, 285, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnGeriDn = new JButton("Geri Dön");
		btnGeriDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new doktorPaneli().setVisible(true);
			}
		});
		btnGeriDn.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnGeriDn.setBounds(17, 341, 167, 29);
		contentPane.add(btnGeriDn);
	}
}
