package read_from_excel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.DBConnection_LMS_Portal;
 

public class Insert_Into_Table {
				
				
	public void insert_into_book(String file_name,String date,String refno,String amount){
		  
		   PreparedStatement ps=null;
		Connection connection=null;
		DBConnection_LMS_Portal obj_DBConnection_FLY_HIND=new DBConnection_LMS_Portal();
		connection=obj_DBConnection_FLY_HIND.getConnection();
		 
	try { 
		String query="INSERT INTO lms (" +
				"'book_sl_no'," +
				" 'book_title'," +
				" 'category_name'," +
				" 'category_num'," +
				" 'author_name'," +
				" 'author_num'," +
				" 'publisher_name'," +
				" 'publisher_num'," +
				" 'edition'," +
				" 'volume'," +
				" 'ean_code'," +
				" 'isbn'," +
				" 'price'," +
				" 'purchase_date'," +
				" 'language'," +
				" 'total_book_in_library', " +
				"'total_book_available'," +
				" 'description', " +
				"'added_on', " +
				"'edited_on'," +
				" 'added_by', " +
				"'edited_by'" +
				") VALUES ();";
		
		ps = connection.prepareStatement(query);
		
		ps.setString(1, file_name);
		ps.setString(2, date);
		ps.setString(3, refno);
		ps.setString(4, amount);
		System.out.println(ps);
			  ps.executeUpdate();
		
		 
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(connection!=null){
			try {
					connection.close();
				}
			 catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(ps!=null){
			try {
				ps.close();
				}
			 catch (Exception e2) {
				 e2.printStackTrace();
			}
		}
		
	}
	 
	}	

	public void insert_into_table2(String file_name,String date,String refno,String amount){
		  
		   PreparedStatement ps=null;
		Connection connection=null;
		DBConnection_HR obj_DBConnection_FLY_HIND=new DBConnection_HR();
		connection=obj_DBConnection_FLY_HIND.getConnection();
		 ResultSet rs=null;
		 final String OLD_FORMAT = "dd-MMM-yyyy";
		 final String NEW_FORMAT = "dd-MM-yyyy";
		 
	try { 
		String query="insert into table2(file_name,date2,ref2,amount2) values (?,?,?,?)";
		
		ps = connection.prepareStatement(query);
		
		
		
		
		
		
		
		
		ps.setString(1, file_name);
		
		try {
			
			String oldDateString = date;
			String newDateString;

			SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
			Date d = sdf.parse(oldDateString);
			sdf.applyPattern(NEW_FORMAT);
			newDateString = sdf.format(d);
			
			
			ps.setString(2, newDateString);
		} catch (Exception e) {
			ps.setString(2, date);
			
		}
		
		 
		ps.setString(3, refno);
		
		if(amount.contains(".00")){
			ps.setString(4, amount.replaceAll(",", ""));
			
		}else{
			ps.setString(4, amount.substring(0, amount.length()-2)+".00");
			
		}
		
		 		
		 
		System.out.println(ps);
			  ps.executeUpdate();
		
		 
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(connection!=null){
			try {
					connection.close();
				}
			 catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(ps!=null){
			try {
				ps.close();
				}
			 catch (Exception e2) {
				 e2.printStackTrace();
			}
		}
		
	}
	 
	}	

	
				
				
				
}

				
				
				

