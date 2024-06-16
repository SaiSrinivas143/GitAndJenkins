package businessComponents;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BooleanSupplier;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import framework.parent.DriverManager;

public class GoogleFlights extends DriverManager{
	
	@BeforeTest
	public void setup() {
		startBrowser();
	}
	
	@Test
	public void printPrice() {
		String dest="Bengaluru";
		String start="Hyderabad, Telangana";
//		System.out.println(prop.getProperty("ApplicationUrl"));
		driver.get(prop.getProperty("ApplicationUrl"));
		
//        driver.findElement(By.xpath("//input[@placeholder='Where from?']")).sendKeys(start);
//
//        driver.findElement(By.xpath("//*[@role='listbox']//*[@role='option']//div[contains(normalize-space(),'"+start+"')]")).click();

        driver.findElement(By.xpath("//input[@placeholder='Where to?']")).sendKeys(dest);

        driver.findElement(By.xpath("//*[@role='listbox']//*[@role='option']//div[contains(normalize-space(),'"+dest+"')]")).click();

        driver.findElement(By.xpath("//input[@placeholder='Departure']")).click();
        
        customUtils.hardWait(2);
        
        driver.findElement(By.xpath("//*[@role='grid']/div[1]//*[normalize-space()='22' and @role='button']")).click();

        driver.findElement(By.xpath("//*[@role='grid']/div[1]//*[normalize-space()='23' and @role='button']")).click();

        driver.findElement(By.xpath("//*[@role='grid']/../../../../../..//button[normalize-space()='Done']")).click();

        driver.findElement(By.xpath("//*[@role='search']//button[normalize-space()='Search']")).click();
        
        

        String price=driver.findElement(By.xpath("//*[normalize-space()='round trip']/..//div[@class='U3gSDe']//span")).getText();

        System.out.println(price);
	}

	@Test
	public void downloadFile() {
		
		driver.get("https://dlcdn.apache.org/maven/maven-3/3.9.7/binaries/apache-maven-3.9.7-bin.zip");
//		customUtils.hardWait(2);
//		new WebDriverWait(driver, 60).until(fileIsDownloaded(prop.getProperty("DownloadPath")));
		boolean flag=true;
		while(flag) {
			if(fileIsDownloaded(DownloadPath,".crdownload").getAsBoolean()==true){
				System.out.println("Downloading");
			}
			if(fileIsDownloaded(DownloadPath,".zip").getAsBoolean()==true){
				System.out.println("Downloaded Successfully.");
				flag=false;
			}
		}
	}
	
	
	@Test
	public void windowHandle() {
		driver.get(prop.getProperty("ApplicationUrl"));
		
	List<String> windows=new ArrayList<>(driver.getWindowHandles());
	System.out.println(windows.toString()+"---"+windows.size());
	
	Set<String> windowses=driver.getWindowHandles();
	
	
	}
		
		
	private static BooleanSupplier fileIsDownloaded(String downloadDirectory, String extension) {
        return () -> {
            try {
         Optional<Path> file= Files.list(Paths.get(DownloadPath)).sorted((p1,p2)->Long.valueOf(p2.toFile().lastModified())
					   .compareTo(p1.toFile().lastModified())).findFirst();
         
				 if (file.get().getFileName().toString().endsWith(extension)) {
				     return true;
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            return false;
        };
    }
	


	
	@AfterTest
	public void teardown() {
		driver.quit();
		System.gc();
	}

}
