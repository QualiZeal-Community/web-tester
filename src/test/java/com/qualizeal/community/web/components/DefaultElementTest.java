package com.qualizeal.community.web.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.qualizeal.community.config.TestConfig;
import com.qualizeal.community.web.api.Dropdown;
import com.qualizeal.community.web.api.Element;
import com.qualizeal.community.web.exceptions.DropdownInstanceCreationException;

import java.util.function.Function;

import org.apache.commons.lang3.text.StrBuilder;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;

class DefaultElementTest {
    @Test
    void testConstructor() {
        assertEquals("Name", (new DefaultElement(mock(TestConfig.class), "Name")).getName());
    }

    @Test
    void testGetIdentifier() {
        DefaultElement defaultElement = new DefaultElement(mock(TestConfig.class), "Name");
        defaultElement.setIdentifier("42");
        assertEquals("By.cssSelector: 42", defaultElement.getIdentifier().toString());
    }

    @Test
    void testGetIdentifier2() {
        DefaultElement defaultElement = new DefaultElement(mock(TestConfig.class), "Name");
        defaultElement.setIdentifier("//");
        assertEquals("By.xpath: //", defaultElement.getIdentifier().toString());
    }

    @Test
    void testClick() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        assertThrows(DropdownInstanceCreationException.class, () -> (new DefaultElement(testConfig, "Name")).click());
        verify(testConfig).getDriver();
    }

    @Test
    void testClick2() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        RemoteWebElement remoteWebElement = mock(RemoteWebElement.class);
        doNothing().when(remoteWebElement).click();

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        defaultElement.setElement(remoteWebElement);
        defaultElement.click();
        verify(testConfig).getDriver();
        verify(remoteWebElement).click();
        assertNull(defaultElement.getText());
    }

    @Test
    void testClear() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        assertThrows(DropdownInstanceCreationException.class, () -> (new DefaultElement(testConfig, "Name")).clear());
        verify(testConfig).getDriver();
    }

    @Test
    void testClear2() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        RemoteWebElement remoteWebElement = mock(RemoteWebElement.class);
        doNothing().when(remoteWebElement).clear();

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        defaultElement.setElement(remoteWebElement);
        defaultElement.clear();
        verify(testConfig).getDriver();
        verify(remoteWebElement).clear();
        assertNull(defaultElement.getText());
    }

    @Test
    void testType() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        assertThrows(DropdownInstanceCreationException.class, () -> defaultElement.type(new StrBuilder()));
        verify(testConfig).getDriver();
    }

    @Test
    void testClearAndType() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        assertThrows(DropdownInstanceCreationException.class, () -> defaultElement.clearAndType(new StrBuilder()));
        verify(testConfig).getDriver();
    }

    @Test
    void testGetAttribute() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        assertThrows(DropdownInstanceCreationException.class,
                () -> (new DefaultElement(testConfig, "Name")).getAttribute("Attribute"));
        verify(testConfig).getDriver();
    }

    @Test
    void testGetAttribute2() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        RemoteWebElement remoteWebElement = mock(RemoteWebElement.class);
        when(remoteWebElement.getAttribute((String) any())).thenReturn("Attribute");

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        defaultElement.setElement(remoteWebElement);
        assertEquals("Attribute", defaultElement.getAttribute("Attribute"));
        verify(testConfig).getDriver();
        verify(remoteWebElement).getAttribute((String) any());
        assertNull(defaultElement.getText());
    }

    @Test
    void testGetText() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        assertThrows(DropdownInstanceCreationException.class, () -> (new DefaultElement(testConfig, "Name")).getText());
        verify(testConfig).getDriver();
    }

    @Test
    void testGetText2() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        RemoteWebElement remoteWebElement = mock(RemoteWebElement.class);
        when(remoteWebElement.getText()).thenReturn("Text");

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        defaultElement.setElement(remoteWebElement);
        assertEquals("Text", defaultElement.getText());
        verify(testConfig).getDriver();
        verify(remoteWebElement).getText();
        assertFalse(defaultElement.isSelected());
    }

    @Test
    void testIsDisplayed() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        assertThrows(DropdownInstanceCreationException.class, () -> (new DefaultElement(testConfig, "Name")).isDisplayed());
        verify(testConfig).getDriver();
    }

    @Test
    void testIsDisplayed2() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        RemoteWebElement remoteWebElement = mock(RemoteWebElement.class);
        when(remoteWebElement.isDisplayed()).thenReturn(true);

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        defaultElement.setElement(remoteWebElement);
        assertTrue(defaultElement.isDisplayed());
        verify(testConfig).getDriver();
        verify(remoteWebElement).isDisplayed();
        assertNull(defaultElement.getText());
    }

    @Test
    void testIsDisplayed3() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        RemoteWebElement remoteWebElement = mock(RemoteWebElement.class);
        when(remoteWebElement.isDisplayed()).thenReturn(false);

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        defaultElement.setElement(remoteWebElement);
        assertFalse(defaultElement.isDisplayed());
        verify(testConfig).getDriver();
        verify(remoteWebElement).isDisplayed();
        assertNull(defaultElement.getText());
    }

    @Test
    void testIsEnabled() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        assertThrows(DropdownInstanceCreationException.class, () -> (new DefaultElement(testConfig, "Name")).isEnabled());
        verify(testConfig).getDriver();
    }

    @Test
    void testIsEnabled2() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        RemoteWebElement remoteWebElement = mock(RemoteWebElement.class);
        when(remoteWebElement.isEnabled()).thenReturn(true);

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        defaultElement.setElement(remoteWebElement);
        assertTrue(defaultElement.isEnabled());
        verify(testConfig).getDriver();
        verify(remoteWebElement).isEnabled();
        assertNull(defaultElement.getText());
    }

    @Test
    void testIsEnabled3() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        RemoteWebElement remoteWebElement = mock(RemoteWebElement.class);
        when(remoteWebElement.isEnabled()).thenReturn(false);

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        defaultElement.setElement(remoteWebElement);
        assertFalse(defaultElement.isEnabled());
        verify(testConfig).getDriver();
        verify(remoteWebElement).isEnabled();
        assertNull(defaultElement.getText());
    }

    @Test
    void testIsSelected() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        assertThrows(DropdownInstanceCreationException.class, () -> (new DefaultElement(testConfig, "Name")).isSelected());
        verify(testConfig).getDriver();
    }

    @Test
    void testIsSelected2() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        RemoteWebElement remoteWebElement = mock(RemoteWebElement.class);
        when(remoteWebElement.isSelected()).thenReturn(true);

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        defaultElement.setElement(remoteWebElement);
        assertTrue(defaultElement.isSelected());
        verify(testConfig).getDriver();
        verify(remoteWebElement).isSelected();
        assertNull(defaultElement.getText());
    }

    @Test
    void testIsSelected3() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        RemoteWebElement remoteWebElement = mock(RemoteWebElement.class);
        when(remoteWebElement.isSelected()).thenReturn(false);

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        defaultElement.setElement(remoteWebElement);
        assertFalse(defaultElement.isSelected());
        verify(testConfig).getDriver();
        verify(remoteWebElement).isSelected();
        assertNull(defaultElement.getText());
    }

    @Test
    void testSelect() {
        TestConfig testConfig = mock(TestConfig.class);
        doNothing().when(testConfig).setElement((com.qualizeal.community.web.api.Element) any());
        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        assertThrows(DropdownInstanceCreationException.class, () -> defaultElement.select(Dropdown.class));
        verify(testConfig).setElement((com.qualizeal.community.web.api.Element) any());
    }

    @Test
    void testSelect2() {
        TestConfig testConfig = mock(TestConfig.class);
        doThrow(new DropdownInstanceCreationException()).when(testConfig)
                .setElement((com.qualizeal.community.web.api.Element) any());
        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        assertThrows(DropdownInstanceCreationException.class, () -> defaultElement.select(Dropdown.class));
        verify(testConfig).setElement((com.qualizeal.community.web.api.Element) any());
    }

    @Test
    void testWaitUntil() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getTimeOut()).thenThrow(new DropdownInstanceCreationException());
        doThrow(new DropdownInstanceCreationException()).when(testConfig)
                .setElement((com.qualizeal.community.web.api.Element) any());
        assertThrows(DropdownInstanceCreationException.class,
                () -> (new DefaultElement(testConfig, "Name")).waitUntil((Function<TestConfig, Object>) mock(Function.class)));
        verify(testConfig).setElement((com.qualizeal.community.web.api.Element) any());
    }

    @Test
    void testElement() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());
        assertThrows(DropdownInstanceCreationException.class, () -> (new DefaultElement(testConfig, "Name")).element());
        verify(testConfig).getDriver();
    }

    @Test
    void testElement2() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenThrow(new DropdownInstanceCreationException());

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        RemoteWebElement remoteWebElement = new RemoteWebElement();
        defaultElement.setElement(remoteWebElement);
        assertSame(remoteWebElement, defaultElement.element());
        verify(testConfig).getDriver();
    }

    @Test
    void testElement3() {
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(null);

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        RemoteWebElement remoteWebElement = new RemoteWebElement();
        defaultElement.setElement(remoteWebElement);
        assertSame(remoteWebElement, defaultElement.element());
        verify(testConfig).getDriver();
    }

    @Test
    void testElement4() {
        ChromeDriver chromeDriver = mock(ChromeDriver.class);
        when(chromeDriver.executeScript((String) any(), (Object[]) any())).thenReturn("Execute Script");
        TestConfig testConfig = mock(TestConfig.class);
        when(testConfig.getDriver()).thenReturn(chromeDriver);

        DefaultElement defaultElement = new DefaultElement(testConfig, "Name");
        RemoteWebElement remoteWebElement = new RemoteWebElement();
        defaultElement.setElement(remoteWebElement);
        assertSame(remoteWebElement, defaultElement.element());
        verify(testConfig).getDriver();
        verify(chromeDriver).executeScript((String) any(), (Object[]) any());
    }

    @Test
    void testUpdateIdentifier() {
        DefaultElement defaultElement = new DefaultElement(mock(TestConfig.class), "Name");
        defaultElement.setIdentifier("42");
        Element actualUpdateIdentifierResult = defaultElement.updateIdentifier("Args");
        assertSame(defaultElement, actualUpdateIdentifierResult);
        assertTrue(actualUpdateIdentifierResult.getIdentifier() instanceof By.ByCssSelector);
    }
}

