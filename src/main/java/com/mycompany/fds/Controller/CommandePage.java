package com.mycompany.fds.Controller;

import com.jfoenix.controls.JFXButton;
import com.mycompany.fds.model.CurrentPanier;
import com.mycompany.fds.model.Repas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class CommandePage implements Initializable {

    private int totalQuantite = 0;
    private float totalPrix;
    @FXML private Label idRepas;
    @FXML private Label nomRepas;
    @FXML private Label description;
    @FXML private Label quantite;
    @FXML private Spinner spinner; //handle quantity
    @FXML private Label prix;
    @FXML private Label total;
    @FXML private JFXButton validerQuantite;
    @FXML private JFXButton addRepas;
    @FXML private JFXButton deleteRepas;
    @FXML private JFXButton valider;
    @FXML private JFXButton annuler;
    @FXML private ImageView photo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int quantiteMax = Integer.parseInt(quantite.getText());
        System.out.println("quantité: "+quantiteMax);
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, quantiteMax, 0);
        this.spinner.setValueFactory(spinnerValueFactory);
        spinner.setEditable(false);
    }
    public void getFood(Repas r1){

        idRepas.setText(String.valueOf(r1.getIdRepas()));
        nomRepas.setText(r1.getNomRepas());
        description.setText(r1.getDescription());
        quantite.setText(String.valueOf(r1.getRank()));
        prix.setText(String.valueOf(r1.getPrix()));
        Image image = new Image(r1.getImg());
        photo.setImage(image);

    }



    public void validerCommande(ActionEvent actionEvent){



//(String nomRepas, float prix, String restaurant, int rank, String description, String img){




        System.out.println("Votre commande est enregistré !");
        int idRepasActuel = Integer.parseInt(idRepas.getText());
        int qteRepasActuel = Integer.parseInt(this.spinner.getValue().toString()); //quantité hya rank f database
        String nomRepasActuel = nomRepas.getText();
        float prixRepas = Float.parseFloat(prix.getText());

        Repas r = new Repas(nomRepasActuel,prixRepas,null,9,null,null);
        CurrentPanier.listeRepas.add(r);
        CurrentPanier.liggneCom.put(nomRepasActuel,qteRepasActuel);
        System.out.println("nom repa" + CurrentPanier.listeRepas.getLast().getNomRepas() + "quantite  " + CurrentPanier.liggneCom.get(CurrentPanier.listeRepas.getLast().getNomRepas()));
        Stage stage = (Stage) valider.getScene().getWindow();
        stage.close();
    }

    public void calculerPrix(ActionEvent actionEvent){
        float prixRepasActuel = Float.parseFloat(prix.getText()); //get food price
        System.out.println("Prix repas actuel: "+prixRepasActuel);
        this.total.setText(this.spinner.getValue().toString()); //get the quantity value and put it in total field
        totalPrix = Float.parseFloat(total.getText()) * prixRepasActuel;
        System.out.println("Prix total: "+totalPrix);
        total.setText(String.valueOf(totalPrix));
    }

    public void closeWindow(ActionEvent actionEvent){
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }

    /*
    public void modifyPrix(ActionEvent actionEvent){
        float prixRepasActuel = Float.parseFloat(prix.getText());
        totalPrix = Float.parseFloat(spinner.getValue().toString());
        totalPrix = totalPrix * prixRepasActuel;
        System.out.println(totalPrix);
        total.setText(String.valueOf(totalPrix));
    }
    */



}
