package add;
public class CSVBuilder implements FileBuilder {

	@Override
	public String Exit( ){
		return "";
	}
	
	@Override
	public String Create( ){
		return ("SSID,MAC,AuthMode,Frequency,Timestamp,Date,CurrentLongitude,CurrentLatitude");
	}
	
	@Override
	public String addInfo(Object allInfo){
		return ((String) allInfo);
	}

}
