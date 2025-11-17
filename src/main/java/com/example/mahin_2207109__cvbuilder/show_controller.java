
package com.example.mahin_2207109__cvbuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.util.Map;

public class show_controller {

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

    public void setData(Map<String, String> data) {
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

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("CV created succesfully");
        alert.showAndWait();

    }

    private String nonNull(String s) {
        return s == null ? "" : s;
    }
}
