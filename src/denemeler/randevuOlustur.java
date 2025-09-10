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
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class randevuOlustur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_tcNo;
	private JTextField txt_tarih;
	private JTextField txt_saat;
	private JTextField txt_doktorNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					randevuOlustur frame = new randevuOlustur();
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
	public randevuOlustur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_1 = new JLabel("RANDEVU OLUŞTUR");
		lbl_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbl_1.setBounds(185, 25, 253, 39);
		contentPane.add(lbl_1);
		
		JLabel lblHastaTcKimlik = new JLabel("Hasta TC Kimlik No:");
		lblHastaTcKimlik.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHastaTcKimlik.setBounds(53, 137, 166, 16);
		contentPane.add(lblHastaTcKimlik);
		
		txt_tcNo = new JTextField();
		txt_tcNo.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_tcNo.setColumns(10);
		txt_tcNo.setBounds(217, 132, 230, 26);
		contentPane.add(txt_tcNo);
		
		JLabel lblRandevuTarihi = new JLabel("Randevu Tarihi:");
		lblRandevuTarihi.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblRandevuTarihi.setBounds(53, 184, 137, 16);
		contentPane.add(lblRandevuTarihi);
		
		txt_tarih = new JTextField();
		txt_tarih.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_tarih.setColumns(10);
		txt_tarih.setBounds(217, 179, 230, 26);
		contentPane.add(txt_tarih);
		
		JLabel lblRandevuSaati = new JLabel("Randevu Saati:");
		lblRandevuSaati.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblRandevuSaati.setBounds(53, 222, 122, 16);
		contentPane.add(lblRandevuSaati);
		
		txt_saat = new JTextField();
		txt_saat.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_saat.setColumns(10);
		txt_saat.setBounds(217, 217, 230, 26);
		contentPane.add(txt_saat);
		
		JLabel lblNewLabel = new JLabel("örn: 2024-12-31");
		lblNewLabel.setBounds(459, 185, 122, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblrn = new JLabel("örn: 14:30");
		lblrn.setBounds(459, 223, 122, 16);
		contentPane.add(lblrn);
		
		JButton btn_kaydet = new JButton("Kaydet");
		btn_kaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tcNo = txt_tcNo.getText();
                String tarih = txt_tarih.getText();
                String saat = txt_saat.getText();
                String doktorNo = txt_doktorNo.getText();
                
                if (tcNo.isEmpty() || tarih.isEmpty() || saat.isEmpty() || doktorNo.isEmpty()) {
                    System.out.println("Lütfen tüm alanları doldurun.");
                    return;
                }

                try {
                    baglantiDB db = new baglantiDB();
                    Connection conn = db.getConnection();

                    // Doktor numarasının veritabanında olup olmadığını kontrol et
                    String sqlCheckDoctor = "SELECT COUNT(*) FROM Doktorlar WHERE doktorNo = ?";
                    PreparedStatement psCheckDoctor = conn.prepareStatement(sqlCheckDoctor);
                    psCheckDoctor.setString(1, doktorNo);
                    ResultSet rsCheckDoctor = psCheckDoctor.executeQuery();
                    rsCheckDoctor.next();

                    if (rsCheckDoctor.getInt(1) == 0) {
                        System.out.println("Girilen doktor numarası veritabanında bulunamadı.");
                    } else {
                        // Eğer doktor numarası varsa, randevuyu kaydet
                        String sqlInsertAppointment = "INSERT INTO Randevular (tcNo, doktorNo, Tarih, Saat) VALUES (?, ?, ?, ?)";
                        PreparedStatement psInsert = conn.prepareStatement(sqlInsertAppointment);
                        psInsert.setString(1, tcNo);
                        psInsert.setString(2, doktorNo);
                        psInsert.setString(3, tarih);
                        psInsert.setString(4, saat);
                        
                        int result = psInsert.executeUpdate();
                        if (result > 0) {
                            System.out.println("Randevu başarıyla kaydedildi.");
                        } else {
                            System.out.println("Randevu kaydedilemedi.");
                        }
                    }
                    rsCheckDoctor.close();
                    psCheckDoctor.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
				
			}
		});
		btn_kaydet.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btn_kaydet.setBounds(203, 272, 173, 29);
		contentPane.add(btn_kaydet);
		
		JButton btn_geriDon = new JButton("Geri Dön");
		btn_geriDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new doktorIslem().setVisible(true);
			}
		});
		btn_geriDon.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btn_geriDon.setBounds(39, 326, 167, 29);
		contentPane.add(btn_geriDon);
		
		JLabel lblHekimNumaras = new JLabel("Hekim Numarası:");
		lblHekimNumaras.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHekimNumaras.setBounds(53, 97, 166, 16);
		contentPane.add(lblHekimNumaras);
		
		txt_doktorNo = new JTextField();
		txt_doktorNo.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txt_doktorNo.setColumns(10);
		txt_doktorNo.setBounds(217, 92, 230, 26);
		contentPane.add(txt_doktorNo);
	}

}
