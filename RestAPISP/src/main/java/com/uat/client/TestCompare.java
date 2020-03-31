package com.uat.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestCompare {

	public static void main(String[] args) throws IOException, ParseException {
		
		FileInputStream inFile = new FileInputStream("C:\\Users\\11825\\Documents\\Empower_Workspace\\RestAPISP\\src\\main\\java\\com\\uat\\data\\SPreq.json");
       
		byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String string = new String(str);
        CompareJsonResp a = new CompareJsonResp();
        System.out.println(a.respComparator(string,"Ben_IFSC"));
        JSONObject object = Jsonupdate.createJSONObject(string);
      //   System.out.println(Jsonupdate.replacekeyInJSONObject(json,"Debit_Acct_No","405405405405"));

		// TODO Auto-generated method stub ObjectMapper mapper = new ObjectMapper();
 
 
       
        //JSONObject object = null; // your json object
 /*       for (Object key : object.keySet()) {
            System.out.println(key + "=" + object.get(key)); // to get the value
            for (Object subKey : ((JSONObject) object.get(key)).keySet()) {
                System.out.println(subKey + "=" + object.get(subKey));
            }
        }
        */
       
        
    }

}   
        
        
