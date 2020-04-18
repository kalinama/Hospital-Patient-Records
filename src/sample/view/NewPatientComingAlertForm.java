package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.controller.ChangerHospitalData;
import sample.model.HospitalWard;

public class NewPatientComingAlertForm {
    private GridPane root;
    private ComboBox wayOfComing;

    private TextField diagnosis;
    private DatePicker dateOfComing;
    private ComboBox<String> wardNumber;

    NewPatientComingAlertForm(ChangerHospitalData changerHospitalData)
    {
        root = new GridPane();
        ObservableList<String> ways = FXCollections.observableArrayList("Направление поликлиники", "Доставлен скорой помощью");
        wayOfComing = new ComboBox(ways);
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
        row.setPercentHeight(20);

        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);

        Label labelWayOfComing = new Label("Способ поступления");
        Label labelDiagnosis = new Label("Диагноз");
        Label labelDateOfComing = new Label("Дата поступления");
        Label labelWardNumber = new Label("Номер палаты");

        root.add(labelWayOfComing, 0, 0);
        root.add(labelDiagnosis, 0, 1);
        root.add(wayOfComing, 1, 0);
        root.add(diagnosis, 1, 1);
        root.add(labelDateOfComing, 0, 2);
        root.add(labelWardNumber, 0, 3);
        root.add(dateOfComing, 1, 2);
        root.add(wardNumber, 1, 3);
    }

    public GridPane getRoot() {
        return root;
    }

    public ComboBox getWayOfComing() {
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
