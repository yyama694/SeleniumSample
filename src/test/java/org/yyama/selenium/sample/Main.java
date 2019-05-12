package org.yyama.selenium.sample;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
	
	private static final String DIR ="C:\\Users\\yyama\\Desktop\\tmp\\screenshot";
	
	public static void main(String[] args) throws Exception {
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
		sleep(2000);
		screenshot(driver);
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("Page title is: " + driver.getTitle());
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });
		sleep(2000);
		screenshot(driver);

		driver.findElement(By.className("LC20lb")).click();
		
        System.out.println("Page title is: " + driver.getTitle());

		sleep(2000);
		screenshot(driver);
        
        sleep(5000);

        driver.quit();
        
	}
	
	private static void sleep(long l) {
        try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
		}
	}
	
	private static void screenshot(WebDriver driver) throws Exception {
		File sfile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile( sfile, new File(DIR+"\\" + sfile.getName()));
	}
}
