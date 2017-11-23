package add;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Section2 {
	
	
	
	public static File kml_to_csv(String csv_path,String kml_path) throws FileNotFoundException{
		File csv_file = new File(csv_path);
		PrintWriter csv_printer = new PrintWriter(csv_file);
		CSVBuilder csv_builder = new CSVBuilder();
		csv_printer.println(csv_builder.Create());
		File kml_File = new File(kml_path);
		Scanner kml_scanner = new Scanner(kml_File);
		String allInfo ="";
		boolean coordinates_Inserted = false;
		KML_Condition kml_condition = new KML_Condition();
		while(kml_scanner.hasNext()){
			String data = kml_scanner.nextLine();
			if(kml_condition.filterInfo(data)){
				if(data.contains("<name>")){
					String name = getName(data);
					if(name != null )
						allInfo += name+",";
				}
				else if(data.contains("<description>")){
					String description = getDescription(data);
					allInfo += (description);
				}
				else if(data.contains("<coordinates>")){
					allInfo +=getCoordination(data);
					coordinates_Inserted = true;
					}
				if(coordinates_Inserted && allInfo.split(",").length == 8){
					csv_printer.println(csv_builder.addInfo(allInfo));
					allInfo = "";
					coordinates_Inserted = false;
				}
				else if(coordinates_Inserted)
				{
					allInfo = "";
					coordinates_Inserted = false;
				}
				
			}
		}
			
		csv_builder.Exit();
		kml_scanner.close();
		csv_printer.close();
		return csv_file;
	}
	
	public static String getName(String data){
		int block_indx = data.indexOf('[');
		data = data.substring(block_indx+1);
		block_indx = data.indexOf('[');
		data = data.substring(block_indx+1);
		block_indx = data.indexOf(']');
		if(block_indx != -1 )
			return data.substring(0, block_indx);
		return null;
	}
	
	public static String getDescription(String data){
		int indx;
		String[] splited = data.split("<b>");
		String ans = "";
		for(int i=1;i<splited.length;i++){
			indx =splited[i].indexOf('<'); 
				ans += (splited[i].substring(0,indx)+",");
		}
		return ans;
	}
		
	
	public static String getCoordination(String data){
		data = data.substring(data.indexOf('<')+1);
		data = data.substring(data.indexOf('>')+1, data.indexOf('<'));
		return data;
	}
}
