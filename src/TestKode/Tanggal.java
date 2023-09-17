/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestKode;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lenovo
 */
public class Tanggal {
    public static void main(String[] args) {
        
        DateTimeFormatter dtff = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        
        LocalDateTime now = LocalDateTime.now();
        
        String tanggal = "2023-05-06 10:11:20.0";
        System.out.println(tanggal);
        
        LocalDateTime waktu1 = LocalDateTime.parse(tanggal, dtff);
        LocalDateTime waktu2 = LocalDateTime.of(2023, 5, 6, 12, 30);
//        System.out.println(waktu1);
        Duration selisih = Duration.between(waktu1, now);
        
        long selisihJam = selisih.toHours();
        System.out.println("Selisih waktu dalam jam: " + selisihJam);
    }
}
