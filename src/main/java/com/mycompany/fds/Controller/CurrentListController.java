package com.mycompany.fds.Controller;

import com.mycompany.fds.model.CurrentPanier;
import com.mycompany.fds.model.Ligne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrentListController implements Initializable {


    @FXML
    private TableView<Ligne> table_info;

    @FXML
    private TableColumn<Ligne, String> col_name;

    @FXML
    private TableColumn<Ligne, String> col_quantite;

    @FXML
    private TableColumn<Ligne, Button> col_sup;


    @FXML
    private TableColumn<Ligne, String> col_prix;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        loadData();
        System.out.println("dfdf");
    }

    public void initTable() {
        initCols();
    }

    public void initCols() {

        col_name.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        col_sup.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
    }

    public void loadData() {
        ObservableList<Ligne> table_data = FXCollections.observableArrayList();
        String n, q,p,pt;

        for (int i = 0; i < CurrentPanier.listeRepas.size(); i++) {
            n = CurrentPanier.listeRepas.get(i).getNomRepas();
            q = String.valueOf(CurrentPanier.liggneCom.get(CurrentPanier.listeRepas.get(i).getNomRepas()));
            p=  String.valueOf(CurrentPanier.listeRepas.get(i).getPrix());
            pt = "String.valueOf(Integer.parseInt(p)*Integer.parseInt(q))";
            Button btn = new Button("Supprimer");
            int finalI = i;
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    table_info.getItems().remove(finalI);
                    CurrentPanier.liggneCom.remove(CurrentPanier.listeRepas.get(finalI).getNomRepas());
                    CurrentPanier.listeRepas.remove(finalI);

                }
            });
            table_data.add(new Ligne(n,q,p,pt,btn));

        }
        table_info.setItems(table_data);
    }
}

