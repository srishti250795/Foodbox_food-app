package com.mycompany.fds.Controller;

import com.lynden.gmapsfx.javascript.object.*;
import com.mycompany.fds.IotLocalisation;
import com.mycompany.fds.IotTemperature;

import com.mycompany.fds.model.SendSMS;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CurrentTrackController implements Initializable {
    public Label chaleurLabel;
    public Label metreLabel;
    static String st ="";
    static  String st2="";
    public AnchorPane pane;
    public WebView mapView;
    public AnchorPane progresse;
    //  public Pane pan2;
    GoogleMap map;

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        progresse.toFront();
        ProgressIndicator pb = new ProgressIndicator();
        pb.setPrefSize(250,250);
        pb.setLayoutX(310);
        pb.setLayoutY(210);
        Label lab2 = new Label("Votre commande est en cours de préparation");
        lab2.setPrefSize(700,60);
        lab2.setLayoutX(140);
        lab2.setLayoutY(520);
        final double MAX_FONT_SIZE = 30.0; // define max font size you need
        lab2.setFont(new Font(MAX_FONT_SIZE));
        progresse.getChildren().addAll(pb,lab2);
        ScheduledExecutorService scheduledExecutorService2;
        scheduledExecutorService2 = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService2.scheduleAtFixedRate(() -> {
            // get a random integer between 0-10

            // Update the chart
            Platform.runLater(() -> {
                System.out.println("ff");
                if(st != ""){
                    progresse.toBack();
                    progresse.getChildren().clear();
                }
            });
        }, 0, 2, TimeUnit.SECONDS);





        IotTemperature it = new IotTemperature();
        Thread th = new Thread(it);
        th.start();

        IotLocalisation it2= new IotLocalisation();
        Thread th2 = new Thread(it2);
        th2.start();


        BufferedReader br = null;
        BufferedReader br2 = null;
        String hote = "127.0.0.1" ;
        int port = 1000 ;
        int port2 = 2000 ;
        Socket soc = null;
        Socket soc2 = null;
        mapView.toBack();
      //  pan2.toFront();


/////////////////////////////////


      //  webView.getEngine().load("https://www.google.com/maps/dir/33.5572788,-7.6113999/33.5588165,-7.611781/@33.5543461,-7.607704,16z/data=!4m2!4m1!3e3");


////////////////////////////
        try {
            soc = new Socket(hote, port);
            soc2 = new Socket(hote, port2);
            InputStream flux = soc.getInputStream();
            InputStream flux2 = soc2.getInputStream();
            br = new BufferedReader(new InputStreamReader(flux));
            br2 = new BufferedReader(new InputStreamReader(flux2));
//defining the axes
            final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Time/s");
            xAxis.setAnimated(true); // axis animations are removed
            yAxis.setLabel("Value");
            yAxis.setAnimated(true); // axis animations are removed

            //creating the line chart with two axis created above
            final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            lineChart.setTitle("Temperature du repas");
            lineChart.setAnimated(true); // disable animations
            //defining a series to display data
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Temperature en Celcius");
            lineChart.setPrefWidth(500);
            lineChart.setPrefHeight(350);
            // add series to chart
            lineChart.getData().add(series);
            pane.getChildren().add(lineChart);


            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();


            BufferedReader finalBr = br;
            BufferedReader finalBr2 = br2;
            scheduledExecutorService.scheduleAtFixedRate(() -> {
            // get a random integer between 0-10

            // Update the chart
            Platform.runLater(() -> {
                try {
                    st = finalBr.readLine();
                    st2= finalBr2.readLine();
                    Integer t= Integer.parseInt(st);
                    if (t == -1) {
                        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                        a.setContentText("Votre Commande est arrivé");
                        a.setOnCloseRequest(new EventHandler<DialogEvent>() {
                            @Override
                            public void handle(DialogEvent event) {

                                Stage stage = (Stage) mapView.getScene().getWindow();
                                stage.close();
                            }
                        });
                        a.show();
                        scheduledExecutorService.shutdown();
                        //message whatsup
                        SendSMS.sendMessage();
                    }else {
                        mapView.getEngine().load("https://www.google.com/maps/dir/"+st2);
                        System.out.println(st);
                        Date now = new Date();
                        // put random number with current time
                        series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), Integer.parseInt(st)));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }, 5, 10, TimeUnit.SECONDS);


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
