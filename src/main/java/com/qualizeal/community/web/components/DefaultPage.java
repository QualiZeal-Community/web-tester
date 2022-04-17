package com.qualizeal.community.web.components;

import com.qualizeal.community.web.api.Browser;
import com.qualizeal.community.web.api.Element;
import com.qualizeal.community.web.api.Page;
import com.qualizeal.community.layouts.Layout;
import com.qualizeal.community.config.TestConfig;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DefaultPage implements Page<Element> {

    private TestConfig testConfig;
    private Layout layout;
    private static final String DEFAULT_SECTION = "Default";

    public DefaultPage(Browser browser, Layout layout) {
        this.testConfig = browser.getTestConfig();
        this.layout = layout;
        this.layout.setSection(DEFAULT_SECTION);
    }

    @Override
    public Page<Element> setContext(String context) {
        testConfig.setContext(context);
        layout.setSection(context);
        return this;
    }

    @Override
    public Element get(String name) {
        DefaultElement element = new DefaultElement(testConfig, name);
        element.setIdentifier(this.layout.get(name).get(testConfig.getTestType()));
        return element;
    }

    @Override
    public List<Element> getAll(String name) {
        List<WebElement> elements = testConfig.getDriver().findElements(get(name).getIdentifier());
        return IntStream.range(0, elements.size()).mapToObj(i -> {
            DefaultElement element = new DefaultElement(testConfig, String.format("%s_%s", name, i));
            element.setElement(elements.get(i));
            element.setIdentifier("auto-generated");
            return element;
        }).collect(Collectors.toList());
    }

    @Override
    public void switchToFrame(String nameOrId) {
        testConfig.getDriver().switchTo().frame(nameOrId);
    }

    @Override
    public void switchToDefaultContent() {
        testConfig.getDriver().switchTo().defaultContent();
    }

    @Override
    public Alert switchToAlert() {
        return testConfig.getDriver().switchTo().alert();
    }
}
