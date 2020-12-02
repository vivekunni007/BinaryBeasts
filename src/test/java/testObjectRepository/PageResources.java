package testObjectRepository;

/**
 * This class is defined in order to read the input values and webelements present in urbanladder.com from JSON file.
 * 
 * @author BINARYBEASTS
 * @since 2020/11/27
 * 
 */

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageResources {
	
	public static String storageMouseHover;
	public static String getBookshelvesTab;
	public static String priceSelect;
	public static String pricemaxRange;
	public static String priceSlid;
	public static String storageTypeTab;
	public static String openType;
	public static String excludeOutStock;
	public static String popupClose;
	public static String itemSearch;
	public static String searchVal;
	public static WebElement element;
	public static WebDriver driver;

	public static WebElement getClosePopup(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("popup")));
		return element;
	}

	public static String getSearchItem(WebDriver driver) {
		itemSearch = Json_Read.readFromJson("item");
		return itemSearch;
	}

	public static WebElement getSearch(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("search")));
		return element;
	}

	public static WebElement getSubmit(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("Submit")));
		return element;
	}

	public static WebElement getPopupClose(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("popupclose")));
		return element;
	}

	public static WebElement getCategory(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("category")));
		return element;
	}

	public static WebElement getBookShelf(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("bookshelves")));
		return element;
	}

	public static WebElement getStorageMenu(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("Storage")));
		return element;
	}

	public static WebElement getBookShelves(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("Bookshelves")));
		return element;
	}

	public static WebElement getPriceSelect(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("Price")));
		return element;
	}

	public static WebElement getPriceChangeSlider(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("slider")));
		return element;
	}

	public static WebElement getPriceSlider(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("Priceslider")));
		return element;
	}

	public static WebElement getPriceRange(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("Maxrange")));
		return element;
	}

	public static WebElement getStorageType(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("Storagetype")));
		return element;
	}

	public static WebElement getOpenFilter(WebDriver driver) {
		element = driver.findElement(By.id(Json_Read.readFromJson("Openfilter")));
		return element;
	}

	public static WebElement getOpenType(WebDriver driver) {
		element = driver.findElement(By.id(Json_Read.readFromJson("open")));
		return element;
	}

	public static WebElement getExcludeStock(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("Excludeoutofstock")));
		return element;
	}

	public static WebElement getCollections(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("collections")));
		return element;

	}

	public static List<WebElement> getCollectionList(WebDriver driver) {
		List<WebElement> list = driver.findElements(By.xpath(Json_Read.readFromJson("beingAtHomeList")));
		return list;
	}
	public static WebElement getGiftCard(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("giftcard")));
		return element;

	}
	public static WebElement getGiftCardCategory(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("categoryGiftCard")));
		return element;

	}
	public static WebElement getGiftCardCustomize(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("amount")));
		return element;

	}
	public static WebElement getMonth(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("month")));
		return element;

	}
	public static WebElement getDate(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("date")));
		return element;

	}
	public static WebElement getNextPath(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("next")));
		return element;

	}
	public static WebElement getRecipientName(WebDriver driver) {
		element = driver.findElement(By.name(Json_Read.readFromJson("recipientsname")));
		return element;

	}
	public static WebElement getRecipientMail(WebDriver driver) {
		element = driver.findElement(By.name(Json_Read.readFromJson("recipientsemail")));
		return element;

	}
	public static WebElement getSendersName(WebDriver driver) {
		element = driver.findElement(By.name(Json_Read.readFromJson("yourname")));
		return element;

	}
	public static WebElement getSendersMail(WebDriver driver) {
		element = driver.findElement(By.name(Json_Read.readFromJson("youremail")));
		return element;

	}	
	public static WebElement getPhoneNum(WebDriver driver) {
		element = driver.findElement(By.name(Json_Read.readFromJson("phone")));
		return element;

	}		
	public static WebElement getConfirmDetails(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("confirm")));
		return element;

	}
	public static WebElement getProceedToPayment(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("proceedtopayment")));
		return element;

	}
	public static WebElement getInvalidMailId(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("customeremailinavalid")));
		return element;

	}
	public static WebElement getRecipentInvalidMailId(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("recipentsemailinvalid")));
		return element;

	}
	public static WebElement getInvalidPhone(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("phoneinvalid")));
		return element;

	}
	public static String getSearchItemChair(WebDriver driver) {
		searchVal = Json_Read.readFromJson("searchStudyChair");
		return searchVal;
	}
	public static WebElement getSortBy(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("sortBy")));
		return element;
	}
	public static WebElement getOption(WebDriver driver) {
		element = driver.findElement(By.xpath(Json_Read.readFromJson("option")));
		return element;
	}
	
	
}
