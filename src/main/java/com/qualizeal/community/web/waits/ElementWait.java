package com.qualizeal.community.web.waits;

import com.qualizeal.community.config.TestConfig;
import org.openqa.selenium.support.ui.FluentWait;

public class ElementWait extends FluentWait<TestConfig> {
    public ElementWait(TestConfig input) {
        super(input);
    }
}
