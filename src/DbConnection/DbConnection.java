/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

/**
 *
 * @author USER
 */

//import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {



/**
 * Created by agungaprian on 30/04/17.
 */

    Connection connection;
    String username = "root";
    String password = "";
    String url = "jdbc:sqlite:note.db";



    public Connection getConnection () throws SQLException {


        try{
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("connect to database");
        }catch (SQLException ex) {
            System.out.println("not connected to database");
            ex.printStackTrace();
        }

        return connection;
    }

}

   

