
package AFDEmp_Project_Individual;

import static AFDEmp_Project_Individual.MessageCRUD.getInbox;
import static AFDEmp_Project_Individual.MessageCRUD.getOutbox;
import java.io.Console;
import java.util.ArrayList;

/**
 *
 * @author alexmakris
 */
public class User {
    
    private int id;
    private String username;
    private String password;
    private String lname;
    private String fname;
    private String role;
    private String status;
    
    
    public User() {}

    public User( int id, String username, String password, String fname, String lname, String role, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lname = lname;
        this.fname = fname;
        this.role = role;
        this.status = status;
    }

    @Override
    public String toString() {
        
        String s =  "User{" + "id= " + this.id + ", username= " + this.username + ", password= " + this.password +
                ", first name= " + this.fname +  ", last name= " + this.lname + ", role = " +
                this.role + ", status = " + this.status + "}";
        
        return s;
    }
    
    public String toString2(){
        String s =  "\t" + this.id + "\t " + username +"\t " + password +
                "\t" + fname +  "\t " + lname + "\t" +
                role + "\t" + status;
          //String e="----------------";
          String fmt = "%-8s  %-18s %-18s %-18s %-18s  %-18s %-18s %n";  
         // String fmt2 = "%1s %-16s %1s %-16s %1s %-16s %1s %-16s %1s %-16s %1s %-16s %1s %-16s %n";
          //System.out.println("");
         System.out.printf(fmt, String.valueOf(id),username,password,fname,lname,role,status);  
        //System.out.printf(fmt2, "|",e,"|", e,"|", e,"|", e,"|", e,"|", e,"|", e);
      
        return s;
        
    }
    
    public String viewUserForUserList(){
        String s =  "\t" + this.id + "\t " + username +"\t " + password +
                "\t" + fname +  "\t " + lname + "\t" +
                role + "\t" + status;
          //String e="----------------";
          String fmt = "\t\t\t\t%-8s  %-18s  %-18s %-18s %n";  
         // String fmt2 = "%1s %-16s %1s %-16s %1s %-16s %1s %-16s %1s %-16s %1s %-16s %1s %-16s %n";
          //System.out.println("");
         System.out.printf(fmt, String.valueOf(id),username,role,status);  
        //System.out.printf(fmt2, "|",e,"|", e,"|", e,"|", e,"|", e,"|", e,"|", e);
      
        return s;
        
    }
    
    public void viewUser(){
        
        System.out.println("\n");
        System.out.printf("%-9s %-18s %-18s %-18s %-19s %-18s %-18s \n", "--", "--------", "--------", "----------", "---------", "----", "------");
        System.out.printf("%-9s %-18s %-18s %-18s %-19s %-18s %-18s \n", "Id", "username", "password", "First Name", "Last Name", "Role", "Status");
        System.out.printf("%-9s %-18s %-18s %-18s %-19s %-18s %-18s \n", "--", "--------", "--------", "----------", "---------", "----", "------");
        String fmt = "%-8s  %-18s %-18s %-18s %-18s  %-18s %-18s %n"; 
        System.out.printf(fmt, String.valueOf(id),username,"****",fname,lname,role,status);
        
        int inbox = getInbox(this).size();
        int outbox = getOutbox(this).size();
        System.out.println("\n\n\n");
        System.out.printf("%-30s %-18s %-30s %-18s \n","",  "---------------", "", "---------------");
        System.out.printf("%-30s %-18s %-30s %-18s \n", "", "    Inbox","", "    Outbox");
        System.out.printf("%-30s %-18s %-30s %-18s \n", "", "---------------","", "---------------");
        //System.out.println(Menus.user.toString2());
        String fmt2 = "%-36s %-18s %-30s %-18s \n";
        System.out.printf(fmt2,"", inbox,"", outbox);
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
	
    
    
    // C - CREATE
    public User createUser(int id, String username, String password, String lname, String fname, String role, String status) {
        User e = new User(id, username, password,lname, fname,role,status);
        return e;
    }
    
   
    
}
