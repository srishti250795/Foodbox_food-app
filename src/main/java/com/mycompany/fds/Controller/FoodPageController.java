package com.mycompany.fds.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.mycompany.fds.View.FoodCard2;
import com.mycompany.fds.api.DbConnection;
import com.mycompany.fds.api.RepasHelper;
import com.mycompany.fds.model.Repas;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FoodPageController implements Initializable {
    @FXML
    public AnchorPane homePane;
    @FXML
    public AnchorPane profilePane;
    @FXML
    public AnchorPane panierPane;
    @FXML
    public JFXButton buttonHome;
    @FXML
    public JFXButton buttonPanier;
    @FXML
    public JFXButton ButtonProfile;
    public AnchorPane searchBar;
    public JFXButton search;
    public AnchorPane searchPane;
    public TextField searchfiled;
    public JFXListView listeRepas;
    @FXML
    private JFXButton bar2;
    @FXML
    private JFXButton bar1;
    @FXML
    private AnchorPane paneslide;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneslide.setTranslateX(-153);
        bar1.setVisible(true);
        bar2.setVisible(false);
        profilePane.setVisible(false);
        panierPane.setVisible(false);
        searchPane.setVisible(false);
        profilePane.getStylesheets().add("/styles/Styles.css");
        panierPane.getStylesheets().add("/styles/Styles.css");
        homePane.getStylesheets().add("/styles/Styles.css");
        searchBar.toBack();
        profilePane.toFront();
        panierPane.toFront();
        homePane.toFront();
        searchBar.setVisible(false);

    }

    public void run1(javafx.scene.input.MouseEvent mouseEvent) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneslide);
        slide.setToX(0);
        slide.play();
        homePane.toBack();
        profilePane.toBack();
        panierPane.toBack();
        paneslide.toFront();
        paneslide.setTranslateX(-153);
        slide.setOnFinished((ActionEvent e) -> {
            bar1.setVisible(false);
            bar2.setVisible(true);
        });
    }

    public void run2(javafx.scene.input.MouseEvent mouseEvent) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneslide);
        slide.setToX(-153);
        slide.play();
        homePane.toBack();
        paneslide.toFront();
        paneslide.setTranslateX(0);
        slide.setOnFinished((ActionEvent e) -> {
            bar1.setVisible(true);
            bar2.setVisible(false);
        });
    }

    public void OnPaniersAction(ActionEvent actionEvent) {
        profilePane.setVisible(false);
        homePane.setVisible(false);
        panierPane.setVisible(true);
        searchPane.setVisible(false);

    }

    public void onHomeAction(ActionEvent actionEvent) {
        profilePane.setVisible(false);
        homePane.setVisible(true);
        panierPane.setVisible(false);
        searchPane.setVisible(false);

    }

    public void OnProfileAction(ActionEvent actionEvent) {
        profilePane.setVisible(true);
        homePane.setVisible(false);
        panierPane.setVisible(false);
        searchPane.setVisible(false);

    }

    public void run4(MouseEvent mouseEvent) {
        searchBar.setVisible(true);
        searchBar.toFront();
    }

    public void run3(ActionEvent actionEvent) {
        searchBar.setVisible(false);
        searchBar.toBack();
        searchPane.toBack();
        searchPane.setVisible(false);

        profilePane.setVisible(false);
        homePane.setVisible(true);
        panierPane.setVisible(false);
    }

    public void run5(ActionEvent actionEvent) {
        listeRepas.getItems().clear();
        searchPane.setVisible(true);
        searchPane.toFront();
        profilePane.setVisible(false);
        homePane.setVisible(false);
        panierPane.setVisible(false);
        String recherche = searchfiled.getText();


        try (
                Connection con = DbConnection.getConnection();
        )
        {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            //Query to show disponible foods
            ResultSet repasDatabase = stmt.executeQuery("select * from repas where nom_repas REGEXP '(^"+ recherche+")'");
            ArrayList repasList = RepasHelper.getRepas(repasDatabase);
            repasList.forEach((repasItem) ->
            {
                Repas r1 = (Repas) repasItem;
                try {
                    AnchorPane Card1 = FoodCard2.creat(r1);
                    listeRepas.getItems().add(Card1);
                } catch (Exception e) {
                    e.printStackTrace();
                }});

            listeRepas.setFocusTraversable(false);
            listeRepas.getStylesheets().add("/styles/Styles.css");
            listeRepas.getStyleClass().add("listeRepas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
