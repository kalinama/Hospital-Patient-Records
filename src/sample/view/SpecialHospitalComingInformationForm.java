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
import sample.model.*;

import java.time.LocalDate;

public class SpecialHospitalComingInformationForm {

    private GridPane root;
    private ObservableList<HospitalWardPatientData> wardHistory;
    private TableView<HospitalWardPatientData> table;
    PatientTreatment patientTreatment;

    private Label labelDiagnosis;
    private Label labelWayOfComing;
    private Label labelDateOfComing;
    private Label labelIsDischarged;
    private Label labelDischargeDate;
    private Label labelDischargeReason;

    private Button dischargeButton;
    private Button buttonEditPatient;
    private Button buttonComeToWard;

    private DischargeAlertForm dischargeAlertForm;
    private ChangerHospitalData changerHospitalData;
    private EditPatientTreatmentInfAlertForm editPatientTreatmentInfAlertForm;
    private ComeToAnotherWardAlertForm comeToAnotherWardAlertForm;

    SpecialHospitalComingInformationForm(ChangerHospitalData changerHospitalData)
    {
        root = new GridPane();
        this.changerHospitalData = changerHospitalData;
       // this.listOfPatientForm = listOfPatientForm;
        wardHistory = FXCollections.observableArrayList();
        //this.showInformationAlert = new Alert(Alert.AlertType.NONE);
        createForm();
        Scenes.getInstance().setSpecialHospitalComingInformationFormScene(new Scene(root, Scenes.getWidth(), Scenes.getHeight()));
       dischargeAlertForm = new DischargeAlertForm();
       editPatientTreatmentInfAlertForm = new EditPatientTreatmentInfAlertForm();
       comeToAnotherWardAlertForm = new ComeToAnotherWardAlertForm(changerHospitalData);


    }


    public void openSpecialHospitalComingInformationForm(PatientTreatment patientTreatment)
    {
        this.patientTreatment= patientTreatment;
        wardHistory = patientTreatment.getWardHistory();
        renewForm();
        Main.getpStage().setScene(Scenes.getInstance().getSpecialHospitalComingInformationFormScene());
    }

private void createTable()
{
    table = new TableView<>();
    table.setPrefWidth(400);
    table.setPrefHeight(100);

    TableColumn<HospitalWardPatientData, String> numberWardColumn = new TableColumn<>("Номер палаты");
    numberWardColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
    numberWardColumn.setPrefWidth(100);

    TableColumn<HospitalWardPatientData, String> phoneNumberColumn = new TableColumn<>("Телефон палаты");
    phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    phoneNumberColumn.setPrefWidth(200);

    TableColumn<HospitalWardPatientData, LocalDate> dateOfComingWardColumn = new TableColumn<>("Дата перевода в палату");
    dateOfComingWardColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfComing"));
    dateOfComingWardColumn.setPrefWidth(200);

    table.getColumns().addAll( numberWardColumn,phoneNumberColumn, dateOfComingWardColumn);
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
        row.setPercentHeight(10);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(40);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row2);

        Button buttonToPrevView = new Button("Назад");
        // Button buttonDeletePatient = new Button(BUTTON_DELETE_PATIENT);
        //Button buttonShowInfPatient = new Button(BUTTON_SHOW_PATIENT);
        dischargeButton = new Button("Выписать");
        buttonEditPatient = new Button("Редактировать");
        buttonComeToWard = new Button("Перевести в другую палату");

        buttonToPrevView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getpStage().setScene(Scenes.getInstance().getShowInformationalScene());
            }
        });
        dischargeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showDischargeAlert();
            }
        });
        buttonEditPatient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showEditAlert();
            }
        });
        buttonComeToWard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComeToWardAlert();
            }
        });

        labelDiagnosis = new Label();
        labelWayOfComing = new Label();
        labelDateOfComing = new Label();
        labelIsDischarged = new Label();
        labelDischargeDate = new Label();
        labelDischargeReason = new Label();


        root.add(table, 1, 6,2,1);
        root.add(labelDiagnosis, 1, 0);
        root.add(labelWayOfComing, 1, 1);
        root.add(labelDateOfComing, 1, 2);
        root.add(labelIsDischarged, 1, 3);
        root.add(labelDischargeDate, 1, 4);
        root.add(labelDischargeReason, 1, 5);
        root.add(buttonToPrevView,0,0);
        root.add(dischargeButton, 0, 1);
        root.add(buttonEditPatient,0,2);
        root.add(buttonComeToWard,0,3);
    }


    private void renewForm()
    {
        wardHistory = patientTreatment.getWardHistory();
       table.setItems(wardHistory);

        labelDiagnosis.setText("Диагноз: "+patientTreatment.getDiagnosis());
        labelWayOfComing.setText("Способ поступления в больницу: " +patientTreatment.getWayOfComing());
        labelDateOfComing.setText("Дата поступления: " +patientTreatment.getDateOfComing().toString());
        if (patientTreatment.getDischarged()) labelIsDischarged.setText("Выписан(а)");
        else labelIsDischarged.setText("Находится в больнице");
        if (patientTreatment.getDischargeDate()!=null) {
            labelDischargeDate.setText("Дата выписки: " + patientTreatment.getDischargeDate().toString());
            labelDischargeReason.setText("Причина выписки: " + patientTreatment.getDischargeReason());
            dischargeButton.setDisable(true);
            buttonEditPatient.setDisable(true);
            buttonComeToWard.setDisable(true);
        }
        else {
            labelDischargeDate.setText("Дата выписки: отсутствует");
            labelDischargeReason.setText("Причина выписки: отсутствует");
            dischargeButton.setDisable(false);
            buttonEditPatient.setDisable(false);
            buttonComeToWard.setDisable(false);
        }

    }

    private void showDischargeAlert()
    {
        Alert dischargeAlert = new Alert(Alert.AlertType.NONE);
        dischargeAlert.getDialogPane().setContent(dischargeAlertForm.getRoot());

        dischargeAlert.getButtonTypes().add(ButtonType.CLOSE);

        Button buttonDischarge = new Button("Выписать");

        dischargeAlertForm.getRoot().add(buttonDischarge, 0, 2, 2, 1);
        buttonDischarge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(dischargeAlertForm.getReason().getValue().toString().isEmpty())&& dischargeAlertForm.getDischargingDate().getValue()!=null&&
                        dischargeAlertForm.getDischargingDate().getValue().isAfter(patientTreatment.getDateOfComing())) {

                    changerHospitalData.dischargePatient(patientTreatment, dischargeAlertForm.getReason().getValue().toString(),
                            dischargeAlertForm.getDischargingDate().getValue());
                    renewForm();
                }

                 else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Ошибка");
                    errorAlert.setHeaderText("Невозможно выписать с такими данными");
                    errorAlert.setContentText("Необходимо, чтобы все поля были заполнены и дата выписки не противоречила дате поступления");
                    errorAlert.show();
                }
            }
        });

        dischargeAlert.showAndWait();
    }

    private void showEditAlert()
    {
        Alert editAlert = new Alert(Alert.AlertType.NONE);
        editAlert.getDialogPane().setContent(editPatientTreatmentInfAlertForm.getRoot());

        editAlert.getButtonTypes().add(ButtonType.CLOSE);

        Button buttonEdit = new Button("Редактировать");

        editPatientTreatmentInfAlertForm.getRoot().add(buttonEdit, 0, 2, 2, 1);
        buttonEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!editPatientTreatmentInfAlertForm.getDiagnosis().getText().isEmpty()&&
                editPatientTreatmentInfAlertForm.getWayOfComing().getValue()!=null) {

                    changerHospitalData.editTreatmentData(patientTreatment, editPatientTreatmentInfAlertForm.getDiagnosis().getText(),
                            editPatientTreatmentInfAlertForm.getWayOfComing().getValue().toString());
                    renewForm();
                }

                else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Ошибка");
                    errorAlert.setHeaderText("Невозможно редактировать");
                    errorAlert.setContentText("Необходимо, чтобы все поля были заполнены");
                    errorAlert.show();
                }
            }
        });

        editAlert.showAndWait();
    }

    private void showComeToWardAlert()
    {
        Alert comeToAnotherWardAlert = new Alert(Alert.AlertType.NONE);
        comeToAnotherWardAlert.getDialogPane().setContent(comeToAnotherWardAlertForm.getRoot());

        comeToAnotherWardAlert.getButtonTypes().add(ButtonType.CLOSE);

        Button buttonCome = new Button("Перевести");

        comeToAnotherWardAlertForm.getRoot().add(buttonCome, 0, 2, 2, 1);
        buttonCome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (comeToAnotherWardAlertForm.getDateOfComing().getValue()!=null && comeToAnotherWardAlertForm.getNumbOfWard().getValue()!=null&&
                        comeToAnotherWardAlertForm.getDateOfComing().getValue().isAfter(patientTreatment.getWardHistory().
                                get(patientTreatment.getWardHistory().size()-1).getDateOfComing()) &&
                        !comeToAnotherWardAlertForm.getNumbOfWard().getValue().isEmpty() &&
                        !comeToAnotherWardAlertForm.getNumbOfWard().getValue().equals(patientTreatment.getWardHistory().
                                get(patientTreatment.getWardHistory().size()-1).getNumber())) {


                    changerHospitalData.cometoAnotherWard(comeToAnotherWardAlertForm.getDateOfComing().getValue(),
                            comeToAnotherWardAlertForm.getNumbOfWard().getValue(), patientTreatment);

                    renewForm();

                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Ошибка");
                    errorAlert.setHeaderText("Невозможно изменить данные");
                    errorAlert.setContentText("Необходимо, чтобы все поля были заполнены, и дата перевода не противоречила дате поступления");
                    errorAlert.show();
                }

            }
        });

        comeToAnotherWardAlert.showAndWait();
    }

}
