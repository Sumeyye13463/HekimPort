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
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class doktorGirisPaneli extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_hekimNo;

	
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doktorGirisPaneli frame = new doktorGirisPaneli();
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
	public doktorGirisPaneli() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHekimNumaras = new JLabel("Hekim Numarası:");
		lblHekimNumaras.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHekimNumaras.setBounds(130, 175, 135, 16);
		contentPane.add(lblHekimNumaras);
		
		txt_hekimNo = new JTextField();
		txt_hekimNo.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_hekimNo.setBounds(281, 171, 130, 26);
		contentPane.add(txt_hekimNo);
		txt_hekimNo.setColumns(10);
		
		JLabel lbl_1 = new JLabel("DOKTOR GİRİŞ PANELİ");
		lbl_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbl_1.setBounds(158, 53, 253, 39);
		contentPane.add(lbl_1);
		
		JLabel lbl_hekimNo = new JLabel("");
		lbl_hekimNo.setBounds(81, 300, 446, 16);
		contentPane.add(lbl_hekimNo);
		
		JButton btnGeriDn = new JButton("Geri Dön");
		btnGeriDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new doktorPaneli().setVisible(true);
			}
		});
		btnGeriDn.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnGeriDn.setBounds(19, 316, 167, 29);
		contentPane.add(btnGeriDn);
		
		JButton btnGiriYap = new JButton("Giriş Yap");
		btnGiriYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String hekimNo = txt_hekimNo.getText(); 
				
                if (hekimNo.isEmpty()) {
                    lbl_hekimNo.setText("Hekim numarası boş bırakılamaz.");
                    return;
                }

                try {
                    baglantiDB db = new baglantiDB();
                    Connection conn = db.getConnection();

                    String sql = "SELECT * FROM Doktorlar WHERE doktorNo = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, hekimNo);

                    // Sorguyu çalıştır ve sonucu al
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        lbl_hekimNo.setText("Giriş başarılı! Hoşgeldiniz, Dr. " + rs.getString("ad") + " " + rs.getString("soyad"));
                        dispose(); 
                        new doktorIslem().setVisible(true);
                    } else {
                        lbl_hekimNo.setText("Hekim numarası bulunamadı.");
                    }

                    // Kaynakları kapat
                    rs.close();
                    ps.close();
                    conn.close();
                } catch (Exception ex) {
                    lbl_hekimNo.setText("Veritabanı hatası: " + ex.getMessage());
                }
				
			}
		});
		btnGiriYap.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnGiriYap.setBounds(254, 241, 117, 29);
		contentPane.add(btnGiriYap);
	}

}
