package com.example.second;

public class App {
    private int aIcon;
    private String txtAName;

    public App() {}

    public App(int aIcon, String txtAName) {
        this.aIcon = aIcon;
        this.txtAName = txtAName;
    }

    public int getaIcon() {
        return aIcon;
    }

    public String getaName() {
        return txtAName;
    }

    public void setAIcon(int aIcon) {
        this.aIcon = aIcon;
    }

    public void setAName(String txtAName) {
        this.txtAName = txtAName;
    }
}
