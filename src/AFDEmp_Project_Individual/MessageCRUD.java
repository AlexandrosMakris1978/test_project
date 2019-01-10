/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFDEmp_Project_Individual;

import static AFDEmp_Project_Individual.UserCRUD.getUserByID;
import static AFDEmp_Project_Individual.Utils.clearConsole;
import static AFDEmp_Project_Individual.Utils.pauseExecution;
import static AFDEmp_Project_Individual.Utils.requestConfirmation;
import static java.lang.Integer.parseInt;
import static java.lang.System.console;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexmakris
 */
public class MessageCRUD {
    
    
    
    private static final String DB_URL = "localhost:3306";
    private static final String DB_NAME = "AFDEmp_Project_Individual"; // change to what ever name you want
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "root"; // change this to your password
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL + "/" + DB_NAME + "?zeroDateTimeBehavior=convertToNull&serverTimezone=Europe/Athens&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false";
    
    
    
    
    public static void sendMessage( int senderId, int receiverId, String title, String messageData, String messageStatus){
      
      Database db = new Database();
      //Date now = Date.valueOf(LocalDate.now());
      LocalDateTime now = LocalDateTime.now();
      Timestamp sqlNow = Timestamp.valueOf(now);
      Files file = new Files();
       messageData = messageData.replaceAll("'", "''");
        String sql = "INSERT INTO `messages` (date_time , sender_id, receiver_id, title, message, message_status ) "
                + "VALUES ('" + sqlNow + "', " + "'" + senderId + "', " + "'" + receiverId 
                + "', " + "'" + title + "', " + "'" + messageData+ "',"+ "'" + messageStatus+ "'"+");";
        System.out.println(db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD,  sql, (byte)1) + "\t\t\t\t\t\t\t message send!");
        file.msgLog(getUserByID(senderId).getUsername() ,getUserByID(receiverId).getUsername() ,title ,  messageData);
   }
    
    
    
   
    
    public static Message getMessageByID(int id){
      Message e = new Message();
      Database db = new Database();
      ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "SELECT * FROM `messages` WHERE id = " + id + ";");
        
        try {
            if (rs.next()){

               e = new Message(rs.getInt(1), rs.getTimestamp(2),rs.getInt(3), rs.getInt(4), rs.getString(5),rs.getString(6), rs.getString(7),rs.getString(8), rs.getString(9), rs.getString(10), rs.getTimestamp(11) ) ;
            } } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
  }  
    
    
    
     public Message getLastMessage(){
      Message e = new Message();
      Database db = new Database();
      ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "SELECT * FROM messages ORDER BY id DESC LIMIT 1;");
        
        try {
            if (rs.next()){

               e = new Message(rs.getInt(1), rs.getTimestamp(2),rs.getInt(3), rs.getInt(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getString(9), rs.getString(10),rs.getTimestamp(11)  ) ;
            } } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
  }  
    
  public ArrayList<Message> getAllMessages(){
       Database db = new Database();
      ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "SELECT * FROM `messages`;");
      ArrayList<Message> allMessages = new ArrayList<Message>();
        try {
            while (rs.next()) {
                Message e = new Message(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9), rs.getString(10),rs.getTimestamp(11));
                //e = e.createUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                allMessages.add(e);
                //System.out.println(resultSet1.getInt(1) + "\t" + resultSet1.getString(2) + "\t" + resultSet1.getString(3));
                //System.out.println(e.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
          
      return allMessages;
  }  
       
  
    public static ArrayList<Message> getInbox(User user){
       Database db = new Database();
      ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "SELECT * FROM `messages` WHERE `receiver_id` = '" + user.getId()+"' AND `receiver_status`!= \"deleted\" ORDER BY id DESC;");
      ArrayList<Message> inbox = new ArrayList<Message>();
        try {
            while (rs.next()) {
                Message e = new Message(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9), rs.getString(10),rs.getTimestamp(11));
               
                inbox.add(e);
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
          
      return inbox;
  }  
    
    
    public static ArrayList<Message> getOutbox(User user){
       Database db = new Database();
      ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "SELECT * FROM `messages` WHERE `sender_id` = '" + user.getId()+"' AND `sender_status`!= \"deleted\" ORDER BY id DESC;");
      ArrayList<Message> outbox = new ArrayList<Message>();
        try {
            while (rs.next()) {
                Message e = new Message(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9), rs.getString(10), rs.getTimestamp(11));
                //e = e.createUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                outbox.add(e);
//                System.out.println(e.getArticleStatus());
//                pauseExecution();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
          
      return outbox;
  }  
    
    
        public static ArrayList<Message> getPublished(){
       Database db = new Database();
      ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "SELECT * FROM `messages` WHERE `article_status` = 'published'  ORDER BY id DESC;");
      ArrayList<Message> published = new ArrayList<Message>();
        try {
            while (rs.next()) {
                Message e = new Message(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9), rs.getString(10), rs.getTimestamp(11));
               
                published.add(e);
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
          
      return published;
  }  
        
         public static ArrayList<Message> getUnpublished(){
       Database db = new Database();
      ResultSet rs = db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD, "SELECT * FROM `messages` WHERE `article_status` = 'unpublished'  ORDER BY id DESC;");
      ArrayList<Message> unpublished = new ArrayList<Message>();
        try {
            while (rs.next()) {
                Message e = new Message(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9), rs.getString(10), rs.getTimestamp(11));
               
                unpublished.add(e);
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
          
      return unpublished;
  }  
    
    public static int viewInbox(User user) {
        clearConsole();
        ArrayList <Message> inbox = getInbox(user);
        System.out.println("\n\n");
        System.out.printf("%-9s %-24s %-18s %-32s %-19s %-12s  \n", "--", "---------------------", "--------", "-----------------------------", "-------- ", " ------");
        System.out.printf("%-9s %-24s %-18s %-30s %-19s %-12s  \n", "Id", "    Date      Time", "  From",  "             Title", "   Status", "    Type" );
        System.out.printf("%-9s %-24s %-18s %-32s %-19s %-12s  \n", "--", "---------------------", "--------", "-----------------------------", "-------- ", " ------");
        
        if (inbox.size()==0){
            System.out.println("\n\n\n\n");
          System.out.println("\t\t\t\t\t\t      |\\      _,,,---,,_\n" +
"\t\t\t\t\t\tZZZzz /,`.-'`'    -.  ;-;;,_\n" +
"\t\t\t\t\t\t     |,4-  ) )-,_. ,\\ (  `'-'\n" +
"\t\t\t\t\t\t    '---''(_/--'  `-'\\_)"); 
           /* System.out.println("  ,-.       _,---._ __  / \\\n" +
" /  )    .-'       `./ /   \\\n" +
"(  (   ,'            `/    /|\n" +
" \\  `-\"             \\'\\   / |\n" +
"  `.              ,  \\ \\ /  |\n" +
"   /`.          ,'-`----Y   |\n" +
"  (            ;        |   '\n" +
"  |  ,-.    ,-'         |  /\n" +
"  |  | (   |        hjw | /\n" +
"  )  |  \\  `.___________|/\n" +
"  `--'   `--'");*/
            
        }
        /*System.out.println("  __________\n" +
" / ___  ___ \\\n" +
"/ / @ \\/ @ \\ \\\n" +
"\\ \\___/\\___/ /\\\n" +
" \\____\\/____/||\n" +
" /     /\\\\\\\\\\//\n" +
"|     |\\\\\\\\\\\\\n" +
" \\      \\\\\\\\\\\\\n" +
"   \\______/\\\\\\\\\n" +
"    _||_||_");*/
      /*  System.out.println(" ,`````.          _________\n" +
"' CiCi  `,       /_  ___   \\\n" +
"'  ^_^   `.     /@ \\/@  \\   \\\n" +
" ` , . , '  `.. \\__/\\___/   /\n" +
"                 \\_\\/______/\n" +
"                 /     /\\\\\\\\\\\n" +
"                |     |\\\\\\\\\\\\\n" +
"                 \\      \\\\\\\\\\\\\n" +
"                  \\______/\\\\\\\\     -ccw-\n" +
"            _______ ||_||_______\n" +
"           (______(((_(((______(@)");*/
      
      
      
      String i1= "  , _ ,\n" +
" ( o o )\n" +
"/'` ' `'\\\n" +
"|'''''''|\n" +
"|\\\\'''//|\n" +
"   \"\"\"";
      
      
        inbox.forEach(msg -> msg.viewMessagePreviewInbox());
        
        return inbox.size();
       
        
  }
    
     public static int viewOutbox(User user) {
        clearConsole();
        ArrayList <Message> outbox = getOutbox(user);
        System.out.println("\n\n");
        System.out.printf("%-9s %-24s %-18s %-32s %-19s %-12s  \n", "--", "---------------------", "--------", "-----------------------------", "-------- ", " ------");
        System.out.printf("%-9s %-24s %-18s %-30s %-19s %-12s  \n", "Id", "    Date      Time", "   To",  "             Title", "   Status", "    Type" );
        System.out.printf("%-9s %-24s %-18s %-32s %-19s %-12s  \n", "--", "---------------------", "--------", "-----------------------------", "-------- ", " ------");


        
        outbox.forEach(msg -> msg.viewMessagePreviewOutbox());
        return outbox.size();
  }
     
     public static int viewPublished() {
        clearConsole();
        ArrayList <Message> published = getPublished();
        System.out.println("\n\n");
        System.out.printf("%-4s %-6s %-22s %-20s %-30s %-15s \n","  ","--", "--------------", "--------------", "-----------------------------", "------------------------");
        System.out.printf("%-4s %-10s %-22s %-28s %-25s %-15s \n", "  ","Id", "writer",  "editor", "Title", "Publication" );
        System.out.printf("%-4s %-6s %-22s %-20s %-30s %-15s \n","  ","--","--------------", "--------------", "-----------------------------", "------------------------");


        
        published.forEach(msg -> msg.viewMessagePreviewPublished());
        return published.size();
  }
     
     public static int viewUnpublished() {
        clearConsole();
        ArrayList <Message> unpublished = getUnpublished();
        System.out.println("\n\n");
        System.out.printf("%-9s %-24s %-18s %-32s %-19s   \n", "--", "---------------------", "--------", "-----------------------------", "-------- ", " ------");
        System.out.printf("%-9s %-24s %-18s %-30s %-19s   \n", "Id", "    Date      Time", "   To",  "             Title", "   Status", "    Type" );
        System.out.printf("%-9s %-24s %-18s %-32s %-19s   \n", "--", "---------------------", "--------", "-----------------------------", "-------- ", " ------");


        
        unpublished.forEach(msg -> msg.viewMessagePreviewUnpublished());
        return unpublished.size();
  }
    
    public static Boolean inInbox(User user, String msgid ){
        
        ArrayList<Message> inbox = new ArrayList<Message>();
        inbox = getInbox(user);
        
        for (int i =0; i<=inbox.size() -1; i++){
             if (inbox.get(i).getId() == parseInt(msgid)){
                 return true;
             }
        }
        return false;
    }
    
    
    public static Boolean inOutbox(User user, String msgid ){
        
        ArrayList<Message> outbox = new ArrayList<Message>();
        outbox = getOutbox(user);
        
        for (int i =0; i<=outbox.size() -1; i++){
             if (outbox.get(i).getId() == parseInt(msgid)){
                 return true;
             }
        }
        return false;
    }
    
    public static Boolean inPublished(String msgid ){
        
        ArrayList<Message> published = new ArrayList<Message>();
        published = getPublished();
        
        for (int i =0; i<=published.size() -1; i++){
             if (published.get(i).getId() == parseInt(msgid)){
                 return true;
             }
           
        }
        
        return false;
    }
    
    public static Boolean inUnpublished(String msgid ){
        
        ArrayList<Message> unpublished = new ArrayList<Message>();
        unpublished = getUnpublished();
        
        for (int i =0; i<=unpublished.size() -1; i++){
             if (unpublished.get(i).getId() == parseInt(msgid)){
                 return true;
             }
           
        }
        
        return false;
    }
    
    
    
    
    public static void updateMsgReceiverStatus(int id){
        Database db = new Database();
      
        String sql = "UPDATE `messages` SET receiver_status = 'read' WHERE id = "+ id +" ;";
        db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD,  sql, (byte)1) ;
        
    }
    
     public static void updateMsgDeleteFromInbox(int id){
        Database db = new Database();
      
        String sql = "UPDATE `messages` SET receiver_status = 'deleted' WHERE id = "+ id +" ;";
        db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD,  sql, (byte)1) ;
        
    }
     
      public static void updateMsgDeleteFromOutbox(int id){
        Database db = new Database();
      
        String sql = "UPDATE `messages` SET sender_status = 'deleted' WHERE id = "+ id +" ;";
        db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD,  sql, (byte)1) ;
        
    }
    
       public static void submitArticle(int id){
        Database db = new Database();
      
        String sql = "UPDATE `messages` SET article_status = 'unpublished' WHERE id = "+ id +" ;";
        db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD,  sql, (byte)1) ;
        
    }
      
    public static void publishArticle(int id){
        Database db = new Database();
        LocalDateTime now = LocalDateTime.now();
        Timestamp sqlNow = Timestamp.valueOf(now);
        
        String sql = "UPDATE `messages` SET article_status = 'published', publication_time = '" + sqlNow + "' WHERE id = "+ id +" ;";
        db.Database(DB_URL, DB_NAME, DB_USER, DB_PASSWD,  sql, (byte)1) ;
        
    }
    
}
