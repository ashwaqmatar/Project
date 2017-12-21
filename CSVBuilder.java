package ConverterPackage;

public class CSVBuilder implements FileBuilder {

	@Override
	public String Create() {
		String ans = "Latitude,Longitude,AltitudeMeters,#wifinetworks";
		for(int indx=1;indx<=10;indx++) {
			ans +=",SSID"+indx+",MAC"+indx+",Signal"+indx+",FirstSeen"+indx+",Timestamp"+indx;
		}
		ans+="\n";
		return ans;
	}

	@Override
	public String Finish() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String addInfo(Object Data) {
		String ans="";
		if(Data instanceof Router) {
			Router router = (Router) Data;
			ans+=router.getSSID()+","+router.getMAC()+","+router.getRSSI()+","+router.getFirstSeen()+","+router.getTimestamp()+",";
		}
		else {
			if(Data instanceof String)
				ans = ans+Data+",#wifinetworks,";
		}
		return ans;
	}

}
