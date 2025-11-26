package com.example.mahin_2207109__cvbuilder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class HomeController {

    @FXML
    void next_input_scene(ActionEvent event) {
        try {
            var resourceUrl = getClass().getResource("cv_input.fxml");
            if (resourceUrl == null) {
                throw new IOException("Cannot find cv_input.fxml");
            }
            Parent root = FXMLLoader.load(resourceUrl);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading cv_input.fxml: " + e.getMessage());
        }
    }

    @FXML
    void view_cv(ActionEvent event) {
        try {
            var resourceUrl = getClass().getResource("all_CV_view.fxml");
            if (resourceUrl == null) {
                throw new IOException("Cannot find all_CV_view.fxml");
            }
            Parent root = FXMLLoader.load(resourceUrl);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading all_CV_view.fxml: " + e.getMessage());
        }
    }
}
