package com.qualizeal.community.web.components;

import com.qualizeal.community.web.api.Dropdown;
import com.qualizeal.community.web.api.Element;
import com.qualizeal.community.config.TestConfig;
import com.qualizeal.community.web.exceptions.DropdownInstanceCreationException;
import com.qualizeal.community.web.waits.ElementWait;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Objects;
import java.util.function.Function;

public class DefaultElement implements Element {

    private TestConfig testConfig;

    @Setter
    private WebElement element;
    private ElementWait wait;

    @Getter
    @Setter
    private String name;
    @Setter
    private String identifier;

    public DefaultElement(TestConfig testConfig, String name) {
        this.testConfig = testConfig;
        this.name = name;
    }

    @Override
    public By getIdentifier() {
        return this.identifier.startsWith("//") ? By.xpath(this.identifier) : By.cssSelector(this.identifier);
    }

    @Override
    public void click() {
        element().click();
    }

    @Override
    public void clear() {
        element().clear();
    }

    @Override
    public void type(CharSequence... entry) {
        element().sendKeys(entry);
    }

    @Override
    public void clearAndType(CharSequence... entry) {
        clear();
        type(entry);
    }

    @Override
    public String getAttribute(String attribute) {
        return element().getAttribute(attribute);
    }

    @Override
    public String getText() {
        return element().getText();
    }

    @Override
    public boolean isDisplayed() {
        return element().isDisplayed();
    }

    @Override
    public boolean isEnabled() {
        return element().isEnabled();
    }

    @Override
    public boolean isSelected() {
        return element().isSelected();
    }

    @Override
    public Dropdown select(Class<? extends Dropdown> clazz) {
        Dropdown dropdown = null;
        testConfig.setElement(this);
        try {
            dropdown = clazz.getDeclaredConstructor(TestConfig.class).newInstance(testConfig);
        } catch (Exception e) {
            throw new DropdownInstanceCreationException();
        }
        return dropdown;
    }

    @Override
    public <V> V waitUntil(Function<TestConfig, V> isTrue) {
        testConfig.setElement(this);
        if (Objects.isNull(wait)) {
            wait = new ElementWait(testConfig);
            wait.withTimeout(testConfig.getTimeOut());
        }
        return wait.until(isTrue);
    }

    public WebElement element() {
        if (Objects.isNull(element)) {
            element = testConfig.getDriver().findElement(getIdentifier());
        }
        try {
            ((JavascriptExecutor) testConfig.getDriver())
                    .executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            // DO NOTHING
        }
        return element;
    }

    @Override
    public Element updateIdentifier(String... args) {
        setIdentifier(String.format(this.identifier, args));
        return this;
    }
}
