package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


public class ExcelUtil {
	
	public static String ReadCellData(String filePath,int vRow, int vColumn) {
	String value=null;          //variable for storing the cell value  
	Workbook wb=null;           //initialize Workbook null  
	try{  
		//reading data from a file in the form of bytes  
		FileInputStream fis=new FileInputStream(("user.dir")+"/src/test/java/resources/file_example_XLSX_10.xlsx");  
		//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
		wb=new XSSFWorkbook(fis);  
	}  
	catch(FileNotFoundException e){  
		e.printStackTrace();  
	}  
	catch(IOException e1){  
		e1.printStackTrace();  
	} 
	
	Sheet sheet=wb.getSheetAt(0);   //getting the XSSFSheet object at given index  
	Row row=sheet.getRow(vRow); //returns the logical row  
	Cell cell=row.getCell(vColumn); //getting the cell representing the given column  
	value=cell.getStringCellValue();    //getting cell value  
	return value;               //returns the cell value  
}  
	
	public static List<HashMap<String, String>> loadDataIntoMap(String filePath, String methodName) { // for multiple Excel sheets
		Workbook wb=null;           //initialize Workbook null  
		try{  
			//reading data from a file in the form of bytes  
			FileInputStream fis=new FileInputStream(filePath);  
			//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
			wb=new XSSFWorkbook(fis);  
		}  
		catch(FileNotFoundException e){  
			e.printStackTrace();  
		}  
		catch(IOException e1){  
			e1.printStackTrace();  
		} 
		
		List<HashMap<String,String>> dataMap = new ArrayList<HashMap<String,String>>();		
		
		List<String> headerList = new ArrayList<String>();
		
		Sheet sheet=wb.getSheet(methodName);
		
		Row headerRow=sheet.getRow(0);
		
		for(Cell mycell : headerRow) {
			headerList.add(mycell.getStringCellValue());
		}
		
		for (Row myrow : sheet) {
			HashMap<String,String> dataRow = new HashMap<String,String>();
			if(myrow.getRowNum()>0) {
			    for (int i=0;i<myrow.getPhysicalNumberOfCells();i++) {
			    	dataRow.put(headerList.get(i), myrow.getCell(i).getStringCellValue());			        
			    }
			    dataMap.add(dataRow);
			}
		    
		}
		
		return dataMap;
	}
	
	public static List<HashMap <String, String>> loadDataIntoMap(String filePath) { //for only one Excel sheet 
		return loadDataIntoMap(filePath, null);
}}
