package framework.parent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import framework.utility.CustomTestUtils;
import framework.utility.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	
	public static WebDriver driver=null;
	public static CustomTestUtils customUtils=new CustomTestUtils();
	public static Properties prop=new Properties();
	public static final String UserHome=System.getProperty("user.home");
	public static String DownloadPath="";
	
	public static void startBrowser() {
		loadProperties();
		String browser=prop.getProperty("BrowserName");
		DownloadPath=UserHome+prop.getProperty("DownloadPath");
		Map<String,Object> prefs=new HashedMap<String, Object>();
		if(!new File(DownloadPath).exists()) {
			new File(DownloadPath).mkdir();
		}
		prefs.put("download.default_directory", DownloadPath);
		prefs.put("download.prompt_for_download", false);
		prefs.put("profile.default_content_settings.popups", 0);
		try {
			if(browser.equalsIgnoreCase("Chrome")) {
				
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();
//			    options.addArguments("--disable-popup-blocking");
//			    options.addArguments("--disable-extensions");
//			    options.addArguments("--safebrowsing-disable-download-protection");
//			    options.addArguments("--no-sandbox");
//			    options.addArguments("--test-type");
//			    options.addArguments("--disable-notifications");
//			    options.addArguments("--disable-infobars");
				options.addArguments("--start-maximized");
			    options.setExperimentalOption("prefs",prefs);

			    driver = new ChromeDriver(options);
			    driver.manage().deleteAllCookies();
			    
			}else if(browser.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();

			}
			
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver= new EventFiringWebDriver(driver).register(new WebEventListener());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@BeforeClass
	private static void loadProperties() {
		
		try {
//			prop=new Properties();
			FileInputStream ip=new FileInputStream(new File("../Interview/src/test/resources/GlobalSettingProperties.properties"));
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
