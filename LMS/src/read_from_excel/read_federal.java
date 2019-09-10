package read_from_excel;



import java.io.FileInputStream; 
import java.util.Iterator;
import java.util.Vector;

 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
 


public class read_federal { 

public static void main( String [] args ) {

	String fileName="C:\\Users\\MIRITPC\\Desktop\\New folder (2)\\all\\federal\\2017\\may\\FED MAY 17 THEIR .xls"; 
	//Read an Excel File and Store in a Vector
    Vector dataHolder=readExcelFile(fileName);
    //Print the data read
    printCellDataToConsole(dataHolder);
}
public static Vector readExcelFile(String fileName)
{
    /** --Define a Vector
        --Holds Vectors Of Cells
     */
    Vector cellVectorHolder = new Vector();

    try{
    /** Creating Input Stream**/
    //InputStream myInput= ReadExcelFile.class.getResourceAsStream( fileName );
    FileInputStream myInput = new FileInputStream(fileName);

    /** Create a POIFSFileSystem object**/
    POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

    /** Create a workbook using the File System**/
     HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

     /** Get the first sheet from workbook**/
    HSSFSheet mySheet = myWorkBook.getSheetAt(0);

    /** We now need something to iterate through the cells.**/
      Iterator rowIter = mySheet.rowIterator();

      while(rowIter.hasNext()){
          HSSFRow myRow = (HSSFRow) rowIter.next();
          Iterator cellIter = myRow.cellIterator();
          Vector cellStoreVector=new Vector();
          while(cellIter.hasNext()){
              HSSFCell myCell = (HSSFCell) cellIter.next();
              cellStoreVector.addElement(myCell);
          }
          cellVectorHolder.addElement(cellStoreVector);
      }
    }catch (Exception e){e.printStackTrace(); }
    return cellVectorHolder;
}

private static void printCellDataToConsole(Vector dataHolder) {

	String refno="";
	String amount="";
	String date="";
	  Insert_Into_Table obj_Insert_Into_Table=new Insert_Into_Table();
	
	try {  
    for (int i=0;i<8000; i++){
    	//System.out.println(i+"--");
    	//Thread.sleep(1000);
     	
    	 refno="";
    	 amount="";
    	 date="";
    	 String file_name="federal";
    		
    	 String array[];
    	
               Vector cellStoreVector=(Vector)dataHolder.elementAt(i);
               
               
               
               //query="insert into book_table(book_title,category_name,author_name,publisher_name,isbn,book_sl_no) values (?,?,?,?,?,?)";    
                    
        for (int j=0; j < cellStoreVector.size();j++){
            HSSFCell myCell = (HSSFCell)cellStoreVector.elementAt(j);
            String stringCellValue = myCell.toString();
            System.out.print(j+" "+stringCellValue+"\t");
            
            array=stringCellValue.trim().replaceAll(" +", " ").split(" ");
           // System.out.println(array);
           /* System.out.println(array[0]+" 0");
            System.out.println(array[1]+" 1");
            System.out.println(array[2]+" 2");
            System.out.println(array[3]+" 3");
            System.out.println();
            
          */  	date=array[0];
             
           
            	refno=array[1].replaceAll("FF/", "");
            	 
           
            	amount=array[3].replaceAll(",", "");
           
            
            
        }
        if(date!=null){
        	if(!date.equals("")){
        	//	 obj_Insert_Into_Table.insert_into_table2(file_name, date, refno, amount);
            }
        }
      
       
       
    }
    
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}