package ConnectionDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bus.Student;

public class StudentDB {

	public static Exception createTable()
	{
		try{
			Connection conn = ConnectionDB.getConn();
			Statement s = null;
			String sqlCreateTable = "create table student (id number(4), firstname varchar2(20), lastname varchar2(20),"
					+ "nas varchar2(17), hiredate date default sysdate not null,"
					+ "basictax number(13,2), sessiontax number(13,2), pay number(13,2))" ;
			String sqlAlterTable = "alter table student add constraint pk_student_id primary key(id)";

			s = conn.createStatement();
			try{
				dropTable();
			}
			catch(Exception ex){}
			s.execute(sqlCreateTable);
			s.execute(sqlAlterTable);

			conn.commit();
			s.close();
			return null;		
		} 
		catch (Exception ex){
			return ex;
		}
	}
	
	public static boolean dropTable()
	{
		try{
			Connection conn = ConnectionDB.getConn();
			Statement s = null;
			String sqlDropTable = "drop table student cascade constraints purge";
			s = conn.createStatement();

			s.execute(sqlDropTable);
			conn.commit();
			s.close();
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
	
	public static int DML(String request){
		int succes = -1;
		try {
			Connection conn = ConnectionDB.getConn();
			Statement s = conn.createStatement();
			String requete = request;	
			succes = s.executeUpdate(requete);	
			conn.commit();
		}
		catch (Exception ex) {}
		return succes;		
	}
	
    public static int add(Student obj){
		return StudentDB.DML("insert into student values (" +
			obj.getId()+  "," + "\'" + obj.getFn() + "\'," + "\'" + obj.getLn() + "\'," + 
					"\'" + obj.getNas() + "\',to_date(\'" + obj.getHiredate().getD() + "-" +  
					obj.getHiredate().getM() + "-" + obj.getHiredate().getY() + "\',\'dd-mm-yyyy\')," +
					obj.getBasictax() + "," + 
					obj.getSessiontax() + "," + 
					obj.GetPay() +")");
    }	

    public static int del(String key){ 								
 		return StudentDB.DML("delete from student where nas = \'" + key.trim() + "\'");	
 	}	

    public static int updateNas(int key, String value){
 		return StudentDB.DML("update student set nas = \'" + value + "\' where id=" + key);	
 	}

    public static boolean tableExists(String tableName)
    {
    	try {
    	  	Connection conn = ConnectionDB.getConn();
    	  	DatabaseMetaData dmd = conn.getMetaData();	
			ResultSet rs = dmd.getTables(null, null, tableName.trim().toUpperCase(), null);
			 if (rs.next())
				 return true; 
		}
		catch(SQLException e){}
    	return false;
   	}
    
    public static void getList()
    {
		try {
		  	 Connection myConnection = ConnectionDB.getConn();
		     String query = "select * from student";
		     Statement command;
		 	 ResultSet resSet;		 	    
		 	 command = myConnection.createStatement();
		 	 resSet  =  command.executeQuery(query);
					
		 	 while(resSet.next()){
	              System.out.println("\n\tID: " + resSet.getString(1) + "\n\tFirst Name: "+ resSet.getString(2) + 
	            		  "\n\tLast Name: " + resSet.getString(3) + "\n\tNAS: " + resSet.getString(4)
	            		  + "\n\tHire date: " + resSet.getString(5) + "\n\tBasic tax: " +
	            		  resSet.getString(6) + "\n\tSession tax: " + resSet.getString(7) 
	            		  +  "\n\tPayment: " + resSet.getString(8));
		 	 }	
		}
		catch(SQLException e){
		 	 System.out.println(e.toString());
		}	
   	} 

	//Search
	public static Student search (String key){
		  try {
			  
			  Connection myConnection = ConnectionDB.getConn();
			  Statement myStatement = myConnection.createStatement();
			
			  String requete = "select * from student where nas = " + "\'" + key.trim() + "\'";
		
			  ResultSet myResultSet = myStatement.executeQuery(requete);
			  
			  if(myResultSet.next()){
				  Student obj= new Student(myResultSet.getString("firstname"), myResultSet.getString("lastname"),
						  myResultSet.getString("nas"), null, null, 
						  myResultSet.getFloat("basictax"), myResultSet.getFloat("sessiontax"));  
				  return obj;		
			  }	
			  else{  
				  return null;
			  }
		  }
		  catch (Exception e) {
	           System.out.println(e.toString());
	           return null;
		  }
	  }
}