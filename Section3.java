package add;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Section3 {
	
	public static File csv_to_kml(String csv_path,String kml_path) throws FileNotFoundException{
		File kml_file = new File(kml_path);
		PrintWriter kml_printer = new PrintWriter(kml_file);
		KMLBuilder kml_builder = new KMLBuilder();
		kml_printer.println((kml_builder.Create()));
		File csv_file =new File(csv_path);
		Scanner csv_scanner = new Scanner(csv_file);
		String data = csv_scanner.nextLine();
		String[] splited_data;
		CSV_Condition csv_Condition = new CSV_Condition();
		if(csv_Condition.filterInfo(data)){
			splited_data = data.split(",");
			kml_printer.println(kml_builder.addPhoneInfo(splited_data));
		}
		String order_in_csv = csv_scanner.nextLine();
		if(csv_Condition.filterInfo(order_in_csv))
			while(csv_scanner.hasNext()){
				data=csv_scanner.nextLine();
				if(csv_Condition.filterInfo(data) ){
					splited_data = data.split(",");
					kml_printer.print(kml_builder.addInfo(splited_data));
			}
		}
		csv_scanner.close();
		kml_printer.close();
		return kml_file;
	}
}
