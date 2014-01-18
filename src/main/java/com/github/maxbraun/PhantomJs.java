package com.github.maxbraun;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;
import java.net.URL;

/**
 * A little PhantomJS Service Wrapper
 */
public class PhantomJs {

    public static PhantomJs create(PhantomJSDriver driver) {
        PhantomJs phantomJs;

        phantomJs = new PhantomJs();

        phantomJs.driver = driver;

        return phantomJs;
    }

    public static PhantomJs create() {
        return create(new PhantomJSDriver());
    }

    private PhantomJSDriver driver;

    public void changeBrowserDimensions(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public String executeScript(String script, String... args){
        return String.valueOf(driver.executePhantomJS(script, args));
    }

    public File takeScreenshot(URL url) {
        driver.get(url.toString());
        return driver.getScreenshotAs(OutputType.FILE);
    }
}
