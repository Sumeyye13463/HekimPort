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

public class randevuPaneli extends JFrame {

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
					randevuPaneli frame = new randevuPaneli();
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
	public randevuPaneli() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_1 = new JLabel("RANDEVU GÖRÜNTÜLE");
		lbl_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbl_1.setBounds(179, 31, 253, 39);
		contentPane.add(lbl_1);
		
		JLabel lblHastaTcKimlik = new JLabel("Hasta TC Kimlik No:");
		lblHastaTcKimlik.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHastaTcKimlik.setBounds(38, 111, 166, 16);
		contentPane.add(lblHastaTcKimlik);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(202, 106, 230, 26);
		contentPane.add(textField);
		
		JLabel lblRandevuTarihi = new JLabel("Randevu Tarihi:");
		lblRandevuTarihi.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblRandevuTarihi.setBounds(57, 204, 134, 16);
		contentPane.add(lblRandevuTarihi);
		
		JLabel lblRandevuSaati = new JLabel("Randevu Saati:");
		lblRandevuSaati.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblRandevuSaati.setBounds(57, 250, 127, 16);
		contentPane.add(lblRandevuSaati);
		
		JLabel lbl_tarih = new JLabel("");
		lbl_tarih.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lbl_tarih.setBounds(205, 204, 200, 16);
		contentPane.add(lbl_tarih);
		
		JLabel lbl_saat = new JLabel("");
		lbl_saat.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lbl_saat.setBounds(202, 250, 230, 16);
		contentPane.add(lbl_saat);
		
		JButton btnNewButton = new JButton("Sorgula");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tcNo = textField.getText();
                if (tcNo.isEmpty()) {
                    System.out.println("Lütfen TC Kimlik No giriniz.");
                    return;
                }

                try {
                    baglantiDB db = new baglantiDB();
                    Connection conn = db.getConnection();

                    // TC numarasına uygun randevuyu sorgula
                    String sqlQuery = "SELECT Tarih, Saat FROM Randevular WHERE tcNo = ?";
                    PreparedStatement ps = conn.prepareStatement(sqlQuery);
                    ps.setString(1, tcNo);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                    	
                        String tarih = rs.getString("Tarih");
                        String saat = rs.getString("Saat");
                        lbl_tarih.setText(tarih);
                        lbl_saat.setText(saat);
                    } else {
                    	
                        lbl_tarih.setText("Randevu bulunamadı.");
                        lbl_saat.setText("");
                    }
                    rs.close();
                    ps.close();
                    conn.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
			}
		});
		btnNewButton.setBounds(447, 107, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btn_anaMenu = new JButton("Ana Menü");
		btn_anaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new girisPaneli().setVisible(true);
			}
		});
		btn_anaMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btn_anaMenu.setBounds(238, 312, 167, 29);
		contentPane.add(btn_anaMenu);
	}
}
