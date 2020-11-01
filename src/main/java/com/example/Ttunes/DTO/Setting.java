package com.example.Ttunes.DTO;

import java.util.ArrayList;
import java.util.List;

public class Setting {

    private Setting() {

    }

    private String id;
    private List<String> requires = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getRequires() {
        return requires;
    }

    public void setRequires(List<String> requires) {
        this.requires = requires;
    }


    public static class Builder{
        private String id;
        private List<String> requires = new ArrayList<>();

        public Builder() {;
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder requires(List<String> requires){
            this.requires = requires;
            return this;
        }

        public Setting build(){
            Setting setting = new Setting();
            setting.id = this.id;
            setting.requires= this.requires;
            return setting;
        }
    }


}