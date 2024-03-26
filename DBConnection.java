/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.*;
/**
 *
 * @author HP
 */
public abstract class DBConnection {
    private final String DBName = "registrationdb";
    private final String username = "admin";
    private final String password = "12345678";
    
    Connection connection;
    
    public void openConnection(){
        this.connection = this.DBConnect(this.DBName, this.username, this.password);
        System.out.println("DB Connection Opened");
    }
    
    public Connection DBConnect(String DBName, String username, String password){
        Connection _connection = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Loaded succcessfully");
        }catch(ClassNotFoundException ex){
            System.out.println("Class not Found: " + ex.getMessage());
        }
        
        try{
            _connection = DriverManager.getConnection("jdbc:mysql://localhost/" + DBName,username,password);
            System.out.println("Successfully connected to database: " + DBName);
        }catch(SQLException sqle){
            System.out.println("SQL Error: " + sqle.getMessage());
        }
        
        return _connection;
    }
    
}
