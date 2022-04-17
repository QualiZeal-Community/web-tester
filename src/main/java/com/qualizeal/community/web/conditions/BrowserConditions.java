package com.qualizeal.community.web.conditions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BrowserConditions {

    private BrowserConditions() {
        //Utility class
    }

    public static ExpectedCondition<Boolean> titleIs(final String title) {
        return ExpectedConditions.titleIs(title);
    }

    public static ExpectedCondition<Boolean> titleContains(final String title) {
        return ExpectedConditions.titleContains(title);
    }

    public static ExpectedCondition<Boolean> urlToBe(final String url) {
        return ExpectedConditions.urlToBe(url);
    }

    public static ExpectedCondition<Boolean> urlContains(final String fraction) {
        return ExpectedConditions.urlContains(fraction);
    }

    public static ExpectedCondition<Boolean> urlMatches(final String regex) {
        return ExpectedConditions.urlMatches(regex);
    }

    public static ExpectedCondition<Alert> alertIsPresent() {
        return ExpectedConditions.alertIsPresent();
    }

    public static ExpectedCondition<Boolean> numberOfWindowsToBe(final int expectedNumberOfWindows) {
        return ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows);
    }

}
