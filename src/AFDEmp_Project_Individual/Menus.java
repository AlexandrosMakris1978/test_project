/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFDEmp_Project_Individual;

import static AFDEmp_Project_Individual.Utils.clearConsole;
import static AFDEmp_Project_Individual.Login.*;
import static AFDEmp_Project_Individual.UserCRUD.*;
import static AFDEmp_Project_Individual.MessageCRUD.*;
import static AFDEmp_Project_Individual.Utils.confirmArticle;
import static AFDEmp_Project_Individual.Utils.confirmArticleEdit;
import static AFDEmp_Project_Individual.Utils.confirmArticletoPublisher;
import static AFDEmp_Project_Individual.Utils.confirmPublication;
import static AFDEmp_Project_Individual.Utils.header;
import static AFDEmp_Project_Individual.Utils.optionToDeleteMsg;
import static AFDEmp_Project_Individual.Utils.optionToDeleteOrUpdateUsr;
import static AFDEmp_Project_Individual.Utils.optionToPublish;
import static AFDEmp_Project_Individual.Utils.pauseExecution;
import static AFDEmp_Project_Individual.Utils.progress;
import static AFDEmp_Project_Individual.Utils.requestConfirmation;
import java.io.Console;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alexmakris
 */
public class Menus {
    
    static User user = new User();
    private Files file = new Files();

    
     public void loginMenu() {
                clearConsole();
		Menu menu = new Menu();
		menu.setTitle("_______ Login Menu _______");
		menu.addItem(new MenuItem("Login", this, "loginForm"));
                menu.addItem(new MenuItem("Exit", this, "exit"));
		
		
		menu.execute();
     }
     
     public void loginForm(){
                Console console = System.console();
                clearConsole();
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("\t\t\t\t\t\tFILL YOUR CREDENTIALS");
				String username = console.readLine("\t\t\t\t\t\tUsername: ");
				String password = new String(console.readPassword("\t\t\t\t\t\tPassword: "));
				//check that user has enter username and password 
				if (validateLogin(username , password)) {
					//check username, password in database
					if(	validateLoginDatabase(username.trim() , password.trim())) {
						clearConsole();
                                                
                                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
                                                file.mainLog(username, "Login");
                                                console.printf("\t\t\t\t\t\t\tWelcome, %1$s.",username);
                                                UserCRUD uc= new UserCRUD();
						uc.updateUserStatus(username,"online");
						user = uc.getUserByUsername(username);	
						
							
                                                System.out.println("\n\n\n");
                                                pauseExecution();
                                                //String role = user.getRole();
                                                //System.out.println(role);
                                                //pauseExecution();
                                                mainMenu();
                                                //Menu.in.close();
//                                                if (role.equals("writer")){
//                                                    new Menus().mainMenu();
//                                                }
                                                
					}else{
                                            System.out.println("oops1");
                                            pauseExecution();
                                            loginMenu();
                                        }
         
                                }else {
                                    System.out.println("oops2");
                                    pauseExecution();
                                    loginMenu();
                                }
     }                           
    
     public void mainMenu() {
		clearConsole();
                
                Menu menu = new Menu();
		menu.setTitle("  ~~~~~~~~~~ Main Menu ~~~~~~~~~~");
		menu.addItem(new MenuItem("Your Profile", this, "profile"));
                menu.addItem(new MenuItem("User List", this, "userList"));
		menu.addItem(new MenuItem("Create Message", this, "createMessage"));
                menu.addItem(new MenuItem("View Inbox", this, "inbox"));
                menu.addItem(new MenuItem("View Outbox", this, "outbox"));

		menu.addItem(new MenuItem("Published Articles", this, "publishedArticles"));
                if (user.getRole().equals("admin")){
                    menu.addItem(new MenuItem("Admin Menu", this, "adminMenu"));
                }
                menu.addItem(new MenuItem("Help", this, "help"));
                menu.addItem(new MenuItem("Logout", this, "logout"));
		menu.execute();
    }
     
     
     public void profile(){
         clearConsole();
         System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
         System.out.println("\t\t\t\t\t\t\tYour Profile");
         user.viewUser();
         System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
         Utils.pauseExecution();
         mainMenu();
         
         
     } 
     
     
     
     public void userList(){
         clearConsole();
         viewUserList();
         System.out.println("\n\n");
         Utils.pauseExecution();
         mainMenu();
     }
     
     public void createMessage(){
     
        Console console = System.console();
        clearConsole();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\t\t\t\t\t\tCreate Message");
	String receiver = console.readLine("\t\t\t\t\t\tSent to: "); 
        
        if (!isActive(receiver)){
            System.out.println("\t\t\t\t\t\tNo such user!");
            pauseExecution();
            mainMenu();
        }
        
        String title = console.readLine("\t\t\t\t\t\tTitle (max 70 characters): "); 
        title = getInputForMsgTitle(70,title);
        String messageData = console.readLine("\t\t\t\t\t\tMessage (max 250 characters): "); 
        messageData = getInputForMsg(2,250,messageData);
        
        
        if (isEditor(receiver) && confirmArticle()){
          
           sendMessage(user.getId(), getUserByUsername(receiver).getId(), title, messageData, "article"); 
            
        }else{
            sendMessage(user.getId(), getUserByUsername(receiver).getId(), title, messageData, "message");
        }
        pauseExecution();
        mainMenu();
         
    }
     
     public void inbox(){
         String msgId = null;
         int i = viewInbox(user);
         
         Console console = System.console();
         System.out.println("\n\n\n");
         
         if (i > 0){
         msgId = console.readLine("\t\t\t\t\t\tSelect a message to read: "); 
         }
         
         if (inInbox(user,msgId)){
            
             Message msg = new Message();
             msg = getMessageByID(parseInt(msgId));
             msg.viewMessage();
             updateMsgReceiverStatus(msg.getId());
             System.out.println("\n\n\n\n\n\n");
             
         
            
             if (user.getRole().equals("editor") && msg.getMessageStatus().equals("article") && msg.getArticleStatus().equals("not_submitted")){
                 
                  if(confirmArticleEdit()){
                  
                   String title = console.readLine("\t\t\t\t\t\tTitle (max 70 characters): "); 
                      //System.out.write("\r"+ msg.getMessageTitle());
                      StringBuffer buffer = new StringBuffer(msg.getMessageTitle());
                      Scanner scanner = new Scanner("");
                      while (scanner.hasNext()){
                      buffer.append(scanner.nextLine());
                      System.out.println(buffer.toString());
}

                   //String abc = buffer.toString();
                   title = getInputForMsgTitle(70,title);
//                   String messageData = console.readLine("\t\t\t\t\t\tMessage (max 250 characters): "); 
//                   messageData = getInputForMsg(2,250,messageData);
                }
                 
                 
                 if(confirmArticletoPublisher()){
                  
                 submitArticle(msg.getId());
                }
             }
             
             
             if (optionToDeleteMsg()){
                 updateMsgDeleteFromInbox(msg.getId());
             }
             
             inbox();
         }
         
         pauseExecution();
         mainMenu();
         
     }
     
     public void outbox(){
         String msgId = null;
         int i = viewOutbox(user);
         
         
         Console console = System.console();
         System.out.println("\n\n\n");
         if (i > 0){
         msgId = console.readLine("\t\t\t\t\t\tSelect a message to read: "); 
         }
         if (inOutbox(user,msgId)){
            
             Message msg = new Message();
             msg = getMessageByID(parseInt(msgId));
             msg.viewMessage();
             //updateMsgReceiverStatus(msg.getId());
             System.out.println("\n\n\n\n\n\n");
             if (optionToDeleteMsg()){
                 updateMsgDeleteFromOutbox(msg.getId());
             }
             //pauseExecution();
             outbox();
         }
         
         pauseExecution();
         mainMenu();
         
     }
     
     
     public void publishedArticles(){
         String msgId = null;
         int i = viewPublished();
         
         Console console = System.console();
         System.out.println("\n\n\n");
         if (i == 0) {
             pauseExecution();
         }
         if (i > 0){
         msgId = console.readLine("\t\t\t\t\t\tSelect an article to read: "); 
         }
         //System.out.println(inPublished(msgId));
         if (inPublished(msgId)){
            
             Message msg = new Message();
             msg = getMessageByID(parseInt(msgId));
             msg.viewMessage();
             System.out.println("");
             System.out.println("\t\t\t\t\t\t\t\t\t\t   by " + getUserByID(msg.getSenderId()).getFname() + " " + getUserByID(msg.getSenderId()).getLname() );
             //updateMsgReceiverStatus(msg.getId());
             System.out.println("\n\n\n\n\n\n");
            
//             if (optionToDeleteMsg()){
//                 updateMsgDeleteFromInbox(msg.getId());
//             }
             pauseExecution();
             publishedArticles();
         }
         
         //pauseExecution();
         mainMenu();
         
     }
     
     
     public void help(){
         clearConsole();
         System.out.println("\n\n");
         String i1 = "\t\t\t\t\t\t\t  __________\n" +
"\t\t\t\t\t\t\t / ___  ___ \\\n" +
"\t\t\t\t\t\t\t/ / @ \\/ @ \\ \\\n" +
"\t\t\t\t\t\t\t\\ \\___/\\___/ /\\\n" +
"\t\t\t\t\t\t\t \\____\\/____/||\n" +
"\t\t\t\t\t\t\t /     /\\\\\\\\\\//\n" +
"\t\t\t\t\t\t\t|     |\\\\\\\\\\\\\n" +
"\t\t\t\t\t\t\t \\      \\\\\\\\\\\\\n" +
"\t\t\t\t\t\t\t   \\______/\\\\\\\\\n" +
"\t\t\t\t\t\t\t    _||_||_";
         String i2 = "\t\t\t\t\t\t\t  , _ ,\n" +
"\t\t\t\t\t\t\t ( o o )\n" +
"\t\t\t\t\t\t\t/'` ' `'\\\n" +
"\t\t\t\t\t\t\t|'''''''|\n" +
"\t\t\t\t\t\t\t|\\\\'''//|\n" +
"\t\t\t\t\t\t\t   \"\"\"";
         String i3 = "";
         System.out.println(i1);
         System.out.println("\n\n\n\n\n");
         pauseExecution();
     }
     
     
     
     public void adminMenu() {
		clearConsole();
                Menu menu = new Menu();
		menu.setTitle("  ~~~~~~~~~~ Adnin Menu ~~~~~~~~~~");
		menu.addItem(new MenuItem("Create User", this, "createUser"));
                menu.addItem(new MenuItem("Admin User List", this, "adminUserList"));
		menu.addItem(new MenuItem("Unpublished Articles", this, "unpublishedArticles"));
                //menu.addItem(new MenuItem("View Inbox", this, "inbox"));
                //menu.addItem(new MenuItem("View Outbox", this, "outbox"));
               
                menu.addItem(new MenuItem("Main Menu", this, "mainMenu"));
		menu.execute();
    }
     
     
     
     public void createUser(){
                Console console = System.console();
                clearConsole();
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("\t\t\t\t\t\tCREATE USER");
                                String username = console.readLine("\t\t\t\t\t\tUsername: ");
                                // validate username
                                if (isUser(username)){
                                    System.out.println("\t\t\t\t\t\tUsername already in use!");
                                    pauseExecution();
                                    adminMenu();
                                }
                                String password = new String(console.readPassword("\t\t\t\t\t\tPassword: "));
                                //validate password
                                String fname = console.readLine("\t\t\t\t\t\tFirst name: ");
                                //validate first name
                                String lname = console.readLine("\t\t\t\t\t\tLast name: ");
                                //validate last name
                                String role = console.readLine("\t\t\t\t\t\tRole: ");
                                //validate role
                                
                                insertUser(username, password, fname, lname, role);
                                pauseExecution();
                                adminMenu();
     }
     
     
     public void adminUserList(){
         String userId= null;
         clearConsole();
         int u = viewAllUsers();
         Console console = System.console();
         System.out.println("\n\n\n");
         if (u > 0){
         userId = console.readLine("\t\t\t\t\t\tSelect a user or enter to return: "); 
         if (getUserByID(parseInt(userId)).getId()  > 0){
             userProfile(getUserByID(parseInt(userId)));
         
         }
         Utils.pauseExecution();
        //adminMenu();
     
         
         }
         
     }
     
     
     
     
     
     public void userProfile(User userX){
         int option = 0;
         clearConsole();
         Console console = System.console();
         System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
         System.out.println("\t\t\t\t\t\t\tProfile");
         userX.viewUser();
         System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
         
         if (!userX.getStatus().equals("online")){
         option = optionToDeleteOrUpdateUsr();
         }
         
         if (option == 1 && requestConfirmation()) {
             updateUserStatus(userX.getUsername(), "inactive");
             
         }else if (option == 2){
             adminUpdateUser(userX);
             
             
             
             System.out.println("update!!!");
             pauseExecution();   
         }
         
         adminMenu();
     
     
     }
     
     
     public void adminUpdateUser(User u){
         Console console = System.console();
                clearConsole();
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("\t\t\t\t\t\tUPDATE USER");
                                String username = console.readLine("\t\t\t\t\t\tUsername: ");
                                // validate username
                                if (isUser(username)){
                                    System.out.println("\t\t\t\t\t\tUsername already in use!");
                                    pauseExecution();
                                    adminUpdateUser(u);
                                }
                                String password = new String(console.readPassword("\t\t\t\t\t\tPassword: "));
                                //validate password
                                String fname = console.readLine("\t\t\t\t\t\tFirst name: ");
                                //validate first name
                                String lname = console.readLine("\t\t\t\t\t\tLast name: ");
                                //validate last name
                                String role = console.readLine("\t\t\t\t\t\tRole: ");
                                //validate role
                                
                                int i = updateUser(u.getId(), username, password, fname, lname, role);
                                if (i == 1) {
                                    System.out.println("user updated");
                                }
                                pauseExecution();
                                adminMenu();
         
         
         
     }
     
     
     public void unpublishedArticles(){
         String msgId = null;
         int i = viewUnpublished();
         
         Console console = System.console();
         System.out.println("\n\n\n");
         if (i == 0) {
             pauseExecution();
         }
         if (i > 0){
         msgId = console.readLine("\t\t\t\t\t\tSelect an article to read: "); 
         }
         //System.out.println(inPublished(msgId));
         if (inUnpublished(msgId)){
            
             Message msg = new Message();
             msg = getMessageByID(parseInt(msgId));
             msg.viewMessage();
             
             System.out.println("\n\n\n\n\n\n");
            
             if (optionToPublish() && confirmPublication()){
                 //updateMsgDeleteFromInbox(msg.getId());
                 
                 publishArticle(msg.getId()); 

             }
             pauseExecution();
             unpublishedArticles();
         }
         
         //pauseExecution();
        adminMenu();
         
     }
     
     
     
     
     
     
     
     
     
//     public void subMenuA() {
//        clearConsole();
//        Menu menu = new Menu();
//	menu.setTitle("*** Sub Menu A ***");
//	menu.addItem(new MenuItem("Option Aa"));
//	menu.addItem(new MenuItem("Option Ab"));
//	menu.execute();
//    }
//	
//    
//	
//    /* Added a confirmation request here to demonstrate how it could be used. */
//    public void performOptionC() {
//        clearConsole();
//    	boolean confirm = Utils.requestConfirmation();
//	if(confirm) 
//            System.out.println("\nDo Option C...");
//        else 
//            System.out.println("\nOption C cancelled!");
//        Utils.pauseExecution();
//    }
    
    public void logout(){
        //UserCRUD uc= new UserCRUD();
        UserCRUD.updateUserStatus(user.getUsername(),"offline");
        file.mainLog(user.getUsername(), "Login");
        loginMenu();
    }
    
    public void exit(){
        
        //clearConsole();
    	boolean confirm = Utils.requestConfirmation();
	if(confirm){ 
            clearConsole();
            
            System.exit(0);
        }else 
            loginMenu();
    }
    
}
