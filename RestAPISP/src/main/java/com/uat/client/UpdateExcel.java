package com.uat.client;



public class UpdateExcel 
{	
	
	String status = null;
	Xls_Reader reader;
	public String ExcelWriter(String path,int rownum,String ar,String fr,String remarks,String req,String res)
	{
		
		try {
				
				reader = new Xls_Reader(path);

				reader.setCellData("Sheet1", "ActualResult", rownum, ar);
				reader.setCellData("Sheet1", "FinalResult", rownum, fr);
				reader.setCellData("Sheet1", "Remarks", rownum, remarks);
				reader.setCellData("Sheet1", "Request", rownum, req);
				reader.setCellData("Sheet1", "Response", rownum, res);
			} catch (Exception e) {
				// TODO: handle exception
				
				status = e.toString();
				e.printStackTrace();
				
			}		
		
		
			
			
			status = "updated";
		
		return status;
	}
	
}
