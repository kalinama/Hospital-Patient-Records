package sample.view.viewRequest;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.Main;
import sample.controller.controllerRequest.RequestDataForming;
import sample.model.modelRequest.PatientRequest;
import sample.model.modelRequest.PatientsDataRequest;
import sample.view.Scenes;

import java.time.LocalDate;

public class SecondRequestView {
    private GridPane root;
    private TableView<PatientRequest> table;
    private PatientsDataRequest patientsDataRequest;
    private RequestDataForming requestDataForming;
    private DatePicker date;

    public SecondRequestView (PatientsDataRequest patientsDataRequest, RequestDataForming requestDataForming)
    {
        root = new GridPane();
        date = new DatePicker();
        this.patientsDataRequest = patientsDataRequest;
        this.requestDataForming = requestDataForming;
        createForm();
        Scenes.getInstance().setSecondRequestScene(new Scene(root, Scenes.getWidth(), Scenes.getHeight()));

    }

    public void openView()
    {
       // table.getColumns().clear();
       // createTable();
        Main.getpStage().setScene(Scenes.getInstance().getSecondRequestScene());

    }

    private void createTable()
    {
        table = new TableView<>();
        table.setPrefWidth(400);
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

        TableColumn<PatientRequest, LocalDate> dateOfComingColumn = new TableColumn<>("Дата поступления");
        dateOfComingColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfComing"));
        dateOfComingColumn.setPrefWidth(100);


        table.getColumns().addAll(fullNameColumn, ageColumn,numberWardColumn,dateOfComingColumn);
    }
    private void createForm() {
        createTable();
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(33);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(90);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(10);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(90);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row2);


        Button buttonToMainView = new Button("Назад");
        Button buttonSearch = new Button("Поиск");
        // Button buttonDeletePatient = new Button(BUTTON_DELETE_PATIENT);
        //Button buttonShowInfPatient = new Button(BUTTON_SHOW_PATIENT);


        buttonToMainView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getpStage().setScene(Scenes.getInstance().getListOfPatientFormScene());
            }
        });

        buttonSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (date.getValue()!=null)
                requestDataForming.requestSecond(date.getValue());
                renewView();

            }
        });

        root.add(table, 0, 1,3,1);
        // root.add(labelDiagnosis, 1, 0);
        //root.add(labelWayOfComing, 1, 1);
        //root.add(labelDateOfComing, 1, 2);
        //root.add(labelIsDischarged, 1, 3);
        //root.add(labelDischargeDate, 1, 4);
        //root.add(labelDischargeReason, 1, 5);
        root.add(buttonToMainView,0,0);
        root.add(date,1,0);
        root.add(buttonSearch,2,0);
    }

    public void renewView()
    {
        table.setItems(patientsDataRequest.getPatientSecondRequests());
    }


}
