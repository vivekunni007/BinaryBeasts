package userDefinedLibraries;

/**
 * This class is defined in order to read from and write to excel.
 * 
 * @author BINARYBEASTS
 * @since 2020/11/27
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite {
	public static File src,exfilepath;
	public static FileInputStream fileip,fip;
	public static FileOutputStream fileop,fop;
	public static XSSFWorkbook workbook,workbookOutput;
	public static XSSFSheet sheet,sheetName,sheetEOS,sheetCollections,sheetGiftCard,sheetGiftCardOutput,sheetGiftCardValid,sheetChairs;
	public static String val1;
	public static int row;
	public static XSSFCell cell;
	public static XSSFRow Row;
	public static String searchInput;
	public static String amount;
	public static String monthAndYear;
	public static String date;
	public static String recipents_name;
	public static String customer_name;
	public static String recipents_email;
	public static String customer_email;
	public static String customer_phonenumber;
	public static String recipents_nameValid;
	public static String customer_nameValid;
	public static String recipents_emailValid;
	public static String customer_emailValid;
	public static String customer_phonenumberValid;
	public static int readExcel(){
		try {
			// Get the source excel file
			src = new File(System.getProperty("user.dir")
					+ "\\src\\test\\resources\\DataTable.xlsx");
			exfilepath=new File(System.getProperty("user.dir")
					+ "\\src\\test\\resources\\OutPut.xlsx");
			// Create FileInputStream object
			fileip = new FileInputStream(src);
			fip=new FileInputStream(exfilepath);
			workbook = new XSSFWorkbook(fileip);
			workbookOutput = new XSSFWorkbook(fip);
			sheet = workbook.getSheetAt(0);
			sheetGiftCard = workbook.getSheetAt(1);
			sheetGiftCardValid = workbook.getSheetAt(2);
			sheetName = workbookOutput.getSheetAt(0);
			sheetCollections=workbookOutput.getSheetAt(1);
			sheetChairs=workbookOutput.getSheetAt(2);
			sheetGiftCardOutput=workbookOutput.getSheetAt(3);
			sheetEOS=workbookOutput.getSheetAt(4);
			
			searchInput = (sheet.getRow(1).getCell(0)).getStringCellValue();
			
			for (int i = 1; i <= sheetGiftCard.getLastRowNum(); i++) {
				if (i == 1) {
					// Getting value from excel and Storing it to variables
					amount = String.valueOf(sheetGiftCard.getRow(i).getCell(0)
							.getNumericCellValue());
					
					monthAndYear = sheetGiftCard.getRow(i).getCell(1).getStringCellValue();
					
					date = String.valueOf((int)(sheetGiftCard.getRow(i).getCell(2).getNumericCellValue()));
					
					recipents_name = sheetGiftCard.getRow(i).getCell(3).getStringCellValue();
					
					customer_name = sheetGiftCard.getRow(i).getCell(4).getStringCellValue();
					
					recipents_email = sheetGiftCard.getRow(i).getCell(5).getStringCellValue();
					
					customer_email = sheetGiftCard.getRow(i).getCell(6).getStringCellValue();
					
					customer_phonenumber = String.valueOf(sheetGiftCard.getRow(i).getCell(7).getNumericCellValue());
					
					row = i;
					break;
				}
			}
			for (int i = 1; i <= sheetGiftCardValid.getLastRowNum(); i++) {
				if (i == 1) {
				// Getting value from excel and Storing it to variables
				recipents_nameValid = sheetGiftCardValid.getRow(i).getCell(3).getStringCellValue();
				
				customer_nameValid = sheetGiftCardValid.getRow(i).getCell(4).getStringCellValue();
				
				recipents_emailValid = sheetGiftCardValid.getRow(i).getCell(5).getStringCellValue();
				
				customer_emailValid = sheetGiftCardValid.getRow(i).getCell(6).getStringCellValue();
				
				customer_phonenumberValid =Integer.toString((int)(sheetGiftCardValid.getRow(i).getCell(7).getNumericCellValue()));
				
				row = i;
				break;
				}
			}
		} catch (FileNotFoundException e) {
			FailReport.reportFail(e.getMessage());
		} catch (IOException e) {
			FailReport.reportFail(e.getMessage());
		}

		return row;
	}

	// Write data into excel sheet
	public static void writeExcel() {
		try {
			fileip.close();
			fip.close();
			// Create an object of FileOutputStream class to create write data in excel file
			fileop = new FileOutputStream(exfilepath);
			// write data in the excel file
			workbookOutput.write(fileop);
			// close output stream
			fileop.close();
		} catch (FileNotFoundException e) {
			FailReport.reportFail(e.getMessage());
		} catch (IOException e) {
			FailReport.reportFail(e.getMessage());
		}
	}
	
}
