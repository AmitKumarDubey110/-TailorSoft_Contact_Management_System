package JAVA_Contact_Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/*
---------------------------------------------------------------------------------------------------------------------
This project hold a detail concept and working of a real Contact Book.
Important feature of contact book like (Add, delete, update, Find and read) are added in this project.
The most important part is that, this book stores actual data on hard drive that can be used for future.
Using File handling techniques this book is now a powerful and considered to be an actual storage of contact details
Furthermore, once a user is registered he has the authority to work on his Contact Book.
---------------------------------------------------------------------------------------------------------------------
 */

public class Contact_Management_System {

	public static void main(String[] args)throws IOException {
		 Scanner input = new Scanner(System.in);
	     char againYES_NO = 'y';
	        do{
	            System.out.println("\n\n*********** [Welcome To Contact Management System] ***********\n");
	          
	                 ContactBook.contact_Main();
	            
	            System.out.println("\nSwitch off the Contact Management System Press [x] or Continue Press [y]: ");
	            againYES_NO = input.next().toLowerCase().charAt(0);// yes
	        }while (againYES_NO == 'y');
	        System.out.println("Signing OFF.....");

	}

}

/*
----------------------------------------------------------------------------------------------------------
This class is the called contact book because it performs the feature that are necessary for contact book.
Like add new contact ,delete the existing contact from the user contact list,update the contact and 
find the contact as per the user requirement
----------------------------------------------------------------------------------------------------------
*/


class ContactBook {
 public static void contact_Main() throws IOException {
     Scanner input = new Scanner(System.in);
     String[] credentials = {"LOG_IN", "SIGN_IN"};
     boolean loop = false;
     char userChoice;
     do {
         System.out.println("\n*********** [ Contact - Book ] ***********");
         for (int i = 0; i < credentials.length; i++)
             System.out.printf("\n[%d] %s ", (i + 1), credentials[i]);
         System.out.print("\nSelect Correct Option (0 to exit): ");
         userChoice = input.next().charAt(0);

         switch (userChoice) {
             case '1' : loop = loginPage();break;
             case '2' : loop = signUpPage();break;
             case '0' : return;
             default: loop = true;
         }
     } while (loop);
 }
 
 
 /*
 ------------------------------------------------------------------------------------------
 After a registered user passed out from the login panel, he will access this method for
 further access to feature of contact book.
 The features includes (add, delete, read, search and update)
 -------------------------------------------------------------------------------------------
 */
 
 
 public static void successfulLogin(String filename)throws IOException{
     Scanner input = new Scanner(System.in);
     String[] infoDetails = {"Contact_Name: ","Roll NO: ","Address: "};
     String[] list = {"Add ","Read ","Find ","Delete Contact / Update Data"};
     String choice;
     String data = "*********** [Contact-Book] ***********";
     char continueOrExit = 'y';
     do {
         for (int i = 0; i < list.length; i++)
             System.out.printf("[%d] %s \n",(i+1),list[i]);
         System.out.print("\nEnter Your Choice: ");
         choice = input.next();
         try{
             if (!(Integer.parseInt(choice) > 0  & Integer.parseInt(choice)<=4)){
                 System.out.println("\nPlease select Correct Option");
                 continue;
             }
         }
         catch (Exception e) {
             System.out.println("\nPlease select Integer Option");
             continue;
         }
         switch (choice){
             case "1": {
                 data +=  add(infoDetails);
                 saveData(data, filename);
             }break;
             case "2": readData(filename);break;
             case "3": findEntity(filename);break;
             case "4": updateContact_contact( filename,infoDetails);break;
         }
         System.out.println("\nFor exit Press [x] \n To continue Press [y] : ");
         continueOrExit = input.next().charAt(0);
     }while (continueOrExit == 'y');
 }
 
 
 /*
 ------------------------------------------------------------- 
 Method for adding data to contact book
 File writer object is used for data appending and inserting
 -------------------------------------------------------------
 */
 
 
 public static String add(String[] info){
     String savedData = "\n";
     char choice;
     System.out.println("\nEnter contact Details...");
     do {
         Scanner input = new Scanner(System.in);
         for (int i = 0; i < info.length; i++) {
             input.useDelimiter("\\r");
             System.out.print("\n" + info[i]);
             String data = input.nextLine();
             input.reset();
             savedData += info[i] + data +"\n";
         }
         savedData += "\n---------------\n";
         System.out.println("\n To save another contact Press (y) or To exit Press (n)");
         choice = input.next().toLowerCase().charAt(0);
     }while(choice == 'y');
     return savedData;
 }
 
 
 /* 
 --------------------------------------------------------------
 Method for Storing data to contact book
 File writer object is used for data appending and inserting
 --------------------------------------------------------------
 */
 
 
 public static void saveData(String data , String filename) throws IOException{
     File contact_book_text_file = new File(filename);
     if(!(contact_book_text_file.exists())) {
         try {
             contact_book_text_file.createNewFile();
         }
         catch (IOException e) {
             e.printStackTrace();
         }
     }
     FileWriter fileWriter = new FileWriter(contact_book_text_file,true);
     fileWriter.write(data);
     fileWriter.close();
 }
 
 
 /*
 ---------------------------------------------------------------
 Method for reading data to contact book
 Scanner object is used for data reading
 The whole contact book is read through hasNext() iterator
 And Sequences are used to counter the contact being found
 ---------------------------------------------------------------
  */
 
 
 public static void readData(String filename)throws IOException{
     File myFile = new File(filename);
     Scanner scan = new Scanner(myFile);
     try {
         scan.nextLine();
     }
     catch (Exception e){
         System.out.println("\nContact_Book exits but it is Empty");
         return;
     }
     Scanner scanner = new Scanner(myFile);
     System.out.println();
     while (scanner.hasNextLine())
         System.out.println(scanner.nextLine());
     scan.close();
 }
 
 
 /* 
 --------------------------------------------------------------- 
 Method for finding data to contact book
 Scanner object is used for data reading from the contact book
 The whole contact book is read through hasNext() iterator
 And Sequences are used to counter the contact being found
 ---------------------------------------------------------------
  */
 
 
 public static void findEntity(String filename)throws IOException{
     File myFile = new File(filename);
     Scanner scan = new Scanner(myFile);
     try {
         scan.nextLine();
     }
     catch (Exception e){
         System.out.println("\nContact_Book exits but it is Empty");//0510111446699
         return;
     }
     System.out.print("\nEnter Name to Find: ");
     Scanner input = new Scanner(System.in);
     String name = input.nextLine().toLowerCase(Locale.ROOT);
     while (scan.hasNextLine()){
         String line = scan.nextLine();
         if (line.startsWith("Contact_Name:") && line.toLowerCase().endsWith(name.toLowerCase())){
             System.out.println("\nEntity found...\n"+line);
             try{
                 System.out.println(scan.nextLine());
                 System.out.println(scan.nextLine());
             }catch (Exception e){
                 System.out.println("\nEnd Of Contact Book Reached");
             }
             scan.close();
             return;
         }
     }
     System.out.println("\nContact not found.");
 }
 
 
 /*
 -----------------------------------------------------------------------------------------------------
 This is the login page whenever a user wants to access his/her contact book,
 he/she must go through this page and insert his/her credentials
 If the credentials matched from the registration file then he will have access to his Contact book.
 -----------------------------------------------------------------------------------------------------
  */
 
 
 public static boolean loginPage()throws IOException{
     Scanner input = new Scanner(System.in);
     System.out.println("\n\t\t*********** [Welcome to LOG_IN Page] ***********\n");
     System.out.print("\nEnter your User_Name: ");
     String username = input.nextLine();
     String filename = "./"+username+".txt";
     File userFile = new File(filename);
     File registerationVerification = new File("./Registered_Area.txt");
     if (!(registerationVerification.exists())){
         System.out.println("\nNot registered.\nTry other User_Name or Sign_UP");
         return true;//break
     }
     else if  (!(userFile.exists())){
         System.out.println("\nLogin Failed \n No Such Id found");
         return true;
     }
     else {
         Scanner userScan = new Scanner(registerationVerification);
         System.out.print("Enter your Password: ");
         String passcode = input.nextLine();
         while (userScan.hasNextLine())
             if (userScan.nextLine().toLowerCase(Locale.ROOT).endsWith(username.toLowerCase(Locale.ROOT))){
                 String s = userScan.nextLine();
                 if (passcode.equals(s.substring(s.indexOf(" ")+1))){
                     System.out.println("\n\nPassword matched.... logging in...\n\n Now you can perform the following functions:");
                     successfulLogin(filename);
                     return false;//return
                 }
             }
     }
     System.out.println("\nWrong Password");
     return true;
 }
 
 
 /* 
 --------------------------------------------------------------------------------------------------------------------------- 
 A new user can access any contact book, unless he signs up
 Sign up page fetched the new user's credentials and these credentials are secured in a file called Registration text file
 When the user Sign's up, a contact book will be generated according to his name
 Now he/she can access his contact book
 ---------------------------------------------------------------------------------------------------------------------------
 */
 
 
 public static boolean signUpPage()throws IOException{
     Scanner input = new Scanner(System.in);
     System.out.println("\n\t\t*********** [Welcome to SIGN_UP Page] ***********\n");
     boolean OK  = true;
     do{
         System.out.print("\nEnter your User_Name: ");
         String username = input.nextLine();
         File f = new File("./"+username+".txt");
         if (f.exists())
             System.out.println("\nUser already Exits \n Try new username");
         else {
             System.out.print("Create your Password: ");
             String password = input.nextLine();
             f.createNewFile();
             File registered = new File("./Registered_Area.txt");
             if (!(registered.exists()))
                 registered.createNewFile();
             FileWriter fileWriter = new FileWriter("./Registered_Area.txt",true);
             fileWriter.write("\nUsername: "+username+"\n");
             fileWriter.write("Password: "+password+"\n");
             fileWriter.close();
             System.out.println("\nUser registered successfully \n\n Now you can Log_In to your Contact Book\n");
             OK = false;
         }
     }while (OK);
     return true;
 }
 
 
 /*
  ---------------------------------------------------------------------------------------------------
  This method hold the feature of deleting or updating.
  According to user choice this method redirect user to the specific (deletion or updating ) method
  ---------------------------------------------------------------------------------------------------
  */
 
 
 public static void updateContact_contact(String filename, String[] info)throws IOException {
     Scanner input = new Scanner(System.in);
     File file = new File(filename);
     Scanner scan = new Scanner(file);
     char choice;
     boolean found = false;
     System.out.println("\nEnter Contact Name to Update...");
     String contactName = input.nextLine();
     while (scan.hasNextLine()) {
         if (scan.nextLine().toLowerCase().endsWith(contactName.toLowerCase())) {
             System.out.println("\nContact Found");
             found = true;
             break;
         }
     }
     if (found) {
         String[] deleteUpdate = {"Delete", "Rename or Update"};
         boolean correct = true;
         do {
             for (int i = 0; i < deleteUpdate.length; i++)
                 System.out.printf("\n[%d] %s", (i + 1), deleteUpdate[i]);
             System.out.print("\nSelect choice: ");
             choice = input.next().charAt(0);
             switch (choice) {
                 case '1': delete(filename,contactName,info);correct = false;break;
                 case '2': updateExistingData(filename,contactName,info);correct = false;break;
                 default: System.out.println("Wrong selection:");continue;
             }
         } while (correct);
     } else {
         System.out.println("\nContact not found \n Try correct Contact Name");
         return;
     }
 }
 
 
 /*
 ------------------------------------------------------------------------------------------------------------------ 
 Delete method requires the contact book name and the entity name for deletion purposes
 Whenever the entity is encountered in contact book. Then he/she can deleted using special technique here below
 ------------------------------------------------------------------------------------------------------------------
 */
 
 
 public static void delete(String filename,String contactName,String[] info)throws IOException{
     File oldFile = new File(filename);
     Scanner scan = new Scanner(oldFile);
     String All_data = "";
     while (scan.hasNextLine()){
         String n = scan.nextLine();
         if (n.toLowerCase(Locale.ROOT).endsWith(contactName.toLowerCase(Locale.ROOT))){
             scan.nextLine();
             scan.nextLine();
         }
         else
             All_data += (n+"\n");
     }
     scan.close();
     boolean x = oldFile.delete();
     if(!x)
         System.out.println("\nContact Deleted Successfully");
     File newFile = new File(filename);
     FileWriter fileWriter = new FileWriter(newFile);
     fileWriter.write(All_data);
     fileWriter.close();
 }
 
 /*
 ----------------------------------------------------------------------------------------------------------------------
 Delete method requires the contact book name and the entity name for updating purposes
 Whenever the entity is encountered in contact book. Then he/she can update data using special technique here below
 ----------------------------------------------------------------------------------------------------------------------
 */
 
 
 public static void updateExistingData(String filename,String contactName,String[] info)throws IOException{
     File oldFile = new File(filename);
     Scanner scan = new Scanner(oldFile);
     Scanner input = new Scanner(System.in);
     String All_data = "";
     while (scan.hasNextLine()){
         String n = scan.nextLine();
         if (n.toLowerCase(Locale.ROOT).endsWith(contactName.toLowerCase(Locale.ROOT))){
             scan.nextLine();
             scan.nextLine();
             System.out.println("\nEnter the data to be get Updated: ");
             for (int i = 0; i < info.length; i++) {
                 input.useDelimiter("\\r");
                 System.out.print("\n" + info[i]);
                 String data = input.nextLine();
                 All_data += info[i] + data +"\n";
                 input.reset();
             }
         }
         else
             All_data += (n+"\n");
     }
     scan.close();
     boolean x = oldFile.delete();
     if(!x)
         System.out.println("\nContact details Updated Successfully");
     File newFile = new File(filename);
     FileWriter fileWriter = new FileWriter(newFile);
     fileWriter.write(All_data);
     fileWriter.close();
 }
}
