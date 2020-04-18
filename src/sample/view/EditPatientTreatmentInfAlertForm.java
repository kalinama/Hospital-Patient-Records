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

public class EditPatientTreatmentInfAlertForm {
    private GridPane root;
    private ComboBox wayOfComing;

    private TextField diagnosis;

    EditPatientTreatmentInfAlertForm()
    {
        root = new GridPane();
        ObservableList<String> ways = FXCollections.observableArrayList("Направление поликлиники", "Доставлен скорой помощью");
        wayOfComing = new ComboBox(ways);
        diagnosis = new TextField();
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

        Label labelWayOfComing = new Label("Способ поступления");
        Label labelDiagnosis = new Label("Диагноз");

        root.add(labelWayOfComing, 0, 0);
        root.add(labelDiagnosis, 0, 1);
        root.add(wayOfComing, 1, 0);
        root.add(diagnosis, 1, 1);
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
}
