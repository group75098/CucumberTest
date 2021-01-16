package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.util.Strings;
import utilities.Config;

public class Hook extends Config {
    static String url;
    static String baseURL = System.getProperty("env");
    static String browserType = System.getProperty("browser");
    // default browser and env
    static String defaultBrowser = "ch";
    static String defaultEnv = "qa";

    @Before
    public void openBrowser(){
        if (Strings.isNullOrEmpty(browserType)){
            browserType = defaultBrowser;
        }
        if (Strings.isNullOrEmpty(baseURL)){
            baseURL = defaultEnv;
        }
        driver = initDriver (browserType);
        switch (baseURL){
            case "qa":
                url = "http://qa.taltektc.com";
                break;
            case "stage":
                url = "http://stage.taltektc.com";
                break;
            case "prod":
                url = "http://taltektc.com";
                break;
        }
        driver.get(url);
    }


    @After
    public void tearDown(Scenario scenario){
        try{
            if (scenario.isFailed()){
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
                System.out.println(scenario.getName());
            }
        } finally {
            driver.quit();
        }
    }


}
