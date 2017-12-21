package ConverterPackage;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class KMLBuilder implements FileBuilder {
	
	/**
	 * Exit
	 * public String Exit() yields the exiting information
	 */
	@Override
	public String Finish( ){
		return ("</Folder>\n</Document>\n</kml>\n");
	}
	
	/**
	 * Create
	 * public String Create() yields the commands to create the KML file
	 */
	@Override
	public String Create( ){
		return ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+"<kml xmlns=\"http://www.opengis.net"
				+ "/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com"
				+ "/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\">"
				+ "<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href"
				+ "></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google"
				+ ".com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder>"
				+ "<name>Wifi Networks</name>");
	}
	
	/**
	 * addInfo
	 * public String addInfo(Object allInfo) yields the information in the proper way of KML file
	 * @param Router
	 * @return the correct format to create place mark that contains the router's data
	 */
	@Override
	public String addInfo(Object obj){
		if(!(obj instanceof Router)) {
			return "";
		}
		Router router = (Router) obj;
		String ans = "";
		ans += createPlacemark();
		ans += addName(router.getSSID());
		ans += createTheDescription(router.getMAC(),router.getFirstSeen(),router.getRSSI());
		ans += createTheCoordinationPoint(router.getLongitude(),router.getLatitude(),router.getAltitudeMeters());
		ans += closePlacemark();
		return ans;
	}
	
	private String createPlacemark( ){
		return ("<Placemark>\n");
	}
	
	private String closePlacemark( ){
		return ("</Placemark>\n");
	}
	
	private String addName(String data){
		return ("<name><![CDATA[ "+data+" ]]></name>\n");
	}
	
	private String createTheDescription(String BSSID,String firstSeen,int RSSI) {
		return ("<description><![CDATA[\nBSSID: <b>"+BSSID+"</b><br/> first seen:"+ firstSeen+
				"</b><br/> timestamp: <b><when>"+ convert_date_to_timestamp(firstSeen).getTime()+"</b><br/>RSSI: <b>"+
				RSSI+"</b><br/>]]></description><styleUrl>#red</styleUrl>\n");
	}
	
	private String createTheCoordinationPoint(double CurrentLongitude,double CurrentLatitude,double AltitudeMeters){
		return ("<Point>\n<coordinates>"+CurrentLongitude+","+CurrentLatitude+","+AltitudeMeters+
				"</coordinates></Point>\n");
	}
	
	public String addPhoneInfo(String[] splited_data){
		String ans = ("<description>\nThe phone description:");
		for(int i=0;i<splited_data.length;i++){
			ans += splited_data[i] + " ";
		}
		return (ans + "\n</description>\n");
	}
	
	private Timestamp convert_date_to_timestamp(String data){
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date lFromDate1 = null;
		Timestamp fromTS1 = null;
		try {
			lFromDate1 = datetimeFormatter1.parse(data);
			fromTS1 = new Timestamp(lFromDate1.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fromTS1;
	}
	
}
