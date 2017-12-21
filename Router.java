package ConverterPackage;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Router {
	private String MAC;
	private String SSID;
	private double Latitude;
	private double Longitude;
	private double AltitudeMeters;
	private String FirstSeen;
	private long timestamp;
	private int RSSI;
	
	public Router(int x) {
		if(x==0) {
			MAC="";
			SSID="";
			Latitude = 0;
			Longitude=0;
			AltitudeMeters=0;
			FirstSeen = "";
			RSSI=0;
		}
	}
	
	
	/**
	 * 
	 * @param MAC the MAC of the router is like an ID
	 * @param SSID the name but in a fancy way
	 * @param FirstSeen the date in format "yyyy-MM-dd hh:mm:ss"
	 * @param RSSI the signal
	 * @param Latitude 
	 * @param Longitude
	 * @param AltitudeMeters
	 */
	public Router(String MAC,String SSID,String FirstSeen,int RSSI,double Latitude,double Longitude,double AltitudeMeters)  {
		this.MAC = new String(MAC);
		this.SSID = new String(SSID);
		this.setLatitude(Latitude);
		this.Longitude = Longitude;
		this.AltitudeMeters = AltitudeMeters;
		this.FirstSeen = new String(FirstSeen);
		timestamp = convert_date_to_timestamp(FirstSeen).getTime();
		this.RSSI = RSSI;
	}
	/**
	 * 
	 * @param MAC the MAC of the router is like an ID
	 * @param SSID the name but in a fancy way
	 * @param FirstSeen the date in format "yyyy-MM-dd hh:mm:ss"
	 * @param RSSI the signal
	 * @param Latitude
	 * @param Longitude
	 * @param AltitudeMeters
	 * @param Timestamp the date in format the computer understand
	 */
	public Router(String MAC,String SSID,String FirstSeen,int RSSI,double Latitude,double Longitude,double AltitudeMeters,long Timestamp) {
		this(MAC,SSID,FirstSeen,RSSI,Latitude,Longitude,AltitudeMeters);
		this.timestamp = Long.valueOf(Timestamp);
	}
	/**
	 * 
	 * @param MAC the MAC of the router is like an ID
	 * @param SSID the name but in a fancy way
	 * @param FirstSeen the date in format "yyyy-MM-dd hh:mm:ss"
	 * @param RSSI the signal
	 * @param Latitude
	 * @param Longitude
	 * @param AltitudeMeters
	 */
	public Router(String MAC,String SSID,String FirstSeen,String RSSI,String Latitude,String Longitude,String AltitudeMeters) {
		this(MAC,SSID,FirstSeen,0,0,0,0);
		this.RSSI = ((Integer)Integer.parseInt(RSSI)).intValue();
		this.Latitude = ((Double)Double.parseDouble(Latitude)).doubleValue();
		this.Longitude = ((Double)Double.parseDouble(Longitude)).doubleValue();
		this.AltitudeMeters=((Double)Double.parseDouble(AltitudeMeters)).doubleValue();
	}
	/**
	 * 
	 * @param MAC the MAC of the router is like an ID
	 * @param SSID the name but in a fancy way
	 * @param FirstSeen the date in format "yyyy-MM-dd hh:mm:ss"
	 * @param RSSI the signal
	 * @param Latitude
	 * @param Longitude
	 * @param AltitudeMeters
	 * @param Timestamp
	 */
	public Router(String MAC,String SSID,String FirstSeen,String RSSI,String Latitude,String Longitude,String AltitudeMeters,String Timestamp) {
		this(MAC,SSID,FirstSeen,RSSI,Latitude,Longitude,AltitudeMeters);
		this.timestamp = ((Long) Long.parseLong(Timestamp)).longValue();
	}
	
	public Router(String[] data) {
		this(data[0],data[1],data[3],data[5],data[6],data[7],data[8]);
	}
	
	private Timestamp convert_date_to_timestamp(String data) {
		try {
			SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date lFromDate1;
			lFromDate1 = datetimeFormatter1.parse(data);
			Timestamp fromTS1 = new Timestamp(lFromDate1.getTime());
			return fromTS1;
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String toString() {
		return "{The name:"+SSID+",The MAC:"+MAC+",The date:"+FirstSeen+",The Signal:"+RSSI+",The coordination"
				+Latitude+","+Longitude+","+AltitudeMeters+"}";
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}

	public String getSSID() {
		return SSID;
	}

	public void setSSID(String sSID) {
		SSID = sSID;
	}
	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public double getAltitudeMeters() {
		return AltitudeMeters;
	}

	public void setAltitudeMeters(double altitudeMeters) {
		AltitudeMeters = altitudeMeters;
	}
	public String getFirstSeen() {
		return FirstSeen;
	}

	public void setFirstSeen(String firstSeen) {
		FirstSeen = firstSeen;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getRSSI() {
		return RSSI;
	}

	public void setRSSI(int rSSI) {
		RSSI = rSSI;
	}


}
