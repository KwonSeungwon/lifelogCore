package lifelogparser;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParsingHR {

	public static void main(String[] args) {
		
        JSONParser parser = new JSONParser();
		
		try {
        	
        	Object obj = parser.parse(new FileReader("c:\\test\\Heratrate2017-08-29.json"));
			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray Slide  = (JSONArray) jsonObject.get("activities-heart");
			Iterator i	 = Slide.iterator();	
		
			while (i.hasNext()){
				JSONObject slide1 = (JSONObject) i.next();
				String date = (String)slide1.get("dateTime");
				System.out.println(date);
			}
						
			
			
			JSONObject heart = (JSONObject) jsonObject.get("activities-heart-intraday");

			JSONArray dataset  = (JSONArray) (heart.get("dataset"));
			
			Iterator ii	 = dataset.iterator();
			while (ii.hasNext()){
				JSONObject slide1 = (JSONObject) ii.next();
				String time = (String)slide1.get("time");
				long value = (long)slide1.get("value");
				System.out.println(time);
				System.out.println(value);
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