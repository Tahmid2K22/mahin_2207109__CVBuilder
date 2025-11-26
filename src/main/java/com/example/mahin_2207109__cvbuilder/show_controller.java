
package com.example.mahin_2207109__cvbuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.util.Map;


public class show_controller {

    private database db;

    @FXML
    private Text name;

    @FXML
    private Label email;

    @FXML
    private Label phn;

    @FXML
    private Label addrs;

    @FXML
    private Text ed_q;

    @FXML
    private Text skill;

    @FXML
    private Text w_ex;

    @FXML
    private Text projects;

    @FXML
    private ImageView pro_pic;

    @FXML
    public void initialize() {
        db = database.getInstance();
    }

    public void setData(Map<String, String> data) {
        setData(data, true);
    }

    public void setData(Map<String, String> data, boolean showAlert) {
        if (data == null) return;

        name.setText(nonNull(data.get("f_name")));
        email.setText(nonNull(data.get("email")));
        phn.setText(nonNull(data.get("p_num")));
        addrs.setText(nonNull(data.get("address")));
        ed_q.setText(nonNull(data.get("edu_qual")));
        skill.setText(nonNull(data.get("skills")));
        w_ex.setText(nonNull(data.get("w_exp")));
        projects.setText(nonNull(data.get("projects")));


        String imgUrl = data.get("img_url");
        if (imgUrl == null) {

            imgUrl = data.get("image");
        }

        if (pro_pic != null) {
            if (imgUrl != null && !imgUrl.isBlank()) {
                try {
                    pro_pic.setImage(new Image(imgUrl, true));
                } catch (Exception ignored) {

                    pro_pic.setImage(null);
                }
            } else {
                pro_pic.setImage(null);
            }
        }

        if (showAlert) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("CV created succesfully");
            alert.showAndWait();
        }

    }


    public void saveToDatabase(Map<String, String> data) {
        if (data == null || db == null) return;

        try {
            String fname = data.get("f_name");
            String emailVal = data.get("email");
            String phoneStr = data.get("p_num");
            String address = data.get("address");
            String education = data.get("edu_qual");
            String skills = data.get("skills");
            String workExp = data.get("w_exp");
            String projectsVal = data.get("projects");
            String imgUrl = data.get("img_url");

            if (imgUrl == null) {
                imgUrl = data.get("image");
            }

            Integer phone = null;
            if (phoneStr != null && !phoneStr.isBlank()) {
                try {
                    phone = Integer.parseInt(phoneStr.trim());
                } catch (NumberFormatException e) {
                    System.err.println("Invalid phone number format: " + phoneStr);
                    e.printStackTrace();
                    phone = 0;
                }
            } else {
                phone = 0;
            }

            db.insertCV(
                nonNull(fname),
                nonNull(emailVal),
                phone,
                nonNull(address),
                nonNull(education),
                nonNull(skills),
                nonNull(workExp),
                nonNull(projectsVal),
                imgUrl
            );

            System.out.println("CV saved to database successfully!");

        } catch (Exception e) {
            System.err.println("Error saving CV to database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String nonNull(String s) {
        return s == null ? "" : s;
    }
}
