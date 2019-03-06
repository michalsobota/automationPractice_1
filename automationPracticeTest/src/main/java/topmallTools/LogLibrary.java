package topmallTools;

import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.PatternLayout;
import java.io.IOException;
import org.apache.log4j.FileAppender;

public class LogLibrary {

//Initialize Log4j logs

	 private static Logger Log = Logger.getLogger(LogLibrary.class.getName());//
	 	 

// This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
	 
	 public static void setUpLogging() throws IOException{
		
		//Define log pattern layout
		  PatternLayout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p [%c{1}] %m %n");
		 
		//Define file appender with layout and output log file name
		  FileAppender appender = new FileAppender(layout,"C://Users//Michał Sobota//Git//parallelTests//logs//log.log",false);  
	      Log.addAppender(appender);
	 
	 }
	 

public static void startTestCase(String sTestCaseName){

	Log.info("****************************************************************************************");

	Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");

	Log.info("****************************************************************************************");

	}

	//This is to print log for the ending of the test case

public static void endTestCase(String sTestCaseName){

	Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");

	Log.info("X");

	Log.info("X");

	}

	// Need to create these methods, so that they can be called  

public static void info(String message) {

		Log.info(message);

		}

public static void warn(String message) {

   Log.warn(message);

	}

public static void error(String message) {

   Log.error(message);

	}

public static void fatal(String message) {

   Log.fatal(message);

	}

public static void debug(String message) {

   Log.debug(message);

	}

}