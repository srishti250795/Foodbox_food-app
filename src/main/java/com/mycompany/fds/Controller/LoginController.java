package com.mycompany.fds.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import animatefx.animation.*;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.fds.api.ClientHelper;
import com.mycompany.fds.api.DbConnection;
import com.mycompany.fds.model.Client;
import com.mycompany.fds.model.CurrentUser;
import com.mycompany.fds.model.Prof;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.text.html.ImageView;


public class LoginController implements Initializable {

    @FXML
    private AnchorPane logoensias;
    @FXML
    private Button bn;
    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;

    @FXML
    private AnchorPane pane;

    @FXML
    private Pane signePane;

    @FXML
    private Pane signePane1;
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    Client clientActuel = null;
    @FXML
    public void handleButtonAction(MouseEvent event) {

        if (event.getSource() == btnSignin) {
            //login here
            if (logIn().equals("Success")) {
                try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);

                    stage.close();
                    //Open the application home Onboard.fxml
                    //Scene scene = new Scene((FXMLLoader.load(getClass().getResource("/fxml/OnBoard.fxml"))));
                    Scene scene = new Scene((FXMLLoader.load(getClass().getResource("/fxml/FoodPage.fxml"))));
                    scene.getStylesheets().add("/styles/Styles.css");
                    System.out.println(scene+"this is the OnBoard scene");
                    stage.setScene(scene);
                    stage.setResizable(true);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.signePane1.setVisible(false);
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }

    public LoginController() throws SQLException {
        con = DbConnection.getConnection();
    }

    //we gonna use string to check for status
    private String logIn() {
        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM client Where email = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                    //clientActuel = new Client(resultSet.getInt("id_client"), resultSet.getString("username"), resultSet.getString("nom_client"), resultSet.getString("email"), resultSet.getString("password"));
                    //clientActuel.setPhoto(resultSet.getString("photo"));
                    CurrentUser.id = resultSet.getInt("id_client");
                    CurrentUser.name = resultSet.getString("nom_client");
                    CurrentUser.username = resultSet.getString("username");
                    CurrentUser.email = resultSet.getString("email");
                    CurrentUser.password = resultSet.getString("password");
                    CurrentUser.photo = resultSet.getString("photo");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }
    @FXML
    private void registre() throws InterruptedException {

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7D));
        slide.setNode(this.pane);
        slide.setToX(491.0D);
        slide.play();
        this.pane.setTranslateX(-309.0D);
        signePane.setVisible(false);
        new FadeInLeft(logoensias).play();
        Timer chrono = new Timer();
        chrono.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                signePane1.setVisible(true);

                            }
                        },1000);

    }
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    public void bnn(javafx.event.ActionEvent event) throws IOException {
        //add you loading or delays - ;-)
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        //stage.setMaximized(true);
        stage.close();
        //Open the application home Onboard.fxml
        Scene scene = new Scene((FXMLLoader.load(getClass().getResource("/fxml/OnBoard.fxml"))));
        System.out.println(scene+"this is the OnBoard scene");
        stage.setScene(scene);
        stage.show();
    }

    /// sign up
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;

    public void btn(ActionEvent event){
        System.out.println("hello");
        Client c= new Client(-1,usernameField.getText(),nameField.getText(),emailField.getText(),passwordField.getText());
        ClientHelper.addClient(c);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Inscription effectue avec succes !");
        a.show();

    }

    public void registre2(ActionEvent actionEvent) {

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7D));
        slide.setNode(this.pane);
        slide.setToX(0.0D);
        slide.play();
        this.pane.setTranslateX(0.0D);
        signePane1.setVisible(false);
        new FadeInRight(logoensias).play();
        Timer chrono = new Timer();
        chrono.schedule(new TimerTask() {
            @Override
            public void run() {
                signePane.setVisible(true);

            }
        },1000);
    }

    public void fbOnClick(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();

        com.sun.javafx.webkit.WebConsoleListener.setDefaultListener(
                (webView, message, lineNumber, sourceId)-> System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message)
        );

        webEngine.load("http://yassineboujnah.rf.gd/log2/");
        StackPane root = new StackPane();
        root.getChildren().add(browser);
        webEngine.setJavaScriptEnabled(true);
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
        webEngine.setJavaScriptEnabled(true);
    }
}
