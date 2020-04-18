package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.controller.ChangerHospitalData;
import sample.model.HospitalWard;

public class CreateNewPatientAlertForm {

    private TextField name;
    private TextField surname;
    private TextField patronymic;
    private TextField height;
    private TextField weight;
    private TextField marks;

    private DatePicker age;
    private ComboBox<String> gender;
    private GridPane root;
    private ComboBox<String> wayOfComing;

    private TextField diagnosis;
    private DatePicker dateOfComing;
    private ComboBox<String> wardNumber;

    CreateNewPatientAlertForm(ChangerHospitalData changerHospitalData)
    {
        root = new GridPane();
        ObservableList<String> reasons = FXCollections.observableArrayList("Мужчина", "Женщина");
        gender = new ComboBox<>(reasons);
        age = new DatePicker();
        name = new TextField();
        surname = new TextField();
        patronymic = new TextField();
        height = new TextField();
        weight = new TextField();
        marks = new TextField();

        ObservableList<String> ways = FXCollections.observableArrayList("Направление поликлиники", "Доставлен скорой помощью");
        wayOfComing = new ComboBox<>(ways);
        diagnosis = new TextField();
        dateOfComing = new DatePicker();
        ObservableList<String> wards = FXCollections.observableArrayList();

        for (HospitalWard hospitalWard: changerHospitalData.getWardsData())
            wards.add(hospitalWard.getNumber());
        wardNumber = new ComboBox<>(wards);


        createForm();
    }

    private void createForm()
    {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(50);

        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(7);

        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);

        Label labelName = new Label("Имя*:");
        Label labelSurname = new Label("Фамилия*:");
        Label labelPatronymic = new Label("Отчество:");
        Label labelAge = new Label("Дата рождения:");
        Label labelGender = new Label("Пол*:");
        Label labelHeight = new Label("Рост:");
        Label labelWeight = new Label("Вес:");
        Label labelMarks = new Label("Особые приметы:");

        Label labelWayOfComing = new Label("Способ поступления");
        Label labelDiagnosis = new Label("Диагноз");
        Label labelDateOfComing = new Label("Дата поступления");
        Label labelWardNumber = new Label("Номер палаты");

        root.add(labelWayOfComing, 0, 8);
        root.add(labelDiagnosis, 0, 9);
        root.add(wayOfComing, 1, 8);
        root.add(diagnosis, 1, 9);
        root.add(labelDateOfComing, 0, 10);
        root.add(labelWardNumber, 0, 11);
        root.add(dateOfComing, 1, 10);
        root.add(wardNumber, 1, 11);

        root.add(labelSurname, 0,0);
        root.add(labelName, 0, 1);
        root.add(labelPatronymic, 0, 2);
        root.add(labelGender, 0, 3);
        root.add(labelAge, 0,4);
        root.add(labelHeight, 0, 5);
        root.add(labelWeight, 0, 6);
        root.add(labelMarks, 0,7);
        root.add(surname, 1,0);
        root.add(name, 1,1);
        root.add(patronymic, 1 ,2);
        root.add(gender, 1,3);
        root.add(age, 1,4);
        root.add(height, 1,5);
        root.add(weight, 1 ,6);
        root.add(marks, 1, 7);

    }

    public TextField getName() {
        return name;
    }

    public TextField getSurname() {
        return surname;
    }

    public TextField getPatronymic() {
        return patronymic;
    }

    public TextField getHeight() {
        return height;
    }

    public TextField getWeight() {
        return weight;
    }

    public TextField getMarks() {
        return marks;
    }

    public DatePicker getAge() {
        return age;
    }

    public ComboBox<String> getGender() {
        return gender;
    }

    public GridPane getRoot() {
        return root;
    }

    public ComboBox<String> getWayOfComing() {
        return wayOfComing;
    }

    public TextField getDiagnosis() {
        return diagnosis;
    }

    public DatePicker getDateOfComing() {
        return dateOfComing;
    }

    public ComboBox<String> getWardNumber() {
        return wardNumber;
    }
}
