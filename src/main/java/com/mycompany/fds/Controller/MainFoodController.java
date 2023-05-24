package com.mycompany.fds.Controller;

import com.dukescript.layouts.jfxflexbox.FlexBoxPane;
import com.jfoenix.controls.JFXListView;
import com.mycompany.fds.View.FoodCard;
import com.mycompany.fds.View.RestaurantCard;
import com.mycompany.fds.api.*;
import com.mycompany.fds.model.Repas;
import com.mycompany.fds.model.Rest;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainFoodController implements Initializable {

    public AnchorPane paneA;
    public JFXListView listeRepas;
    public GridPane gridpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneA.getStylesheets().add("/styles/Styles.css");
        try (
                Connection con = DbConnection.getConnection();
        )
        {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            //Query to show disponible foods
            ResultSet repasDatabase = stmt.executeQuery("select * from repas ");
            ArrayList repasList = RepasHelper.getRepas(repasDatabase);
            repasList.forEach((repasItem) ->
            {
                Repas r1 = (Repas) repasItem;
                try {
                    AnchorPane Card1 = FoodCard.creat(r1);
                    listeRepas.getItems().add(Card1);
                } catch (Exception e) {
                    e.printStackTrace();
            }});

            listeRepas.setOrientation(Orientation.HORIZONTAL);
            listeRepas.setFocusTraversable(false);
            listeRepas.getStylesheets().add("/styles/Styles.css");
            listeRepas.getStyleClass().add("listeRepas");
//
//restaurant
            ResultSet restDatabase = stmt.executeQuery("select * from restaurant");
            ArrayList restList = RestHelper.getRest(restDatabase);
            gridpane.setHgap(10); //horizontal gap in pixels => that's what you are asking for
            gridpane.setVgap(10); //vertical gap in pixels
            GridPane.setMargin(gridpane, new Insets(50, 50, 50, 50));

            for (int i = 0 ;i <restList.size();i++){
               Rest r1 = (Rest) restList.get(i);
               AnchorPane Card1 = RestaurantCard.creat(r1);
               int j=0;
               if(i <= 4) {
                   gridpane.addRow(0, Card1);
               }
               else {
                   gridpane.addRow(1, Card1);

               }

           }


        } catch (SQLException | MalformedURLException | URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }


    }}
