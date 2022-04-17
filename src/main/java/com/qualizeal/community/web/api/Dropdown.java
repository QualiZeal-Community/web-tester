package com.qualizeal.community.web.api;

import java.util.List;

public interface Dropdown {
    void byText(String text);
    void byIndex(int index);
    List<String> getOptions();
}
