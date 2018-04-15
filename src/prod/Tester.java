package prod;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ConnectionDB.ConnectionDB;
import ConnectionDB.StudentDB;
import bus.DataCollection;
import bus.Date;
import bus.IPayable;
import bus.Member;
import bus.MemberCategory;
import bus.Professor;
import bus.Sequence;
import bus.Student;
import bus.ValidatorException;
import data.FileHandler;

public class Tester {

	public static void display(){
        System.out.println("\n\n\t-- -- -- Display Table Content -- -- --");
		Object obj = new Student();
        ((Student)obj).getList();
    }

	public static IPayable add(Scanner in){
		try {
			String msgNumberFormatException = "\n\t\t\tNot in format for Float data type !\n";
	
			IPayable mem = null;
			System.out.print("\t\tCategory of member (1. Student, 2. Professor) = ");
			switch(in.nextInt()) {
				case 1:{
					in.nextLine();
					mem = new Student();
					((Student)mem).setCategory(MemberCategory.valueOf("Student"));						
					while (true) {
						try {
							System.out.print("\t\tBasic tax = ");
							((Student)mem).setBasictax(Float.parseFloat(in.nextLine()));
							break;
						}
						catch(ValidatorException e) {
							System.out.println(e.getMessage());
						}
						catch(NumberFormatException e) {
							System.out.println(msgNumberFormatException);
						}
					}
					break;
				}
				case 2:{
					in.nextLine();
					mem = new Professor();
					((Professor)mem).setCategory(MemberCategory.valueOf("Professor"));
					while (true) {
						try {
							System.out.print("\t\tNumber of hours worked per week = ");
							((Professor)mem).setNbrworkweek(Float.parseFloat(in.nextLine()));
							break;
						}
						catch(ValidatorException e) {
							System.out.println(e.getMessage());
						}
						catch(NumberFormatException e) {
							System.out.println(msgNumberFormatException);
						}
					}
					while (true) {
						try {
							System.out.print("\t\tHourly wage = ");
							((Professor)mem).setSalhour(Float.parseFloat(in.nextLine()));
							break;
						}
						catch(ValidatorException e) {
							System.out.println(e.getMessage());
						}
						catch(NumberFormatException e) {
							System.out.println(msgNumberFormatException);
						}
					}
					break;
				}				
			}//end switch category
			((Member)mem).setId();
			while (true) {	
				try{
					System.out.print("\t\tFirst name = ");
					((Member)mem).setFn(in.nextLine());
					break;
				}
				catch(ValidatorException e) {
					System.out.println(e.getMessage());
				}
			}
			
			while (true) {
				try{
					System.out.print("\t\tLast name = ");
					((Member)mem).setLn(in.nextLine());
					break;
				}
				catch(ValidatorException e) {
					System.out.println(e.getMessage());
				}			
			}
			while (true) {	
				try {
					System.out.print("\t\tNAS = ");
					((Member)mem).setNas(in.nextLine());
					break;
				}
				catch(ValidatorException e) {
					System.out.println(e.getMessage());
				}			
			}
			System.out.println("\t\tHire date:");
			Date hd = new Date();
			while (true) {	
				try{
					System.out.print("\t\t\tDay = ");
					hd.setD(Integer.parseInt(in.nextLine()));
					break;
				}
				catch(ValidatorException e) {
					System.out.println(e.getMessage());
				}
				catch(NumberFormatException e) {
					System.out.println(msgNumberFormatException);
				}
			}
			while (true) {	
				try{
					System.out.print("\t\t\tMonth = ");
					hd.setM(Integer.parseInt(in.nextLine()));
					break;
				}
				catch(ValidatorException e) {
					System.out.println(e.getMessage());
				}
				catch(NumberFormatException e) {
					System.out.println(msgNumberFormatException);
				}
			}
			while (true) {	
				try{
					System.out.print("\t\t\tYear = ");
					hd.setY(Integer.parseInt(in.nextLine()));
					break;
				}
				catch(ValidatorException e) {
					System.out.println(e.getMessage());
				}
				catch(NumberFormatException e) {
					System.out.println(msgNumberFormatException);
				}
			}
			
			((Member)mem).setHiredate(hd);
			return mem;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static void ManagingDB(Scanner in, DataCollection<IPayable> myCol) throws SQLException {

		if (ConnectionDB.getConn() == null){
			//LOGIN to DB Oracle XE
			do{
				System.out.println("\n\n\t-- -- -- LOGIN to Oracle XE -- -- --");
				System.out.print("\n\t\tUser Name: ");
				ConnectionDB.setUsername(in.nextLine().trim());
				System.out.print("\t\tPassword: ");
				ConnectionDB.setPassword(in.nextLine().trim());
			}while(!ConnectionDB.SetDBConnection()); //Connect to DB
		}
		
		Integer option = -1;
		do {
			System.out.println("\n\t-*--*---*---- Database management ----*---*--*-");
			System.out.println("\t1. Create the Student table");
			System.out.println("\t2. Adding the list of students");
			System.out.println("\t3. Searching for a Student");
			System.out.println("\t4. Deleting a Student");
			System.out.println("\t5. Updating a Student");			
			System.out.println("\t6. View the Students list");
			System.out.println("\t7. Drop the Student table");			
			System.out.println("\tFor exit - 0");	
			System.out.print("\n\tOption = ");
			try{
				option = in.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("\n\t\tNot an integer !");
			}
			in.nextLine();
								
			Student stud = new Student(); //to accede to the method tableExists
			switch(option) {
				case -1: case 0:
					break;
				case 1:{		
					System.out.println("\n\t-- -- -- Create the Student table -- -- --\n");				
					if (!stud.tableExists("student")){
				        if (StudentDB.createTable() == null){
				        	System.out.println("\n\t\tSuccessfully created table");
				        }
				        else
				        	System.out.println("\n\t\tUnsuccessfully created table");
					}
					else
						System.out.println("\n\t\tThe table is already created !");
					break;
				}//end case 1
				case 2:{
					System.out.println("\n\t-- -- -- Adding a student(s) -- -- --\n");
					if (stud.tableExists("student")){
					    for (IPayable obj : myCol.getMyList()) {
							if (obj instanceof Student){
								if (((Student)obj).add((Student)obj)>0)
									System.out.println("\n\t\tStudent added [ID: " + ((Student)obj).getId() +"] !");
								else
									System.out.println("\n\t\tFailure adding data [ID: " + ((Student)obj).getId() +"] !");
							}
						}	
					}
					else
						System.out.println("\n\t\tTable " + stud.getClass().getSimpleName() + 
								" doesn't exist !\n\t\tStep 1 is required !");
					break;
				}//end case 2
				case 3:{		
					System.out.println("\n\t-- -- -- Searching for a Student by NAS -- -- --\n");				
					if (stud.tableExists("student")){
						IPayable obj = new Student();
				        String msg, nas;
						System.out.print("\n\tEnter NAS for search student: ");				
						nas = in.nextLine();
						Student s = ((Student)obj).search(nas);
				        if (s != null){        	   
				        	msg = "\n\tData found:" + 
				            "\n\t\tFirst Name: " + s.getFn()
				            + "\n\t\tLast Name: " + s.getLn() 
				            + "\n\t\tNAS: " + s.getNas()
				            + "\n\t\tHire date: " + s.getHiredate()
				            + "\n\t\tBasic tax: " + s.getBasictax() 
				            + "\n\t\tSession tax: " + s.getSessiontax() 
				            + "\n\t\tPay: " + s.GetPay();
				        }
				        else
				        	msg = "\n\tData not found"; 	  
				        System.out.println(msg);
					}
					else
						System.out.println("\n\t\tTable " + stud.getClass().getSimpleName() + 
								" doesn't exist !\n\t\tStep 1 is required !");
					break;
				}//end case 3
				case 4:{		
					if (stud.tableExists("student")){
						System.out.println("\n\t-- -- -- Deleting a Student by NAS -- -- --\n");				
						IPayable obj = new Student();
				        String msg;
						System.out.print("\n\tEnter NAS for remove: ");				
				        if (((Student)obj).del(in.nextLine())>0)
				        	msg = "\n\t\tSuccessfully deleting data !";
				        else 
				            msg = "\n\t\tUnsuccessfully deleting data, check NAS !";					  
				        System.out.println(msg);
					}
					else
						System.out.println("\n\t\tTable " + stud.getClass().getSimpleName() + 
								" doesn't exist !\n\t\tStep 1 is required !");
					break;
				}//end case 4
				case 5:{		
					if (stud.tableExists("student")){
						System.out.println("\n\t-- -- -- Updating NAS for student by ID -- -- --\n");				
						IPayable obj = new Student();
				        String msg, nas; int id;
						System.out.print("\n\tEnter ID: ");
						id = in.nextInt();
						in.nextLine();
						System.out.print("\n\tEnter new NAS: ");
						nas = in.nextLine();
						if (((Student)obj).update(id, nas)>0)
				        	msg = "\n\t\tSuccessfully updating data !";
				        else 
				            msg = "\n\t\tUnsuccessfully updating data, check NAS !";					  
				        System.out.println(msg);
					}
					else
						System.out.println("\n\t\tTable " + stud.getClass().getSimpleName() + 
								" doesn't exist !\n\t\tStep 1 is required !");
					break;
				}//end case 5
				case 6:{		
					System.out.println("\n\t-- -- -- View the Students list -- -- --\n");				
					if (stud.tableExists("student"))
						display();
					else
						System.out.println("\n\t\tTable " + stud.getClass().getSimpleName() + 
								" doesn't exist !\n\t\tStep 1 is required !");
					break;
				}//end case 6
				case 7:{		
					System.out.println("\n\t-- -- -- Drop the Students table -- -- --\n");				
					if (stud.tableExists("student")){
						if (stud.dropTable())
						 	System.out.println("\n\t\tSuccessfully table deleted !");
				        else 
				        	System.out.println("\n\t\tUnsuccessfully table deleted !");
					}
					else
						System.out.println("\n\t\tTable " + stud.getClass().getSimpleName() + 
								" doesn't exist !\n\t\tStep 1 is required !");
					break;
				}//end case 7
				default:{
					System.out.println("\n\t\tOut of range !");
					break;
				}//end default

			}//end switch
			stud = null; //clear instance
		}while(option != 0);
 
	}
	
	public static void main(String[] args) throws SQLException, ValidatorException, IOException, ClassNotFoundException {
		
		//Create Data collection
		DataCollection<IPayable> myCol = new DataCollection<IPayable>();

		Sequence.setIdx(1);
		
		try{
			//FOR TESTING
			//Create anonymos instances of Professor
			IPayable ip1 = new Professor("Mimi", "Lili", "123-456-789-012", 
					new Date(2, 3, 2016), MemberCategory.Professor, 35, (float) 11.25);
			IPayable ip2 = new Professor("Dojo", "Mirela", "987-654-321-098", 
					new Date(7, 2, 2015), MemberCategory.Professor, 40, (float) 32.0);
			
			//Create anonymos instances of Student
			IPayable ip3 = new Student("Titi", "Ray", "444-111-222-000", 
					new Date(1, 4, 2017), MemberCategory.Student, 200, 360);
			IPayable ip4 = new Student("Yoyo", "Lyon", "000-222-333-444", 
					new Date(7, 2, 2017), MemberCategory.Student, 420, 530);
			
			myCol.add(ip1); myCol.add(ip2); myCol.add(ip3); myCol.add(ip4);
		
			//Display Data collection for anonymos instances
			//System.out.println("\n-- View Anonymos Instances --\n\n" + myCol);
			
			Scanner in = new Scanner(System.in);
			Integer option = -1;
			do {
				System.out.println("\n- -- --- ---- Member management ---- --- -- -");
				System.out.println("\t1. Adding a new member");
				System.out.println("\t2. Searching member");
				System.out.println("\t3. Modifying an member (transfering Student to Professor)");
				System.out.println("\t4. Removing an member");
				System.out.println("\t5. Sorting the list of member");
				System.out.println("\t6. Display list of member");	
				System.out.println("\t7. Display number of member");				
				System.out.println("\t8. Write to serailizable file");
				System.out.println("\t9. Read from serailizable file");
				System.out.println("\t10. Database management");				
				System.out.println("\tFor exit - 0");	
				System.out.print("\n\tOption = ");		
				try{
					option = in.nextInt();
				}
				catch(InputMismatchException e){
					System.out.println("\n\t\tNot an integer !");
				}
				in.nextLine();
				
				switch(option) {
					case -1: case 0:
						break;
					case 1:{		
						System.out.println("\n\t-- -- -- Adding a new Member -- -- --\n");				
						IPayable tmp = add(in);
						if (tmp != null){
							myCol.add(tmp);
							System.out.println("\n\tSuccessful Adding !");
						}
						else
							System.out.println("\n\tUnsuccessful Adding !");
						break;
					}//end case 1
					case 2:{		
						System.out.println("\n\t-- -- -- Searching Member by NAS -- -- --\n");				
						IPayable obj = null;
						System.out.print("\n\tEnter NAS for search: ");
						obj = myCol.SearchByNAS(in.nextLine());
						System.out.println("\n\t- -- --- Member found --- -- -\n\n\t" + 
								(obj == null ? "\n\t\tNo data found !" : obj) );						
						break;
					}//end case 2
					case 3:{
						System.out.println("\n\t- -- --- Transfering Student to Professor - -- ---");
						System.out.print("\n\tEnter NAS for search and update the category: ");
						Integer position = myCol.GetIndexByNAS(in.nextLine());
						if (position != -1){
							Object obj = myCol.GetObjectByIndex(position);
							IPayable ip = new Professor();
							((Professor)ip).setId(((Student)obj).getId());
							((Professor)ip).setFn(((Student)obj).getFn());
							((Professor)ip).setLn(((Student)obj).getLn());
							((Professor)ip).setNas(((Student)obj).getNas());
							((Professor)ip).setHiredate(((Student)obj).getHiredate());							
							((Professor)ip).setCategory(MemberCategory.Professor);
							System.out.print("\n\tEnter hourly wage: ");
							((Professor)ip).setSalhour(in.nextFloat());
							System.out.print("\n\tEnter the hour number: ");
							((Professor)ip).setNbrworkweek(in.nextFloat());
							myCol.UpdateByNAS(position, ip);
							System.out.println("\n\t--> Successfully updated ! <--");
						}
						else
							System.out.println("\n\t--> Unsuccessfully updated. Verify NAS ! <--");						
						break;
					}//end case 3
					case 4:{
						System.out.println("\n\t- -- --- Removing an Member (by NAS) - -- ---");
						System.out.print("\n\tEnter NAS for search: ");
						if (myCol.Remove(in.nextLine()))
							System.out.println("\n\t--> Successfully deleted ! <--");
						else {
							System.out.println("\n\t--> Unsuccessfully deleted. Verify NAS ! <--");						
							}
						break;
					}//end case 4
					case 5:{
						System.out.println("\n\t- -- --- Sorting the list of member by NAS - -- ---");
						myCol.SortByNas();
						System.out.println(myCol);
						break;
					}//end case 5
					case 6: {
						System.out.println(myCol);
						break;
					}//end case 6
					case 7: {
						System.out.println("\n\t- -- --- Total nomber of employees - -- ---");
						System.out.println("\t\t" + myCol.GetNumberOfList() + " members");
						break;
					}//end case 7
					case 8: {
						System.out.println("\n\t- -- --- Write to BIN file - -- ---");
						if (FileHandler.WriteToBinFile(myCol.GetList()))
							System.out.println("\n\t--> Successful Writing ! <--");
						else
							System.out.println("\n\t--> Unsuccessful Writing ! <--");
						break;
					}//end case 8
					case 9: {
						System.out.println("\n\t- -- --- Read from BIN file - -- ---");
						myCol.erase();
						int size = myCol.getSize();
						for (Object obj : FileHandler.ReadFromBinFile()) {
							myCol.add((IPayable)obj);
						}
						if (size != myCol.getSize()){
							System.out.println("\n\tSuccessful Reading !\n\n");
							System.out.println(myCol);
						}
						else
							System.out.println("\n\tUnsuccessful Reading !");					
						break;
					}//end case 9
					case 10: {
						System.out.println("\n\t- -- --- Database management - -- ---");
						ManagingDB(in, myCol);
						break;
					}//end case 10
					default:{
						System.out.println("\n\t\tOut of range !");
						break;
					}//end default

				}//end switch
			}while(option != 0);
		}
		catch(ValidatorException e){
			System.out.println(e.getMessage());
		}

		//LogOUT from Oracle XE
		if (ConnectionDB.getConn() != null)
			ConnectionDB.logOut();
	}
}