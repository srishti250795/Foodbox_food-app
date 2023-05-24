package com.mycompany.fds.model;

public class Commande {
    
    private String id;
    private  String address;
    private String date;
    private  String prix;
    private int client;

    public Commande(String id, String address, String date, String prix, int client) {
        this.id = id;
        this.address = address;
        this.date = date;
        this.prix = prix;
        this.client = client;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }
}
