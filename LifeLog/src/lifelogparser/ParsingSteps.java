package lifelogparser;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ParsingSteps {

	public static void main(String[] args) {
		
		
        JSONParser parser = new JSONParser();
        
        

        
        try {
        	
        	String date = null ;
        	String arr[] = new String[1000];
        	String arr2[];
        	
        	
        	Object obj = parser.parse(new FileReader("c:\\test\\Steps2017-08-29.json"));
			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray Slide  = (JSONArray) jsonObject.get("activities-steps");
			Iterator i	 = Slide.iterator();	
			
			
		
			while (i.hasNext()){
				JSONObject slide1 = (JSONObject) i.next();
				String date1 = (String)slide1.get("dateTime");
				date = date1 ;
			}
			System.out.println(date);
						
			
			JSONObject steps = (JSONObject) jsonObject.get("activities-steps-intraday");
			JSONArray dataset  =(JSONArray) (steps.get("dataset"));
			
			Iterator ii	 = dataset.iterator();
			while (ii.hasNext()){
				JSONObject slide1 = (JSONObject) ii.next();
				String time = (String)slide1.get("time");
				long value = (long)slide1.get("value");
				System.out.println(date+" " +time);
			}
			
	
		

			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        	


	}

}
