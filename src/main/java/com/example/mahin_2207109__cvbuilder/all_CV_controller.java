package com.example.mahin_2207109__cvbuilder;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class all_CV_controller {

    @FXML
    private TableView<CVData> cvTable;

    @FXML
    private TableColumn<CVData, Integer> idColumn;

    @FXML
    private TableColumn<CVData, String> nameColumn;

    @FXML
    private TableColumn<CVData, String> emailColumn;

    @FXML
    private TableColumn<CVData, String> phoneColumn;

    @FXML
    private TableColumn<CVData, String> addressColumn;

    @FXML
    private TableColumn<CVData, String> educationColumn;

    @FXML
    private TableColumn<CVData, String> skillsColumn;

    @FXML
    private TableColumn<CVData, String> workExperienceColumn;

    @FXML
    private TableColumn<CVData, String> projectsColumn;

    @FXML
    private TableColumn<CVData, Void> updateColumn;

    @FXML
    private TableColumn<CVData, Void> deleteColumn;

    @FXML
    private TableColumn<CVData, Void> showColumn;

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        educationColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        skillsColumn.setCellValueFactory(new PropertyValueFactory<>("skills"));
        workExperienceColumn.setCellValueFactory(new PropertyValueFactory<>("workExperience"));
        projectsColumn.setCellValueFactory(new PropertyValueFactory<>("projects"));

        updateColumn.setCellFactory(new Callback<TableColumn<CVData, Void>, TableCell<CVData, Void>>() {
            @Override
            public TableCell<CVData, Void> call(TableColumn<CVData, Void> param) {
                return new TableCell<CVData, Void>() {
                    private final Button updateBtn = new Button("Update");

                    {
                        updateBtn.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-background-radius: 3; -fx-cursor: hand; -fx-font-size: 12px; -fx-padding: 5 15 5 15;");
                        updateBtn.setOnAction(event -> {
                            CVData data = getTableView().getItems().get(getIndex());
                            handleUpdate(data.getId());
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(updateBtn);
                        }
                    }
                };
            }
        });


        deleteColumn.setCellFactory(new Callback<TableColumn<CVData, Void>, TableCell<CVData, Void>>() {
            @Override
            public TableCell<CVData, Void> call(TableColumn<CVData, Void> param) {
                return new TableCell<CVData, Void>() {
                    private final Button deleteBtn = new Button("Delete");

                    {
                        deleteBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-background-radius: 3; -fx-cursor: hand; -fx-font-size: 12px; -fx-padding: 5 15 5 15;");
                        deleteBtn.setOnAction(event -> {
                            CVData data = getTableView().getItems().get(getIndex());
                            handleDelete(data.getId());
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteBtn);
                        }
                    }
                };
            }
        });


        showColumn.setCellFactory(new Callback<TableColumn<CVData, Void>, TableCell<CVData, Void>>() {
            @Override
            public TableCell<CVData, Void> call(TableColumn<CVData, Void> param) {
                return new TableCell<CVData, Void>() {
                    private final Button showBtn = new Button("Show");

                    {
                        showBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 3; -fx-cursor: hand; -fx-font-size: 12px; -fx-padding: 5 15 5 15;");
                        showBtn.setOnAction(event -> {
                            CVData data = getTableView().getItems().get(getIndex());
                            handleShow(data.getId());
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(showBtn);
                        }
                    }
                };
            }
        });


        loadData();
    }

    private void loadData() {
        ObservableList<CVData> data = FXCollections.observableArrayList();
        database db = database.getInstance();
        ResultSet rs = db.fetchAll();

        try {
            while (rs != null && rs.next()) {
                data.add(new CVData(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        String.valueOf(rs.getInt("phone")),
                        rs.getString("address"),
                        rs.getString("education"),
                        rs.getString("skills"),
                        rs.getString("work_experience"),
                        rs.getString("projects")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cvTable.setItems(data);
    }

    private void handleUpdate(int id) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update CV");
        alert.setHeaderText(null);
        alert.setContentText("Update functionality for CV ID: " + id + " will be implemented soon.");
        alert.showAndWait();

    }

    private void handleDelete(int id) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete CV");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete CV ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            database db = database.getInstance();
            db.delete(id);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("CV deleted successfully!");
            successAlert.showAndWait();

            loadData();
        }
    }

    private void handleShow(int id) {
        try {

            database db = database.getInstance();
            ResultSet rs = db.fetchAll();

            Map<String, String> cvData = null;
            while (rs != null && rs.next()) {
                if (rs.getInt("id") == id) {
                    cvData = new HashMap<>();
                    cvData.put("f_name", rs.getString("name"));
                    cvData.put("email", rs.getString("email"));
                    cvData.put("p_num", String.valueOf(rs.getInt("phone")));
                    cvData.put("address", rs.getString("address"));
                    cvData.put("edu_qual", rs.getString("education"));
                    cvData.put("skills", rs.getString("skills"));
                    cvData.put("w_exp", rs.getString("work_experience"));
                    cvData.put("projects", rs.getString("projects"));
                    cvData.put("img_url", rs.getString("pro_pic"));
                    break;
                }
            }

            if (cvData == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("CV not found!");
                alert.showAndWait();
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CV_show.fxml"));
            Parent root = fxmlLoader.load();

            show_controller showController = fxmlLoader.getController();
            showController.setData(cvData, false);

            Stage stage = new Stage();
            stage.setTitle("CV Details - ID: " + id);
            stage.setScene(new Scene(root, 900, 600));
            stage.show();

        } catch (IOException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load CV: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNewCV(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cv_input.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load CV input form: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static class CVData {
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty email;
        private final SimpleStringProperty phone;
        private final SimpleStringProperty address;
        private final SimpleStringProperty education;
        private final SimpleStringProperty skills;
        private final SimpleStringProperty workExperience;
        private final SimpleStringProperty projects;

        public CVData(int id, String name, String email, String phone, String address,
                      String education, String skills, String workExperience, String projects) {
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
            this.phone = new SimpleStringProperty(phone);
            this.address = new SimpleStringProperty(address);
            this.education = new SimpleStringProperty(education);
            this.skills = new SimpleStringProperty(skills);
            this.workExperience = new SimpleStringProperty(workExperience);
            this.projects = new SimpleStringProperty(projects);
        }

        public int getId() {
            return id.get();
        }

        public String getName() {
            return name.get();
        }

        public String getEmail() {
            return email.get();
        }

        public String getPhone() {
            return phone.get();
        }

        public String getAddress() {
            return address.get();
        }

        public String getEducation() {
            return education.get();
        }

        public String getSkills() {
            return skills.get();
        }

        public String getWorkExperience() {
            return workExperience.get();
        }

        public String getProjects() {
            return projects.get();
        }
    }
}
