package ua.com.alevel.controller;

import ua.com.alevel.entity.OsNames;

import java.io.File;

public class FindOsController {
    private final String osName = System.getProperty("os.name").toLowerCase();

    public boolean isWindows() {
        return (osName.indexOf("win") >= 0);
    }
    public boolean isMac() {
        return (osName.indexOf("mac") >= 0);
    }
    public boolean isLinux() {
        return (osName.indexOf("lin") >= 0);
    }
    public String getOsName() {
        return osName;
    }


}
