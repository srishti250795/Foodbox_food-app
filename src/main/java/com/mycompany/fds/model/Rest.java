package com.mycompany.fds.model;

public class Rest {
    private String nom;
    private int id ;
    private String images;
    private String Description;

    public Rest(String nom, int id, String images, String description) {
        this.nom = nom;
        this.id = id;
        this.images = images;
        Description = description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public String getImages() {
        return images;
    }

    public String getDescription() {
        return Description;
    }
}
