/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fds.Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.fds.api.ClientHelper;
import com.mycompany.fds.model.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FXMLController {

    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXPasswordField passwordField;

    public void btn(ActionEvent event){
        System.out.println("hello");
        Client c= new Client(-1,usernameField.getText(),nameField.getText(),emailField.getText(),passwordField.getText());
        ClientHelper.addClient(c);
    }
    
}
