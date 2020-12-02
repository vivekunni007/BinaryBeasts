
package testScenarios;

/**
 * This class is defined in order to implement the given test scenario
 * Separate methods are defined to execute at certain priorities
 * defined by execution flow of different test cases. 
 * 
 * @author BINARYBEASTS
 * @since 2020/11/27
 * 
 */


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testObjectRepository.PageResources;
import userDefinedLibraries.DriverSetup;
import userDefinedLibraries.ExcelReadWrite;
import userDefinedLibraries.ExtentReportManager;
import userDefinedLibraries.ScreenShot;

public class UrbanLadder {
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFCell cell, cellValue, cell1, cell2, cell3, cell4, cell5, cell6;
	public static int rowNum;
	public static String[] resultSet;
	public static String[] resultSet2;
	public static String errorInvalidMail;
	public static String invalidRecipentsMail;
	public static String errorInvalidPhone;
	public static int chromeItem;
	public static int operaItem;
	public static int item1;
	public static int item2;
	public static int rowNum1;
	public static int rowNum2;
	public static String browserType;
	public static List<WebElement> list;
	public static List<WebElement> resultList;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static WebDriver driver;
	public static Row row;
	Actions action;

	@Parameters("browser")
	@BeforeClass(groups={"Smoke Test one","Smoke Test two","Regression Test one","Regression Test two"})
	public void driverConfig(String browser) {
		report = ExtentReportManager.getReportInstance();
		driver = DriverSetup.driverInstantiate(browser);

	}

	// Searching bookshelves through search bar - including out of stock
	@Test(priority = 1,groups={"Smoke Test one","Smoke Test two","Regression Test one","Regression Test two"})
	void operationSearchBar() {

		try {
			Thread.sleep(7000);

			WebElement pop = PageResources.getClosePopup(driver);
			pop.click();
			logger = report.createTest("operationSearchBar");
			logger.log(Status.PASS, "Popup is being closed");
			WebElement search = PageResources.getSearch(driver);
			String item = PageResources.getSearchItem(driver);
			search.sendKeys(item);
			WebElement submit = PageResources.getSubmit(driver);
			submit.submit();
			logger = report.createTest("operationSearchBar");
			logger.log(Status.PASS, "Bookshelves searched");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To select bookshelves from category - including out of stock
	@Test(priority = 2,groups={"Smoke Test one","Smoke Test two","Regression Test one","Regression Test two"})
	void operationCategoryMenu() {

		try {

			logger = report.createTest("operationCategoryMenu");

			Actions action = new Actions(driver);
			WebElement category = PageResources.getCategory(driver);
			action.moveToElement(category).click().build().perform();

			WebElement bookshelf = PageResources.getBookShelf(driver);

			// status for extent report
			if (bookshelf.getText().equals("Bookshelves")) {
				// pass status
				logger.log(Status.PASS, "Category selected as Bookshelves is successful");

			} else {
				// fail status
				logger.log(Status.FAIL, "Category selected as Bookshelves failed");

			}

			bookshelf.click();

			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(PageResources.getPopupClose(driver)));
			PageResources.getPopupClose(driver).click();

			logger.log(Status.INFO, "Category drop down is selected as bookshelves");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To adjust the price slider to 15065
	@Test(priority = 3,groups={"Smoke Test one","Smoke Test two","Regression Test one","Regression Test two"})
	void operationPriceSelect() {

		try {

			Thread.sleep(1500);
			logger = report.createTest("operationPriceSelect");

			WebElement hover3 = PageResources.getPriceSelect(driver);

			Actions action = new Actions(driver);
			action.moveToElement(hover3).click().build().perform();
			WebElement slider = PageResources.getPriceChangeSlider(driver);

			Actions move = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Action action1 = (Action) move.dragAndDropBy(slider, -207, 0).build();
			action1.perform();

			WebElement exPrice = PageResources.getPriceRange(driver);

			// status for extent report
			if (exPrice.getText().substring(1).equals("15,065")) {
				// pass status
				logger.log(Status.PASS, "price selected maximum as 15065");

			} else {
				// fail status
				logger.log(Status.FAIL, "price selection has got failed");

			}

			logger.log(Status.INFO, "maximum price is choosen as 15065");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To select storage type as 'open' - including out of stock
	@Test(priority = 4,groups={"Smoke Test one","Smoke Test two","Regression Test one","Regression Test two"})
	void operationStorageSelect() {

		try {

			Thread.sleep(2000);
			logger = report.createTest("operationStorageSelect");
			
			WebElement hover2 = PageResources.getStorageType(driver);
			Actions action = new Actions(driver);
			action.moveToElement(hover2).pause(300).click().build().perform();
			WebElement w2 = PageResources.getOpenType(driver);
			w2.click();
			
			Thread.sleep(4000);
			logger.log(Status.INFO, "Storage type is choosen as open");
			// Taking screenshot of result with applied filters
			ScreenShot.screenShot(driver);
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Writing the results to excel - including out of stock
	@Parameters("browser")
	@Test(priority = 5,groups={"Smoke Test one","Smoke Test two","Regression Test one","Regression Test two"})
	public void print(String browser) {

		try {
			browserType = browser;
			ExcelReadWrite.readExcel();

			logger = report.createTest("print");
			logger.log(Status.INFO, "Initializing write to Excel...");

			if (DriverSetup.browsertype.equalsIgnoreCase("opera")) {

				int rowCount = 1;

				cellValue = ExcelReadWrite.sheetName.createRow(0).createCell(0);
				cellValue.setCellValue(browserType.toUpperCase());
				logger.log(Status.INFO, "Excel writing started");

				// Getting list of results from webpage
				for (int i = 1; i <= 3; i++) {

					WebElement bookshelf = driver.findElement(
							By.xpath("//*[@id=\"search-results\"]/div[3]/ul/li[" + i + "]/div//a/div[1]/span"));
					WebElement price = driver.findElement(
							By.xpath("//*[@id=\"search-results\"]/div[3]/ul/li[" + i + "]/div//a/div[2]/span"));

					logger.log(Status.INFO, "Bookshelf: " + bookshelf.getText() + "  Price: " + price.getText());
					row = ExcelReadWrite.sheetName.createRow(++rowCount);
					System.out.println("Bookshelf: " + bookshelf.getText() + "  Price: " + price.getText());
					// Writing the results from webpage to excel cells
					for (int c = 0; c < 2; c++) {

						if (c == 0)
							row.createCell(c).setCellValue(bookshelf.getText());
						if (c == 1) {
							row.createCell(c).setCellValue(price.getText());
						}

					}

				}

				ExcelReadWrite.writeExcel();

			} else if (browserType.equalsIgnoreCase("chrome")) {

				int rowCount = 5;

				cellValue = ExcelReadWrite.sheetName.createRow(rowCount).createCell(0);
				cellValue.setCellValue(browserType.toUpperCase());
				logger.log(Status.INFO, "Excel writing started");

				// Getting list of results from webpage
				for (int i = 1; i <= 3; i++) {

					WebElement bookshelf = driver.findElement(
							By.xpath("//*[@id=\"search-results\"]/div[3]/ul/li[" + i + "]/div//a/div[1]/span"));
					WebElement price = driver.findElement(
							By.xpath("//*[@id=\"search-results\"]/div[3]/ul/li[" + i + "]/div//a/div[2]/span"));

					System.out.println("Bookshelf: " + bookshelf.getText() + "  Price: " + price.getText());
					logger.log(Status.INFO, "Bookshelf: " + bookshelf.getText() + "  Price: " + price.getText());
					row = ExcelReadWrite.sheetName.createRow(++rowCount);

					// Writing the results from webpage to excel cells
					for (int c = 0; c < 2; c++) {
						if (c == 0)
							row.createCell(c).setCellValue(bookshelf.getText());
						if (c == 1) {
							row.createCell(c).setCellValue(price.getText());
						}

					}

				}

				ExcelReadWrite.writeExcel();
			}

			report.flush();
			Thread.sleep(7000);

			driver.navigate().to(DriverSetup.url);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Reading excel file to get the keyword for search - excluding out of
	// stock
	@Test(priority = 6,groups={"Smoke Test one","Smoke Test two"})
	public void testCaseReadExl() {

		try {

			logger = report.createTest("testCaseReadEx");
			logger.log(Status.INFO, "Reading excel file...");
			ExcelReadWrite.readExcel();
			logger.log(Status.PASS, "Excel verified");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Search 'bookshelves' through searchbar - excluding out of stock
	@Test(priority = 7,groups={"Smoke Test one","Smoke Test two"})
	void operationSearch() throws Exception {

		try {

			Thread.sleep(7000);
			logger = report.createTest("operationSearch");

			logger.log(Status.INFO, "Waiting to get Bookshelves keyword");

			WebElement sea = PageResources.getSearch(driver);
			System.out.println(ExcelReadWrite.searchInput);
			sea.clear();
			sea.sendKeys(ExcelReadWrite.searchInput);
			WebElement sub = PageResources.getSubmit(driver);
			sub.click();

			logger.log(Status.PASS, "Bookshelves searched");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To select 'bookshelves' from storage menu - excluding out of stock
	@Test(priority = 8,groups={"Smoke Test one","Smoke Test two"})
	void operationCategory() {

		try {
			
			logger = report.createTest("operationCategory");
			
			action = new Actions(driver);
			WebElement hover1 = PageResources.getStorageMenu(driver);
			action.moveToElement(hover1).click().build().perform();
			logger.log(Status.INFO, "Storage menu bar clicked");
			
			WebElement w1 = PageResources.getBookShelves(driver);
			w1.click();

			logger.log(Status.INFO, "Bookshelves page opened under living storage option of storage menu");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To adjust the price slider to '15065' as maximum price - excluding out of
	// stock
	@Test(priority = 9,groups={"Smoke Test one","Smoke Test two"})
	public void operationPrice() {

		try {

			Thread.sleep(5000);
			WebElement hover3 = PageResources.getPriceSelect(driver);
			action.moveToElement(hover3).click().build().perform();

			logger = report.createTest("operationPrice");
			logger.log(Status.INFO, "Price clicked");
			Thread.sleep(2000);

			WebElement slider = PageResources.getPriceSlider(driver);
			Actions move = new Actions(driver);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			Action action1 = (Action) move.dragAndDropBy(slider, -207, 0).build();
			action1.perform();

			WebElement exPrice = PageResources.getPriceRange(driver);

			// status foe Extent report
			if (exPrice.getText().substring(1).equals("15,065")) {
				// pass status
				logger.log(Status.PASS, "Price selected as maximum Rs. 15,065");
			} else {
				// fail status
				logger.log(Status.FAIL, "Price selection failed");
			}

			System.out.println("Price slider set to maximum Rs 15,065");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To select the storage type as 'open' - excluding out of stock
	@Test(priority = 10,groups={"Smoke Test one","Smoke Test two"})
	void operationStorage() {

		try {
			Thread.sleep(5000);

			logger = report.createTest("operationStorage");
			logger.log(Status.INFO, "Selecting storage type as open");

			WebElement hove2 = PageResources.getStorageType(driver);
			Actions action1 = new Actions(driver);
			action1.moveToElement(hove2).pause(300).click().build().perform();
			WebElement e2 = PageResources.getOpenType(driver);
			e2.click();

			logger.log(Status.INFO, "Storage type as open filter applied");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Selecting the 'exclude out of stock' option
	@Test(priority = 11,groups={"Smoke Test one","Smoke Test two"}) // includes regression test
	public void operationstock() {

		try {
			Thread.sleep(5000);
			WebElement stock = PageResources.getExcludeStock(driver);

			// status for extent report
			logger = report.createTest("operationStock");
			logger.log(Status.INFO, "Selecting Exclude out of stock");

			if (stock.getText().equals("Exclude Out Of Stock")) {
				// pass status
				logger.log(Status.PASS, "Exclude out of stock selection successful");
			} else {
				// fail status
				logger.log(Status.FAIL, "Exclude out of stock selection failed");
			}

			// selecting the desired option
			stock.click();

			logger.log(Status.INFO, "Exclude Out of stock box is selected");
			Thread.sleep(3000);
			// Taking screenshot of result with applied filters
			ScreenShot.screenShot(driver);
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Writing the results to excel - excluding out of stock
	@Parameters("browser")
	@Test(priority = 12,groups={"Smoke Test one","Smoke Test two"})
	public static void toExcelWrite(String browser) {

		try {

			ExcelReadWrite.readExcel();

			browserType = browser;
			logger = report.createTest("toExcelWrite");
			logger.log(Status.INFO, "Initializing write to Excel...");

			if (browserType.equalsIgnoreCase("chrome")) {

				int rowCount = 1;

				cellValue = ExcelReadWrite.sheetEOS.createRow(0).createCell(0);
				cellValue.setCellValue(browserType.toUpperCase());
				logger.log(Status.INFO, "Excel writing started");

				// Getting list of results from webpage
				for (int i = 1; i <= 3; i++) {

					WebElement bookshelf = driver
							.findElement(By.xpath("//*[@id='content']/div[4]/ul/li[" + i + "]/div/div[3]"));
					WebElement price = driver.findElement(
							By.xpath("//*[@id='content']/div[4]/ul/li[" + i + "]/div/div[3]/a/div[2]/span"));
					
					logger.log(Status.INFO, "Bookshelf" + bookshelf.getText() + "  price" + price.getText());
					row = ExcelReadWrite.sheetEOS.createRow(++rowCount);
					System.out.println("Bookshelf:" + bookshelf.getText() + "  Price: " + price.getText());
					// Writing the results from webpage to excel cells
					for (int c = 0; c < 2; c++) {

						if (c == 0)
							row.createCell(c).setCellValue(bookshelf.getText());
						if (c == 1) {
							row.createCell(c).setCellValue(price.getText());
						}

					}

				}

				ExcelReadWrite.writeExcel();

			} else if (browserType.equalsIgnoreCase("opera")) {

				int rowCount = 5;

				cellValue = ExcelReadWrite.sheetEOS.createRow(rowCount).createCell(0);
				cellValue.setCellValue(browserType.toUpperCase());

				// Getting list of results from webpage
				for (int i = 1; i <= 3; i++) {

					WebElement bookshelf = driver
							.findElement(By.xpath("//*[@id='content']/div[4]/ul/li[" + i + "]/div/div[3]"));
					WebElement price = driver.findElement(
							By.xpath("//*[@id='content']/div[4]/ul/li[" + i + "]/div/div[3]/a/div[2]/span"));

					row = ExcelReadWrite.sheetEOS.createRow(++rowCount);
					logger.log(Status.INFO, "Bookshelf: " + bookshelf.getText() + "  Price:" + price.getText());
					System.out.println("Bookshelf:" + bookshelf.getText() + "  Price: " + price.getText());
					// Writing the results from webpage to excel cells
					for (int c = 0; c < 2; c++) {

						if (c == 0)
							row.createCell(c).setCellValue(bookshelf.getText());
						if (c == 1) {
							row.createCell(c).setCellValue(price.getText());
						}

					}

				}

				ExcelReadWrite.writeExcel();
			}

			logger.log(Status.INFO, "Excel writing finished");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Writing Being At Home Collections to excel file
	@Parameters("browser")
	@Test(priority = 13,groups={"Smoke Test one","Smoke Test two"})
	public static void getBeingAtHomeCollections(String browser) {

		try {

			driver.navigate().to(DriverSetup.url);
			logger = report.createTest("getBeingAtHomeCollections");
			browserType = browser;

			if (driver.getTitle().equals(
					"Furniture Online: Buy Home Wooden Furniture Online In India At Best Price - Urban Ladder - Urban Ladder")) {
				// for pass status
				logger.log(Status.PASS, "Navigated to the home page of Urban Ladder successful");
			} else {
				// for fail status
				logger.log(Status.FAIL, "Navigated to the home page of Urban Ladder failed");
			}

			Actions action = new Actions(driver);
			// hover over the collections option
			WebElement collectionHover = PageResources.getCollections(driver);
			action.moveToElement(collectionHover).click().build().perform();
			// extracting collections items
			list = PageResources.getCollectionList(driver);
			logger.log(Status.INFO, "Items under Being At Home collections retrieved");
			Thread.sleep(2000);
			System.out.println("Total being at home collections :" + list.size());
			System.out.println("Items are: ");
			logger.log(Status.INFO, "13 Being at Home Collection items are displayed");
			resultSet = new String[list.size()];

			if (browserType.equalsIgnoreCase("chrome")) {

				chromeItem = 0;
				for (WebElement el : list) {

					resultSet[chromeItem] = el.getText();
					System.out.println(resultSet[chromeItem]);
					chromeItem += 1;

				}

				ExcelReadWrite.readExcel();
				for (rowNum1 = 1, item1 = 0; rowNum1 <= list.size(); rowNum1++, item1++) {

					cell = ExcelReadWrite.sheetCollections.createRow(rowNum1).createCell(0);
					cell.setCellValue(resultSet[item1]);
					logger.log(Status.INFO, "Excel write successfully done " + resultSet[item1]);
				}

				ExcelReadWrite.writeExcel();
				logger.log(Status.INFO, "Values written successfully");

			} else if (browserType.equalsIgnoreCase("opera")) {

				operaItem = 0;
				for (WebElement el : list) {

					resultSet[operaItem] = el.getText();
					System.out.println(resultSet[operaItem]);
					operaItem += 1;

				}

				ExcelReadWrite.readExcel();
				for (rowNum2 = 1, item2 = 0; rowNum2 <= list.size(); rowNum2++, item2++) {

					cell = ExcelReadWrite.sheetCollections.getRow(rowNum2).createCell(1);
					cell.setCellValue(resultSet[item2]);
					logger.log(Status.INFO,"Excel write successfully done " + resultSet[item2]);
				}

				ExcelReadWrite.writeExcel();
				logger.log(Status.INFO, "Values written successfully");
			}

			ScreenShot.screenShot(driver);
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Navigating gift card page
	@Test(priority = 14,groups={"Smoke Test one","Smoke Test two"})
	void testcase_clickGiftCards() {

		try {

			driver.navigate().to(DriverSetup.url);
			WebElement path = PageResources.getGiftCard(driver);
			path.click();

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,600)");
			Thread.sleep(2000);
			logger = report.createTest("Navigation to GiftCard page");

			if (driver.getTitle()
					.equals("Gift Card - Buy Gift Cards & Vouchers Online for Wedding, Birthday | Urban Ladder")) {
				// pass status
				logger.log(Status.PASS, "Navigated to Gift Cards page");
			} else {
				// fail status
				logger.log(Status.FAIL, "Navigation to Gift Cards failed failed");
			}
			logger.log(Status.INFO, "Navigated to gift cards page");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Selecting gift card type as 'Birthday/Anniversary'
	@Test(priority = 15,groups={"Smoke Test one","Smoke Test two"})
	public void testcase_selectGiftCards() {

		try {

			logger = report.createTest("testcase_selectGiftCards");
			WebElement pathCategory = PageResources.getGiftCardCategory(driver);
			pathCategory.click();

			Thread.sleep(1000);
			if (pathCategory.getText().contains("Gift something memorable to help them celebrate great memories!")) {
				logger.log(Status.PASS, "Occassion selected as birthday/anniversary");
			} else {
				logger.log(Status.FAIL, "Occassion not selected as birthday/anniversary");
			}

			logger.info("Occassion selected as Birthday/Anniversary");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Customising the gift card with setting price and certain date
	@Test(priority = 16,groups={"Smoke Test one","Smoke Test two"})
	public void testcase_customiseGiftCard() {

		try {

			logger = report.createTest("testcase_customiseGiftCards");
			WebElement pathCustomize = PageResources.getGiftCardCustomize(driver);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String amount = ExcelReadWrite.amount;
			pathCustomize.sendKeys(amount);

			// selecting the desired month and year to deliver the card
			WebElement monthPath = PageResources.getMonth(driver);
			Select month = new Select(monthPath);
			month.selectByVisibleText(ExcelReadWrite.monthAndYear);

			// selecting the desired date to deliver the card
			WebElement datePath = PageResources.getDate(driver);
			Select date = new Select(datePath);
			date.selectByVisibleText(ExcelReadWrite.date);
			
			// selecting the next option to proceed to the next step
			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebElement nextPath = PageResources.getNextPath(driver);
			//WebElement nxt = driver.findElement(By.xpath("//*[contains(text(),'Next')]"));
			nextPath.click();

			if (nextPath.getText().equals("NEXT")) {
				// pass status
				logger.log(Status.PASS, "Proceeded to the next step for recipient's and sender's details");
			} else {
				// fail status
				logger.log(Status.FAIL, "Not proceeded to the next step for recipient's and sender's details");
			}

			logger.info("Customied GiftCard");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Filling invalid details for customizing gift card section
	@Test(priority = 17,groups={"Smoke Test one","Smoke Test two"})
	public void testcase_FillDetails() {

		try {

			logger = report.createTest("testcase_FillDetails");
			System.out.println("Form details are: ");

			// filling the receipient's details
			// filling the form starting from recepient's name
			WebElement recipentsname = PageResources.getRecipientName(driver);
			recipentsname.sendKeys(ExcelReadWrite.recipents_name);

			// printing recepient's name
			String strName1 = recipentsname.getAttribute("value");
			System.out.println("Receipient name :" + strName1);

			// status for extent report
			if (strName1.equals(ExcelReadWrite.recipents_name)) {
				// pass status
				logger.log(Status.PASS, "Recipient's name entered");
			} else {
				// fail status
				logger.log(Status.FAIL, "Recipient's name not entered");
			}

			// filling recepient's email id
			WebElement recipentsemail = PageResources.getRecipientMail(driver);
			recipentsemail.sendKeys(ExcelReadWrite.recipents_email);

			// printing recepient's email id
			String strEmail1 = recipentsemail.getAttribute("value");
			System.out.println("Receipient's email :" + strEmail1);

			// status for extent report
			if (strEmail1.equals(ExcelReadWrite.recipents_email)) {// pass
																	// status
				logger.log(Status.PASS, "Recipient's email id entered");
			} else {// fail status
				logger.log(Status.FAIL, "Recipient's email id not entered");
			}

			// Sender's details
			// filling sender's name
			WebElement sendersname = PageResources.getSendersName(driver);
			sendersname.sendKeys(ExcelReadWrite.customer_name);

			// printing sender's name
			String strName2 = sendersname.getAttribute("value");
			System.out.println("Sender's name :" + strName2);

			// status for extent report
			if (strName2.equals(ExcelReadWrite.customer_name)) {
				// pass status
				logger.log(Status.PASS, "Sender's name entered");
			} else {
				// fail status
				logger.log(Status.FAIL, "Sender's name not entered");
			}

			// filling and printing sender's email id
			WebElement sendersemail = PageResources.getSendersMail(driver);
			sendersemail.sendKeys(ExcelReadWrite.customer_email);

			// printing sender's email id
			String strEmail2 = sendersemail.getAttribute("value");
			System.out.println("Sender's email :" + strEmail2);

			// status for extent report
			if (strEmail2.equals(ExcelReadWrite.customer_email)) {
				// pass status
				logger.log(Status.PASS, "Sender's email entered");
			} else {
				// fail status
				logger.log(Status.FAIL, "Sender's email not entered");
			}

			// filling sender's mobile number
			WebElement phone = PageResources.getPhoneNum(driver);
			phone.sendKeys(ExcelReadWrite.customer_phonenumber);

			// printing sender's mobile number
			String strmob = phone.getAttribute("value");
			System.out.println("Mobile number :" + strmob);

			// status for extent report
			if (strmob.equals(ExcelReadWrite.customer_phonenumber)) {// pass
																		// status
				logger.log(Status.PASS, "mobile number entered");
			} else {// fail status
				logger.log(Status.FAIL, "Mobile number not entered");
			}

			logger.info("Details of the form are filled");

			// screenshot of the the filled form
			ScreenShot.screenShot(driver);
			WebElement confirm = PageResources.getConfirmDetails(driver);
			confirm.click();

			// status for extent report
			if (confirm.getText().equals("CONFIRM")) {
				// pass status
				logger.log(Status.PASS, "Confirmation of form fill up successful");
			} else {
				// fail status
				logger.log(Status.FAIL, "Confirmation of form fill up not successful");
			}

			Thread.sleep(2000);
			WebElement proceedToPay = PageResources.getProceedToPayment(driver);
			proceedToPay.click();
			Thread.sleep(2000);

			// status for extent report
			if (proceedToPay.getText().equals("REDIRECTING TO PAYMENT GATEWAY")) {
				logger.log(Status.FAIL, "No Error: Payment Successful");
			} else {
				logger.log(Status.PASS, "Error: Payment Unsuccessful");
			}

			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Printing error message
	@Test(priority = 18,groups={"Smoke Test one","Smoke Test two"})
	public static void errorMessage() {

		try {

			logger = report.createTest("errorMessage");

			WebElement errorMessage_customeremail = PageResources.getInvalidMailId(driver);
			errorInvalidMail = errorMessage_customeremail.getText();

			WebElement errorMessage_recipentsemail = PageResources.getRecipentInvalidMailId(driver);
			invalidRecipentsMail = errorMessage_recipentsemail.getText();

			WebElement errormessage_Phone = PageResources.getInvalidPhone(driver);
			errorInvalidPhone = errormessage_Phone.getText();

			// status for extent report
			if (errorInvalidMail.equals("Customer email is invalid")) {// pass
																		// status
				logger.log(Status.PASS, "Error message captured");

			} else {// fail status
				logger.log(Status.FAIL, "No error message captured");
			}

			// printing the error message
			System.out.println("Error Message :" + errorInvalidMail);
			System.out.println("Error Message :" + invalidRecipentsMail);
			System.out.println("Error Message :" + errorInvalidPhone);

			// taking the screenshot of error message
			ScreenShot.screenShot(driver);
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Writing error messages to excel file
	@Parameters("browser")
	@Test(priority = 19,groups={"Smoke Test one","Smoke Test two"})
	public static void write_ErrorMessage(String browser) {

		try {

			browserType = browser;
			ExcelReadWrite.readExcel();
			logger = report.createTest("write_ErrorMessage");

			if (browserType.equalsIgnoreCase("chrome")) {

				cell2 = ExcelReadWrite.sheetGiftCardOutput.createRow(0).createCell(0);
				cell2.setCellValue(browserType.toUpperCase());
				for (int count = 1; count <= 4; count++) {

					cell1 = ExcelReadWrite.sheetGiftCardOutput.createRow(count).createCell(0);
					if (count == 1) {

						cell1.setCellValue(errorInvalidMail);

					} else if (count == 2) {

						cell1.setCellValue(invalidRecipentsMail);

					} else if (count == 3) {

						cell1.setCellValue(errorInvalidPhone);
					}
				}

				ExcelReadWrite.writeExcel();

			} else if (browserType.equalsIgnoreCase("opera")) {

				cell3 = ExcelReadWrite.sheetGiftCardOutput.createRow(5).createCell(0);
				cell3.setCellValue(browserType.toUpperCase());

				cell4 = ExcelReadWrite.sheetGiftCardOutput.createRow(6).createCell(0);
				cell5 = ExcelReadWrite.sheetGiftCardOutput.createRow(7).createCell(0);
				cell6 = ExcelReadWrite.sheetGiftCardOutput.createRow(8).createCell(0);

				cell4.setCellValue(errorInvalidMail);
				cell5.setCellValue(invalidRecipentsMail);
				cell6.setCellValue(errorInvalidPhone);

				ExcelReadWrite.writeExcel();
			}
			logger.log(Status.INFO, "Error message written successfully to excel");
			System.out.println("Error message written successfully to excel");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Taking screenshots of webpages at different instances
	@Test(priority = 20,groups={"Smoke Test one","Smoke Test two"})
	public static void ScreenShot() {

		try {

			logger = report.createTest("ScreenShot");
			// screenshot the the payment gateway
			ScreenShot.screenShot(driver);
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Reading valid data from excel file
	@Test(priority = 21,groups={"Smoke Test one","Smoke Test two"})
	public void testcase_readExcelDataValid() {

		try {

			logger = report.createTest("testcase_readExcelValid");
			ExcelReadWrite.readExcel();
			logger.log(Status.INFO, " Data read from excel successfully");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Filling valid form data to gift card section
	@Test(priority = 22,groups={"Smoke Test one","Smoke Test two"})
	public void testcase_FillValidDetails() {

		try {

			logger = report.createTest("testcase_FillValidDetails");

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-600)");
			System.out.println("Valid Form details are: ");

			// filling the receipient's details
			// filling the form starting from recepient's name
			WebElement recipentsname = PageResources.getRecipientName(driver);
			recipentsname.clear();
			recipentsname.sendKeys(ExcelReadWrite.recipents_nameValid);

			// printing recepient's name
			String strName1 = recipentsname.getAttribute("value");
			System.out.println("Receipient name :" + strName1);

			// status for extent report
			if (strName1.equals(ExcelReadWrite.recipents_nameValid)) {
				// pass status
				logger.log(Status.PASS, "Recipient's name entered");
			} else {
				// fail status
				logger.log(Status.FAIL, "Recipient's name not entered");
			}

			// filling recepient's email id
			WebElement recipentsemail = PageResources.getRecipientMail(driver);
			recipentsemail.clear();
			recipentsemail.sendKeys(ExcelReadWrite.recipents_emailValid);

			// printing recepient's email id
			String strEmail1 = recipentsemail.getAttribute("value");
			System.out.println("Receipient's email :" + strEmail1);

			// status for extent report
			if (strEmail1.equals(ExcelReadWrite.recipents_emailValid)) {
				// pass status
				logger.log(Status.PASS, "Recipient's email id entered");
			} else {
				// fail status
				logger.log(Status.FAIL, "Recipient's email id not entered");
			}

			// Sender's details
			// filling sender's name
			WebElement sendersname = PageResources.getSendersName(driver);
			sendersname.clear();
			sendersname.sendKeys(ExcelReadWrite.customer_nameValid);

			// printing sender's name
			String strName2 = sendersname.getAttribute("value");
			System.out.println("Sender's name :" + strName2);

			// status for extent report
			if (strName2.equals(ExcelReadWrite.customer_nameValid)) {
				// pass status
				logger.log(Status.PASS, "Sender's name entered");
			} else {
				// fail status
				logger.log(Status.FAIL, "Sender's name not entered");
			}

			// filling sender's email id
			WebElement sendersemail = PageResources.getSendersMail(driver);
			sendersemail.clear();
			sendersemail.sendKeys(ExcelReadWrite.customer_emailValid);

			// printing sender's email id
			String strEmail2 = sendersemail.getAttribute("value");
			System.out.println("Sender's email :" + strEmail2);

			// status for extent report
			if (strEmail2.equals(ExcelReadWrite.customer_emailValid)) {
				// pass status
				logger.log(Status.PASS, "Sender's email entered");
			} else {
				// fail status
				logger.log(Status.FAIL, "Sender's email not entered");
			}

			// filling sender's mobile number
			WebElement phone = PageResources.getPhoneNum(driver);
			phone.clear();
			phone.sendKeys(ExcelReadWrite.customer_phonenumberValid);

			// printing sender's mobile number
			String strmob = phone.getAttribute("value");
			System.out.println("Mobile number :" + strmob);

			// status for extent report
			if (strmob.equals(ExcelReadWrite.customer_phonenumberValid)) {
				// pass status
				logger.log(Status.PASS, "mobile number entered");
			} else {
				// fail status
				logger.log(Status.FAIL, "Mobile number not entered");
			}

			logger.info("Details of the form are filled");

			// taking screenshot of the filled form
			ScreenShot.screenShot(driver);

			WebElement confirm = PageResources.getConfirmDetails(driver);
			confirm.click();

			// status for extent report
			if (confirm.getText().equals("CONFIRM")) {
				// pass status
				logger.log(Status.PASS, "Confirmation of form fill up successful");
			} else {
				// fail status
				logger.log(Status.FAIL, "Confirmation of form fill up not successful");
			}
			
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Redirecting to payment page
	@Test(priority = 23,groups={"Smoke Test one","Smoke Test two"})
	public static void proceedToPayment() {
		try {

			logger = report.createTest("proceedToPayment");
			Thread.sleep(2000);
			WebElement proceedToPayment = PageResources.getProceedToPayment(driver);
			proceedToPayment.click();
			Thread.sleep(2000);

			// status for extent report
			if (proceedToPayment.getText().equals("REDIRECTING TO PAYMENT GATEWAY")) {
				logger.log(Status.PASS, "No Error: Payment Successful");
			} else {
				logger.log(Status.FAIL, "Error: Payment unsuccessful");
			}

			Thread.sleep(4000);
			// taking screenshot of the payment gateway page
			ScreenShot.screenShot(driver);
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Searching for 'study chairs'
	@Test(priority = 24,groups={"Smoke Test one","Smoke Test two","Regression Test one","Regression Test two"})
	public static void studyChairSearch() {

		try {

			driver.navigate().to(DriverSetup.url);
			logger = report.createTest("studyChair");

			if (driver.getTitle().equals(
					"Furniture Online: Buy Home Wooden Furniture Online In India At Best Price - Urban Ladder - Urban Ladder")) {
				// for pass status
				logger.log(Status.PASS, "Navigated to the home page of Urban Ladder successful");
			} else {
				// for fail status
				logger.log(Status.FAIL, "Navigated to the home page of Urban Ladder failed");
			}

			WebElement search = PageResources.getSearch(driver);
			search.clear();

			String searchVal = PageResources.getSearchItemChair(driver);
			search.sendKeys(searchVal);

			WebElement submit = PageResources.getSubmit(driver);
			submit.submit();

			logger = report.createTest("studyChairSearch");
			logger.log(Status.PASS, "Study Chair searched");
			// taking screenshot of the study chair results
			ScreenShot.screenShot(driver);
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Sorting study chairs based on 'Recommended'
	@Test(priority = 25,groups={"Smoke Test one","Smoke Test two","Regression Test one"})
	public static void operationSortBy() {

		try {
			logger = report.createTest("operationSortBy");
			WebElement hover = PageResources.getSortBy(driver);
			Actions action = new Actions(driver);
			action.moveToElement(hover).pause(300).click().build().perform();

			WebElement option = PageResources.getOption(driver);
			option.click();
			logger.log(Status.PASS, "Sort By option Recommended Clicked");
			report.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Writing study chairs results to excel file
	@Parameters("browser")
	@Test(priority = 26,groups={"Smoke Test one","Smoke Test two","Regression Test one"})
	public void StudyChairprint(String browser) {

		try {

			ExcelReadWrite.readExcel();

			browserType = browser;

			logger = report.createTest("StudyChairprint");
			logger.log(Status.INFO, "Initializing write to Excel...");

			if (browserType.equalsIgnoreCase("chrome")) {

				int rowCount = 1;
				cellValue = ExcelReadWrite.sheetChairs.createRow(0).createCell(0);
				cellValue.setCellValue(browserType.toUpperCase());
				logger.log(Status.INFO, "Excel writing started from chrome browser...");
				
				// Getting list of results from webpage
				for (int i = 1; i <= 3; i++) {

					WebElement studyChair = driver
							.findElement(By.xpath("//*[@id='search-results']/div[3]/ul/li[" + i + "]//a/div[1]/span"));

					WebElement price = driver.findElement(
							By.xpath("//*[@id=\"search-results\"]/div[3]/ul/li[" + i + "]//a/div[2]/span"));

					logger.log(Status.INFO, "Study Chair: " + studyChair.getText() + "  Price:" + price.getText());
					System.out.println("Study Chair: " + studyChair.getText() + "  Price: " + price.getText());
					row = ExcelReadWrite.sheetChairs.createRow(++rowCount);
					
					// Writing the results from webpage to excel cells
					for (int c = 0; c < 2; c++) {

						if (c == 0)
							row.createCell(c).setCellValue(studyChair.getText());
						if (c == 1) {
							row.createCell(c).setCellValue(price.getText());
						}

					}

				}

				ExcelReadWrite.writeExcel();

			} else if (browserType.equalsIgnoreCase("opera")) {

				int rowCount = 5;
				cellValue = ExcelReadWrite.sheetChairs.createRow(rowCount).createCell(0);
				cellValue.setCellValue(browserType.toUpperCase());
				logger.log(Status.INFO, "Excel writing started  from opera browser...");
				
				// Getting list of results from webpage
				for (int i = 1; i <= 3; i++) {

					WebElement studyChair = driver
							.findElement(By.xpath("//*[@id='search-results']/div[3]/ul/li[" + i + "]//a/div[1]/span"));

					WebElement price = driver.findElement(
							By.xpath("//*[@id=\"search-results\"]/div[3]/ul/li[" + i + "]//a/div[2]/span"));

					logger.log(Status.INFO, "Study Chair: " + studyChair.getText() + "  Price: " + price.getText());
					System.out.println("Study Chair: " + studyChair.getText() + "  Price: " + price.getText());
					row = ExcelReadWrite.sheetChairs.createRow(++rowCount);
					
					// Writing the results from webpage to excel cells
					for (int c = 0; c < 2; c++) {
						if (c == 0)
							row.createCell(c).setCellValue(studyChair.getText());
						if (c == 1) {
							row.createCell(c).setCellValue(price.getText());
						}

					}

				}

				ExcelReadWrite.writeExcel();
			}

			report.flush();
			Thread.sleep(7000);
			driver.navigate().to(DriverSetup.url);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Closing the browser
	@AfterClass(groups={"Smoke Test one","Smoke Test two","Regression Test one","Regression Test two"})
	public void driverExit() {

		try {

			DriverSetup.driverClose();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
