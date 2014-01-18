package com.github.maxbraun;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static junit.framework.Assert.assertNotNull;

/**
 * needs an installed PhantomJs -> -Dphantomjs.binary.path
 */
public class PhantomJsIT {
    PhantomJs phantomjs;


    @Before
    public void createInstance() throws Exception {
        phantomjs = PhantomJs.create();
    }

    @Test
    public void testExecuteScript() throws Exception {
        phantomjs.changeBrowserDimensions(1200,1200);
    }


    @Test
    public void testTakeScreenshot() throws Exception {
        File screenshot = phantomjs.takeScreenshot(new URL("http://google.com"));
        assertNotNull(screenshot);
    }
}
