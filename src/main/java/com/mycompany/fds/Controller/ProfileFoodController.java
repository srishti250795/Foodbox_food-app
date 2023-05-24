package com.mycompany.fds.Controller;
//package com.mycompany.fds.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.fds.api.DbConnection;
import com.mycompany.fds.model.Client;
import com.mycompany.fds.model.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.ActionEvent;
import java.net.ConnectException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.mycompany.fds.api.DbConnection.CONN;

public class ProfileFoodController implements Initializable {
    @FXML private Label id;
    @FXML private Label username;
    @FXML private Label nom;
    @FXML private Label prenom;
    @FXML private Label email;
    @FXML private Label password;
    @FXML private ImageView photo;
    @FXML private JFXButton update;
    @FXML private JFXButton delete;

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int st = 0;
    //Inputs to update client infos
    @FXML
    private JFXPasswordField updatePassword;
    @FXML
    private JFXTextField updateEmail;
    @FXML
    private JFXTextField updatePrenom;
    @FXML
    private JFXTextField updateNom;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setText(String.valueOf(CurrentUser.id));
        username.setText(String.valueOf(CurrentUser.username));
        nom.setText(String.valueOf(CurrentUser.name));
        prenom.setText(String.valueOf(CurrentUser.prenom));
        email.setText(String.valueOf(CurrentUser.email));
        password.setText(String.valueOf(CurrentUser.password));
        Image image = new Image(CurrentUser.photo);
        photo.setImage(image);
    }

    public void getInfo(javafx.event.ActionEvent actionEvent) {
        updateNom.setText(String.valueOf(CurrentUser.name));
        updatePrenom.setText(String.valueOf(CurrentUser.prenom));
        updateEmail.setText(String.valueOf(CurrentUser.email));
        updatePassword.setText(String.valueOf(CurrentUser.password));
    }

    public void updateInfo(javafx.event.ActionEvent actionEvent)  {
        /*
        try {
            String sql = "UPDATE client SET id_client=?, nom_client=?, password=?, email=? WHERE='"+id.getText()+"' ";
            con= DbConnection.getConnection();
            preparedStatement = con.prepareStatement(sql);
            //preparedStatement.setString(1, CurrentUser.id+"");
            preparedStatement.setString(1, id.getText());
            preparedStatement.setString(2, updateNom.getText());
            //preparedStatement.setString(2, updateNom.getText());
            preparedStatement.setString(3, updatePassword.getText());
            preparedStatement.setString(4, updateEmail.getText());
            st = preparedStatement.executeUpdate();
            System.out.println("L'utilisateur "+id.getText()+" est modifié avec succés !");
        }
        catch(Exception e){
            e.printStackTrace();
        }

         */
    }

    public void deleteInfo(javafx.event.ActionEvent actionEvent)  {
        /*
        try {
            String sql = "DELETE FROM client WHERE='"+id.getText()+"' ";
            con= DbConnection.getConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id.getText()));
            st = preparedStatement.executeUpdate();
            //preparedStatement.setString(1, CurrentUser.id+"");
            //preparedStatement.setString(1, updateNom.getText());
            //preparedStatement.setString(2, updateNom.getText());
            //preparedStatement.setString(3, updatePassword.getText());
            //preparedStatement.setString(4, updateEmail.getText());
            System.out.println("L'utilisateur "+id.getText()+" est supprimé avec succés !");
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

         */
    }


    /*
    public void getUserInfo(Client client){
        username.setText(client.getUsername());
        password.setText(client.getName());
    }

     */


}
