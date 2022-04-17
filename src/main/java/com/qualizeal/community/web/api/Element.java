package com.qualizeal.community.web.api;

import com.qualizeal.community.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public interface Element {
    String getName();
    By getIdentifier();
    void setIdentifier(String identifier);
    void click();
    void clear();
    void type(CharSequence... entry);
    void clearAndType(CharSequence... entry);
    String getAttribute(String attribute);
    String getText();
    boolean isDisplayed();
    boolean isEnabled();
    boolean isSelected();
    Dropdown select(Class<? extends Dropdown> clazz);
    <V> V waitUntil(Function<TestConfig, V> isTrue);
    WebElement element();
    Element updateIdentifier(String... args);
}
