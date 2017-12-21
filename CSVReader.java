package ConverterPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Stream;

public class CSVReader {
	
	public static Hashtable<String,TreeMap<Integer,Router>> readCSV(File csvFile,Hashtable<String,TreeMap<Integer,Router>> ans) {
		Router router = null;
		Scanner csvScanner;
		try {
			csvScanner = new Scanner(csvFile);
			String[] data;
			csvScanner.nextLine();
			while(csvScanner.hasNext()) {
				data = csvScanner.nextLine().split(",");
				String Latitude = data[0];String Longitude = data[1]; String AltitudeMeters = data[2];
				for(int i = 4; i < data.length-5;i+=5) {
					router = new Router(data[i+1],data[i],data[i+3],data[i+2],Latitude,Longitude,AltitudeMeters,data[i+4]);
					String key = router.getMAC();
					TreeMap<Integer, Router> value = ans.get(key);
					int RSSI = router.getRSSI();
					if(value != null) {
						value.put(RSSI, router);
						ans.put(key,value );
					}
					else {
						value = new TreeMap<Integer, Router>();
						value.put(RSSI, router);
						ans.put(key,value);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;
	}
	
	public static Hashtable<String,TreeMap<Integer,Router>> readCSVDir(File csvFilesDir,Hashtable<String,TreeMap<Integer,Router>> ans) {
		Scanner csvScanner;
		ArrayList<Path> ListOfPaths = new ArrayList<Path>(); 
		//Hashtable<String,TreeMap<Integer,Router>> temp = new Hashtable<String,TreeMap<Integer,Router>>();
		try (Stream<Path> paths = Files.walk(Paths.get("C:\\Users\\Ali Masarweh\\Desktop\\data"))) {
		    paths.forEach(path->{
		    	ListOfPaths.add(path);
		    });
		    ListOfPaths.remove(0);
		    while(!ListOfPaths.isEmpty()) {
		    	csvScanner = new Scanner(ListOfPaths.remove(0));
		    	TreeMap<Integer,Router> routersMap = new TreeMap<Integer,Router>();
		    	while(csvScanner.hasNext()) {
		    		String scanned = csvScanner.nextLine();
		    		String[] splited = scanned.split(",");
		    		if(splited.length == 11 && !splited[0].equals("MAC")) {
		    			Router router= new Router(scanned.split(","));
		    			//System.out.println(router);
		    			String Lat = router.getLatitude()+""; Lat = Lat.substring(0, 5);
		    			String Lon = router.getLongitude() +""; Lon = Lon.substring(0, 5);
		    			String coordination =Lat+","+Lon+","+router.getAltitudeMeters();
		    			routersMap.put(router.getRSSI(), router);
		    			if(ans.get(coordination)!=null&&ans.get(coordination).size()<11)
		    				ans.put(coordination, routersMap);
		    			else {
		    				ans.put(coordination, routersMap);
		    				Iterator<Integer> sigs = ans.get(coordination).keySet().iterator();
		    				int minSig= sigs.next();
		    				ans.get(coordination).remove(minSig);
		    			}
		    			//if(ans.get(coordination).size()>1)System.out.println(ans.get(coordination).size());
		    		}
		    	}
		    	csvScanner.close();
		    }
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Iterator<String> coordination=temp.keySet().iterator();
		while(coordination.hasNext()) {
			String coordi = coordination.next();
			TreeMap<Integer,Router> routersMap = temp.get(coordi);
			Iterator<Integer> RSSIs=routersMap.keySet().iterator();
			TreeMap<Integer,Router> tempTree = new TreeMap<Integer,Router>();
			while(RSSIs.hasNext()) {
				int sig=RSSIs.next();
				Router router = routersMap.get(sig);
				tempTree.put(-sig, router);
			}
			ans.put(coordi, tempTree);
		}*/
		
		return ans;
	}


}
