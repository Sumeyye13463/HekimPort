package denemeler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class kayitliHastaSorgulama extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kayitliHastaSorgulama frame = new kayitliHastaSorgulama();
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
	public kayitliHastaSorgulama() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_1 = new JLabel("KAYITLI HASTA SORGULAMA");
		lbl_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbl_1.setBounds(142, 37, 327, 39);
		contentPane.add(lbl_1);
		
		JLabel lblHastaTcKimlik = new JLabel("Hasta TC Kimlik No:");
		lblHastaTcKimlik.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHastaTcKimlik.setBounds(34, 113, 166, 16);
		contentPane.add(lblHastaTcKimlik);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
                // Eğer girilen karakter sayı değilse, girişi engelle
                if (!Character.isDigit(c)) {
                    e.consume(); // Karakteri engeller
                }
                // Eğer 11 haneden fazla karakter girilmeye çalışılıyorsa, girişi engelle
                if (textField.getText().length() >= 11) {
                    e.consume(); // Karakteri engeller
                }
			}
		});
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(198, 108, 230, 26);
		contentPane.add(textField);
		
		JLabel lblHastannHastal = new JLabel("Hastanın Hastalığı:");
		lblHastannHastal.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHastannHastal.setBounds(34, 172, 166, 29);
		contentPane.add(lblHastannHastal);
		
		JLabel lblTedaviBiimi = new JLabel("Tedavi Biçimi:");
		lblTedaviBiimi.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTedaviBiimi.setBounds(34, 213, 138, 26);
		contentPane.add(lblTedaviBiimi);
		
		JLabel lbl_hastalik = new JLabel("");
		lbl_hastalik.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lbl_hastalik.setBounds(198, 172, 353, 29);
		contentPane.add(lbl_hastalik);
		
		JLabel lbl_tedavi = new JLabel("");
		lbl_tedavi.setVerticalAlignment(SwingConstants.TOP);
		lbl_tedavi.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lbl_tedavi.setBounds(198, 213, 353, 102);
		contentPane.add(lbl_tedavi);
		
		JButton btnAnaMen = new JButton("Ana Menü");
		btnAnaMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new girisPaneli().setVisible(true);
			}
		});
		btnAnaMen.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnAnaMen.setBounds(215, 359, 131, 29);
		contentPane.add(btnAnaMen);
		
		JButton btnGeriDn = new JButton("Geri Dön");
		btnGeriDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new doktorIslem().setVisible(true);
			}
		});
		btnGeriDn.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnGeriDn.setBounds(34, 359, 167, 29);
		contentPane.add(btnGeriDn);
		
		JButton btn_sorgula = new JButton("Sorgula");
		btn_sorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String tcKimlikNo = textField.getText(); 

	                try {
	                    baglantiDB db = new baglantiDB();
	                    Connection conn = db.getConnection();
	                    
	                    String sql = "SELECT Hastalik, Tedavi FROM Hastalıklar WHERE tcNo = ?";
	                    PreparedStatement ps = conn.prepareStatement(sql);
	                    ps.setString(1, tcKimlikNo);

	                    ResultSet rs = ps.executeQuery();

	                    if (rs.next()) {
	                        String hastalik = rs.getString("Hastalik");
	                        String tedavi = rs.getString("Tedavi");

	                        lbl_hastalik.setText(hastalik);
	                        lbl_tedavi.setText(tedavi);
	                    } else {
	                        lbl_hastalik.setText("Hasta bulunamadı.");
	                        lbl_tedavi.setText("");
	                    }

	                    rs.close();
	                    ps.close();
	                    conn.close();

	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    lbl_hastalik.setText("Hata: Veritabanına bağlanırken bir sorun oluştu.");
	                    lbl_tedavi.setText("");
	                }
			}
		});
		btn_sorgula.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btn_sorgula.setBounds(435, 107, 167, 29);
		contentPane.add(btn_sorgula);
	}

}
