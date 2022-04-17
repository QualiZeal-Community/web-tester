package com.qualizeal.community.web.exceptions;

public class DropdownInstanceCreationException extends RuntimeException {
    public DropdownInstanceCreationException() {
        super("Unable to create a dropdown instance");
    }
}
