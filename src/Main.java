package com.clock;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {
    private Stage stage;
    @FXML
    private Label label;

    public void animate() throws IOException {
        String name = "Kali Clock";
        for (int i = 0; i < name.length(); i++) {
            try {
                String tem = name.substring(i, i + 1);
                Thread.sleep(60);
                Platform.runLater(() -> label.setText(label.getText() + tem));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(1000);
            Platform.runLater(()->{
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.stage = HomePage.getObj().getStage();
        new Thread(() -> {
            try {
                animate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}

