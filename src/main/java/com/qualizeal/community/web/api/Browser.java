package com.qualizeal.community.web.api;

import com.qualizeal.community.config.TestConfig;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedCondition;

public interface Browser {
    TestConfig getTestConfig();
    void open(String url);
    void switchToNextWindowOrTab();
    void switchToPreviousWindowOrTab();
    void newWindow();
    void newTab();
    void addCookie(Cookie cookie);
    void deleteCookie(Cookie cookie);
    <X> X takeScreenShot(OutputType<X> outputType);
    Cookie getCookieByName(String name);
    void close();
    void closeAll();
    void quit();
    Boolean waitUntil(ExpectedCondition<Boolean> browserCondition);
}
