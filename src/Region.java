package com.clock;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.Set;

public class Region implements Initializable {
    private Stage stage;
    @FXML
    private TextField search;
    @FXML
    private ListView<String> listView;
    private String[] arr;

    public void searchAction(ActionEvent event) {
        boolean flag = true;
        listView.getItems().clear();
//        ObservableList<String> items = listView.getItems();
//        items.clear();
        System.out.println(arr[2]);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].toLowerCase().contains(search.getText())) {
                listView.getItems().add(arr[i]);
                flag = false;
            }
        }
        if (flag) {
            listView.getItems().addAll("Not Found");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.stage = HomePage.getObj().getStage();

        arr = ZoneId.getAvailableZoneIds().toArray(new String[0]);
        listView.setCellFactory(listView -> new CustomizeCell());

        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                 String new_val) -> {
                    System.out.println(new_val);
                    HomePageController.getObj().setZone(new_val);
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });

    }
}

class CustomizeCell extends ListCell<String> {
    private final VBox vBox;
    private final Label label;

    public CustomizeCell() {
        label = new Label();
        label.setFont(Font.font(20));
        label.setTextFill(Color.WHITE);
        vBox = new VBox(label);
        vBox.setPrefHeight(50);
        vBox.setAlignment(Pos.CENTER_LEFT);
        setGraphic(vBox);
    }

    @Override
    protected void updateItem(String string, boolean b) {
        super.updateItem(string, b);
        label.setText(string);
    }
}
