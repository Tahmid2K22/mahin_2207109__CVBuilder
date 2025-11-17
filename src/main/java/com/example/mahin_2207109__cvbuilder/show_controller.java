
package com.example.mahin_2207109__cvbuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    public void setData(Map<String, String> data) {
        if (data == null) return;
        name.setText(nonNull(data.get("f_name")));
        email.setText(nonNull(data.get("email")));
        phn.setText(nonNull(data.get("p_num")));
        addrs.setText(nonNull(data.get("address")));

        // Populate the newly added fields
        ed_q.setText(nonNull(data.get("edu_qual")));
        skill.setText(nonNull(data.get("skills")));
        w_ex.setText(nonNull(data.get("w_exp")));
        projects.setText(nonNull(data.get("projects")));
    }

    private String nonNull(String s) {
        return s == null ? "" : s;
    }
}
