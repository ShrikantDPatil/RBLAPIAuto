package com.uat.client;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.json.JsonValue;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;
import org.apache.wink.json4j.OrderedJSONObject;
import org.json.JSONTokener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonStreamParser;
import com.google.gson.internal.LinkedTreeMap;
import com.uat.base.TestBase;

public class ParseJson<E> extends TestBase{

	TestBase testBase;
    TESTreade createreqjson;
    String serviceUrl;
    String apiUrl;
    String url;
    JSONObject jsonObj = null;
    JSONArray jsonArr = null;
    JSONObject json;
    Object     intervention;
    JSONArray  interventionJsonArray;
    JSONObject interventionObject;
    public static JSONObject objectToJSONObject(Object object){
        Object json = null;
        JSONObject jsonObject = null;
        json = new JSONTokener(object.toString()).nextValue();
        if (json instanceof JSONObject) {
            jsonObject = (JSONObject) json;
        }
        return jsonObject;
    }

    public static JSONArray objectToJSONArray(Object object){
        Object json = null;
        JSONArray jsonArray = null;
        json = new JSONTokener(object.toString()).nextValue();
        if (json instanceof JSONArray) {
            jsonArray = (JSONArray) json;
        }
        return jsonArray;
    }
	public static void main(String[] args) throws IOException, JSONException {
		
	    
		
		// TODO Auto-generated method stub
		ParseJson PJ = new ParseJson();
		PJ.get();
		System.out.println();
		
	}
	void get() throws IOException, JSONException
	{
		
		
		FileInputStream inFile = new FileInputStream(prop.getProperty("jsonreqsample"));
        byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String string = new String(str);
		OrderedJSONObject ActReqtemplete;
		ActReqtemplete = new OrderedJSONObject(string);
    	HashMap<String, Object> templete = new Gson().fromJson(ActReqtemplete.toString(), HashMap.class);
       	
    	
    	
    	HashMap<String, Object> b = new LinkedHashMap<String, Object>();
    	HashMap<String, Object> c = new LinkedHashMap<String, Object>();
    	HashMap<String,List<String>> myMap = new HashMap<String,List<String>>();    
    	Object obj = new Object();
    	//System.out.println("templete  "+templete);
    	for (String key: templete.keySet()) 
    	{
    		System.out.println(key);
    		System.out.println(templete.get(key));    		
    		System.out.println(templete.get(key).getClass().getName());
    		if(templete.get(key).getClass().getName().toString().equalsIgnoreCase("java.lang.String"))
    		{
    			b.put(key, "hay");
    		}
    		else if(templete.get(key).getClass().getName().toString().equalsIgnoreCase("java.util.ArrayList"))
    		{  
    			ArrayList al = new ArrayList<>();
        		al.addAll((Collection) templete.get(key));
        		
    			LinkedTreeMap<String,String> ltm=new LinkedTreeMap<String,String>();
    			ltm.putAll((Map<String,String>) al.get(0));
        		
        		for (String ks: ltm.keySet()) 
        		{
        			

        			ltm.put(ks,"hai");
        			
        		}
        		ArrayList a2 = new ArrayList<>();
        		
        		a2.add(ltm);
        		b.put(key, a2);
    		}
    		else
    		{
    			
    		}	
    		
    		
    	}
    	GsonBuilder gsonMapBuilder = new GsonBuilder();
   	 
		Gson gsonObject = gsonMapBuilder.create();
 
		String JSONObject = gsonObject.toJson(b);
    	System.out.println("final  "+JSONObject);
    }

}

