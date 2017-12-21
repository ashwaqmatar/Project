package ConverterPackage;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeMap;

public class CSVToKML_DirToCSV {
	
	/**
	 * Convert_csv_to_kml
	 * public static File convert_csv_to_kml(String csv_path,String kml_path) makes from the CSV file given its path by the first argument to
	 * a KML file given its path by the second argument and yields the KML file.
	 * @param csv_path is the path to the CSV file
	 * 	   	  kml_path is the path to the KML file
	 * @return
	 */
	public static void convert_csv_to_kml(String csv_path,String kml_path)  {
		File csv_file = new File(csv_path);
		Hashtable<String,TreeMap<Integer,Router>> hashByMAC_SortedBySig = new Hashtable<String,TreeMap<Integer,Router>>();
		CSVReader.readCSV(csv_file, hashByMAC_SortedBySig);
		ArrayList<Router> listOfRouters = new ArrayList<Router>();
		router_Analyst analyst = new router_Analyst();
		Iterator<String> MACs = hashByMAC_SortedBySig.keySet().iterator();
		while(MACs.hasNext()) {
			TreeMap<Integer,Router> tempTreeMap = hashByMAC_SortedBySig.get(MACs.next());
			Iterator<Integer> Sigs = tempTreeMap.keySet().iterator();
			if(Sigs.hasNext()) {
				Router tempRouter = tempTreeMap.get(Sigs.next());
				if(analyst.analyze(tempRouter))
					listOfRouters.add(tempRouter);//the highest signal recored to this specific MAC
			}	
		}
		createFile.createKMLFileWithListofData(listOfRouters,kml_path,"MapOfRouters",new KMLBuilder());
	}
	
	public static void convert_csvDir_to_csv(String csv_dir_path,String csv_path) {
		File csv_file = new File(csv_dir_path);
		Hashtable<String,TreeMap<Integer,Router>> hashByMAC_SortedBySig = new Hashtable<String,TreeMap<Integer,Router>>();
		CSVReader.readCSVDir(csv_file, hashByMAC_SortedBySig);
		ArrayList<Router> listOfRouters = new ArrayList<Router>();
		router_Analyst analyst = new router_Analyst();
		Iterator<String> Coordination = hashByMAC_SortedBySig.keySet().iterator();
		while(Coordination.hasNext()) {
			TreeMap<Integer,Router> tempTreeMap = hashByMAC_SortedBySig.get(Coordination.next());
			Iterator<Integer> Sigs = tempTreeMap.keySet().iterator();
			//int count = 1;
			while(Sigs.hasNext() /*&& count<=10*/) {
				Router tempRouter = tempTreeMap.get(Sigs.next());
				if(analyst.analyze(tempRouter)) {
					listOfRouters.add(tempRouter);//the highest signal recored to this specific MAC
					//count++;
				}
			}
			listOfRouters.add(new Router(0));//a router to be skipped 
		}
		createFile.createCSVFileWithListofData(listOfRouters, csv_path, "FilteredCSV");
	}
}
