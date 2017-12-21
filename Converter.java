package ConverterPackage;
import java.util.Scanner;

public class Converter {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		System.out.println("To convert a directory of csv files into one csv file enter 1.\nTo convert a csv file to kml enter2.");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		if(choice==1) {
			String dirPath ="C:\\Users\\Ali Masarweh\\Desktop\\data";
			String csvPath = "C:\\Users\\Ali Masarweh\\Desktop";
			CSVToKML_DirToCSV.convert_csvDir_to_csv(dirPath, csvPath+"\\");
		}
		else {
			String csvPath ="C:\\Users\\Ali Masarweh\\Desktop\\FilteredCSV.csv";
			String kmlPath = "C:\\Users\\Ali Masarweh\\Desktop\\";
			CSVToKML_DirToCSV.convert_csv_to_kml(csvPath, kmlPath);
		}
		scan.close();

	}

}
