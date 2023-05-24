package com.mycompany.fds;

import com.mycompany.fds.api.ClientHelper;
import java.sql.DriverManager;
import com.mycompany.fds.api.DbConnection;
import com.mycompany.fds.api.ProfHelper;
import com.mycompany.fds.api.RepasHelper;
import com.mycompany.fds.model.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("iFood App");
       // stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

 
    public static void main(String[] args) {

        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        System.out.println("hello ilyass");
        try (
                Connection con =DbConnection.getConnection();
                )
        {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs=stmt.executeQuery("select * from prof where id = '2'");
            ArrayList proflist = ProfHelper.getProf(rs);
            Prof p1= (Prof) proflist.get(0);
            System.out.println(p1.getUsername());

            //Query to show disponible foods
            ResultSet repasDatabase = stmt.executeQuery("select * from repas ");  
            ArrayList repasList = RepasHelper.getRepas(repasDatabase);
            //repasList.forEach((repasItem) -> System.out.println(repasItem));
            Repas r1= (Repas) repasList.get(0);
            System.out.println("Description: "+r1.getDescription());
            
            ResultSet clientDatabase = stmt.executeQuery("select * from client ");  
            ArrayList clientList = ClientHelper.getClient(clientDatabase);
            //repasList.forEach((repasItem) -> System.out.println(repasItem));
            Client c1= (Client) clientList.get(0);
            System.out.println(c1.getUsername());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        launch(args);
    

}
}

