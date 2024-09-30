package com.eod.sitree.project.domain.model.type;

public enum PlatformType {

    IOS, ANDROID, WINDOWS, MAC_OS, WEB
    ;

    public boolean isIos() {
        return this.equals(IOS);
    }

    public boolean isAndroid() {
        return this.equals(ANDROID);
    }

    public boolean  isWindows() {
        return this.equals(WINDOWS);
    }

    public boolean isMacOs() {
        return this.equals(MAC_OS);
    }

    public boolean isWeb() {
        return this.equals(WEB);
    }
}
