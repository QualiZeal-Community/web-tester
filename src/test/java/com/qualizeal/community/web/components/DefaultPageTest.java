package com.qualizeal.community.web.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.qualizeal.community.config.TestConfig;
import com.qualizeal.community.layouts.Layout;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class DefaultPageTest {
    @Test
    void testConstructor() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(null);
        DefaultBrowser browser = new DefaultBrowser(testConfig);
        new DefaultPage(browser, new Layout());
    }

    @Test
    void testConstructor2() {
        DefaultBrowser defaultBrowser = mock(DefaultBrowser.class);
        when(defaultBrowser.getTestConfig()).thenReturn(mock(TestConfig.class));
        new DefaultPage(defaultBrowser, new Layout());
    }

    @Test
    void testConstructor3() {
        DefaultBrowser defaultBrowser = mock(DefaultBrowser.class);
        when(defaultBrowser.getTestConfig()).thenReturn(mock(TestConfig.class));
        Layout layout = mock(Layout.class);
        doNothing().when(layout).setSection((String) any());
        new DefaultPage(defaultBrowser, layout);

    }

    @Test
    void testSetContext() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(null);
        doNothing().when(testConfig).setContext((String) any());
        DefaultBrowser browser = new DefaultBrowser(testConfig);
        DefaultPage defaultPage = new DefaultPage(browser, new Layout());
        assertSame(defaultPage, defaultPage.setContext("Context"));
        verify(testConfig).getDriver();
        verify(testConfig).setContext((String) any());
    }

    @Test
    void testSetContext2() {
        TestConfig testConfig = mock(TestConfig.class);
        doNothing().when(testConfig).setContext((String) any());
        DefaultBrowser defaultBrowser = mock(DefaultBrowser.class);
        when(defaultBrowser.getTestConfig()).thenReturn(testConfig);
        Layout layout = mock(Layout.class);
        doNothing().when(layout).setSection((String) any());
        DefaultPage defaultPage = new DefaultPage(defaultBrowser, layout);
        assertSame(defaultPage, defaultPage.setContext("Context"));
        verify(defaultBrowser).getTestConfig();
        verify(testConfig).setContext((String) any());
        verify(layout, atLeast(1)).setSection((String) any());
    }

    @Test
    void testGet() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getTestType()).thenReturn("Test Type");
        when(testConfig.getDriver()).thenReturn(null);
        DefaultBrowser browser = new DefaultBrowser(testConfig);
        Layout layout = mock(Layout.class);
        when(layout.get((String) any())).thenReturn(new HashMap<>());
        doNothing().when(layout).setSection((String) any());
        assertEquals("Name", (new DefaultPage(browser, layout)).get("Name").getName());
        verify(testConfig).getTestType();
        verify(testConfig).getDriver();
        verify(layout).get((String) any());
        verify(layout).setSection((String) any());
    }
}

