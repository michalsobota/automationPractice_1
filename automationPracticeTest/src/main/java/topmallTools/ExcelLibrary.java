package topmallTools;

	import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	import topmallTools.LogLibrary;	

public class ExcelLibrary {
	
	 public static final String Path_TestData = System.getProperty("user.dir") +"//src//testData//";
			 
	 public static final String File_TestData = "TestData.xlsx";
	 
	 private static XSSFSheet ExcelWSheet;

 	 private static XSSFWorkbook ExcelWBook;

	 private static XSSFCell Cell;

	 private static XSSFRow Row;

			
	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

	public static void setExcelFile(String Path,String SheetName) throws Exception {

			try {

   			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			} catch (Exception e){

				LogLibrary.error("Exception in method");
				throw (e);
			}

	}

	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

    public static String getCellData(int RowNum, int ColNum) throws Exception{

			try{

  			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
  			String CellData = Cell.getStringCellValue();

  			return CellData;

  			}catch (Exception e){
  				LogLibrary.error("Exception in method");
  				throw (e);
  			}

    }
    
  //This method is to read the test data from the Excel and return number of parameters

    public static int getNumberOfParameters() throws Exception{

			try{
				//index of last row
	  			int indexOfLastRow = ExcelWSheet.getLastRowNum();
	  			return indexOfLastRow;
  			}catch (Exception e){
  				LogLibrary.error("Exception in method");
  				throw (e);
  			}
    }
    
 
    public static String getCellDataByParameterNameAndColumnName(String ParameterName, String ColumnName) throws Exception{

			try{
				int columnIndex = 0;
				int rowIndex = 0;
	  			String CellData = "";
	  			//index of last row
	  			int indexOfLastRow = ExcelWSheet.getLastRowNum();
	  			//index of last column
	  			int indexOfLastColumn = ExcelWSheet.getRow(0).getLastCellNum();
	  			
	  			for(int i=0;i<indexOfLastColumn;i++){
	  				Cell = ExcelWSheet.getRow(0).getCell(i);
		  			CellData = Cell.getStringCellValue();
	  				
		  			if(ColumnName.equals(CellData)){
		  				columnIndex = Cell.getColumnIndex();
		  				break;
		  			}else if(i==indexOfLastColumn-1){
		  				System.out.println("Brak kolumny o padanej nazwie: "+ ColumnName);
		  				LogLibrary.error("Brak kolumny o padanej nazwie: "+ ColumnName +"w metodzie");
		  			}		
	  			}
	  			
	  			for(int i=0;i<indexOfLastRow+1;i++){
	  				Cell = ExcelWSheet.getRow(i).getCell(0);
		  			CellData = Cell.getStringCellValue();
	  				
		  			if(ParameterName.equals(CellData)){
		  				rowIndex = Cell.getRowIndex();
		  				break;
		  			}else if(i==indexOfLastRow){
		  				System.out.println("Brak wiersza o padanej nazwie: "+ ParameterName);
		  				LogLibrary.error("Brak wiersza o padanej nazwie: "+ ParameterName +"w metodzie");
		  			}		
	  			}
	  			
	  			if(rowIndex==0 || columnIndex==0){
	  				System.out.println("Wartość komórki nie odnaleziona");
	  				LogLibrary.error("Wartość komórki nie odnaleziona metoda");
	  				return "";
	  			}else{
	  				Cell = ExcelWSheet.getRow(rowIndex).getCell(columnIndex);
		  			CellData = Cell.getStringCellValue();
		  			return CellData;
	  			}
	  			
  			}catch (Exception e){	
  				throw (e);
  			}

    }
    
    
	//This method is to write in the Excel cell, Row num and Col num are the parameters

	public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

			try{

  			Row  = ExcelWSheet.getRow(RowNum);

			Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);

			if (Cell == null) {

				Cell = Row.createCell(ColNum);

				Cell.setCellValue(Result);

				} else {

					Cell.setCellValue(Result);

				}

  // Constant variables Test Data path and Test Data file name

  				FileOutputStream fileOut = new FileOutputStream(Path_TestData + File_TestData);

  				ExcelWBook.write(fileOut);

  				fileOut.flush();

					fileOut.close();

				}catch(Exception e){

					throw (e);

			}

		}

}