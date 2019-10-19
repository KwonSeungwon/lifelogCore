package lifelogcollector;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class MssqlDbConnector {
	public static String curr (String a,String b,String c, String d, long e){
		
	 	String curr = "insert into DATA3 values('" +
				a +"','"+
				b +"','"+
				c +"','"+
				d +"','"+
				e +"')";
		
		return curr ;
	}
	
	public static String curr2 (String ab,String bc,String dd,String da, String cf ){
		
		String curr2 = "insert into DATA3 values('" +
				ab +"','"+
				bc +"','"+
				dd +"','"+
				da +"','"+
				cf +"')";
		
		return curr2;
	}

	
	  public static void main(String args[]) {
	    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	    String url = "jdbc:sqlserver://220.68.27.110:1433;";
	    String user = "sa";
	    String pw = "dsem1010!";
	    String db = "TestDb3";
	    
	    
	    GregorianCalendar gc = new GregorianCalendar();

        long milis = gc.getTimeInMillis();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd"); 

        Date d = gc.getTime(); 

        String str = sf.format(d);

        str = str.substring(0,4)+str.substring(4,6)+str.subSequence(6, 8) + str.substring(8, 10);
	    
        JSONParser parser = new JSONParser();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			
	    try {
	    	long startTime = System.currentTimeMillis();

	      Class.forName(driver);
	      
	      Connection conn = DriverManager.getConnection(url + "databaseName=" + db, user, pw);
	      
	      Statement stmt = conn.createStatement();
	      
     
	      String date = null ;
      	  String datetime  = null;
      	
      	
      	Object obj = parser.parse(new FileReader("c:\\STEPS\\Steps" + str +".json"));
			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray Slide  = (JSONArray) jsonObject.get("activities-steps");
			Iterator i	 = Slide.iterator();	

			while (i.hasNext()){
				JSONObject slide = (JSONObject) i.next();
				String date1 = (String)slide.get("dateTime");
				date = date1 ;
			}
			System.out.println("데이터 전송");
						
			
			JSONObject steps = (JSONObject) jsonObject.get("activities-steps-intraday");
			JSONArray datasetst  =(JSONArray) (steps.get("dataset"));
			
			Iterator j	 = datasetst.iterator();
			while (j.hasNext()){
				JSONObject slide1 = (JSONObject) j.next();
				String time = (String)slide1.get("time");
				long value = (long)slide1.get("value");
				datetime = date+ " " +time;
				
				 String Stepscurr = curr("user001",datetime,"m","steps",value);
				 
				 stmt.executeLargeUpdate(Stepscurr);
			
				 
		
			 
			}
			((File) obj).delete();
			
			
			Object objhr = parser.parse(new FileReader("c:\\HR\\Heratrate"+str+".json"));
			JSONObject jsonObjecthr =  (JSONObject) objhr;
			JSONArray hrslide  = (JSONArray) jsonObjecthr.get("activities-heart");
			Iterator i2	 = hrslide.iterator();	
			
			JSONObject heart = (JSONObject) jsonObjecthr.get("activities-heart-intraday");

			JSONArray datasethr  = (JSONArray) (heart.get("dataset"));
			
			Iterator k	 = datasethr.iterator();
			while (k.hasNext()){
				JSONObject slide1 = (JSONObject) k.next();
				String time = (String)slide1.get("time");
				long value = (long)slide1.get("value");
				datetime = date+ " " +time;
				
				 String HRcurr = curr("user001",datetime,"bpm","HR",value);
		
			    stmt.executeLargeUpdate(HRcurr);
		
			}
			
			
			
			
				
			File file = new File("c:\\test\\TCX"+ str +".xml");

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuilder = dbf.newDocumentBuilder();
			Document doc = dbuilder.parse(file);
			
			doc.getDocumentElement().normalize(); 
			Element root = doc.getDocumentElement();
			
			NodeList n1 = root.getElementsByTagName("ns0:Trackpoint");
			
			for (int temp = 0; temp < n1.getLength(); temp++) {
				Node nNode = n1.item(temp);
				
		             Element eElement = (Element) nNode;
		             String tcxtimer = eElement.getElementsByTagName("ns0:Time").item(0).getTextContent();
		             Date tcxtimer2 = inputFormat.parse(tcxtimer);
		             String tcxtime = df.format(tcxtimer2);
		             String lat = eElement.getElementsByTagName("ns0:LatitudeDegrees").item(0).getTextContent();
		             String lon = eElement.getElementsByTagName("ns0:LongitudeDegrees").item(0).getTextContent();
		             String alt = eElement.getElementsByTagName("ns0:AltitudeMeters").item(0).getTextContent();    
		             String tcxhr = eElement.getElementsByTagName("ns0:Value").item(0).getTextContent();
		               
		           		String tcxlat = curr2("user001",tcxtime,"unkwon","gps_lat",lat);
		           		String tcxlon = curr2("user001",tcxtime,"unkwon","gps_lon",lon);
		           		String tcxalt = curr2("user001",tcxtime,"unkwon","gps_alt",alt);
		           		String tcxhrcurr = curr2("user001",tcxtime,"unkwon","tcxhr",tcxhr);

		    			stmt.executeLargeUpdate(tcxlat);
		    			stmt.executeLargeUpdate(tcxlon);
		    			stmt.executeLargeUpdate(tcxalt);
		    			stmt.executeLargeUpdate(tcxhrcurr);
		    			
			}
		  file.delete();		
	      stmt.close();
	      conn.close();
	      long endTime = System.currentTimeMillis();
	      long lTime = endTime - startTime/1000;
	      System.out.println("소요시간:" + lTime);
	      System.out.println("데이터 삽입완료");
	      Thread.sleep(10000000);
			
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    
	    
	  }
	  
	}

