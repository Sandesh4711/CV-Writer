package com.example.myapplication05.ResumePackage.Activity.ModelResume;

import java.io.Serializable;

public class ModelResumeHome implements Serializable {
    String heading;
    int backgroundimages,icons;
    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setBackgroundimages(int backgroundimages) {
        this.backgroundimages = backgroundimages;
    }

    public void setIcons(int icons) {
        this.icons = icons;
    }

    public int getBackgroundimages() {
        return backgroundimages;
    }

    public int getIcons() {
        return icons;
    }

    public String getHeading() {
        return heading;
    }
}
