package com.uat.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class TESTreade {
	static Xls_Reader reader;
	public static void main(String[] args) throws IOException, InvalidFormatException {
		Map<Integer, String> headers= new LinkedHashMap<Integer, String>();
		
		//String roots = TESTreade.getrootrequest("C:\\Users\\11825\\Documents\\Empower_Workspace\\RestAPISP\\src\\main\\java\\com\\uat\\data\\geneXl.xlsx");

		//headers = TESTreade.getHeaders("C:\\Users\\11825\\Documents\\Empower_Workspace\\RestAPISP\\src\\main\\java\\com\\uat\\data\\geneXl.xlsx");
		//System.out.println(TESTreade.getheaderelements("C:\\Users\\11825\\Documents\\Empower_Workspace\\RestAPISP\\src\\main\\java\\com\\uat\\data\\geneXl.xlsx","Body" ,"End"));
		//System.out.println(headers);
		
		//System.out.println(getrootrequest ("C:\\Users\\11825\\Documents\\Empower_Workspace\\RestAPISP\\src\\main\\java\\com\\uat\\data\\geneXl.xlsx"));
		ArrayList<Object[]> FT_Sheet =  new ArrayList<Object[]>();
		FT_Sheet = XlsdataExtract.getDataFromExcels("C:\\Users\\11825\\Documents\\Empower_Workspace\\RestAPISP\\src\\main\\java\\com\\uat\\data\\SPFT.xlsx");
		System.out.println(FT_Sheet.get(0));
	}
	
	public static String getrootrequest (String xlsxfilepath)
	{
		
		
		reader = new Xls_Reader(xlsxfilepath);
		
		return reader.getCellData("Sheet1", 0, 1);
		
	}
	
	public static LinkedHashMap<Integer, String> getHeaders(String xlsxfilepath)
	{
		reader = new Xls_Reader(xlsxfilepath);
		LinkedHashMap<Integer, String> headers= new LinkedHashMap<Integer, String>();
		int k =0;
		System.out.println(reader.getColumnCount("Sheet1"));
		for(int i = 0 ;i<= reader.getColumnCount("Sheet1");i++)
		{
			if(reader.getCellData("Sheet1", i, 1).isEmpty())
			{
				
			}
			else
			{  
				headers.put(k, reader.getCellData("Sheet1", i, 1));
				k = k+1;
			}
		}
		
		return headers;
		
	}
	public static int getSearchposition(String xlsxfilepath,String param) throws IOException, InvalidFormatException
	{
		
		Workbook wb = WorkbookFactory.create(new File(xlsxfilepath));
		
		DataFormatter formatter = new DataFormatter();
		Sheet sheet1 = wb.getSheetAt(0);
		Row row = sheet1.getRow(0);
			int columnnum = 0;
			
			
			for (Cell cell : row) {
		        CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());

		        // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
		        String text = formatter.formatCellValue(cell);

		      if(param.isEmpty()) 
		      {
		    	  columnnum = 0;
		      }
		      else
		      {// is it an exact match?
		        if (param.equals(text)) {
		          
		           columnnum = cellRef.getCol();
		        }
		        // is it a partial match?
		        else if (text.contains(param)) {
		           columnnum = 0;
		        }
			}
			}
            return columnnum;
     }
	
	
	public static LinkedHashMap<Integer, String> getheaderelements(String xlsxfilepath,String element1,String element2) throws InvalidFormatException, IOException
	{
		reader = new Xls_Reader(xlsxfilepath);
		int stnum,stend =0;
		System.out.println("hai");
		stnum = getSearchposition( xlsxfilepath, element1);
		stend = getSearchposition( xlsxfilepath, element2);
		System.out.println("stnum "+stnum);
		System.out.println("stend "+stend);
		
		if (stend == 0)
		{
			stend = reader.getColumnCount("Sheet1");
			System.out.println("Newstend"+stend);
		}
		
		LinkedHashMap<Integer, String> headers_elements= new LinkedHashMap<Integer, String>();
		int k =0;
		for (int i = stnum ;i < stend ; i++)
			
		{
			headers_elements.put(k, reader.getCellData("Sheet1", i, 2));
			System.out.println(reader.getCellData("Sheet1", i, 2).toString());
			k =k+1;
		}
		
		
		return headers_elements;
		
	}
	
		
	
	
}
