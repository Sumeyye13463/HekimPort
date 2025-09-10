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

public class doktorIslem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doktorIslem frame = new doktorIslem();
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
	public doktorIslem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_1 = new JLabel("DOKTOR İŞLEM PANELİ");
		lbl_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbl_1.setBounds(176, 52, 253, 39);
		contentPane.add(lbl_1);
		
        
		JButton btnHastaKaydet = new JButton("Hasta Kaydet");
		btnHastaKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new hastaKayitPaneli().setVisible(true);
			}
		});
		btnHastaKaydet.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnHastaKaydet.setBounds(219, 139, 167, 29);
		contentPane.add(btnHastaKaydet);
		
		JButton btnKaytlHastaSorgula = new JButton("Kayıtlı Hasta Sorgula");
		btnKaytlHastaSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new kayitliHastaSorgulama().setVisible(true);
			}
		});
		btnKaytlHastaSorgula.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnKaytlHastaSorgula.setBounds(196, 180, 233, 29);
		contentPane.add(btnKaytlHastaSorgula);
		
		JButton btnRandevuGrntleOlutur = new JButton("Randevu Oluştur");
		btnRandevuGrntleOlutur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new randevuOlustur().setVisible(true);
			}
		});
		btnRandevuGrntleOlutur.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnRandevuGrntleOlutur.setBounds(196, 221, 233, 29);
		contentPane.add(btnRandevuGrntleOlutur);
		
		JButton btnGeriDn = new JButton("Geri Dön");
		btnGeriDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new doktorGirisPaneli().setVisible(true);
			}
		});
		btnGeriDn.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnGeriDn.setBounds(27, 353, 167, 29);
		contentPane.add(btnGeriDn);
		
		JButton btnRandevuGrntle = new JButton("Randevu Görüntüle");
		btnRandevuGrntle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Mevcut pencereyi kapatır
                new randevuPaneli().setVisible(true);
			}
		});
		btnRandevuGrntle.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnRandevuGrntle.setBounds(196, 262, 233, 29);
		contentPane.add(btnRandevuGrntle);
	}

}
