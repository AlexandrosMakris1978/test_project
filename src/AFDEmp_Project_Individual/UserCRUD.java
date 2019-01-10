/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFDEmp_Project_Individual;

import static AFDEmp_Project_Individual.Utils.*;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexmakris
 */
public class UserCRUD {
    
    
    
    private static final String DB_URL = "localhost:3306";
    private static final String DB_NAME = "AFDEmp_Project_Individual"; // change to what ever name you want
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "root"; // change this to your password
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL + "/" + DB_NAME + "?zeroDateTimeBehavior=convertToNull&serverTimezone=Europe/Athens&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false";
    
  
  public static User getUserByID(int id){
      User e = new User();
      Database db = new Database();
      ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "SELECT * FROM `users` WHERE id = " + id +";");
        
        try {
            if (rs.next()){
//                e.setId(rs.getInt(1));
//                e.setUsername(rs.getString(2));
//                e.setPassword(rs.getString(3));
//                e.setFname(rs.getString(4));
//                e.setLname(rs.getString(5));
//                e.setRole(rs.getString(6));
//                e.setStatus(rs.getString(7));

               e = e.createUser(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7)  ) ;
            } } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
  }  
  
  
  
  public static User getUserByUsername(String username){
      User e = new User();
      Database db = new Database();
      ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "SELECT * FROM `users` WHERE username = '"+username+"';");
        
        try {
            if (rs.next()){
               e = e.createUser(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7)  ) ;
            } } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
  } 
  
  
  
  public static Boolean isUser(String username){
      
      User u = getUserByUsername(username);
      return u.getStatus() != null;
      
    }
  
  
  
  
  
  
  
  public static Boolean isActive(String username){
      
      User u = getUserByUsername(username);
      if (u.getStatus() == null) {
          return false;
      }else if (u.getStatus().equals("inactive")){
       return false;
      
      }
      return true;
      
    }
  
  public static Boolean isEditor(String username){
      
      User u = getUserByUsername(username);
      return u.getRole().equals("editor");
      
      
      
      
    }
    
        public static int updateUserStatus(String username, String status){
            
            int e = 0;
            Database db = new Database();
            String sql = "UPDATE users SET status = '" + status + "'  WHERE  username = '" + username + "' ;  ";
            e =db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD,  sql, (byte)1);
      
            return e;  
            
        }
        
        
        public static int updateUser(int id,String username,String password, String fname, String lname,  String role){
            
            int e = 0;
            Database db = new Database();
            String sql = "UPDATE users SET username = '" + username + "', password = '" + password +"', fname = '" + fname + "', lname = '" + lname + "', role = '" + role + "'  WHERE  id = '" + id + "' ;  ";
            e =db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD,  sql, (byte)1);
      
            return e;  
            
        }
  
  
  public static void insertUser(String username , String password, String fname, String lname, String role){
      
      Database db = new Database();
        String sql = "INSERT INTO `users` (username, password, fname, lname, role) "
                + "VALUES ('" + username + "', " + "'" +password + "', " + "'" +fname 
                + "', " + "'" +lname + "', " + "'" +role + "');";
        System.out.println(db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD,  sql, (byte)1) + " user added!");
      
  }
  
  
    
  public static ArrayList<User> getAllUsers(){
       Database db = new Database();
      ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "SELECT * FROM `users`;");
      ArrayList<User> allUsers = new ArrayList<User>();
        try {
            while (rs.next()) {
                User e = new User();
                e = e.createUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                allUsers.add(e);
                //System.out.println(resultSet1.getInt(1) + "\t" + resultSet1.getString(2) + "\t" + resultSet1.getString(3));
                //System.out.println(e.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
          
      return allUsers;
  }  
  
  public static ArrayList<User> getAllActiveUsers(){
       Database db = new Database();
      ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "SELECT * FROM `users` WHERE status != 'inactive' ;");
      ArrayList<User> allUsers = new ArrayList<User>();
        try {
            while (rs.next()) {
                User e = new User();
                e = e.createUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                allUsers.add(e);
                //System.out.println(resultSet1.getInt(1) + "\t" + resultSet1.getString(2) + "\t" + resultSet1.getString(3));
                //System.out.println(e.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
          
      return allUsers;
  }  
     
    
    
    
    
    
    
    
    
    
    
    
    public static int viewAllUsers() {
        int i = 0;
        clearConsole();
        ArrayList <User> allUsers = getAllUsers();
        System.out.println();
        System.out.printf("%-9s %-18s %-18s %-18s %-19s %-18s %-18s \n", "--", "--------", "--------", "----------", "---------", "----", "------");
        System.out.printf("%-9s %-18s %-18s %-18s %-19s %-18s %-18s \n", "Id", "username", "password", "First Name", "Last Name", "Role", "Status");
        System.out.printf("%-9s %-18s %-18s %-18s %-19s %-18s %-18s \n", "--", "--------", "--------", "----------", "---------", "----", "------");

        i = allUsers.size();
        allUsers.forEach(usr -> usr.toString2());
        //requestConfirmation();
        return i;
  }
    
    
    public static void viewAllActiveUsers() {
        clearConsole();
        ArrayList <User> allUsers = getAllActiveUsers();
        System.out.println();
        System.out.printf("%-9s %-18s %-18s %-18s %-19s %-18s %-18s \n", "--", "--------", "--------", "----------", "---------", "----", "------");
        System.out.printf("%-9s %-18s %-18s %-18s %-19s %-18s %-18s \n", "Id", "username", "password", "First Name", "Last Name", "Role", "Status");
        System.out.printf("%-9s %-18s %-18s %-18s %-19s %-18s %-18s \n", "--", "--------", "--------", "----------", "---------", "----", "------");

        
        allUsers.forEach(usr -> usr.toString2());
        //requestConfirmation();
  }
    
    
    public static void viewUserList() {
        clearConsole();
        ArrayList <User> allUsers = getAllActiveUsers();
        System.out.println("\n\n");
        System.out.printf("\t\t\t\t%-9s %-20s %-18s %-18s \n", "--", "--------",  "----", "------");
        System.out.printf("\t\t\t\t%-9s %-20s %-18s %-18s \n", "Id", "username",  "Role", "Status");
        System.out.printf("\t\t\t\t%-9s %-20s %-18s %-18s \n", "--", "--------",  "----", "------");

        
        allUsers.forEach(usr -> usr.viewUserForUserList());
        //requestConfirmation();
  }
    
    public void viewUserProfile(){
        
        
        
    }
    
    
    
}
