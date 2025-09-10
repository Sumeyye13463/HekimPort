package denemeler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class hastaKayitPaneli extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_tcNo;
	private JTextField txt_hastalik;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hastaKayitPaneli frame = new hastaKayitPaneli();
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
	public hastaKayitPaneli() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_1 = new JLabel("HASTA KAYIT PANELİ");
		lbl_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbl_1.setBounds(168, 36, 253, 39);
		contentPane.add(lbl_1);
		
		JLabel lblHastaTcKimlik = new JLabel("Hasta TC Kimlik No:");
		lblHastaTcKimlik.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHastaTcKimlik.setBounds(55, 120, 166, 16);
		contentPane.add(lblHastaTcKimlik);
		
		txt_tcNo = new JTextField();
		txt_tcNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
                if (!Character.isDigit(c)) {
                    e.consume(); // Karakteri engeller
                }
                
                if (txt_tcNo.getText().length() >= 11) {
                    e.consume(); // Karakteri engeller
                }
			}
		});
		txt_tcNo.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_tcNo.setColumns(10);
		txt_tcNo.setBounds(219, 115, 230, 26);
		contentPane.add(txt_tcNo);
		
		JLabel lblHastannHastal = new JLabel("Hastanın Hastalığı:");
		lblHastannHastal.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHastannHastal.setBounds(55, 159, 166, 16);
		contentPane.add(lblHastannHastal);
		
		txt_hastalik = new JTextField();
		txt_hastalik.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_hastalik.setColumns(10);
		txt_hastalik.setBounds(219, 154, 276, 26);
		contentPane.add(txt_hastalik);
		
		JLabel lblTedaviBiimi = new JLabel("Tedavi Biçimi:");
		lblTedaviBiimi.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTedaviBiimi.setBounds(55, 200, 138, 16);
		contentPane.add(lblTedaviBiimi);
		
		JTextPane txt_tedavi = new JTextPane();
		txt_tedavi.setBounds(219, 200, 276, 102);
		contentPane.add(txt_tedavi);
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                String tcNo = txt_tcNo.getText();
                String hastalik = txt_hastalik.getText();
                String tedavi = txt_tedavi.getText();

                if (tcNo.isEmpty() || hastalik.isEmpty() || tedavi.isEmpty()) {
                    System.out.println("Lütfen tüm alanları doldurun.");
                    return;
                }

                try {
                    // Veritabanı bağlantısı
                    baglantiDB db = new baglantiDB();
                    Connection conn = db.getConnection();

                    // SQL INSERT sorgusu
                    String sql = "INSERT INTO Hastalıklar (tcNo, Hastalik, Tedavi) VALUES (?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, tcNo);
                    ps.setString(2, hastalik);
                    ps.setString(3, tedavi);

                    // Veriyi ekleme
                    int rowsInserted = ps.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("Kayıt başarıyla eklendi.");
                    } else {
                        System.out.println("Kayıt eklenirken bir sorun oluştu.");
                    }

                    // Kaynakları kapat
                    ps.close();
                    conn.close();

                } catch (Exception ex) {
                    System.out.println("Veritabanı hatası: " + ex.getMessage());
                }
			}
		});
		btnKaydet.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnKaydet.setBounds(284, 329, 167, 29);
		contentPane.add(btnKaydet);
		
		JButton btnGeriDn = new JButton("Geri Dön");
		btnGeriDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new doktorIslem().setVisible(true);
			}
		});
		btnGeriDn.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnGeriDn.setBounds(6, 355, 167, 29);
		contentPane.add(btnGeriDn);
	}
}
