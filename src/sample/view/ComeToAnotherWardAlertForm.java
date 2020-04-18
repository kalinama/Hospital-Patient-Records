package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.controller.ChangerHospitalData;
import sample.model.HospitalWard;

public class ComeToAnotherWardAlertForm {

    private GridPane root;
    private ComboBox<String> numbOfWard;

    private DatePicker dateOfComing;

    ComeToAnotherWardAlertForm(ChangerHospitalData changerHospitalData)
    {
        root = new GridPane();
        ObservableList<String> wards = FXCollections.observableArrayList();
        for (HospitalWard hospitalWard: changerHospitalData.getWardsData())
            wards.add(hospitalWard.getNumber());

        numbOfWard = new ComboBox<>(wards);
        dateOfComing = new DatePicker();
        createForm();
    }

    private void createForm()
    {

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(50);

        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(33);

        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);

        Label labelNumb = new Label("Номер палаты");
        Label date = new Label("Дата перевода");

        root.add(labelNumb, 0, 0);
        root.add(date, 0, 1);
        root.add(numbOfWard, 1, 0);
        root.add(dateOfComing, 1, 1);
    }

    public GridPane getRoot() {
        return root;
    }

    public ComboBox<String> getNumbOfWard() {
        return numbOfWard;
    }

    public DatePicker getDateOfComing() {
        return dateOfComing;
    }
}
