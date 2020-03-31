package com.uat.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class Jsonupdate {
	
	public static void main(String[] args) throws IOException {

        FileInputStream inFile = new FileInputStream("C:\\Users\\11825\\Documents\\Empower_Workspace\\RestAPISP\\src\\main\\java\\com\\uat\\data\\Colalert.json");
        byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String string = new String(str);
        System.out.println(string);
        JSONObject json = Jsonupdate.createJSONObject(string);
        //System.out.println(Jsonupdate.replacekeyInJSONObject(json,"Debit_Acct_No","405405405405"));
        
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.readerForUpdating(json);
        JSONParser jq1 = new JSONParser();
       
        System.out.println("Response :"+objectReader.readValue(Jsonupdate.replacekeyInJSONObject(json,"Debit_Acct_No","405405405405").toString()));
    }
	


    private static Object toList(JSONArray value) {
		// TODO Auto-generated method stub
		return null;
	}

	public static JSONObject replacekeyInJSONObject(JSONObject jsonObject, String jsonKey,
            String jsonValue) {

        for (Object key : jsonObject.keySet()) {
            if (key.equals(jsonKey) && ((jsonObject.get(key) instanceof String)||(jsonObject.get(key) instanceof Number)||jsonObject.get(key) ==null)) {
                jsonObject.put(key, jsonValue);
                return jsonObject;
            } else if (jsonObject.get(key) instanceof JSONObject) {
                JSONObject modifiedJsonobject = (JSONObject) jsonObject.get(key);
                if (modifiedJsonobject != null) {
                    replacekeyInJSONObject(modifiedJsonobject, jsonKey, jsonValue);
                }
            }

        }
        return jsonObject;
    }
    
    
    

    public static JSONObject createJSONObject(String jsonString){
        JSONObject  jsonObject=new JSONObject();
        JSONParser jsonParser=new  JSONParser();
        if ((jsonString != null) && !(jsonString.isEmpty())) {
            try {
                jsonObject=(JSONObject) jsonParser.parse(jsonString);
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
	
}
