package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection cnx;

    private SingletonConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/catalogue_java","root","mysql");
            System.out.println("connecté");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static Connection getCnx(){
        if(cnx == null)
            new SingletonConnection();
        return cnx;
    }
}
