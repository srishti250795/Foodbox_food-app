package com.mycompany.fds.View;

import com.mycompany.fds.Controller.CommandePage;
import com.mycompany.fds.Controller.RestaurantPageController;
import com.mycompany.fds.model.Rest;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static javafx.scene.text.TextAlignment.CENTER;

public class RestaurantCard {

    public static AnchorPane creat(Rest rest) throws MalformedURLException, URISyntaxException, FileNotFoundException {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(110);
        anchorPane.setPrefWidth(140);
        Image img2 = new Image(rest.getImages());
        ImageView image = new ImageView(img2);

        // image.setImage(img2);
        image.setFitHeight(110);
        image.setFitWidth(155);
        image.setPickOnBounds(true);
        image.setLayoutX(2);
        image.getStyleClass().add(rest.getImages());

        Label lab = new Label();
        lab.setLayoutX(5);
        lab.setLayoutY(100);
        lab.setPrefHeight(44);
        lab.setPrefWidth(140);
        lab.setText(" "+rest.getNom());
        lab.setStyle("-fx-font-weight: bold;" );
        lab.setTextAlignment( CENTER );
        lab.setAlignment(Pos.CENTER);

        Label lab2 = new Label();
        lab2.setLayoutX(5); //5
        lab2.setLayoutY(120);
        lab2.setPrefHeight(44);
        lab2.setPrefWidth(140);
        lab2.setText(rest.getDescription());
        anchorPane.getStylesheets().add("/styles/Styles.css");

        anchorPane.getChildren().add(image);
        anchorPane.getChildren().add(lab);
        anchorPane.getChildren().add(lab2);
        anchorPane.getStyleClass().add("h");

        anchorPane.setOnMouseClicked((e) -> {
            System.out.println(rest.getNom());
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root;
            try {
                root = loader.load(FoodCard.class.getResource("/fxml/restaurantPage.fxml").openStream());
                System.out.println(root);
                Scene scene = new Scene(root);
                RestaurantPageController restpage = (RestaurantPageController) loader.getController();
                restpage.getRest(rest);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        return anchorPane;
    }


}
