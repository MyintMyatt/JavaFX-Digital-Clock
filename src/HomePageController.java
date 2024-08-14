package com.clock;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Label clock , second , date , zone;
    private String zone_value;
    private static HomePageController obj = new HomePageController();

    public static HomePageController getObj() {
        return obj;
    }

    public void setZone(String zone_value) {
        this.zone_value = zone_value;
    }

    public HomePageController() {

    }

    public String getZone_value() {
        return zone_value;
    }

    public void selectRegionbtnAction(ActionEvent event){
        progressIndicator.setVisible(true);
        new Thread(() -> {
            try {
                Thread.sleep(800);
                Platform.runLater(() ->{
                    Parent parent = null;
                    try {
                        parent = FXMLLoader.load(getClass().getResource("region.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(parent);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setting stage
         zone_value = HomePageController.getObj().getZone_value();
        if (zone_value == null) {
            zone_value = ZoneId.systemDefault().toString();
        }
        zone.setText(zone_value);

        //setting clock
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//        DateTimeFormatter secondFormatter = DateTimeFormatter.ofPattern("ss");
//        DateTimeFormatter period = DateTimeFormatter.ofPattern("a");
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
        DateTimeFormatter secondFormatter = DateTimeFormatter.ofPattern("ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy EEE a", Locale.ENGLISH);


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> {
//            ZoneId zoneId = ZoneId.of(zone_value);
//            LocalDateTime now = LocalDateTime.now(zoneId);
//            Date today = new Date();
//            String currentDate = DateFormat.getDateInstance().format(today);
//
//            clock.setText(now.format(df));
//            second.setText(now.format(secondFormatter));
//            date.setText(currentDate + " " + now.format(period));

            ZoneId zoneId = ZoneId.of(zone_value);
            ZonedDateTime now = ZonedDateTime.now(zoneId);
            String hour = now.format(timeFormatter);
            String d = now.format(dateFormatter);
            String s = now.format(secondFormatter);

            clock.setText(hour);
            second.setText(s);
            date.setText(d);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }
}