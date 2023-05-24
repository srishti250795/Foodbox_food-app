package com.mycompany.fds.model;

public class Facture {
    private String idFacture;
    private float prix;
    private String idCommande;

    public Facture(String idFacture, float prix, String idCommande) {
        this.idFacture = idFacture;
        this.prix = prix;
        this.idCommande = idCommande;
    }

    public String getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }
    
    
}
