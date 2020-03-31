package com.uat.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.xml.stream.utils.NestedThrowable;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.uat.base.TestBase;
import com.uat.client.Xls_Reader;

public class XlsdataExtract 
{
	static Xls_Reader reader;
	

 
 public static  ArrayList<Object[]> getDataFromExcels(String xlsxfilepath) {
		

		ArrayList<Object[]> FT_Sheet =  new ArrayList<Object[]>();
		
		
		try {
			reader = new Xls_Reader(xlsxfilepath);

			//reader = new Xls_Reader("C:\\Users\\11825\\Documents\\Empower_Workspace\\RestAPISP\\src\\main\\resources\\TestData\\Test.xlsx");
			
		} catch (Exception e) {
			if (e.toString().contains(xlsxfilepath))
					{		
						System.out.println("please check properties file for xlsx file path");
					}
			else
					{  
				
						System.out.println(e);
					}
		}
		
		
		
		 List<Map<String,String>> lom = new ArrayList<Map<String,String>>();
		// System.out.println("row count"+reader.getRowCount("Sheet1"));
		 for(int rowNum = 2; rowNum <= reader.getRowCount("Sheet1"); rowNum++  )
		
		 {
		
			 Map<String,String> map1 = new HashMap<String,String>();
			 for(int colnum = 0; colnum < reader.getColumnCount("Sheet1");colnum++ )
				{
				// System.out.println(reader.getCellData("Sheet1",colnum , 1));
				// System.out.println(reader.getCellData("Sheet1",reader.getCellData("Sheet1",colnum , 1), rowNum));

				 map1.put(reader.getCellData("Sheet1",colnum , 1), reader.getCellData("Sheet1",reader.getCellData("Sheet1",colnum , 1), rowNum));
				 if (reader.getCellData("Sheet1",colnum , 1).equalsIgnoreCase("Expectedresult"))
				 {
					 colnum = reader.getColumnCount("Sheet1");
				 }
				}					 
			 
			 lom.add(map1);
			
			 
		 }
		 
		 for(Map<String,String> map:lom){
	        	FT_Sheet.add(new Object[]{map});
	        }
		 System.out.println("loaded test cases are  "+FT_Sheet.size() ); 
		 
		return FT_Sheet;
	 }
 
}
