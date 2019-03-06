package topmallTools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

public class topmallTools {
	
	public static int maxTimeWaitUntilElementPresent = 30;

	public static void captureScreenShot(WebDriver ldriver, String className){
		 
		  // Take screenshot and store as a file format
		 // File src= ((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE); //sekwencyjnie
		 ldriver = new Augmenter().augment(ldriver);						//rownolegle
		 File src= ((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE);
			try {
				  // now copy the  screenshot to desired location using copyFile method 
				 FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/seleniumScreens/"+className+"_"+System.currentTimeMillis()+".png"));
			} 
			catch (IOException e){			
				System.out.println(e.getMessage());
			}
			 
	}
	
	public static void AtuReportConfiguration(String filePathToUserDir){
		
		String filePathToUserDirAtuReports=filePathToUserDir+"\\ATU Reports";
		DeleteFilesUnderUserDir(filePathToUserDirAtuReports);
		
		String filePathToUserDirScreens = filePathToUserDir+"\\seleniumScreens";
		DeleteFilesUnderUserDir(filePathToUserDirScreens);
		
		setFilePathAtuReporter(filePathToUserDir);
		System.out.println("AtuReportConfiguration is completed");
		LogLibrary.info("AtuReportConfiguration is completed");
	}
	
	public static void DeleteFilesUnderUserDir(String filePathToUserDir){
		
		//Delete all old files under filePathToUserDir
		try {			
			//Delete all old files under filePathToUserDir
			for(int i=0; i<2;i++){//check twice if there are no 2 old dirr
				FileUtils.forceDelete(new File(filePathToUserDir));
				System.out.println("Method AtuReportConfiguration deleted all old files under path: "+ filePathToUserDir);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Method DeleteFilesUnderUserDir there is no file to delete");
			LogLibrary.info("Method DeleteFilesUnderUserDir there is no file to delete");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//LogLibrary.error("Method DeleteFilesUnderUserDir Stack trace:\n\n", e);
		}
	}
	
		public static void setFilePathAtuReporter(String filePathToUserDir) {
		      String oldFileName = "atu.properties";
		      String tmpFileName = "tmp_atu.properties";
		      String pathToATUConfigFile = filePathToUserDir + "\\lib\\ATUReporter\\";
				String search = "atu.reports.dir=";
				String replace = filePathToUserDir + "\\ATU Reports";
				replace = replace.replace("\\", "/");

		      BufferedReader br = null;
		      BufferedWriter bw = null;
		      try {
		         br = new BufferedReader(new FileReader(pathToATUConfigFile+oldFileName));
		         bw = new BufferedWriter(new FileWriter(pathToATUConfigFile+tmpFileName));
		         String line;
		         while ((line = br.readLine()) != null) {
		            if (line.contains(search))
		               line = search+replace;
		            bw.write(line+"\n");
		         }
		      } catch (Exception e) {
		         return;
		      } finally {
		         try {
		            if(br != null)
		               br.close();
		         } catch (IOException e) {
		            //
		         }
		         try {
		            if(bw != null)
		               bw.close();
		         } catch (IOException e) {
		            //
		         }
		      }
		      // Once everything is complete, delete old file..
		      File oldFile = new File(pathToATUConfigFile+oldFileName);
		      oldFile.delete();

		      // And rename tmp file's name to old file name
		      File newFile = new File(pathToATUConfigFile+tmpFileName);
		      newFile.renameTo(oldFile);
		   }

}