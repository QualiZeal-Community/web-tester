package com.qualizeal.community.web.components;

import com.qualizeal.community.web.api.Browser;
import com.qualizeal.community.config.TestConfig;
import com.qualizeal.community.web.constants.WindowSwitch;
import com.qualizeal.community.web.exceptions.WindowSwitchingException;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultBrowser implements Browser {

    @Getter
    private WebDriver driver;
    @Getter
    private TestConfig testConfig;
    private List<String> windowHandles;
    private WebDriverWait wait;

    public DefaultBrowser(TestConfig testConfig) {
        this.driver = testConfig.getDriver();
        this.testConfig = testConfig;
        windowHandles = new ArrayList<>();
    }

    @Override
    public void open(String url) {
        driver.get(url);
        if (Objects.nonNull(testConfig.getViewPort())) {
            driver.manage().window().setSize(testConfig.getViewPort().getDimensions());
        }
        if ((!testConfig.getPlatform().equals(Platform.IOS) || !testConfig.getPlatform().equals(Platform.ANDROID))
                && !windowHandles.contains(driver.getWindowHandle())) {
            windowHandles.add(driver.getWindowHandle());
        } else if (isMobilePlatform()) {
            windowHandles.add("DEFAULT_HANDLE_MOBILE");
        }
    }

    @Override
    public void switchToNextWindowOrTab() {
        switchToWindow(WindowSwitch.NEXT);
    }

    @Override
    public void switchToPreviousWindowOrTab() {
        switchToWindow(WindowSwitch.PREVIOUS);
    }

    public void switchToWindow(WindowSwitch windowSwitch) {
        driver.getWindowHandles().stream().forEach(h -> {
            if (!windowHandles.contains(h)) {
                windowHandles.add(h);
            }
        });
        int currentIndex = windowHandles.indexOf(driver.getWindowHandle());
        if (currentIndex == 0 || currentIndex < driver.getWindowHandles().size() - 1
                && windowSwitch.equals(WindowSwitch.NEXT)) {
            driver.switchTo().window(windowHandles.get(currentIndex + 1));
        } else if (currentIndex > 0 && windowSwitch.equals(WindowSwitch.PREVIOUS)) {
            driver.switchTo().window(windowHandles.get(currentIndex - 1));
        } else {
            throw new WindowSwitchingException();
        }
    }

    @Override
    public void newWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
        windowHandles.add(driver.getWindowHandle());
    }

    @Override
    public void newTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        windowHandles.add(driver.getWindowHandle());
    }

    @Override
    public void addCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    @Override
    public void deleteCookie(Cookie cookie) {
        driver.manage().deleteCookie(cookie);
    }

    @Override
    public <X> X takeScreenShot(OutputType<X> outputType) {
        return ((TakesScreenshot) driver).getScreenshotAs(outputType);
    }

    @Override
    public Cookie getCookieByName(String name) {
        return driver.manage().getCookieNamed(name);
    }

    @Override
    public void close() {
        if (!isMobilePlatform()) {
            windowHandles.remove(driver.getWindowHandle());
            driver.close();
        }
    }

    @Override
    public void closeAll() {
        if (!isMobilePlatform()) {
            driver.getWindowHandles().forEach(h -> {
                driver.switchTo().window(h);
                driver.close();
            });
        }
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Boolean waitUntil(ExpectedCondition<Boolean> browserCondition) {
        if (Objects.isNull(wait)) {
            return new WebDriverWait(driver, testConfig.getTimeOut()).until(browserCondition);
        } else {
            wait = new WebDriverWait(driver, testConfig.getTimeOut());
        }
        return wait.until(browserCondition);
    }

    private boolean isMobilePlatform() {
        return Objects.nonNull(testConfig.getPlatform()) && (testConfig.getPlatform().equals(Platform.IOS) ||
                testConfig.getPlatform().equals(Platform.ANDROID));
    }
}