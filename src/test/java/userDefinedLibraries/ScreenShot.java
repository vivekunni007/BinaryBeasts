package userDefinedLibraries;

/**
 * This class is defined in order to take screenshots of webpage at different instances.
 * 
 * @author BINARYBEASTS
 * @since 2020/11/27
 * 
 */

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	public static String screenShot(WebDriver ldriver){
		File sourceFile = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		
		File destFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots\\" + System.currentTimeMillis() + ".png");
		try { 
			//copy the screenshot to desired location using copyFile method
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return destFile.getPath();
	}
}
