package sample.view.viewRequest;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.Main;
import sample.model.HospitalWard;
import sample.model.HospitalWardsData;
import sample.model.Patient;
import sample.model.modelRequest.PatientRequest;
import sample.model.modelRequest.PatientsDataRequest;
import sample.view.Scenes;

public class FirstRequestView {

    private GridPane root;
    private TableView<PatientRequest> table;
    private PatientsDataRequest patientsDataRequest;

    public FirstRequestView(PatientsDataRequest patientsDataRequest)
    {
        root = new GridPane();
        this.patientsDataRequest = patientsDataRequest;
        createForm();
        Scenes.getInstance().setFirstRequestScene(new Scene(root, Scenes.getWidth(), Scenes.getHeight()));

    }

    public void openView()
    {
        renewView();
        Main.getpStage().setScene(Scenes.getInstance().getFirstRequestScene());

    }

    private void createTable()
    {
        table = new TableView<>(patientsDataRequest.getPatientFirstRequests());
        table.setPrefWidth(500);
        table.setPrefHeight(300);

        TableColumn<PatientRequest, String> fullNameColumn = new TableColumn<>("ФИО");
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        fullNameColumn.setPrefWidth(200);

        TableColumn<PatientRequest, String> ageColumn = new TableColumn<>("Возраст");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageColumn.setPrefWidth(100);

        TableColumn<PatientRequest, String> numberWardColumn = new TableColumn<>("Номер палаты");
        numberWardColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        numberWardColumn.setPrefWidth(100);

        TableColumn<PatientRequest, String> phoneNumberColumn = new TableColumn<>("Телефон палаты");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumberColumn.setPrefWidth(100);


        table.getColumns().addAll(fullNameColumn, ageColumn,numberWardColumn,phoneNumberColumn);
    }
    private void createForm() {
        createTable();
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(10);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(90);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column2);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(100);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(40);
        root.getRowConstraints().add(row);


        Button buttonToMainView = new Button("Назад");
        // Button buttonDeletePatient = new Button(BUTTON_DELETE_PATIENT);
        //Button buttonShowInfPatient = new Button(BUTTON_SHOW_PATIENT);


        buttonToMainView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getpStage().setScene(Scenes.getInstance().getListOfPatientFormScene());
            }
        });

        root.add(table, 1, 0);
        // root.add(labelDiagnosis, 1, 0);
        //root.add(labelWayOfComing, 1, 1);
        //root.add(labelDateOfComing, 1, 2);
        //root.add(labelIsDischarged, 1, 3);
        //root.add(labelDischargeDate, 1, 4);
        //root.add(labelDischargeReason, 1, 5);
        root.add(buttonToMainView,0,0);
    }

    public void renewView()
    {
        table.setItems(patientsDataRequest.getPatientFirstRequests());
    }

}
