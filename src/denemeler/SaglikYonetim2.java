package denemeler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;

public class SaglikYonetim2 {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("\n*******SAĞLIK YÖNETİM PANELİMİZE HOŞ GELDİNİZ.*******\n");
        AnaMenu();
    }

    public static void AnaMenu() throws IOException, ParseException {
        System.out.println("Yapmak İstediğiniz İşlemi Giriniz:\n");

        Scanner secim1 = new Scanner(System.in);
        System.out.println("1-Doktor Girişi.");
        System.out.println("2-Hasta Girişi.");
        System.out.println("3-Çıkış.");
        int secim = secim1.nextInt();

        switch (secim) {
            case 1:
                doktorGiris();
                break;
            case 2:
                hastaGiris();
                break;
            case 3:
                cikis();
                break;
            default:
                System.out.println("Geçersiz seçim! Ana menüye dönülüyor...");
                AnaMenu();
                break;
        }
    }

    public static void doktorGiris() throws IOException, ParseException {
        System.out.println("\n******** DOKTOR PANELİMİZE HOŞ GELDİNİZ. ********\n");
        System.out.println("Yapmak İstediğiniz İşlemi Giriniz:\n");

        Scanner secim1 = new Scanner(System.in);
        System.out.println("1-Kayıt Yap.");
        System.out.println("2-Giriş Yap.");
        int secim = secim1.nextInt();
        secim1.nextLine(); 
        
        if (secim == 1) {
            doktorKaydı();
        } else {
            doktorGirisi();
        }
    }

    public static void doktorKaydı() throws IOException, ParseException {
        String path = "/Users/serrasari/Desktop/javaDersNot/SaglikYonetimSistemi/DOKTOR_BILGILERI.txt";
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fwriter = new FileWriter(file, true);
        BufferedWriter bWriter = new BufferedWriter(fwriter);
        PrintWriter pWriter = new PrintWriter(bWriter);
        
        System.out.println("KAYIT YAP SEÇİLDİ.\n");
        System.out.println("Kayıt yap menüsüne hoş geldiniz.\n");

        Scanner input = new Scanner(System.in);
        System.out.println("Adınızı Giriniz:");
        String ad = input.nextLine();
        System.out.println("Soyadınızı Giriniz:");
        String soyad = input.nextLine();
        System.out.println("Branşınızı Giriniz:");
        String brans = input.nextLine();

        Random random = new Random();
        int drNo = random.nextInt(250) + 1;

        pWriter.println(drNo + "," + ad + "," + soyad + "," + brans);
        pWriter.close();

        System.out.println("Kayıt İşleminiz Yapılmıştır Sayın " + ad + " " + soyad + ". Giriş Yap Menüsünden İşlemlerinize Devam Edebilirsiniz.");
        System.out.println("Size atanan Doktor Numarası: " + drNo + " Bu numara ile yeniden giriş yapınız.");

        girisMenu();
    }

    public static void doktorGirisi() throws IOException, ParseException {
        System.out.println("GİRİŞ YAP SEÇİLDİ.");
        Scanner input = new Scanner(System.in);
        System.out.println("Hekim Numaranızı Giriniz:");
        int hekimNumarası = input.nextInt();
        input.nextLine();

        if (hekimNumarasiKontrol(hekimNumarası)) {
            System.out.println("Yapacağınız İşlemi Giriniz:\n");
            System.out.println("1-Hasta Kaydet.");
            System.out.println("2-Kayıtlı Hasta Sorgula.");
            System.out.println("3-Randevuları Görüntüle veya Oluştur.");
            int secim2 = input.nextInt();
            input.nextLine();

            switch (secim2) {
                case 1:
                    hastaKaydet();
                    break;
                case 2:
                    hastaSorgula();
                    break;
                case 3:
                    randevuGoruntulemeOlusturma();
                    break;
                default:
                    System.out.println("Geçersiz seçim! Ana menüye dönülüyor...");
                    AnaMenu();
                    break;
            }
        } 
        else {
            System.out.println("Hatalı Hekim Numarası. Giriş Başarısız.");
        }

        Scanner donus = new Scanner(System.in);
        System.out.println("Ana Menüye Dönmek İstiyorsanız 0 Tuşlayın:");
        int donus1 = donus.nextInt();
        if (donus1 == 0) {
            doktorMenu();
        }
    }

    private static boolean hekimNumarasiKontrol(int hekimNumarasi) throws IOException {
        String path = "/Users/serrasari/Desktop/javaDersNot/SaglikYonetimSistemi/DOKTOR_BILGILERI.txt";
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }

        BufferedReader bReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bReader.readLine()) != null) {
            String[] parts = line.split(",");
            int drNo = Integer.parseInt(parts[0]);
            if (drNo == hekimNumarasi) {
                bReader.close();
                return true;
            }
        }
        
        bReader.close();
        return false;
    }
    
    public static void hastaGiris() throws IOException, ParseException {
        String path2 = "/Users/serrasari/Desktop/javaDersNot/SaglikYonetimSistemi/HASTA_BILGILERI2.txt";
        File file3 = new File(path2);
        if (!file3.exists()) {
            file3.createNewFile();
        }

        FileWriter fwriter3 = new FileWriter(file3, true);
        BufferedWriter bWriter3 = new BufferedWriter(fwriter3);

        System.out.println("\n*******HASTA PANELİMİZE HOŞ GELDİNİZ.*******\n");

        Scanner input = new Scanner(System.in);

        System.out.println("TC Kimlik Numarası Giriniz:");
        String tc = input.nextLine();
        bWriter3.write(tc);
        bWriter3.newLine();

        System.out.println("Branş Giriniz:");
        String brans = input.nextLine();
        bWriter3.write(brans);
        bWriter3.newLine();

        bWriter3.close();

        System.out.println("Ana Menüye Dönmek İstiyorsanız 0 Tuşlayın:");
        int donus1 = input.nextInt();
        if (donus1 == 0) {
            AnaMenu();
        }
    }
        

    public static void cikis() {
        System.out.println("\n*******SİSTEMDEN ÇIKIŞ YAPILIYOR.*******\n");
    }

    public static void girisMenu() throws IOException, ParseException {
        System.out.println("\n**** GİRİŞ MENÜSÜNE GERİ DÖNÜŞ YAPILIYOR.\n");
        doktorGiris();
    }

    public static void hastaKaydet() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Hasta TC Kimlik Numarası Giriniz:");
        String tcStr = input.nextLine();

        System.out.println("Hastanın Hastalığını Giriniz:");
        String hastalik = input.nextLine();

        System.out.println("Hastanın Tedavi Biçimini Giriniz (kullanılacak ilaç veya terapi tipi):");
        String tedavi = input.nextLine();

        String dosyaAdi = "HASTA_" + tcStr + "_" + getCurrentTimestamp() + ".txt";
        String path = "/Users/serrasari/Desktop/javaDersNot/SaglikYonetimSistemi/" + dosyaAdi;
        File file = new File(path);

        FileWriter fwriter = new FileWriter(file);
        BufferedWriter bWriter = new BufferedWriter(fwriter);

        bWriter.write("TC Kimlik Numarası: " + tcStr);
        bWriter.newLine();
        bWriter.write("Hastalık: " + hastalik);
        bWriter.newLine();
        bWriter.write("Tedavi: " + tedavi);
        bWriter.newLine();

        bWriter.close();
    }

    public static String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static void hastaSorgula() throws IOException, ParseException {
        Scanner hastaBilgisi = new Scanner(System.in);
        System.out.println("Hasta TC Kimlik Numarası Giriniz:");
        String tcStr = hastaBilgisi.nextLine();
        
        String directoryPath = "/Users/serrasari/Desktop/javaDersNot/SaglikYonetimSistemi/";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        
        if (files != null) {
            boolean found = false;
            for (File file : files) {
                if (file.isFile() && file.getName().contains("HASTA_" + tcStr)) {
                    System.out.println("Hasta Bilgileri Dosyası: " + file.getName());
                    BufferedReader bReader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = bReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    bReader.close();
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Belirtilen TC kimlik numarasına ait hasta bilgisi bulunamadı.");
            }
        } else {
            System.out.println("Hasta bilgileri dosya dizini bulunamadı.");
        }
    }

    public static void randevuGoruntulemeOlusturma() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Hasta TC Kimlik Numarası Giriniz:");
        String tcStr = input.nextLine();

        String randevuDosyasi = "RANDEVU_" + tcStr + ".txt";
        String path = "/Users/serrasari/Desktop/javaDersNot/SaglikYonetimSistemi/" + randevuDosyasi;
        File file = new File(path);

        if (file.exists()) {
            System.out.println("Hasta için mevcut randevular:");
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bReader.readLine()) != null) {
                System.out.println(line);
            }
            bReader.close();
        } else {
            System.out.println("Bu hasta için randevu bilgisi yok. Yeni randevu oluşturulacak.");
        }

        FileWriter fwriter = new FileWriter(file, true);
        BufferedWriter bWriter = new BufferedWriter(fwriter);

        System.out.println("Randevu Tarihini Giriniz (örnek: 2024-12-31):");
        String tarih = input.nextLine();
        System.out.println("Randevu Saatini Giriniz (örnek: 14:30):");
        String saat = input.nextLine();

        bWriter.write("Randevu Tarihi: " + tarih + " Saat: " + saat);
        bWriter.newLine();
        bWriter.close();

        System.out.println("Randevu başarıyla oluşturuldu veya güncellendi.");
    }

    public static void doktorMenu() throws IOException, ParseException {
        System.out.println("\n**** DOKTOR MENÜSÜNE GERİ DÖNÜŞ YAPILIYOR. ****\n");
        doktorGiris();
    }
}
