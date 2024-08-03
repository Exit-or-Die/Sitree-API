package com.eod.sitree.project.domain.model.type;

public enum ImageType {
    BACKGROUND, DETAIL, REPRESENT, ARCHITECTURE;

    public boolean isBackgroundImage() {
        return this.equals(BACKGROUND);
    }

    public boolean isDetailImage() {
        return this.equals(DETAIL);
    }
    public boolean isRepresentImage() {
        return this.equals(REPRESENT);
    }
}
