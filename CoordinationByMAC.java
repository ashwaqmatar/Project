package GUI;

import java.io.File;
import java.util.TreeMap;
import ConverterPackage.CSVReader;
import ConverterPackage.Router;

public class CoordinationByMAC {
	
	final int algorithmSize=3;
	
	public double[] CoordinationByMAC1(String MAC,String[] paths) {
		double[] ans = new double[3];
		TreeMap<Integer, Router> myMap = new TreeMap<Integer,Router>();
		for(int i = 0;i < paths.length; i++) {
			getDataByMAC(MAC, paths[i], myMap);
		}
		ans = algorithm1(myMap);
		return ans;
	}

	public double[] CoordinationByMAC_MoreAccurate(String MAC,String[] paths) {
		double[] ans = new double[3];
		TreeMap<Integer, Router> myMap = new TreeMap<Integer,Router>();
		for(int i = 0;i < paths.length; i++) {
			getDataByMAC(MAC, paths[i], myMap);
		}
		
		return ans;
	}
	
	public double[] algorithm1(TreeMap<Integer, Router> myMap) {
		Integer[] keys = (Integer[]) myMap.keySet().toArray();
		double sumLat = 0,sumLon=0,sumAlt=0,sumRSSI=0;
		for(int i=0;i<keys.length && i<algorithmSize;i++) {
			Router router = myMap.get(keys[i]);
			double lat = router.getLatitude();
			double lon = router.getLongitude();
			double alt = router.getAltitudeMeters();
			double cubedRssi = 1.0/(keys[i].intValue()*keys[i].intValue());
			sumLat += cubedRssi*lat;
			sumLon += cubedRssi*lon;
			sumAlt += cubedRssi*alt;
			sumRSSI +=cubedRssi;
		}
		double[] ans = new double[3];
		ans[0] = sumLat/sumRSSI;
		ans[1] = sumLon/sumRSSI;
		ans[2] = sumAlt/sumRSSI;
		return ans;
	}
	
	public TreeMap<Integer, Router> getDataByMAC(String MAC,String path,TreeMap<Integer, Router> myMap) {
		File csvFile = new File(path);
		//CSVReader.readCSV(csvFile,myMap);
		/*String[] data;
		try {
			Scanner csvScanner = new Scanner(csvFile);
			while(csvScanner.hasNext()) {
				data = csvScanner.nextLine().split(",");
				if(MAC.equals(data[0])) {
					int RSSI = Integer.parseInt(data[5]);
					double[] temp = new double[3];
					for(int i=0;i<temp.length;i++)
						temp[i] = Double.parseDouble(data[i+6]);
					if(myMap.size()<algorithmSize) {
						myMap.put(RSSI,temp);
					}
					else {
						Integer[] compare = (Integer[]) myMap.keySet().toArray();
						int indx = binarySearchIndx(compare, RSSI);
						if(indx >=0 && indx <compare.length) {
							myMap.remove(compare[compare.length-1]);
							myMap.put(RSSI, temp);
						}
					}
				}
			}
			csvScanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Integer[] keys = (Integer[]) myMap.keySet().toArray();
		for(int i=0;i<keys.length;i++) {
			Router router = myMap.get(keys[i]);
			if(!router.getMAC().equals(MAC))
				myMap.remove(keys[i]);
		}
		return myMap;
	}
	
	public int binarySearchIndx(Integer[] compare,int RSSI) {
		int high = compare.length;
		int low = 0;
		int mid = (high+low)/2;
		if(compare[0].intValue() >= RSSI) {
			return 0;
		}
		while(high>low) {
			if(compare[mid].intValue() >= RSSI && compare[mid-1].intValue() <= RSSI)
				return mid;
			else if(compare[mid].intValue()>RSSI) {
				high = mid-1;
				mid = (high+mid)/2;
		}
			else {
				low = mid+1;
				mid = (high+low)/2;
			}
		}
		
		return -1;
	}
}
