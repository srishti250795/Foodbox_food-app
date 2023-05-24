package com.mycompany.fds.View;

import com.mycompany.fds.Controller.CommandePage;
import com.mycompany.fds.model.Repas;
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

public class FoodCard2 {
    public static AnchorPane creat(Repas r1) throws MalformedURLException, URISyntaxException, FileNotFoundException {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(119);
        anchorPane.setPrefWidth(402);
        Image img2 = new Image(r1.getImg());
        ImageView image = new ImageView(img2);

        // image.setImage(img2);
        image.setFitHeight(122);
        image.setFitWidth(177);
        image.setPickOnBounds(true);
        image.setLayoutX(2);


        Label lab = new Label();
        lab.setLayoutX(145);
        lab.setPrefHeight(44);
        lab.setPrefWidth(257);
        lab.setText(" "+r1.getNomRepas());
        lab.setStyle("-fx-font-weight: bold;" );
        lab.setTextAlignment( CENTER );
        lab.setAlignment(Pos.CENTER);

        Label lab2 = new Label();
        lab2.setLayoutX(200);
        lab2.setLayoutY(56);
        lab2.setPrefHeight(64);
        lab2.setPrefWidth(257);
        lab2.setText(r1.getPrix()+"DH "+r1.getDescription());
        anchorPane.getStylesheets().add("/styles/Styles.css");

        anchorPane.getChildren().add(image);
        anchorPane.getChildren().add(lab);
        anchorPane.getChildren().add(lab2);
        anchorPane.getStyleClass().add("h");
//(String nomRepas, float prix, String restaurant, int rank, String description, String img){

        anchorPane.setOnMouseClicked((e) -> {
            System.out.println(r1.getNomRepas());
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root;
            try {
                root = loader.load(FoodCard.class.getResource("/fxml/commandePage.fxml").openStream());
                System.out.println(root);
                Scene scene = new Scene(root);
                CommandePage commandePage = (CommandePage) loader.getController();
                commandePage.getFood(r1);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        return anchorPane;
    }
}
