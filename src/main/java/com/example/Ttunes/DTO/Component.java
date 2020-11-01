package com.example.Ttunes.DTO;

public class Component {

    private Component(){

    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder{
        private String name;

        public Builder() {;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Component build(){
            Component Component = new Component();
            Component.name = this.name;
            return Component;
        }
    }
}