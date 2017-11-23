package add;
import java.io.File;


public class CSV_Condition implements ConditionsOfFile {

/*filter information* method will filter the most relevant 
	information (the table's orderMAC & the coordinations)*/
	public boolean filterInfo(String data) {
		// TODO Auto-generated method stub
		if(data.equals("MAC,SSID,AuthMode,FirstSeen,Channel,RSSI,CurrentLatitude,CurrentLongitude,AltitudeMeters"
				+ ",AccuracyMeters,Type")){ return true;
		}
		String[] splited = data.split(",");
		if(splited[0].equals("WigleWifi-1.4"))
			return true;
		else{if(splited.length==11){
				boolean b = true;
				if(!filterMAC(splited[0]))
					b = false;
				if(!can_be_RSSI(splited[5]))
					b = false;
				double lat = Double.parseDouble(splited[6]);
				double lon = Double.parseDouble(splited[7]);
				if(Math.abs(lat)>90 || Math.abs(lon)>180)
					b=false;
				return b;
			}
		}
		return false;
	}
	
	private boolean can_be_RSSI(String data) {
		if(data.charAt(0)!='-')
			return false;
		char c = data.charAt(1);
		if(c<'0'||c>'9')
			return false;
		c = data.charAt(2);
		if(c<'0'||c>'9')
			return false;
		
		return true;
	}


	private boolean filterMAC(String data){
		String[] splited = data.split(":");
		if(splited.length != 6)
			return false;
		for(int i=0;i<6;i++){
			if(splited[i].length()!=2)
				return false;
			if(!checkCharNumberOrSmaalCharcter(splited[i].charAt(0))
					|| !checkCharNumberOrSmaalCharcter(splited[i].charAt(1)))
				return false;
		}
		return true;
	}
	
	private boolean checkCharNumberOrSmaalCharcter(char c){
		if(c>='0' && c<='9')
			return true;
		if(c>='a' && c<='z')
			return true;
		return false;
	}

}
