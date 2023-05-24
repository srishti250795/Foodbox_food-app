package com.mycompany.fds.Controller;

import com.jfoenix.controls.JFXTextField;
import com.mycompany.fds.model.Commande;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.mycompany.fds.Controller.PanierFoodController.table_data;

public class AdressController implements Initializable {

    public Label lab;
    public JFXTextField text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void btn(ActionEvent actionEvent) {
        Commande c = new Commande("3",text.getText(),"13/02/2020","130 DH",65);
        table_data.add(c);
        Stage stage = (Stage) lab.getScene().getWindow();
        stage.close();
    }
}
