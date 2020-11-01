package com.example.Ttunes.DTO;

import java.util.ArrayList;
import java.util.List;

public class Juke {

    private Juke() {

    }

    private String id;
    private String model;
    private List<Component> components = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }


    public static class Builder{
        private String id;
        private String model;
        private List<Component> components = new ArrayList<>();

        public Builder() {;
        }
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder model(String model){
            this.model = model;
            return this;
        }
        public Builder components(List<Component> components){
            this.components = components;
            return this;
        }

        public Juke build(){
            Juke juke = new Juke();
            juke.id = this.id;
            juke.model= this.model;
            juke.components = this.components;
            return juke;
        }
    }
}