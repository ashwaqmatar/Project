package add;

public class KMLBuilder implements FileBuilder {
	
	@Override
	public String Exit( ){
		return ("</Folder>\n</Document>\n</kml>\n");
	}
	
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
	
	@Override
	public String addInfo(Object splited_data1){
		String[] splited_data = (String[]) splited_data1;
		String ans = "";
		ans += createPlacemark();
		ans += addName(splited_data[1]);
		ans += createTheDescription(splited_data[0],splited_data[2],splited_data[4],splited_data[5],splited_data[9],splited_data[10]);
		ans += createTheCoordinationPoint(splited_data[7],splited_data[6],splited_data[8]);
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
	
	private String createTheDescription(String BSSID,String Capabilities,String channel,String RSSI
			,String AccuracyMeters,String Type){
		return ("<description><![CDATA[\nBSSID: <b>"+BSSID+"</b><br/>Capabilities: <b>"+Capabilities
				+"</b><br/>channel: <b>"+channel+"</b><br/>RSSI: <b>"+RSSI+"</b><br/>AccuracyMeters: <b>"
				+AccuracyMeters+"</b><br/>Type: <b>"+Type+"</b><br/>]]></description><styleUrl>#red</styleUrl>\n");
	}
	
	private String createTheCoordinationPoint(String CurrentLongitude,String CurrentLatitude,String AltitudeMeters){
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
	
}
