package com.qualizeal.community.config;

import com.qualizeal.community.web.api.Element;
import com.qualizeal.community.web.constants.ViewPort;
import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@Builder
@Data
public class TestConfig {
    private WebDriver driver;
    private Element element;
    private String context;
    private Platform platform;
    private String testType;
    private Duration timeOut;
    private ViewPort viewPort;
}
