/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lenovo
 */
public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost/parkir";
    private static final String USER = "root"; // user ke MySL
    private static final String PASSWORD = ""; // password MySQL, sesuaikan dengan punya Anda

    public static Connection getKoneksi() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
