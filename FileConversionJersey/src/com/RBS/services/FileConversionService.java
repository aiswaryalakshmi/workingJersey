package com.RBS.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
//import java.io.FilenameFilter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Path("/csvToXlsx")
public class FileConversionService {
	
	 public void csvToXLSX(String csvFileAddress) {
		 try {
			 
			 System.out.println("csv File Path: "+csvFileAddress);
			 
			 //Change csv to xlsx
			 String xlsxFileAddress = csvFileAddress.replaceAll("csv", "xlsx");
			 
			 //Make dir if the output folder doesn't exists
			 File xlsxFilePath = new File(xlsxFileAddress);
			 File xlsxFolder = xlsxFilePath.getParentFile();
			 if(!xlsxFolder.exists())
				 xlsxFolder.mkdirs();
			 
		     //String xlsxFileAddress = "F:\\xlsxFolder\\"+csvFileAddress.substring(csvFileAddress.lastIndexOf('\\')+1, csvFileAddress.lastIndexOf('.'))+".xlsx"; //xlsx file address
			 System.out.println("xlsx File Path: "+xlsxFileAddress);
			 
			 //Convert csv data to xlsx data
			 XSSFWorkbook workBook = new XSSFWorkbook();
			 XSSFSheet sheet = workBook.createSheet("sheet1");
			 String currentLine=null;
			 int RowNum=0;
			 BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
			 while ((currentLine = br.readLine()) != null) {
				 String str[] = currentLine.split(",");
				 RowNum++;
				 XSSFRow currentRow=sheet.createRow(RowNum);
				 for(int i=0;i<str.length;i++){
					 currentRow.createCell(i).setCellValue(str[i]);
				 }
			 }
			 br.close();
			 
			 //Write converted data to xlsx file 
			 FileOutputStream fileOutputStream =  new FileOutputStream(xlsxFileAddress);
			 workBook.write(fileOutputStream);
			 fileOutputStream.close();
			 
			 System.out.println("Coversion: csv -------> xlsx Done");
			 
		 } catch (Exception ex) {
		        System.out.println(ex.getMessage()+"Exception in try");
		 }
	}
	 
	 public void convEachCsvFile(File folder){
		 
		 /*FilenameFilter csvFilter = new FilenameFilter() {
		        public boolean accept(File dir, String name) {
		            return name.toLowerCase().endsWith(".csv");
		        }
		    };*/
		 //File[] listOfFiles = folder.listFiles(csvFilter);
		 
		    File[] listOfFiles = folder.listFiles();
			System.out.println("Total no. of files & folders in the current directory: "+listOfFiles.length);
			
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  
		    	  System.out.println("File: " + listOfFiles[i].getName());
		    	  
		    	  //if the file is a .csv file
		    	  if(listOfFiles[i].getName().endsWith(".csv"))
		    		  csvToXLSX(folder+"\\"+listOfFiles[i].getName());
		    	  
		      } else if (listOfFiles[i].isDirectory()) {
			        System.out.println("Directory: " + listOfFiles[i].getName());
			        convEachCsvFile(new File(folder+"\\"+listOfFiles[i].getName()));
		      }
		    }
	 }
	 
	@GET
    @Path("convertAllFiles")
	public void convertAllFiles() {
		File folder = new File("F:\\csvFolder");
		convEachCsvFile(folder);
	}
}
