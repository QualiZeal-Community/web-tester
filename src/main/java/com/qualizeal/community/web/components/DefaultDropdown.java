package com.qualizeal.community.web.components;

import com.qualizeal.community.web.api.Dropdown;
import com.qualizeal.community.config.TestConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultDropdown implements Dropdown {

    TestConfig testConfig;
    Select select;

    public DefaultDropdown(TestConfig testConfig) {
        select = new Select(testConfig.getDriver().findElement(testConfig.getElement().getIdentifier()));
        this.testConfig = testConfig;
    }

    @Override
    public void byText(String text) {
        ((JavascriptExecutor) testConfig.getDriver()).executeScript("arguments[0].scrollIntoView(true);", select);
        select.selectByVisibleText(text);
    }

    @Override
    public void byIndex(int index) {
        select.selectByIndex(index);
    }

    @Override
    public List<String> getOptions() {
        return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
