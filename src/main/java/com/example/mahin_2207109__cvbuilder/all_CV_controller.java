package com.example.mahin_2207109__cvbuilder;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

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
