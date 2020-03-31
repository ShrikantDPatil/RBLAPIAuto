package com.uat.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


import javax.json.JsonValue;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.OrderedJSONObject;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uat.base.TestBase;
import com.uat.client.CompareJsonResp;
import com.uat.client.Jsonupdate;
import com.uat.client.RestClient;
import com.uat.client.TESTreade;
import com.uat.client.UpdateExcel;
import com.uat.client.XlsdataExtract;



public class PostAPITest extends TestBase{
                TestBase testBase;
                TESTreade createreqjson;
                String serviceUrl;
                String apiUrl;
                String url;
                String xlsxfilepath = prop.getProperty("xlsxpath");
                RestClient restClient;
                CloseableHttpResponse closebaleHttpResponse;
                UpdateExcel ue = new UpdateExcel();  
                int rownum = 2;
                String ar = null;
                String fr = null;
                String remarks = null;
                org.json.simple.JSONObject inpuReqtemplete ;
                OrderedJSONObject ActReqtemplete;
                String responseString = null;
                @BeforeMethod
                public void setUp() throws ClientProtocolException, IOException, JSONException, ParseException
	                {
	                                testBase = new TestBase();
	                                serviceUrl = prop.getProperty("URL");
	                                apiUrl = prop.getProperty("serviceURL");
	                                url = serviceUrl + apiUrl;
	                                FileInputStream inFile = new FileInputStream(prop.getProperty("jsonreqsample"));
	                                byte[] str = new byte[inFile.available()];
	                                inFile.read(str);
	                                String string = new String(str);
	                                inpuReqtemplete = Jsonupdate.createJSONObject(string);
	                                
	                                ActReqtemplete = new OrderedJSONObject(string);                              
	                }                
                @DataProvider
                public Iterator<Object[]> getTestData()
	                {
	                	ArrayList<Object[]> testdata = XlsdataExtract.getDataFromExcels(xlsxfilepath);
						return testdata.iterator();
	                	
	                }              
                
                @Test(dataProvider = "getTestData")
                
                public void postAPITest(Map<String,String> map) throws JsonGenerationException, JsonMappingException, IOException, ParseException, InvalidFormatException, NullPointerException, JSONException
					{
                	
                	String arrOfStr[] = null;
                	String crrOfStr[] = null;
                	 org.json.simple.JSONObject inpuReq = null;
                    restClient = new RestClient();
                    HashMap<String, String> headerMap = new HashMap<String, String>();
                    headerMap.put("Content-Type", "application/json");
                	 System.out.println(map.size());
                	 inpuReq = inpuReqtemplete;
                	 for (Map.Entry<String, String> entry : map.entrySet()) {
                			String k = entry.getKey();
                			String v = entry.getValue();
                			
                			inpuReq = Jsonupdate.replacekeyInJSONObject(inpuReq,k,v);
                			//System.out.println("Key: " + k + ", Value: " + v);
                		}
                	//System.out.println("Req"+inpuReq);
                	
                	
                	HashMap<String, Object> templete = new Gson().fromJson(ActReqtemplete.toString(), HashMap.class);
                	           	
                	
                	HashMap<String, Object> b = new LinkedHashMap<String, Object>();
                	HashMap<String, Object> c = new LinkedHashMap<String, Object>();
                	
                	
                	for (String key: templete.keySet()) 
                	{
             	    // System.out.println("ROOTkey : " + key);             	     
             	    // System.out.println("ROOTvalue : " + templete.get(key));   	    
               	    
                	
             	     for(String keys: ((Map<String, JsonValue>) templete.get(key)).keySet())
             	     {
             	    	//System.out.println("HEADERkey : " + keys);             	     
                	   //  System.out.println("HEADERvalue : " + ((Map<String, JsonValue>) templete.get(key)).get(keys));   	
             	    	HashMap<String, Object> a = new LinkedHashMap<String, Object>();
                 	     for(String elementkeys: ((Map<String, JsonValue>) ((Map<String, JsonValue>) templete.get(key)).get(keys)).keySet())
                 	     {
                 	    	 //System.out.println("elementkeys : " + elementkeys);                    	     
                     	     //System.out.println("elementvalues : " + ((Map<String, JsonValue>) ((Map<String, JsonValue>) templete.get(key)).get(keys)).get(elementkeys));
                     	    CompareJsonResp respcomp = new CompareJsonResp();
                     	    Object obj = ((Map<String, JsonValue>) ((Map<String, JsonValue>) templete.get(key)).get(keys)).get(elementkeys);
                     	    a.put(elementkeys,map.get(elementkeys));
                     	    		//respcomp.respComparator(inpuReq.toString(), obj.toString()));
                 	     
                 	     }
                 	     b.put(keys, a);
             	     }
             	          c.put(key, b);       	
                	}
                	
                	               	
                	
                	GsonBuilder gsonMapBuilder = new GsonBuilder();
                	 
            		Gson gsonObject = gsonMapBuilder.create();
             
            		String JSONObject = gsonObject.toJson(c);
            		//System.out.println("modefied"+JSONObject);
            		System.out.println("Url:"+url);
            		ActReqtemplete = new OrderedJSONObject(JSONObject);
            		
            		System.out.println("modefied"+ActReqtemplete);
					closebaleHttpResponse = restClient.post(url, ActReqtemplete, headerMap); //call the API
                                int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
                                responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
                                 arrOfStr = map.get("Expectedresult").toString().split("\\|");                                        
                                 System.out.println("status code : "+statusCode);
                                 
                                 
                                if ((statusCode == 200)||(statusCode == 201))
                                {     
                                	System.out.println(statusCode);
                                	System.out.println(responseString);
                                	for (String ste : arrOfStr) 
                                	{
										
                                		
                                		crrOfStr = ste.split("\\:");
                                		
                                		if (crrOfStr[0].equalsIgnoreCase("resproottag"))
                                		{
                                		
                                			String k = crrOfStr[0];
                                			String v = crrOfStr[1];
                                			System.out.println(k);
                                			System.out.println(v);
                                			if (responseString.contains(v))
                                			{
                                			
                                				ar = k+":"+v+"|";
                                            	fr = "Test Pass";
                                            	remarks = String.valueOf(statusCode);
                                            	
                                    			
                                			}
                                			else
                                			{
                                				ar = k+":"+v+"|"+ar;
                                            	fr = "Test Fail";
                                            	remarks = "root tag at response not available";
                                            	
                                    			
                                			}
                                		}
                                		else {
    
                                			String k = crrOfStr[0];
                                			CompareJsonResp a = new CompareJsonResp();
                                			String v = a.respComparator(responseString,crrOfStr[0]);
                                			System.out.println("out :"+v);
                                			

                                			if (v.equalsIgnoreCase(crrOfStr[1]))
                                			{
                                			
                                				ar = k+":"+v+"|"+ar;
                                            	fr = "Test Pass";
                                            	remarks = String.valueOf(statusCode);
                                            	
                                    			
                                			}
                                			else
                                			{
                                				ar = k+":"+v+"|"+ar;
                                            	fr = "Test Fail";
                                            	remarks = String.valueOf(statusCode);
                                            	
                                    			
                                			}
										}

                                		                                		
                                		
									}
                                	
                                }                                
                                else
                                {
                                	ar =  String.valueOf(statusCode);
                                	fr = "Test Fail";
                                	remarks = closebaleHttpResponse.toString();
                                	//System.out.println("Remarks : "+closebaleHttpResponse.toString());
                                	
                                	
                                }
			                              
                               
                            	String status = ue.ExcelWriter(xlsxfilepath,rownum, ar, fr, remarks,inpuReq.toString(),responseString);
                            	System.out.println(status);
                                rownum = rownum+1;
                                System.out.println("actuvl res :"+ar);
                                System.out.println("final result :"+fr);
                               
                                
                                responseString = null;
                                
                }
					
				

				
                
                
                	
                
               
            	
                
                
                
}

                
                