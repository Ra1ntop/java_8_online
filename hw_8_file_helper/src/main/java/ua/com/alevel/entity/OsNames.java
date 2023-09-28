package ua.com.alevel.entity;

public enum OsNames {

    MacOs("Mac OS X"),
    LinuxOs("Linux"),
    WindowsOs("Windows");

    private final String osName;

    OsNames(String osName) {
        this.osName = osName;
    }

    public String getOsName() {
        return osName;
    }
}
