package com.example.Ttunes.DTO;

import java.util.ArrayList;
import java.util.List;

public class Settings {

    private Settings() {

    }
    private List<Setting> settings = new ArrayList<>();

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public static class Builder{
        private List<Setting> settings;

        public Builder() {;
        }

        public Builder settings(List<Setting> settings){
            this.settings = settings;
            return this;
        }

        public Settings build(){
            Settings settings = new Settings();
            settings.settings = this.settings;
            return settings;
        }
    }

}
