package add;
import java.io.File;

public class KML_Condition implements ConditionsOfFile{

	
	/**
	 * dvdgdgsdgS
	 * @param pathname
	 */

//*filter information* method will filter the most relevant information (MAC & the coordinations)
	public boolean filterInfo(String data){
		String[] split =data.split(">");
		if(split[0].equals("<name"))
			return true;
		else if(split[0].equals("<description")){
			/*assuming that the KML file is created by wiggle-wifi  it 
			 *should have at least 18 > without counting the last on
			 *otherwise information have been lost
			 */
			if(split.length < 19){
				return false;
			}
			int endIndx = split[2].indexOf('<');
			return filterMAC(split[2].substring(0,endIndx));
		}
		else if(split[0].contains("<coordinates")){
			int endIndx = split[1].indexOf('<');
			if(!filterCoordinates(split[1].substring(0,endIndx))){
				return false;
			}
		}
		return true;
	}
	private boolean filterCoordinates(String data){
		String[] splited = data.split(",");
		double lat = Double.parseDouble(splited[0]);
		double lon = Double.parseDouble(splited[1]);
		if(Math.abs(lat)<=90 && Math.abs(lon)<=180)
			return true;
		else{
			return false;
		}
		
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
