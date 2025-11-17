package com.example.mahin_2207109__cvbuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class inputController {

    @FXML
    private TextField f_name;

    @FXML
    private TextField email;

    @FXML
    private TextField p_num;

    @FXML
    private TextField address;

    @FXML
    private TextField edu_qual;

    @FXML
    private TextField skills;

    @FXML
    private TextField w_exp;

    @FXML
    private TextField projects;

    @FXML
    private Button sub_btn;

    @FXML
    private void submit(ActionEvent event) {
        Map<String, String> inputs = collectInputs();

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
        return m;
    }

    private String textOf(TextField tf) {
        return tf == null ? "" : tf.getText();
    }
}
