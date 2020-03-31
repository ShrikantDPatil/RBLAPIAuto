package com.uat.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.xml.stream.utils.NestedThrowable;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.uat.base.TestBase;
import com.uat.client.Xls_Reader;

public class Checkexcep {

	Xls_Reader reader;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("HAI");

		
		
		Checkexcep a =new Checkexcep();
			System.out.println(a.getDataFromExcels("hai"));


		 
	}
			 ArrayList<Object[]> getDataFromExcels(String xlsxfilepath) {
				

				ArrayList<Object[]> FT_Sheet =  new ArrayList<Object[]>();
				
				
				try {

					reader = new Xls_Reader("C:\\Users\\11825\\Documents\\Empower_Workspace\\RestAPISP\\src\\main\\java\\com\\uat\\data\\geneXl.xlsx");
					
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
				System.out.println("column count :"+reader.getColumnCount("Sheet1"));
				
				for(int colnum = 0; colnum < reader.getColumnCount("Sheet1");colnum++ )
				{
					System.out.println("header name :"+reader.getCellData("Sheet1",colnum , 1));
				}
				
				
				
				 List<Map<String,String>> lom = new ArrayList<Map<String,String>>();
				 Map<String,String> map1 = new HashMap<String,String>();
				 System.out.println("row count :"+reader.getRowCount("Sheet1"));
				 for(int rowNum = 2; rowNum <= reader.getRowCount("Sheet1"); rowNum++  )
				
				 {
					 
					 for(int colnum = 0; colnum < reader.getColumnCount("Sheet1");colnum++ )
						{
						 map1.put(reader.getCellData("Sheet1",colnum , 1), reader.getCellData("Sheet1",reader.getCellData("Sheet1",colnum , 1), rowNum));
						}					 
					 
					 lom.add(map1);
				 }
				 
				 System.out.println("loaded map size "+lom.size() ); 
				 
				 for(Map<String,String> map:lom){
			        	FT_Sheet.add(new Object[]{map});
			        }
				 System.out.println("loaded test cases are "+FT_Sheet.size() ); 
				return FT_Sheet;
			 }
		 
		

		
}