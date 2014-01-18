package com.github.maxbraun;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;
import java.net.URL;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PhantomJsTest {
    PhantomJSDriver driver;
    PhantomJs phantomjs;

    public void prepareMock() throws Exception{
        driver = mock(PhantomJSDriver.class);
        File screenshot = new File(PhantomJsTest.class.getResource("/google.png").toURI());
        when(driver.executePhantomJS("window.performance", "")).thenReturn("ok");
        when(driver.getScreenshotAs(OutputType.FILE)).thenReturn(screenshot);

    }

    @Before
    public void createInstance() throws Exception {
        prepareMock();
        phantomjs = PhantomJs.create(driver);
    }

    @Test
    public void testExecuteScript() throws Exception {
        assertEquals("ok", phantomjs.executeScript("window.performance", ""));
        verify(driver).executePhantomJS("window.performance", "");

    }


    @Test
    public void testTakeScreenshot() throws Exception {
        File screenshot = phantomjs.takeScreenshot(new URL("http://google.com"));
        assertNotNull(screenshot);
        verify(driver).get("http://google.com");
        verify(driver).getScreenshotAs(OutputType.FILE);
    }
}
