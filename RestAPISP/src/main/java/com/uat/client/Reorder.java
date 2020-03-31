package com.uat.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


import org.apache.wink.json4j.JSONObject;
import org.apache.wink.json4j.OrderedJSONObject;
import org.apache.wink.json4j.compat.JSONException;
import org.apache.wink.json4j.utils.XML;

import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Reorder 
{
	
	
    public static void main(String args[]) 
    { 
        
    	FileInputStream inFile;
		try {
			inFile = new FileInputStream("C:\\Users\\11825\\Documents\\Empower_Workspace\\RestAPISP\\src\\main\\java\\com\\uat\\data\\SPreq.json");
			byte[] str = new byte[inFile.available()];
	        inFile.read(str);
	        
	        String string = new String(str);	        
	    	OrderedJSONObject oj = new OrderedJSONObject(string);
	    	//System.out.println(oj);
	    	
	    	ObjectMapper mapper = new ObjectMapper();

	    	JsonNode tree1 = mapper.readTree(oj.toString());
	    	
	    	Iterator<JsonNode> iterator = (Iterator<JsonNode>) tree1.spliterator();
	    	
	    	System.out.println(tree1.spliterator());
	    	 while (iterator.hasNext()) {
	                
	                System.out.println(iterator.next());
	                
	                //System.out.println(node);
	            }
	    	
	    	//JsonNode tree2 = mapper.readTree(jsonInput2);

	    	//boolean areTheyEqual = tree1.equals(tree2);
	    	//System.out.println(tree1.findValue("Header"));
	    
	    	//tree1.has(0);
	    	
	    	
	         //printCompleteJson(nodeList);	    	
	    	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.apache.wink.json4j.JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		
		
		
        
		
    } 
    
    
    
    
    
    static void printCompleteJson(ArrayList<JsonNode> rootNode) throws IOException {
        for (int i = 0; i < rootNode.size(); i++) {
            Iterator<JsonNode> iterator = rootNode.get(i).iterator();
            JsonNode node = null;
            ArrayList<JsonNode> nodeList = new ArrayList<JsonNode>();
            boolean isEmpty = true;
            while (iterator.hasNext()) {
                isEmpty = false;
                node = iterator.next();
                nodeList.add(node);
                //System.out.println(node);
            }
            if(isEmpty){
                return;
            }
            printCompleteJson(nodeList);
        }
    }
} 

