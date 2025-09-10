package denemeler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class hastaPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_tc;
	private JTextField txt_ad;
	private JTextField txt_soyad;
	private JTextField txt_brans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hastaPanel frame = new hastaPanel();
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
	public hastaPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_1 = new JLabel("HASTA PANELİNE HOŞGELDİNİZ");
		lbl_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbl_1.setBounds(128, 37, 361, 39);
		contentPane.add(lbl_1);
		
		JLabel lblHastaTcKimlik = new JLabel("Hasta TC Kimlik No:");
		lblHastaTcKimlik.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHastaTcKimlik.setBounds(68, 117, 166, 16);
		contentPane.add(lblHastaTcKimlik);
		
		txt_tc = new JTextField();
		txt_tc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
                // Eğer girilen karakter sayı değilse, girişi engelle
                if (!Character.isDigit(c)) {
                    e.consume(); // Karakteri engeller
                }
                // Eğer 11 haneden fazla karakter girilmeye çalışılıyorsa, girişi engelle
                if (txt_tc.getText().length() >= 11) {
                    e.consume(); // Karakteri engeller
                }
			}
		});
		txt_tc.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_tc.setColumns(10);
		txt_tc.setBounds(232, 112, 230, 26);
		contentPane.add(txt_tc);
		
		JLabel lblNewLabel = new JLabel("Adınız:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(126, 155, 61, 16);
		contentPane.add(lblNewLabel);
		
		txt_ad = new JTextField();
		txt_ad.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_ad.setColumns(10);
		txt_ad.setBounds(232, 150, 230, 26);
		contentPane.add(txt_ad);
		
		JLabel lblSoyadnz = new JLabel("Soyadınız:");
		lblSoyadnz.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSoyadnz.setBounds(126, 194, 80, 16);
		contentPane.add(lblSoyadnz);
		
		txt_soyad = new JTextField();
		txt_soyad.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_soyad.setColumns(10);
		txt_soyad.setBounds(232, 189, 230, 26);
		contentPane.add(txt_soyad);
		
		JLabel lblBran = new JLabel("Branş:");
		lblBran.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblBran.setBounds(126, 235, 80, 16);
		contentPane.add(lblBran);
		
		txt_brans = new JTextField();
		txt_brans.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_brans.setColumns(10);
		txt_brans.setBounds(232, 230, 230, 26);
		contentPane.add(txt_brans);
		
		JButton btnGeriDn = new JButton("Geri Dön");
		btnGeriDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new girisPaneli().setVisible(true);
			}
		});
		btnGeriDn.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnGeriDn.setBounds(6, 370, 167, 29);
		contentPane.add(btnGeriDn);
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String tcNo = txt_tc.getText();
                String ad = txt_ad.getText();
                String soyad = txt_soyad.getText();
                String brans = txt_brans.getText();

                if (tcNo.isEmpty() || ad.isEmpty() || soyad.isEmpty() || brans.isEmpty()) {
                    System.out.println("Lütfen tüm alanları doldurun.");
                    return;
                }
                try {
                    baglantiDB db = new baglantiDB();
                    Connection conn = db.getConnection();

                    String sqlQuery = "INSERT INTO Hastalar (tcNo, Ad, Soyad, Brans) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sqlQuery);
                    ps.setString(1, tcNo);
                    ps.setString(2, ad);
                    ps.setString(3, soyad);
                    ps.setString(4, brans);

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Hasta başarıyla kaydedildi.");
                    } else {
                        System.out.println("Hasta kaydedilemedi.");
                    }

                    ps.close();
                    conn.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Veritabanı hatası: " + ex.getMessage());
                }
			}
		});
		btnKaydet.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnKaydet.setBounds(272, 278, 167, 29);
		contentPane.add(btnKaydet);
		
		JButton btnRandevuGrntle = new JButton("Randevu Görüntüle");
		btnRandevuGrntle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new randevuPaneli().setVisible(true);
			}
		});
		btnRandevuGrntle.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnRandevuGrntle.setBounds(248, 354, 214, 29);
		contentPane.add(btnRandevuGrntle);
	}

}
