package com.mycompany.fds.model;

public class Repas {
    private int idRepas;
    private String nomRepas;
    private float prix;
    private String restaurant;
    private int rank;
    private String description;
    private String img;
    
    public Repas(int idRepas, String nomRepas, float prix, String restaurant, int rank, String description, String img){
        this.idRepas = idRepas;
        this.nomRepas = nomRepas;
        this.prix = prix;
        this.restaurant = restaurant;
        this.rank = rank;
        this.description = description;
        this.img = img;
    }
    public Repas(String nomRepas, float prix, String restaurant, int rank, String description, String img){
        this.nomRepas = nomRepas;
        this.prix = prix;
        this.restaurant = restaurant;
        this.rank = rank;
        this.description = description;
        this.img = img;

    }
    
    public int getIdRepas() {
        return idRepas;
    }
    
    public void setIdRepas(int idRepas) {
        this.idRepas = idRepas;
    }

    public String getNomRepas() {
        return nomRepas;
    }

    public void setNomRepas(String nomRepas) {
        this.nomRepas = nomRepas;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Repas{" +
                "idRepas=" + idRepas +
                ", nomRepas='" + nomRepas + '\'' +
                ", prix=" + prix +
                ", restaurant='" + restaurant + '\'' +
                ", rank=" + rank +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
