package com.bridgelabz.pom.base;

import com.bridgelabz.pom.util.TestUtil;
import com.bridgelabz.pom.util.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

        public static WebDriver driver;            //Global variables - to be used in all classes
        public static Properties prop;
        public static EventFiringWebDriver e_driver;
        public static WebEventListener eventListener;

        public TestBase(){                  //Constructor, Same Name as Class - to read properties
            try{
                prop = new Properties();
                FileInputStream ip = new FileInputStream("C:\\Users\\Prakash Udikeri\\IdeaProjects\\CFP - Automation Testing\\POM\\src\\main\\java\\com\\bridgelabz\\pom\\config\\config.properties");
                prop.load(ip);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public static void initialization(){
            String browserName = prop.getProperty("browser");
            if (browserName.equals("chrome")){
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Prakash Udikeri\\IdeaProjects\\" +
                        "CFP - Automation Testing\\WebDrivers\\chromedriver.exe");
                driver = new ChromeDriver();
            }else if (browserName.equals("edge")){
                System.setProperty("webdriver.edge.driver",  "C:\\Users\\Prakash Udikeri\\IdeaProjects\\" +
                        "CFP - Automation Testing\\WebDrivers\\msedgedriver.exe");
                driver = new EdgeDriver();
            }

            e_driver = new EventFiringWebDriver(driver);
            // Now create object of EventListerHandler to register it with EventFiringWebDriver
            eventListener = new WebEventListener();
            e_driver.register(eventListener);
            driver = e_driver;

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICT_WAIT, TimeUnit.SECONDS);

            driver.get(prop.getProperty("url"));
        }
    }


