/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFDEmp_Project_Individual;
import java.io.Console;
import java.io.IOException;
//import static java.lang.System.console;
import java.util.Arrays;

/**
 *
 * @author alexmakris
 */
public class Utils {

    private static Console console = null;
   
    // returns a string of n characters ch 
    public static String stringOfSize(int n, char ch)
{
    final char[] array = new char[n];
    Arrays.fill(array, ch);
    return new String(array);
}
    
    
    
    
    public static void wrapText(String text, int width, int indentation, char ch) {
        
        
        int count = 0;
        String ind = stringOfSize(indentation, ch );
        int ind2 = 0;
        int ind3= 0;
        
        System.out.print(ind);
        
        for (String word : text.split("\\s+")) {
            if (count + word.length() >= width) {
                ind2 = count + ind.length();
                
                System.out.print(stringOfSize(150 - ind2, ch));
                System.out.println();
                System.out.print(ind);
                count = 0;
            }

            System.out.print(word);
            System.out.print(' ');

            count += word.length() + 1;
            ind3 = count + ind.length() ;
        }
    
        //System.out.println(stringOfSize(150 - int3, ch));
        System.out.println(stringOfSize(150 - ind3 - 50, ch));
    }
    
    
    
    
    
    public static void pauseExecution() {
            if(console == null) console = System.console();
            System.out.print("\t\t\t\t\t\t  Press Enter to Continue... ");
		console.readLine();
	}
   
    public static void progress() throws IOException, InterruptedException{
        
        String anim = "|/-\\";
        for (int x=0 ; x<100 ; x++){
            String data ="\r" + anim.charAt(x % anim.length()) + " " + x;
            System.out.write(data.getBytes());
            Thread.sleep(100);
        }
    }
    
    
    public static boolean requestConfirmation() {
            String in = "";
            if(console == null) console = System.console();
            
            while (true) {
			System.out.print("Confirm Operation (y/n)... ");
			in = console.readLine().toLowerCase();
			if(in.equals("y") || in.equals("yes"))
                            return true;
			else if(in.equals("n") || in.equals("no"))
                            return false;
            }
	}
   
    
    
    
    public static boolean confirmArticle() {
            String in = "";
            if(console == null) console = System.console();
            
            while (true) {
			System.out.print("\t\t\t\t\t\tSubmit as an Article? (y/n)... ");
			in = console.readLine().toLowerCase();
			if(in.equals("y") || in.equals("yes"))
                            return true;
			else if(in.equals("n") || in.equals("no"))
                            return false;
            }
	}
    
    public static boolean confirmArticleEdit() {
            String in = "";
            if(console == null) console = System.console();
            
            while (true) {
			System.out.print("Edit Article before submition? (y/n)... ");
			in = console.readLine().toLowerCase();
			if(in.equals("y") || in.equals("yes"))
                            return true;
			else if(in.equals("n") || in.equals("no"))
                            return false;
            }
	}
    
    public static boolean confirmArticletoPublisher() {
            String in = "";
            if(console == null) console = System.console();
            
            while (true) {
			System.out.print("Submit Article to Publisher? (y/n)... ");
			in = console.readLine().toLowerCase();
			if(in.equals("y") || in.equals("yes"))
                            return true;
			else if(in.equals("n") || in.equals("no"))
                            return false;
            }
	}
    
    public static boolean confirmPublication() {
            String in = "";
            if(console == null) console = System.console();
            
            while (true) {
			System.out.print("Publish Article? (y/n)... ");
			in = console.readLine().toLowerCase();
			if(in.equals("y") || in.equals("yes"))
                            return true;
			else if(in.equals("n") || in.equals("no"))
                            return false;
            }
	}
    
    public static boolean optionToDeleteMsg() {
            String in = "";
            if(console == null) console = System.console();
            
            while (true) {
			System.out.print("Enter d to delete message or enter to return :");
			in = console.readLine().toLowerCase();
                return in.equalsIgnoreCase("d");
            }
	}
    
    public static boolean optionToPublish() {
            String in = "";
            if(console == null) console = System.console();
            
            while (true) {
			System.out.print("Enter p to publish article or enter to return :");
			in = console.readLine().toLowerCase();
                return in.equalsIgnoreCase("p");
            }
	}
    
    public static int optionToDeleteOrUpdateUsr() {
            String in = "";
            if(console == null) console = System.console();
            
            while (true) {
			System.out.print("Enter d to deactivate user, u to update user or enter to return :");
			in = console.readLine().toLowerCase();
                        if (in.equalsIgnoreCase("d")) return 1;
                        if (in.equalsIgnoreCase("u")) return 2;
                        return 0;
            }
	}
    
     public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");
            System.out.println(os);
            if (os.contains("Windows"))
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
            {
                Runtime.getRuntime().exec("clear");
                System.out.print("\033\143");
            }
        }
        catch (final IOException | InterruptedException e) {
        }
    } 
     
     
    public final static void header() {
        
        System.out.println(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. ");
        
        
        
        
    }
     
}


    