
package AFDEmp_Project_Individual;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login {
    
    
    private static final String DB_URL = "localhost:3306";
    private static final String DB_NAME = "AFDEmp_Project_Individual"; // change to what ever name you want
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "root"; // change this to your password
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL + "/" + DB_NAME + "?zeroDateTimeBehavior=convertToNull&serverTimezone=Europe/Athens&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false";
    
    
    public Login() {
		
	}

    
    
    
    
    
    
    
    
    public static boolean validateLogin(String username , String password){
	if (username == null || username.trim().length()==0){
		System.out.println("Username required");
		return false;
	}
	if (password == null || password.trim().length()==0){
		System.out.println("Password required");
		return false;
	}
	return true;
	}
    
    
    
    public static boolean validateLoginDatabase(String username , String password) {	
		String user = null;
		String pass = null;
                String status = null;
		try {
                        Database db = new Database();
                         ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "Select username , password from users where username = '" + username +"' AND status != 'inactive' ;");
                        
                        while (rs.next()) {
				user = rs.getString("username");
				pass = rs.getString("password");
			}       
                        } catch (SQLException e) {
			
			System.out.println("Ooops! Something's wrong");
                        }
                        if (username.trim().equals(user) && password.trim().equals(pass)) {
			
			return true;
                        }else {
                            System.out.println("No user with this username or password exists.");
			return false;
		}
    }
    
    
    public String getCorrectInput(int min , int max , String input) {
	Console console = System.console();	
	while(input.length() < min || input.length() > max || input.contains(" ")){
		System.out.println("Please enter a valid input!" +
				"\nAny char between the following range "+ min + "-" + max + " without space between them.");
		input = console.readLine();		
	}	
	return input;
	}
    
    

    public static String getInputForMsg(int min , int max , String input) {
	Console console = System.console();
	while(input.length() < min || input.length() > max){
		System.out.println("\t\t\t\t\t\tPlease enter a valid input!");
		input = console.readLine("\t\t\t\t\t\tMessage:");		
	}
        
         if (input.length() != 0)  {
        return input;
        }
         
         return "Empty message"  ; 
	}
     
    
    public static String getInputForMsgTitle(int max , String input) {
	Console console = System.console();
	while( input.length() > max){
		System.out.println("\t\t\t\t\t\tPlease enter a valid input!");
		input = console.readLine("\t\t\t\t\t\tTitle:");		
	}
        
        if (input.length() != 0)  {
        return input;
        }
            
         return "NO TITLE"  ; 
        
	}
    
    
}
