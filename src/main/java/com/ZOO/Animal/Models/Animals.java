package com.ZOO.Animal.Models;


import java.util.UUID;

public class Animals {
    private String id;
    private String breed;
    private String name;
    private String dateB;
    private String dateR;

    public Animals(String breed, String name, String dateB, String dateR){
        this.id = UUID.randomUUID().toString();
        this.breed = breed;
        this.name = name;
        this.dateB = dateB;
        this.dateR = dateR;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed){
        this.breed = breed;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDateB() {
        return dateB;
    }
    public void setDateB(String dateB){
        this.dateB = dateB;
    }
    public String getDateR() {
        return dateR;
    }
    public void setDateR(String dateR){
        this.dateR = dateR;
    }

    @Override
    public String toString() {
        return "Animals{" +
                "id='" + id + '\'' +
                ", breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", dateB='" + dateB + '\'' +
                ", dateR='" + dateR + '\'' +
                '}';
    }
}


