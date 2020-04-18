package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.Main;
import sample.controller.ChangerHospitalData;
import sample.model.HospitalWard;
import sample.model.HospitalWardPatientData;
import sample.model.HospitalWardsData;
import sample.model.PatientTreatment;

import java.time.LocalDate;

public class ShowHospitalWardInformationView {
    private GridPane root;
    private ObservableList<HospitalWard> wards;
    private TableView<HospitalWard> table;
    private HospitalWardCreateAndEditAlertForm hospitalWardCreateAndEditAlertForm;
    private HospitalWardsData hospitalWardsData;
    private ChangerHospitalData changerHospitalData;

    ShowHospitalWardInformationView(HospitalWardsData hospitalWardsData, ChangerHospitalData changerHospitalData)
    {
        root = new GridPane();
        hospitalWardCreateAndEditAlertForm = new HospitalWardCreateAndEditAlertForm();
        this.hospitalWardsData = hospitalWardsData;
        this.changerHospitalData = changerHospitalData;
        // this.listOfPatientForm = listOfPatientForm;
        wards = hospitalWardsData.getHospitalWards();
        //this.showInformationAlert = new Alert(Alert.AlertType.NONE);
        createForm();
        Scenes.getInstance().setShowHospitalWardInformationViewScene(new Scene(root, Scenes.getWidth(), Scenes.getHeight()));


    }


    public void openSpecialHospitalComingInformationForm()
    {

        Main.getpStage().setScene(Scenes.getInstance().getShowHospitalWardInformationViewScene());
    }

    private void createTable()
    {
        table = new TableView<>(wards);
        table.setPrefWidth(500);
        table.setPrefHeight(300);

        TableColumn<HospitalWard, String> numberWardColumn = new TableColumn<>("Номер палаты");
        numberWardColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        numberWardColumn.setPrefWidth(500);

        TableColumn<HospitalWard, String> phoneNumberColumn = new TableColumn<>("Телефон палаты");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumberColumn.setPrefWidth(500);


        table.getColumns().addAll( numberWardColumn,phoneNumberColumn);
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
        row.setPercentHeight(20);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(80);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row2);


        Button buttonToMainView = new Button("Назад");
        Button buttonEditWards = new Button("Редактировать");
        Button buttonAddWard = new Button("Добавить палату");
        // Button buttonDeletePatient = new Button(BUTTON_DELETE_PATIENT);
        //Button buttonShowInfPatient = new Button(BUTTON_SHOW_PATIENT);

        buttonToMainView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getpStage().setScene(Scenes.getInstance().getListOfPatientFormScene());
            }
        });
        buttonEditWards.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openEditAlert(table.getSelectionModel().getSelectedItem());
            }
        });
        buttonAddWard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openAddAlert();
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
        root.add(buttonEditWards,1,0);
        root.add(buttonAddWard,2,0);
    }

    private void openEditAlert(HospitalWard hospitalWard)
    {
        Alert editWardAlert = new Alert(Alert.AlertType.NONE);
        GridPane alertRoot = new GridPane();
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(100);

        alertRoot.getColumnConstraints().add(column);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(50);
        alertRoot.getRowConstraints().add(row);

        editWardAlert.getDialogPane().setContent(alertRoot);

        editWardAlert.getButtonTypes().add(ButtonType.CLOSE);


        Button buttonEdit = new Button("Редактировать");

        alertRoot.add(buttonEdit, 0, 1);
        alertRoot.add(hospitalWardCreateAndEditAlertForm.getRoot(),0,0);
        buttonEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (checkNewWardName(hospitalWard, hospitalWardCreateAndEditAlertForm.getNewName().getText())&&
                        !hospitalWardCreateAndEditAlertForm.getNewName().getText().isEmpty()) {

                    changerHospitalData.editHospitalWard(hospitalWardCreateAndEditAlertForm.getNewName().getText(),
                            hospitalWardCreateAndEditAlertForm.getNewPhone().getText(), hospitalWard);

                    renewView();
                }

                else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Ошибка");
                    errorAlert.setHeaderText("Невозможно редактировать");
                    errorAlert.setContentText("Необходимо, чтобы новое название не совпадало с одним из существующих");
                    errorAlert.show();
                }
            }
        });

        editWardAlert.showAndWait();
    }

    private void openAddAlert()
    {
        Alert addAlert = new Alert(Alert.AlertType.NONE);
        GridPane alertRoot = new GridPane();
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(100);

        alertRoot.getColumnConstraints().add(column);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(50);
        alertRoot.getRowConstraints().add(row);

        addAlert.getDialogPane().setContent(alertRoot);

        addAlert.getButtonTypes().add(ButtonType.CLOSE);


        Button buttonAdd = new Button("Добавить");

        alertRoot.add(buttonAdd, 0, 1);
        alertRoot.add(hospitalWardCreateAndEditAlertForm.getRoot(),0,0);
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (checkNewWardName(hospitalWardCreateAndEditAlertForm.getNewName().getText())&&
                        !hospitalWardCreateAndEditAlertForm.getNewName().getText().isEmpty()) {

                    changerHospitalData.addHospitalWard(hospitalWardCreateAndEditAlertForm.getNewName().getText(),
                            hospitalWardCreateAndEditAlertForm.getNewPhone().getText());
                    renewView();
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Ошибка");
                    errorAlert.setHeaderText("Невозможно добавить");
                    errorAlert.setContentText("Необходимо, чтобы новое название не совпадало с одним из существующих");
                    errorAlert.show();
                }

            }

        });
        addAlert.showAndWait();
    }

    private boolean checkNewWardName(HospitalWard changingHospitalWard, String newName)
    {
        for (HospitalWard hospitalWard:hospitalWardsData.getHospitalWards())
            if (hospitalWard.getNumber().equals(newName)&&hospitalWard!=changingHospitalWard) return false;
            return true;
    }
    private boolean checkNewWardName( String newName)
    {
        for (HospitalWard hospitalWard:hospitalWardsData.getHospitalWards())
            if (hospitalWard.getNumber().equals(newName)) return false;
        return true;
    }

    public void renewView()
    {
        table.setItems(hospitalWardsData.getHospitalWards());
    }


}
