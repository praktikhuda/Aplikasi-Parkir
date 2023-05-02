/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestKode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author lenovo
 */
public class MenghitungWaktu {
    public static void main(String[] args) {
        LocalTime waktu = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String waktuString = waktu.format(formatter);
        String waktuKoma = waktuString.replace(":", ", ");
        System.out.println(waktuKoma);
        
    // membuat objek LocalTime dengan waktu jam 08:30:45
        LocalTime time1 = LocalTime.of(10, 30, 45);
        
        // membuat objek LocalTime dengan waktu jam 14:15:30
        LocalTime time2 = LocalTime.parse(waktuString);
        
        // menghitung durasi antara time1 dan time2
        Duration duration = Duration.between(time1, time2);
        
        // mengubah durasi menjadi satuan jam
        long hours = duration.toHours();
        
        // menampilkan hasil
        System.out.println("Durasi: " + duration);
        System.out.println("Jam: " + hours);
        int nin = -10;
        int ninn = -10000;
        int hit = nin * ninn;
        System.out.println("Hasil : "+hit);
        
        int angka = -5;
        
        LocalDateTime waktuSekarang = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String waktuFormatted = waktuSekarang.format(dtf);
        System.out.println("Waktu sekarang : " + waktuFormatted);
        
        LocalTime time3 = LocalTime.of(07, 30, 45);
        
        // membuat objek LocalTime dengan waktu jam 14:15:30
        LocalTime time4 = LocalTime.parse(waktuFormatted);
        
        // menghitung durasi antara time1 dan time2
        Duration duration1 = Duration.between(time3, time4);
        
        // mengubah durasi menjadi satuan jam
        long hours1 = duration1.toHours();
        
        // menampilkan hasil
        System.out.println("Durasi: " + duration1);
        System.out.println("Jam: " + hours1);
        
        String strDateTime = "2022-05-20 10:30:10";
        
        DateTimeFormatter dtff = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(strDateTime, dtff);
        String nn = dateTime.format(dtf);
        
        
//        LocalDateTime truncated = LocalDateTime.parse(strDateTime); 
        LocalDateTime truncatedDateTime = waktuSekarang.truncatedTo(ChronoUnit.SECONDS);
        String localDateTimeStr = truncatedDateTime.toString();
        System.out.println(waktuFormatted);
        
  }
}
