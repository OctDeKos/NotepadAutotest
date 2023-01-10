package org.example;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class App
{

//    First, we are defining the windows driver instance which is we are going to use,
//    to automate the actions on the desktop app.
    private static WindowsDriver notepadApp = null;
//Before we do whatever actions we want to do on the desktop app, we need to configure the windows driver.
// Since we have to do it beforehand, we do the configuring part with “@BeforeClass” TestNG annotation in the setup() method.
// So within this method, we create desired capabilities and define the platform & device name.
//
//Then we can initiate the windows driver by passing the capabilities we created and the URL that the windows driver is listening to.
// By default, it listens to “http://127.0.0.1:4723”.
    @BeforeClass
    public static void setup()
    {
        try
        {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
            caps.setCapability("platformName", "Windows");
            caps.setCapability("deviceName", "WindowsPC");
            notepadApp = new WindowsDriver(new URL("http://127.0.0.1:4723"), caps);
            notepadApp.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void tearDown()
    {
        notepadApp.quit();
    }

    @Test
    public void setTextOnNotepad()
    {
        String message = "Welcome to desktop app automation";
        notepadApp.findElementByClassName("RichEditD2DPT").click();
        notepadApp.findElementByClassName("RichEditD2DPT").sendKeys(message);
        Assert.assertEquals((notepadApp.findElementByClassName("RichEditD2DPT").getText()), message);
        notepadApp.findElementByClassName("RichEditD2DPT").clear();
    }

//    @Test
//    public void checkAboutWindow() {
//        notepadApp.findElementByName("Help").click();
//        notepadApp.findElementByName("About Notepad").click();
//        notepadApp.findElementByName("OK").click();
//    }
}