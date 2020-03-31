package com.uat.client;

import org.json.simple.JSONObject;

public class CompareJsonResp {
  String response_val = null;
  String response_key = null; 
  
	
	public String respComparator(String str,String jsonKey)
	{
		
	//	 System.out.println("hai iam for respComparator "+str);
         String string = new String(str.toUpperCase());
         JSONObject inpuReqtemplete = Jsonupdate.createJSONObject(string);
  
         
         compateresponsejson(inpuReqtemplete,jsonKey.toUpperCase());
         System.out.println("Hai i am from class :"+response_val);
       //  System.out.println("Key : " +response_key);
         if(response_key != null) {
         //	System.out.println("reponse : " +response_val);
         }
         else
         {
        	// System.out.println("key not vailable ");
        	 response_val = "key not vailable";
         }
		return response_val;

	}
	 public  JSONObject compateresponsejson(JSONObject jsonObject, String jsonKey)
	 {
      for (Object key : jsonObject.keySet())
      {
		 if (key.equals(jsonKey) && ((jsonObject.get(key) instanceof String)||(jsonObject.get(key) instanceof Number)||jsonObject.get(key) ==null)) {
             //jsonObject.put(key, jsonValue);
			 response_key = key.toString();
			 response_val = jsonObject.get(key).toString();
            // System.out.println("key :"+key.toString()+"value :"+jsonObject.get(key));
			 
             return jsonObject;
         } else if (jsonObject.get(key) instanceof JSONObject) {
             JSONObject modifiedJsonobject = (JSONObject) jsonObject.get(key);
             if (modifiedJsonobject != null) {
            	 compateresponsejson(modifiedJsonobject, jsonKey);
             }
         }
	 }
	return jsonObject;
	 }
}
