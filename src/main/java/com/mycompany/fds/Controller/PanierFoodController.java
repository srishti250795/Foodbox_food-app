package com.mycompany.fds.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.mycompany.fds.View.FoodCard;
import com.mycompany.fds.api.DbConnection;
import com.mycompany.fds.model.Commande;
import com.mycompany.fds.model.CurrentUser;
import com.mycompany.fds.model.Ligne;
import com.mycompany.fds.model.Rest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Properties;
import java.util.ResourceBundle;

public class PanierFoodController implements Initializable {
    public  JFXButton afficherButton;
    public  JFXButton confirmerButton;
    public  JFXButton trackerButton;
    public TableView<Commande> tablePanier2;


    @FXML
    private TableColumn<Commande,String> col_id;

    @FXML
    private TableColumn<Commande,String> col_address;

    @FXML
    private TableColumn<Commande,String> col_total;

    @FXML
    private TableColumn<Commande,String> col_date;

    public static ObservableList<Commande> table_data = FXCollections.observableArrayList();

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        trackerButton.setVisible(false);
        loadData();

    }

        public void afficherAction(MouseEvent mouseEvent) {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        try {
            root = loader.load(FoodCard.class.getResource("/fxml/currentList.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void confirmerAction(MouseEvent mouseEvent) {
        confirmerButton.setVisible(false);
        trackerButton.setVisible(true);
        trackerButton.toFront();
        afficherButton.setVisible(false);
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        try {
            root = loader.load(FoodCard.class.getResource("/fxml/adress.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void trackerAction(MouseEvent mouseEvent) {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        trackerButton.setVisible(false);
        afficherButton.setVisible(true);
        confirmerButton.setVisible(true);
        Parent root;
       try {
            root = loader.load(FoodCard.class.getResource("/fxml/currentTrack.fxml").openStream());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void initTable() {
        initCols();
    }

    public void initCols() {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    public void loadData() {

        String a, d;
        Float t;
        int i;
                try {
                    String sql = "SELECT * FROM Commande Where client = ? ";
                    con= DbConnection.getConnection();
                    preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, CurrentUser.id+"");
            resultSet = preparedStatement.executeQuery();
            boolean r;
           while (resultSet.next()) {
               System.out.println(resultSet.getString("address"));
                Commande c = new Commande(String.valueOf(resultSet.getInt("id")),resultSet.getString("address"),resultSet.getString("date"),resultSet.getString("prix")+" DH",resultSet.getInt("client"));
               System.out.println(c.getPrix()+c.getClient()+c.getAddress()+"");
               table_data.add(c);
            }


             } catch (SQLException e) {
            e.printStackTrace();
              }
        tablePanier2.setItems(table_data);


    }
}
