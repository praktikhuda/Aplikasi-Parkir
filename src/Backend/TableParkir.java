/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lenovo
 */
public class TableParkir {
//    Koneksi 
    private Connection con;
    private Statement st;
    private String sql;
    private ResultSet rs;
    private String error;
    
//    Table Parkir Masuk
    private String id_masuk;
    private String no_plat;
    private String waktu_masuk;
    private String jenis;
    
//    Table Parkir Keluar
    private String id_keluar;
    private String waktu_keluar;
    private int harga;
    
//    Table Login
    private int id_user;
    private String username;
    private String password;
    private String nama;
    
    private int banyakJumlah;
    
    
    public String getId_Masuk(){
        return id_masuk;
    }
    public void setId_Masuk(String id_masuk){
        this.id_masuk = id_masuk;
    }
    public String getNo_Plat(){
        return no_plat;
    }
    public void setNo_Plat(String no_plat){
        this.no_plat = no_plat;
    }
    public String getWaktu_Masuk(){
        return waktu_masuk;
    }
    public void setWaktu_Masuk(String waktu_masuk){
        this.waktu_masuk = waktu_masuk;
    }
    public String getJenis(){
        return jenis;
    }
    public void setJenis(String jenis){
        this.jenis = jenis;
    }
    
    public String getId_Keluar(){
        return id_keluar;
    }
    public void setId_Keluar(String id_keluar){
        this.id_keluar = id_keluar;
    }
    public String getWaktu_Keluar(){
        return waktu_keluar;
    }
    public void setWaktu_Keluar(String waktu_keluar){
        this.waktu_keluar = waktu_keluar;
    }
    public int getHarga(){
        return harga;
    }
    public void setHarga(int harga){
        this.harga = harga;
    }
    
//    Table Login
    public int getId_User(){
        return id_user;
    }
    public void setId_User(int id_user){
        this.id_user = id_user;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public int getBanyakJumlah(){
        return banyakJumlah;
    }
    
//    Membuat ID dengan cara mengacak angka
    public String generateRandomInt() {
        String id = "";
        Random random = new Random();
        for(int i=0; i<10; i++){
            id += String.valueOf(random.nextInt(10));
        }
        return id;
    }
    
//    Menambah data ke table parkir_masuk
    public int ganti(String sql){
        int n = 0;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            n = st.executeUpdate(sql);
            st.close(); 
            rs.close(); 
            con.close(); 
        }catch (Exception e){
            error = "gagal baca tabel";
        }
        System.out.println("sql: "+sql);
        System.out.println("n: "+n);
        return n;
    }
    
    public int tambahParkirMasuk(){
        sql = "insert into parkir_masuk values ('" +id_masuk + "', '" + no_plat + "', '" +waktu_masuk + "', '" +jenis+ "')"; 
        return ganti(sql);
    }
    public int tambahParkirKeluar(){
        sql = "insert into parkir_keluar values ('"+id_keluar+"', '"+no_plat+"', '"+waktu_masuk+"', '"+waktu_keluar+"', '"+jenis+"', '"+harga+"')";
        return ganti(sql);
    }   
    
//    Cari Riwayat Parkir Masuk berdasarkan Plat Nomer
    public boolean cariRiwayat(String no_plat){
        boolean ketemu = false;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select * from parkir_masuk where no_plat = '" + no_plat + "' order by waktu_masuk desc";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setId_Masuk(rs.getString("id_masuk"));
                this.setNo_Plat(rs.getString("no_plat"));
                this.setWaktu_Masuk(rs.getString("waktu_masuk"));
                this.setJenis(rs.getString("jenis"));
                
                ketemu = true;
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
                ketemu = false;
            }
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Gagal akses database");
        }
        return ketemu;
    }
    
//    Menampilkan semua data di parkir_keluar
    public boolean cariParkirKeluar(){
        boolean ketemu = false;
        try{
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select * from parkir_keluar order by waktu_masuk desc";
            rs = st.executeQuery(sql);
            while(rs.next()){
                String id_keluar = rs.getString("id_keluar");
                String no_plat = rs.getString("no_plat");
                String waktu_masuk = rs.getString("waktu_masuk");
                String waktu_keluar = rs.getString("waktu_keluar");
                String jenis = rs.getString("jenis");
                int harga = rs.getInt("harga");
                
                System.out.println("id : "+id_keluar);
                System.out.println("no : "+no_plat);
                System.out.println("masuk : "+waktu_masuk);
                System.out.println("keluar : "+waktu_keluar);
                System.out.println("jenis : "+jenis);
                System.out.println("harga : "+harga);
                System.out.println("==========");
                
            }
        }catch(Exception e){
            System.out.println("Gagal akses database");
        }
        return ketemu;
    }
    
//    Convert String to MD5
    public String getMD5(String passwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(passwd.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
//    Membuat Login
    public boolean CariUser(String nama){
        boolean ketemu = false;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select * from login where username = '" + nama + "'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.id_user = rs.getInt("id_user");
                this.username = rs.getString("username");
                this.password = rs.getString("password");
                this.nama = rs.getString("nama");
                ketemu = true;  
            }else{
                ketemu = false;
            }
            
            st.close();
            con.close();
        } catch (Exception e) {}
            return ketemu;
    }
    
    public static void main(String agrs []){
        Scanner in = new Scanner (System.in);
        TableParkir tp = new TableParkir();
        
        System.out.print("Masukan : ");
        int masuk = in.nextInt();
        
        if(masuk ==1){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String waktu = now.format(formatter);

            String id = tp.generateRandomInt();
            System.out.println(id);

            System.out.print("Masukan Nomer Plat : ");
            String no_plat = in.next();
            System.out.print("Masukan Jenis Kendaraan (Motor/Mobil/Lain) : ");
            String jenis = in.next();

            tp.setId_Masuk(id);
            tp.setNo_Plat(no_plat);
            tp.setWaktu_Masuk(waktu);
            tp.setJenis(jenis);

            int hasil = tp.tambahParkirMasuk();
            if(hasil == 1){
                System.out.println("Berhasil menanmbah data!!");
            }else{
                System.out.println("Gagal menambah data!!");
            }
        }else if(masuk ==2){
            System.out.print("Cari Nomer Plat : ");
            String cariPlat = in.next();
        
            boolean ketemu = tp.cariRiwayat(cariPlat);
            if(ketemu){
                
//                Untuk mengambil data di parkir_masuk
                String id = tp.getId_Masuk();
                String no = tp.getNo_Plat();
                String wakm = tp.getWaktu_Masuk();
                String jenis = tp.getJenis();
                
                System.out.println("Id : "+tp.getId_Masuk());
                System.out.println("No : "+tp.getNo_Plat());
                System.out.println("Waktu : "+tp.getWaktu_Masuk());
                System.out.println("Jenis : "+tp.getJenis());
                
//                Convert jenis tanggal
                DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                DateTimeFormatter formatDateTimeM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
               
//                Menghitung Selesih Tanggal
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime before = LocalDateTime.parse(wakm, formatDateTimeM);
                Duration duration = Duration.between(before, now);
                
                long hours = duration.toHours();
                int hoursInt = (int)hours;
                System.out.println("Jam : "+hoursInt);
                
//                Buat variabel
                String waktu1 = now.format(formatDateTime);
                String waktu2 = before.format(formatDateTime);
                
                int harga = 0;
                
                if(hoursInt == 0){
                    harga = 10000;
                }else if(hoursInt >= 1){
                    harga = hoursInt * 10000;
                }else{
                    System.out.println("Gagal");
                }
                
                tp.setId_Keluar(id);
                tp.setNo_Plat(no);
                tp.setWaktu_Masuk(waktu2);
                tp.setWaktu_Keluar(waktu1);
                tp.setJenis(jenis);
                tp.setHarga(harga);
                
                int hasil = tp.tambahParkirKeluar();
                if(hasil ==1){
                    System.out.println("Berhasil menanmbah data!!");
                }else{
                    System.out.println("Gagal menambah data!!");
                }
                
            }
        }else if(masuk == 3){
            String pwbenar;
            System.out.print("Cari username : ");
            String user = in.next();
            System.out.print("Masukan Pass : ");
            String Pass = in.next();
            boolean ketemu = tp.CariUser(user);
            String PassMD5 = tp.getMD5(Pass);
            System.out.println("Pass : "+PassMD5);
            if(ketemu){
                int id = tp.getId_User();
                String User = tp.getUsername();
                String pass = tp.getPassword();
                String nama = tp.getNama();
                if(pass.equals(PassMD5)){
                    System.out.println("Ajim");
                    System.out.println("id : "+id);
                    System.out.println("Username : "+User);
                    System.out.println("Password : "+pass);
                    System.out.println("Nama : "+nama);
                }else{
                    System.out.println("Password Salah!!");
                }
            }else{
                System.out.println("Username tidak ketemu!!");
            }
            
        }else if(masuk == 4){
            tp.cariParkirKeluar();
        }else{
            System.out.println();
        }
        
    }
}
