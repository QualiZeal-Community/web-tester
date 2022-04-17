package com.qualizeal.community.web.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.qualizeal.community.config.TestConfig;
import com.qualizeal.community.web.constants.WindowSwitch;
import com.qualizeal.community.web.exceptions.WindowSwitchingException;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

class DefaultBrowserTest {
    @Test
    void testConstructor() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(null);
        assertNull((new DefaultBrowser(testConfig)).getDriver());
        verify(testConfig).getDriver();
    }

    @Test
    void testConstructor2() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new WindowSwitchingException());
        assertThrows(WindowSwitchingException.class, () -> new DefaultBrowser(testConfig));
        verify(testConfig).getDriver();
    }

    @Test
    void testSwitchToNextWindowOrTab() {
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.getWindowHandle()).thenReturn("Window Handle");
        when(chromeDriver.getWindowHandles()).thenReturn(new HashSet<>());
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        assertThrows(WindowSwitchingException.class, () -> (new DefaultBrowser(testConfig)).switchToNextWindowOrTab());
        verify(testConfig).getDriver();
        verify(chromeDriver).getWindowHandle();
        verify(chromeDriver, atLeast(1)).getWindowHandles();
    }

    @Test
    void testSwitchToNextWindowOrTab2() {
        HashSet<String> stringSet = new HashSet<>();
        stringSet.add("Unable to switch to window");
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.switchTo()).thenThrow(new WindowSwitchingException());
        when(chromeDriver.getWindowHandle()).thenReturn("Window Handle");
        when(chromeDriver.getWindowHandles()).thenReturn(stringSet);
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        assertThrows(WindowSwitchingException.class, () -> (new DefaultBrowser(testConfig)).switchToNextWindowOrTab());
        verify(testConfig).getDriver();
        verify(chromeDriver).getWindowHandle();
        verify(chromeDriver, atLeast(1)).getWindowHandles();
        verify(chromeDriver).switchTo();
    }

    @Test
    void testSwitchToPreviousWindowOrTab() {
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.getWindowHandle()).thenReturn("Window Handle");
        when(chromeDriver.getWindowHandles()).thenReturn(new HashSet<>());
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        assertThrows(WindowSwitchingException.class, () -> (new DefaultBrowser(testConfig)).switchToPreviousWindowOrTab());
        verify(testConfig).getDriver();
        verify(chromeDriver).getWindowHandle();
        verify(chromeDriver, atLeast(1)).getWindowHandles();
    }

    @Test
    void testSwitchToPreviousWindowOrTab2() {
        HashSet<String> stringSet = new HashSet<>();
        stringSet.add("Unable to switch to window");
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.getWindowHandle()).thenReturn("Window Handle");
        when(chromeDriver.getWindowHandles()).thenReturn(stringSet);
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        assertThrows(WindowSwitchingException.class, () -> (new DefaultBrowser(testConfig)).switchToPreviousWindowOrTab());
        verify(testConfig).getDriver();
        verify(chromeDriver).getWindowHandle();
        verify(chromeDriver, atLeast(1)).getWindowHandles();
    }

    @Test
    void testSwitchToPreviousWindowOrTab3() {
        HashSet<String> stringSet = new HashSet<>();
        stringSet.add("foo");
        stringSet.add("Unable to switch to window");
        stringSet.add("Unable to switch to window");
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.getWindowHandle()).thenReturn("Window Handle");
        when(chromeDriver.getWindowHandles()).thenReturn(stringSet);
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        assertThrows(WindowSwitchingException.class, () -> (new DefaultBrowser(testConfig)).switchToPreviousWindowOrTab());
        verify(testConfig).getDriver();
        verify(chromeDriver).getWindowHandle();
        verify(chromeDriver, atLeast(1)).getWindowHandles();
    }

    @Test
    void testSwitchToWindow() {
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.getWindowHandle()).thenReturn("Window Handle");
        when(chromeDriver.getWindowHandles()).thenReturn(new HashSet<>());
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        assertThrows(WindowSwitchingException.class,
                () -> (new DefaultBrowser(testConfig)).switchToWindow(WindowSwitch.NEXT));
        verify(testConfig).getDriver();
        verify(chromeDriver).getWindowHandle();
        verify(chromeDriver, atLeast(1)).getWindowHandles();
    }

    @Test
    void testSwitchToWindow2() {
        HashSet<String> stringSet = new HashSet<>();
        stringSet.add("Unable to switch to window");
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.switchTo()).thenThrow(new WindowSwitchingException());
        when(chromeDriver.getWindowHandle()).thenReturn("Window Handle");
        when(chromeDriver.getWindowHandles()).thenReturn(stringSet);
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        assertThrows(WindowSwitchingException.class,
                () -> (new DefaultBrowser(testConfig)).switchToWindow(WindowSwitch.NEXT));
        verify(testConfig).getDriver();
        verify(chromeDriver).getWindowHandle();
        verify(chromeDriver, atLeast(1)).getWindowHandles();
        verify(chromeDriver).switchTo();
    }

    @Test
    void testTakeScreenShot() throws WebDriverException {
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.getScreenshotAs((OutputType<Object>) any())).thenReturn("Screenshot As");
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        assertEquals("Screenshot As", (new DefaultBrowser(testConfig)).takeScreenShot(null));
        verify(testConfig).getDriver();
        verify(chromeDriver).getScreenshotAs((OutputType<Object>) any());
    }

    @Test
    void testClose() {
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.getWindowHandle()).thenReturn("Window Handle");
        doNothing().when(chromeDriver).close();
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getPlatform()).thenReturn(Platform.WINDOWS);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        (new DefaultBrowser(testConfig)).close();
        verify(testConfig, atLeast(1)).getPlatform();
        verify(testConfig).getDriver();
        verify(chromeDriver).getWindowHandle();
        verify(chromeDriver).close();
    }

    @Test
    void testCloseAll() {
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.getWindowHandles()).thenReturn(new HashSet<>());
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getPlatform()).thenReturn(Platform.WINDOWS);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        (new DefaultBrowser(testConfig)).closeAll();
        verify(testConfig, atLeast(1)).getPlatform();
        verify(testConfig).getDriver();
        verify(chromeDriver).getWindowHandles();
    }

    @Test
    void testCloseAll2() {
        HashSet<String> stringSet = new HashSet<>();
        stringSet.add("foo");
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.switchTo()).thenThrow(new WindowSwitchingException());
        when(chromeDriver.getWindowHandles()).thenReturn(stringSet);
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getPlatform()).thenReturn(Platform.WINDOWS);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        assertThrows(WindowSwitchingException.class, () -> (new DefaultBrowser(testConfig)).closeAll());
        verify(testConfig, atLeast(1)).getPlatform();
        verify(testConfig).getDriver();
        verify(chromeDriver).getWindowHandles();
        verify(chromeDriver).switchTo();
    }

    @Test
    void testQuit() {
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        doNothing().when(chromeDriver).quit();
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(chromeDriver);
        (new DefaultBrowser(testConfig)).quit();
        verify(testConfig).getDriver();
        verify(chromeDriver).quit();
    }
}

