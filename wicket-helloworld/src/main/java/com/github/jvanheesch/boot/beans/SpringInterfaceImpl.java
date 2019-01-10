package com.github.jvanheesch.boot.beans;

import org.springframework.stereotype.Service;

@Service
public class SpringInterfaceImpl implements SpringInterface {
    public SpringInterfaceImpl() {
        System.out.println("constructing SpringInterfaceImpl");
    }

    @Override
    public String getString() {
        return "Hello World!";
    }
}
