package com.eod.sitree.project.domain.model.type;

public enum ImageType {
    BACKGROUND, DETAIL,;

    public boolean isBackgroundImage() {
        return this.equals(BACKGROUND);
    }

    public boolean isDetailImage() {
        return this.equals(DETAIL);
    }
}
