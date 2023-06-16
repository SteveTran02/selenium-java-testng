package Automatic_Template;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.Time;
import java.time.Duration;
import java.util.Random;
 
 
public class VerifyFileDownload {
    public static String GECKODRIVER_PATH = "D:\\Software Testing\\Automation Testing\\2 - Selenium WebDriver\\selenium-java-testng\\browserDrivers\\geckodriver.exe";
    public static Random r = new Random();
    
    @Test
    public void TC() {
 
 
        System.setProperty("webdriver.gecko.driver", GECKODRIVER_PATH);
        WebDriver driver = new FirefoxDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
 
        //get the firefox browser & get the file downloaded
        String siteLink = "https://sourceforge.net/projects/idmwithcrack/";
        driver.get(siteLink);
 
        //Get Download Button
        WebElement downloadButton = driver.findElement(By.cssSelector("a.button.download.big-text.green"));
        downloadButton.click();
 
 
        //wait , until file started downloading  : 7-9 sec
        System.out.println("wait, until file started downloading");
        threadWait(7, 9);
 
        //open the downloads section of firefox
        driver.get("about:downloads");
 
        //get the most recent file name
        String fileName = (String) js.executeScript("return document.querySelector('#contentAreaDownloadsView .downloadMainArea .downloadContainer description:nth-of-type(1)').value");
 
        //wait, expecting file to be downloaded , after 15-20 sec :
        System.out.println("wait, expecting file to be downloaded.");
        threadWait(15, 20);
 
        //verify from Downloads Directory is this file exists
        String FILES_DIRECTORY = "D:\\Software Testing\\Automation Testing\\2 - Selenium WebDriver\\selenium-java-testng\\downloads";
        File Folder = new File(FILES_DIRECTORY);
        File[] allFiles = new File(Folder.getPath()).listFiles();
        for (File file : allFiles) {
            String eachFile = file.getName();
            if (eachFile.contains(fileName))
                System.out.println("--Verified: File : " + fileName + " Has Been Download.");
            else continue;
        }
 
    }
 
    public  void threadWait(int low, int high) {
        int num = getRandomNumber(low, high);
        System.out.println(num + " sec Thread Wait : Started");
        try {
            Thread.sleep(1000 * num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(num + " sec Thread Wait : Ended");
 
    }
 
    public  int getRandomNumber(int low, int high) {
        int num = r.nextInt(high - low) + low;
        return num;
    }
}