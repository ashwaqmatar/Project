package ConverterPackage;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;



public class createFile {
	public static void createKMLFileWithListofData(ArrayList<Router> listOfRouters,String filePath,String fileName,KMLBuilder kmlBuilder) {
		kmlBuilder = new KMLBuilder();
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(filePath+fileName+".kml"), "utf-8"))) {
			writer.write(kmlBuilder.Create());
			for(int indx = 0;indx<listOfRouters.size();indx++) {
				Router router = listOfRouters.get(indx);
				String data = kmlBuilder.addInfo(router);
				writer.write(data);
			}
			writer.write(kmlBuilder.Finish());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createCSVFileWithListofData(ArrayList<Router> listOfRouters,String filePath,String fileName) {
		CSVBuilder csvBuilder= new CSVBuilder();
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(filePath+fileName+".csv"), "utf-8"))) {
			writer.write(csvBuilder.Create());
			boolean coorWritten = false;
			String data = "";int count=1;
			for(int indx = 0;indx<listOfRouters.size();indx++) {
				Router router = listOfRouters.get(indx);
				if(router.getFirstSeen().equals("")&&router.getMAC().equals("")&&router.getSSID().equals("")) {
					coorWritten = false;
					writer.write(data+"\n");
					//System.out.println();
					count=0;
					data = "";
				}
				else if(count>=10) {
					coorWritten = false;
					writer.write(data+"\n");
					count=0;
					data = "";
					while(!router.getFirstSeen().equals("")||!router.getMAC().equals("")||!router.getSSID().equals("") && indx<listOfRouters.size()) {
						router = listOfRouters.get(indx);
						indx++;
					}
				}
				else {
					data = csvBuilder.addInfo(router)+data;
					count++;
					if(!coorWritten) {
						//System.out.print(router.getLatitude()+","+router.getLongitude()+","+router.getAltitudeMeters()+",#Wifinetworks,");
						writer.write(csvBuilder.addInfo(router.getLatitude()+","+router.getLongitude()+","+router.getAltitudeMeters()));
						coorWritten = true;
					}
					//System.out.print(data);
				}
			}
			writer.write(csvBuilder.Finish());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
