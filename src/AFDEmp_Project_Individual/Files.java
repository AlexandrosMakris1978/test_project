/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFDEmp_Project_Individual;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alexmakris
 */
public class Files {
    private static final String FILENAME_PART_1 = "statement_";
    private static final String FILENAME_PART_2 = ".txt";
    private static String fileName = null;
    private static PrintWriter LogFile = null;
    private Timestamp dt;
    public void Files() {
    }
    
    
    
    
    
    
 public void mainLog(String username , String action) {
		BufferedWriter bw = null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy G 'at' HH:mm:ss z");
		String dateFormat = sdf.format(date);
		//String header = "______TIMESTAMP____________________USERNAME________ACTION_______";
		
                
                try {
			//Specify the file name and path here
			//Important change the path where you want to save.
			File file = new File("main_Log.txt");
			if (!file.exists()) {
				file.createNewFile();
				FileWriter fw2 = new FileWriter(file);
				BufferedWriter bw2 = new BufferedWriter(fw2);
				//bw2.write(header);
				bw2.close();
			}
			FileWriter fw = new FileWriter(file , true);
			bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(dateFormat +" | " + username + "     | " + action );
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("\nChange the path of file to the currect one to fit on your local machine.");
		}
		finally
		{ 
			try{
				if(bw!=null)
					bw.close();

			}catch(Exception ex){
				System.out.println("Error closing the BufferedWriter"+ex);
			}
		}
	}	

	public void msgLog(String sender , String receiver , String title, String  messageData) {
       
            BufferedWriter bw = null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy G 'at' HH:mm:ss z");
		String dateFormat = sdf.format(date);
		
		try {
			//Specify the file name and path here
			//Important change the path where you want to save.
			File file = new File("msg_Log.txt");
			if (!file.exists()) {
				file.createNewFile();
				FileWriter fw2 = new FileWriter(file);
				BufferedWriter bw2 = new BufferedWriter(fw2);
				//bw2.write(header);
				bw2.close();
			}
			FileWriter fw = new FileWriter(file , true);
			bw = new BufferedWriter(fw);
			bw.newLine();
                        
			bw.write(dateFormat + " sender = " + sender + " receiver = " + receiver + " title = " + title+ " message = " + messageData );
		} catch (IOException ioe) {
			ioe.printStackTrace();			
			System.out.println("\nChange the path of file to the currect one to fit on your local machine.");	
		}
		finally
		{ 
			try{
				if(bw!=null)
					bw.close();
			}catch(Exception ex){
				System.out.println("Error closing the BufferedWriter"+ex);
			}
		}
	}   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}