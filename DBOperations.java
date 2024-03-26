/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class DBOperations extends DBConnection {
    public boolean insert(String name, String mobile, String gender, String DOB, String address){
        String query = "insert into users(name, contact, gender, date_of_birth, address) values(?, ?, ?, ?, ?)";
        boolean status = false;
        try{
//            Create Statement
            this.openConnection();
            PreparedStatement pst;
            pst = connection.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, mobile);
            pst.setString(3, gender);
            pst.setDate(4, java.sql.Date.valueOf(DOB));
            pst.setString(5, address);
            
//            Execute Statement
            int index;
            index = pst.executeUpdate();
            
//            Process Result
            if(index > 0){
                System.out.println(name + " successfully added to database");
                status = true;
            }else{
                System.err.println("Unable to insert '" + name + ",");
            }

        }catch(SQLException sqle){
            System.err.println("Error while inserting to DB: " + sqle.getMessage());          
        }
        
        try{
            this.connection.close();
        }catch(SQLException sqle){
            System.err.println("Error: " + sqle.getMessage());
        }
        
        return status;
        
}
    
    public ArrayList<String[]> selectAll(){
        String query = "SELECT * from users";
        ResultSet rs;
        ArrayList<String[]> users = new ArrayList<>();
        
        try{
            PreparedStatement pst;
            openConnection();
            pst = this.connection.prepareStatement(query);
            rs = pst.executeQuery();
            
            
            while(rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String address = rs.getString("contact");
                String contact = rs.getString("contact");
                String DOB = rs.getString("date_of_birth");
                String[] user = {id,name,gender,address,contact,DOB};
                users.add(user);
            }            
        }catch(SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        
        try{
            this.connection.close();
        }catch(SQLException sqle){
            System.err.println("Error: " + sqle.getMessage());
        }
        
        return users;
    }
    
}
