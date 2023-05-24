package com.mycompany.fds.model;


import javafx.scene.control.Button;

public class Ligne {
    String Nom,Quantite,Prix;

    Button supprimer;

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }


    public String getQuantite() {
        return Quantite;
    }

    public void setQuantite(String quantite) {
        Quantite = quantite;
    }

    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }

    public Ligne(String nom, String quantite, String prix, String prixTotal, Button supprimer) {
        Nom = nom;
        Quantite = quantite;
        Prix = prix;
        this.supprimer = supprimer;
    }

    public String getPrix() {
        return Prix;
    }

    public void setPrix(String prix) {
        Prix = prix;
    }
}
