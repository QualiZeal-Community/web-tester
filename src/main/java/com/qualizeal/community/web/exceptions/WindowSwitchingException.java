package com.qualizeal.community.web.exceptions;

public class WindowSwitchingException extends RuntimeException{
    public WindowSwitchingException() {
        super("Unable to switch to window");
    }
}
