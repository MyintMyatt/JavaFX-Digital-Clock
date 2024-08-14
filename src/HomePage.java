package com.clock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage extends Application {
    private Stage stage;
    private static HomePage obj = new HomePage();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("main.fxml"));
        getObj().setStage(stage);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Kali Clock");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static HomePage getObj() {
        return obj;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}