/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFDEmp_Project_Individual;

import static AFDEmp_Project_Individual.Utils.*;

import static AFDEmp_Project_Individual.UserCRUD.getUserByID;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;




public class Message {
    private int id;
    //private Date dateTime;
    private Timestamp dt;
    private int senderId;
    private int receiverId;
    private String messageTitle;
    private String messageData;
    private String senderStatus;
    private String receiverStatus;
    private String messageStatus;
    private String articleStatus;
    private Timestamp publicationDT;

    public Message(){
        
    }
    
    
    public Message( int id, Timestamp timedate, int senderId, int receiverId, String messageTitle, String messageData, String senderStatus, String receiverStatus, String messageStatus, String articleStatus, Timestamp publicationDT) {
        this.id = id;
        this.dt = timedate;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageTitle = messageTitle;
        this.messageData = messageData;
        this.senderStatus = senderStatus;
        this.receiverStatus = receiverStatus;
        this.messageStatus = messageStatus;
        this.articleStatus = articleStatus;
        this.publicationDT = publicationDT;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Timestamp getDt() {
        return dt;
    }

    public void setDt(Timestamp dt) {
        this.dt = dt;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    public String getSenderStatus() {
        return senderStatus;
    }

    public void setSenderStatus(String senderStatus) {
        this.senderStatus = senderStatus;
    }

    public String getReceiverStatus() {
        return receiverStatus;
    }

    public void setReceiverStatus(String receiverStatus) {
        this.receiverStatus = receiverStatus;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Timestamp getPublicationDT() {
        return publicationDT;
    }

    public void setPublicationDT(Timestamp publicationDT) {
        this.publicationDT = publicationDT;
    }
    
   
    
    
  
    public String toStringPreview() {
        UserCRUD uc = new UserCRUD();
        return "Message id=" + id + ", dt=" + dt + ", senderId=" + senderId + ", receiverId=" + receiverId + ", Title=" + messageTitle + ", type=" + messageStatus +'}';
    }

    @Override
    public String toString() {
        return "Message id=" + id + ", dt=" + dt + ", senderId=" + senderId + ", receiverId=" + receiverId + 
                ", Title=" + messageTitle + "\n" + 
                "message=" + messageData + "type=" + messageStatus+'}';
    }
    
    public void viewMessage(){
        
        clearConsole();
        //System.out.println("Message from " + getUserByID(senderId).getUsername() + " send : " );
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        wrapText( "~  " + messageTitle,70,15,'~');
        
       
        System.out.println();
        wrapText(messageData,40,50, ' ');
        
    }
    
    public void viewMessagePreviewInbox(){ 
        String fmt = "%-8s  %-24s %-18s %-34s %-18s %-12s  %n"; 
        System.out.printf(fmt, String.valueOf(id), dt,UserCRUD.getUserByID(senderId).getUsername(),messageTitle,receiverStatus, messageStatus);
        
       }

    
    public void viewMessagePreviewOutbox(){ 
        String fmt = "%-8s  %-24s %-18s %-34s %-18s %-12s  %n"; 
        System.out.printf(fmt, String.valueOf(id), dt,UserCRUD.getUserByID(receiverId).getUsername(),messageTitle,senderStatus, messageStatus);
        
       }
    
    public void viewMessagePreviewPublished(){ 
        String fmt = "%-4s %-9s %-22s %-18s %-30s %-15s   %n"; 
        System.out.printf(fmt, "  ",String.valueOf(id),UserCRUD.getUserByID(senderId).getUsername(),UserCRUD.getUserByID(receiverId).getUsername(),messageTitle,publicationDT);
        
       }
    
    public void viewMessagePreviewUnpublished(){ 
        String fmt = "%-8s  %-24s %-18s %-34s    %n"; 
        System.out.printf(fmt, String.valueOf(id), UserCRUD.getUserByID(senderId).getUsername(),UserCRUD.getUserByID(receiverId).getUsername(),messageTitle);
        
       }
    
    
    
    
    
    
    
    
    
    
}