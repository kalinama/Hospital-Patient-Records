package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class DischargeAlertForm {

    private GridPane root;
    private ComboBox<String> reason;

    private DatePicker dischargingDate;

    DischargeAlertForm()
    {
        root = new GridPane();
        ObservableList<String> reasons = FXCollections.observableArrayList("Выздоровление", "Направление в санаторий", "Перевод в другую больницу");
        reason = new ComboBox<>(reasons);
        dischargingDate = new DatePicker();
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

       Label labelreason = new Label("Причина выписки");
       Label date = new Label("Дата выписки");




        root.add(labelreason, 0, 0);
        root.add(date, 0, 1);
        root.add(reason, 1, 0);
        root.add(dischargingDate, 1, 1);
    }

    public GridPane getRoot() {
        return root;
    }

    public ComboBox<String> getReason() {
        return reason;
    }

    public DatePicker getDischargingDate() {
        return dischargingDate;
    }
}
