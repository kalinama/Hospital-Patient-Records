package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class HospitalWardCreateAndEditAlertForm {

    private GridPane root;
    private TextField newPhone;

    private TextField newName;

    HospitalWardCreateAndEditAlertForm()
    {
        root = new GridPane();
        newPhone = new TextField();
        newName = new TextField();
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

        Label labelWayOfComing = new Label("Телефон");
        Label labelDiagnosis = new Label("Номер палаты");

        root.add(labelWayOfComing, 0, 0);
        root.add(labelDiagnosis, 0, 1);
        root.add(newPhone, 1, 0);
        root.add(newName, 1, 1);
    }

    public GridPane getRoot() {
        return root;
    }

    public TextField getNewPhone() {
        return newPhone;
    }

    public TextField getNewName() {
        return newName;
    }
}
