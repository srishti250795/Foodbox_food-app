package com.mycompany.fds.Controller;

import com.jfoenix.controls.JFXListView;
import com.mycompany.fds.View.FoodCard;
import com.mycompany.fds.View.FoodCard2;
import com.mycompany.fds.api.DbConnection;
import com.mycompany.fds.api.RepasHelper;
import com.mycompany.fds.model.Repas;
import com.mycompany.fds.model.Rest;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RestaurantPageController implements Initializable {
    public Label lab;
    public JFXListView listeRepas;
    public ImageView restImage;
    public AnchorPane RestaurantPagePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RestaurantPagePane.getStylesheets();
    }
    public void getRest(Rest r){
        Image image = new Image(r.getImages(), 391, 181, false, false);
        restImage.setImage(image);
        //restImage.setFitWidth(391);
        //restImage.setFitHeight(181);


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
    }}
