package add;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Converter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Create a folder and put in it these files:\n (1) WigleWifi's csv\n (2) WigleWifi's kml\n (3) Two txt files "
				+ "one will be converted to kml and the other to csv\nInsert the folder's path:");
		Scanner scan = new Scanner(System.in);
		String path = scan.nextLine();
		System.out.println("The Wigle's csv name:");
		String wigleCSV = scan.nextLine();
		System.out.println("The Wigle's kml name:");
		String WigleKML = scan.nextLine();
		System.out.println("The text file's ,who will be converted to kml, name:");
		String kml = scan.nextLine();
		System.out.println("The text file's ,who will be converted to csv, name:");
		String csv = scan.nextLine();
		try {
			Section2.kml_to_csv(path +"\\"+csv+".txt", path + "\\"+WigleKML+".kml");
			Section3.csv_to_kml(path + "\\"+wigleCSV+".csv", path + "\\"+kml+".txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scan.close();

	}

}
