
package com.mycompany.fds.Controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.*;

public class OnBoardController implements Initializable {
    @FXML private JFXButton menuButton;
    @FXML private JFXButton myAccountButton;
    @FXML private JFXButton myOrdersButton;
    @FXML private JFXButton myDeliveryButton;
    @FXML private JFXButton logoutButton;
    @FXML private Pane menuPane;
    @FXML private Pane myAccountPane;
    @FXML private Pane myOrdersPane;
    @FXML private Pane myDeliveryPane;

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == menuButton) {
            menuPane.toFront();
        }
        if (actionEvent.getSource() == myAccountButton) {
            myAccountPane.setStyle("-fx-background-color : #53639F");
            myAccountPane.toFront();
        }
        if (actionEvent.getSource() == myOrdersButton) {
            myOrdersPane.setStyle("-fx-background-color : #02030A");
            myOrdersPane.toFront();
        }
        if(actionEvent.getSource() == myDeliveryButton)
        {
            //myDeliveryPane.setStyle("-fx-background-color : #464F67");
            myDeliveryPane.toFront();
        }
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/fxml/FoodList.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                myDeliveryPane.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
