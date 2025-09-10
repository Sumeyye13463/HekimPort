package denemeler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class baglantiDB {
	
	private String dbKulAdi = "root";
    private String dbSifre = "";
    private String dbURL = "jdbc:mysql://localhost:3306/SaglikYonetimi";

    public Connection connection = null;

    public baglantiDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Sürücüyü yükle
            
            connection = DriverManager.getConnection(dbURL, dbKulAdi, dbSifre); // Veritabanına bağlan
            
            System.out.println("Bağlantı başarılı.");
            
        } catch (ClassNotFoundException e) {
        	
            System.out.println("Driver bulunamadı." + e);
            
        } catch (SQLException e) {
        	
            System.out.println("Bağlantı kurulamadı." + e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

}


