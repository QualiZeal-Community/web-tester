package com.qualizeal.community.web.api;

import org.openqa.selenium.Alert;

import java.util.List;

public interface Page <T extends Element>{
    Page<T> setContext(String context);
    T get(String name);
    List<T> getAll(String name);
    void switchToFrame(String name);
    void switchToDefaultContent();
    Alert switchToAlert();
}