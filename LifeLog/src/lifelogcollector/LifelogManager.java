package lifelogcollector;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.parsers.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.*;

public class LifelogManager extends Thread {
	public void run() {
		DBConnector dbc = new DBConnector();
		JSONParser parser = new JSONParser();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		FileManager f1 = new FileManager();
			
		try {
			String date = null ;
			String datetime  = null;
			
	      	if (!dbc.connect()) {
	      		return;
	      	}
	      	System.out.println("데이터 삽입시작 쓰레드 시작");
	      	
			List<File> flist = f1.Slist(null) ;
			for (int sri = 0 ; sri <flist.size(); sri ++){
				File sname = flist.get(sri);		
		    	Object obj = parser.parse(new FileReader(sname));
		    	JSONObject jsonObject =  (JSONObject) obj;
				JSONArray Slide  = (JSONArray) jsonObject.get("activities-steps");
				Iterator i	 = Slide.iterator();	

				while (i.hasNext()){
				JSONObject slide = (JSONObject) i.next();
					String date1 = (String)slide.get("dateTime");
					date = date1 ;
					}
					JSONObject steps = (JSONObject) jsonObject.get("activities-steps-intraday");
					JSONArray datasetst  =(JSONArray) (steps.get("dataset"));
					
					Iterator j	 = datasetst.iterator();
					while (j.hasNext()){
						JSONObject slide1 = (JSONObject) j.next();
						String time = (String)slide1.get("time");
						long value = (long)slide1.get("value");
						datetime = date+ " " +time;
						dbc.insertLifelog("user001",datetime,"steps","step",String.valueOf(value));	
						sname.delete();
					}
				}
				

				List<File> hlist = f1.HRlist(null) ;
				for (int hri = 0 ; hri < hlist.size(); hri ++ ){
					File hrname = hlist.get(hri);
					Object objhr = parser.parse(new FileReader(hrname));
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
					dbc.insertLifelog("user001",datetime,"bpm","HR",String.valueOf(value));	
					
					}
				}
			
					List<File> tlist = f1.TCXlist(null) ;
					for (int ttl = 0 ; ttl<tlist.size(); ttl++){
							File tcxname = tlist.get(ttl);
							DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
							DocumentBuilder dbuilder = dbf.newDocumentBuilder();
							Document doc = dbuilder.parse(tcxname);					
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
					             
					        dbc.insertLifelog("user001",tcxtime,"unkwon","gps_lat",lat);
					        dbc.insertLifelog("user001",tcxtime,"unkwon","gps_lon",lon);
					        dbc.insertLifelog("user001",tcxtime,"unkwon","gps_alt",alt);
					        dbc.insertLifelog("user001",tcxtime,"bpm","hr",tcxhr);
					        tcxname.delete();
						}
					}
		      dbc.close();
		      System.out.println("데이터 삽입완료 쓰레드 휴식");
		      Thread.sleep(10000000);		      
		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		    }
	}
	

	
	
	

		      
public static void main(String args[]) {
			
		LifelogManager thread = new LifelogManager();
		thread.start();
		}
	}




		      
				
		