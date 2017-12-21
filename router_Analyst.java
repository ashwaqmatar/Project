package ConverterPackage;

public class router_Analyst implements analyst {

	/**
	 * analyseData
	 * public boolean analyseData(Object data) analyze the given Router by the most relevant information(MAC & coordinations),
	 * if determined to be a valid data yields true, otherwise yields false.
	 * @param Router
	 * @return boolean
	 */
	@Override
	public boolean analyze(Object data) {	
		if(!(data instanceof Router)) {
			return false;
		}
		Router router = (Router) data;
		if(!filterMAC(router.getMAC()))	
			return false;		
		double lat = router.getLatitude();
		double lon = router.getLongitude();
		if(Math.abs(lat)>90 ||Math.abs(lon)>180)			
			return false;
		return true;
	}

	private boolean filterMAC(String data){
		if(data.charAt(data.length()-1) == ':')
			return false;
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
