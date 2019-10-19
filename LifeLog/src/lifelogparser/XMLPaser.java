package lifelogparser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class XMLPaser {

	public static void main(String[] args) {
		
		
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			File file = new File("c:\\test\\TCX2017-08-299561896174.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			
			doc.getDocumentElement().normalize(); 
			Element root = doc.getDocumentElement();
			
			System.out.println("root ³ëµå´Â :" + root) ;
			
			NodeList n1 = root.getElementsByTagName("ns0:Trackpoint");
			System.out.println(n1.getLength());
			
			
			for (int temp = 0; temp < n1.getLength(); temp++) {
				Node nNode = n1.item(temp);
				
		               Element eElement = (Element) nNode;
		               String time = eElement.getElementsByTagName("ns0:Time").item(0).getTextContent();
		               Date date = inputFormat.parse(time);
		               String output = df.format(date);
		               String lat = eElement.getElementsByTagName("ns0:LatitudeDegrees").item(0).getTextContent();
		               String lon = eElement.getElementsByTagName("ns0:LongitudeDegrees").item(0).getTextContent();
		               String alt = eElement.getElementsByTagName("ns0:AltitudeMeters").item(0).getTextContent();
		               String hr = eElement.getElementsByTagName("ns0:Value").item(0).getTextContent();
		               System.out.println(output);
		               System.out.println(lat);
		               System.out.println(lon);
		               System.out.println(alt);
		               System.out.println(hr);
		               

			}
		

	} catch (ParserConfigurationException e) {
			e.printStackTrace();
	} catch (SAXException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
	}
}
		
		



	






