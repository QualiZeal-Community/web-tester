package com.qualizeal.community.web.conditions;

import com.qualizeal.community.web.api.Element;
import com.qualizeal.community.web.api.ElementCondition;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class ElementConditions {

    private ElementConditions() {
        // Utility class
    }

    public static ElementCondition<Element> present() {
        return slayerConfig -> {
            try {
                slayerConfig.getElement().element();
                return slayerConfig.getElement();
            } catch (Exception e) {
                return null;
            }
        };
    }

    public static ElementCondition<Element> clickable() {
        return slayerConfig -> {
            try {
                WebElement element = slayerConfig.getDriver().findElement(slayerConfig.getElement().getIdentifier());
                WebElement visibleElement = element.isEnabled() ? element : null;
                if (Objects.nonNull(visibleElement)) return slayerConfig.getElement();
            } catch (Exception e) {
                // DO NOTHING
            }
            return null;
        };
    }

    public static ElementCondition<Element> visible() {
        return slayerConfig -> {
            try {
                WebElement element = slayerConfig.getDriver().findElement(slayerConfig.getElement().getIdentifier());
                WebElement visibleElement = element.isDisplayed() ? element : null;
                if (Objects.nonNull(visibleElement)) return slayerConfig.getElement();
            } catch (Exception e) {
                // DO NOTHING
            }
            return null;
        };
    }

    public static ElementCondition<Boolean> attributeMatches(String attribute, String regex) {
        return slayerConfig -> {
            try {
                WebElement element = slayerConfig.getDriver().findElement(slayerConfig.getElement().getIdentifier());
                return element.getAttribute(attribute).matches(regex);
            } catch (Exception e) {
                return false;
            }
        };
    }

    public static ElementCondition<Boolean> attributeIsPresent(String attribute) {
        return slayerConfig -> {
            try {
                WebElement element = slayerConfig.getDriver().findElement(slayerConfig.getElement().getIdentifier());
                return Objects.nonNull(element.getAttribute(attribute)) && !element.getAttribute(attribute).isEmpty();
            } catch (Exception e) {
                return false;
            }
        };
    }

    public static ElementCondition<Boolean> stale(String attribute) {
        return slayerConfig -> {
            try {
                WebElement element = slayerConfig.getDriver().findElement(slayerConfig.getElement().getIdentifier());
                element.isEnabled();
                return false;
            } catch (StaleElementReferenceException e) {
                return true;
            }
        };
    }

    public static ElementCondition<Boolean> textMatches(String regex) {
        return slayerConfig -> {
            try {
                WebElement element = slayerConfig.getDriver().findElement(slayerConfig.getElement().getIdentifier());
                return element.getText().matches(regex);
            } catch (Exception e) {
                return false;
            }
        };
    }

}
