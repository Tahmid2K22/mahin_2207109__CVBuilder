package com.example.mahin_2207109__cvbuilder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class inputController {



    @FXML
    private TextField f_name;

    @FXML
    private TextField email;

    @FXML
    private TextField p_num;

    @FXML
    private TextArea address;

    @FXML
    private TextArea edu_qual;

    @FXML
    private TextArea skills;

    @FXML
    private TextArea w_exp;

    @FXML
    private TextArea projects;

    @FXML
    private Button sub_btn;

    @FXML
    private ImageView imgView;

    @FXML
    private Button btnSelectImage;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    @FXML
    private void prof_pic_upload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose Profile Picture");
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) btnSelectImage.getScene().getWindow();

        File selectedFile = fc.showOpenDialog(stage);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imgView.setImage(image);
        }
    }

    @FXML
    private void submit(ActionEvent event) throws IOException {
        String validationError = validateForm();
        if (validationError != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation error");
            alert.setHeaderText(null);
            alert.setContentText(validationError);
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CV_show.fxml"));
        Parent root = fxmlLoader.load();

        show_controller showController = fxmlLoader.getController();
        showController.setData(collectInputs());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,900,600));
        stage.show();
    }


    private String validateForm() {
        String nameText = textOf(f_name).trim();
        String emailText = textOf(email).trim();
        String phoneText = textOf(p_num).replaceAll("\\s+", "");

        if (nameText.isEmpty()) {
            return "Full name is required.";
        }
        if (emailText.isEmpty()) {
            return "Email is required.";
        }
        if (!EMAIL_PATTERN.matcher(emailText).matches()) {
            return "Please enter a valid email address.";
        }
        if (!phoneText.isEmpty()) {
            if (!phoneText.matches("\\d{7,15}")) {
                return "Phone number must contain 7 to 15 digits.";
            }
        }

        return null;
    }

    public Map<String, String> collectInputs() {
        Map<String, String> m = new HashMap<>();
        m.put("f_name", textOf(f_name));
        m.put("email", textOf(email));
        m.put("p_num", textOf(p_num));
        m.put("address", textOf(address));
        m.put("edu_qual", textOf(edu_qual));
        m.put("skills", textOf(skills));
        m.put("w_exp", textOf(w_exp));
        m.put("projects", textOf(projects));

        String imgUrl = "";
        if (imgView != null && imgView.getImage() != null) {
            imgUrl = imgView.getImage().getUrl();
            if (imgUrl == null) imgUrl = "";
        }
        m.put("img_url", imgUrl);
        return m;
    }

    private String textOf(TextInputControl tic) {
        return tic == null ? "" : tic.getText();
    }
}
