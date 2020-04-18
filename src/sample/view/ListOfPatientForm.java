package sample.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.controller.ChangerHospitalData;
import sample.controller.controllerRequest.RequestDataForming;
import sample.model.HospitalWardsData;
import sample.model.Patient;
import sample.model.PatientsData;
import sample.model.modelRequest.PatientsDataRequest;
import sample.view.viewRequest.FirstRequestView;
import sample.view.viewRequest.SecondRequestView;
import sample.view.viewRequest.ThirdRequestView;

public class ListOfPatientForm {

    private static final String BUTTON_ADD_PATIENT = "Добавить";
    private static final String BUTTON_SHOW_PATIENT = "Показать";
    private static final String BUTTON_DELETE_PATIENT = "Удалить";
    private static final String BUTTON_SHOW_WARDS = "Информация о палатах";

    private GridPane root;
    private TableFormPatients tableFormPatients;

    private PatientsData patientsData;
    private ShowInformationForm showInformationAlert;
    private ShowHospitalWardInformationView showHospitalWardInformationView;
    private ChangerHospitalData changerHospitalData;
    private CreateNewPatientAlertForm createNewPatientAlertForm;

    private FirstRequestView firstRequestView;
    private SecondRequestView secondRequestView;
    private ThirdRequestView thirdRequestView;

    private PatientsDataRequest patientsDataRequest;
    private RequestDataForming requestDataForming;

   private Scenes scenes;


   public ListOfPatientForm(PatientsData patientsData, HospitalWardsData hospitalWardsData,
                            ChangerHospitalData changerHospitalData, PatientsDataRequest patientsDataRequest, RequestDataForming requestDataForming) {

       root = new GridPane();
       this.patientsData = patientsData;


       this.changerHospitalData = changerHospitalData;
      tableFormPatients = new TableFormPatients(patientsData.getPatients());
 showInformationAlert = new ShowInformationForm(changerHospitalData);
 showHospitalWardInformationView =  new ShowHospitalWardInformationView(hospitalWardsData, changerHospitalData);
createNewPatientAlertForm = new CreateNewPatientAlertForm(changerHospitalData);
firstRequestView = new FirstRequestView(patientsDataRequest);
secondRequestView = new SecondRequestView(patientsDataRequest, requestDataForming);
this.patientsDataRequest = patientsDataRequest;
this.requestDataForming = requestDataForming;
this.thirdRequestView = new ThirdRequestView(patientsDataRequest, requestDataForming);
      createForm();
      Scenes.getInstance().setListOfPatientFormScene(new Scene(root, Scenes.getWidth(), Scenes.getHeight()));

    }


    private void createForm(){
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(14);

        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(75);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(15);
        RowConstraints row3 = new RowConstraints();
        row2.setPercentHeight(10);
        root.getRowConstraints().add(row2);
        root.getRowConstraints().add(row3);
        root.getRowConstraints().add(row);

        Button buttonAddPatient = new Button(BUTTON_ADD_PATIENT);
        Button buttonDeletePatient = new Button(BUTTON_DELETE_PATIENT);
        Button buttonShowInfPatient = new Button(BUTTON_SHOW_PATIENT);
        Button buttonShowHospitalWards = new Button(BUTTON_SHOW_WARDS);
        Button buttonFirstRequest = new Button("Поиск-1");
        Button buttonSecondRequest = new Button("Поиск-2");
        Button buttonThirdRequest = new Button("Поиск-3");


        root.add(tableFormPatients.getRootOfTableForm(), 1, 2, 6,1);
        root.add(buttonShowHospitalWards,4,0,2,1);
        root.add(buttonAddPatient, 3,0);
        root.add(buttonDeletePatient, 2,0);
        root.add(buttonShowInfPatient, 1,0);
        root.add(buttonFirstRequest,0,0);
        root.add(buttonSecondRequest,0,1);
        root.add(buttonThirdRequest,0,2);


        buttonShowInfPatient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               showInformationAlert.openShowInformationAlert(tableFormPatients.getTable().getSelectionModel().getSelectedItem());

            }
        });

        buttonShowHospitalWards.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showHospitalWardInformationView.openSpecialHospitalComingInformationForm();
            }
        });
        buttonAddPatient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createNewPatient();
            }
        });
        buttonDeletePatient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changerHospitalData.deletePatient(tableFormPatients.getTable().getSelectionModel().getSelectedItem());
                tableFormPatients.renewTable(patientsData.getPatients());
            }
        });
        buttonFirstRequest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tableFormPatients.getSearchTextField().getText().isEmpty())
                {
                    String patronymic = tableFormPatients.getSearchTextField().getText();
                    String surname = patronymic.substring(0, patronymic.indexOf(" "));
                    patronymic = patronymic.substring(patronymic.indexOf(" ") + 1);
                    String name = patronymic.substring(0, patronymic.indexOf(" "));
                    patronymic = patronymic.substring(patronymic.indexOf(" ") + 1);

                requestDataForming.requestFirst(name, surname, patronymic);
                firstRequestView.openView();
            }
        }});
        tableFormPatients.getTable().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tableFormPatients.getSearchTextField().setText(tableFormPatients.getTable().getSelectionModel().getSelectedItem().getFullName().toString());
            }
        });
        buttonSecondRequest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondRequestView.openView();
            }
        });
        buttonThirdRequest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                thirdRequestView.openView();
            }
        });
        }



    private void createNewPatient()
    {
        Alert createGeneralInfAlert = new Alert(Alert.AlertType.NONE);
        createGeneralInfAlert.getDialogPane().setContent(createNewPatientAlertForm.getRoot());

        createGeneralInfAlert.getButtonTypes().add(ButtonType.CLOSE);

        Button buttonEdit = new Button("Добавить");

        createNewPatientAlertForm.getRoot().add(buttonEdit, 0, 8, 2, 1);
        buttonEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              Patient p;
                int weight = 0, height = 0;
                if (!createNewPatientAlertForm.getHeight().getText().equals("")) height = Integer.parseInt(createNewPatientAlertForm.getHeight().getText());
                if (!createNewPatientAlertForm.getWeight().getText().equals("")) weight = Integer.parseInt(createNewPatientAlertForm.getWeight().getText());

                if (!(createNewPatientAlertForm.getName().getText().isEmpty()) && !(createNewPatientAlertForm.getSurname().getText().isEmpty())
                        && createNewPatientAlertForm.getGender().getValue() != null && weight >= 0
                        && height >= 0&&!createNewPatientAlertForm.getDiagnosis().getText().isEmpty()&&
                        createNewPatientAlertForm.getWayOfComing().getValue()!=null && createNewPatientAlertForm.getDateOfComing() !=null
                        && createNewPatientAlertForm.getWardNumber().getValue()!=null) {

                   p = changerHospitalData.newPatient(createNewPatientAlertForm.getName().getText(), createNewPatientAlertForm.getSurname().getText(),
                            createNewPatientAlertForm.getPatronymic().getText(), createNewPatientAlertForm.getAge().getValue(),
                            createNewPatientAlertForm.getGender().getValue().toString(), weight, height,
                            createNewPatientAlertForm.getMarks().getText());

                    changerHospitalData.newComing(createNewPatientAlertForm.getDateOfComing().getValue(),
                            createNewPatientAlertForm.getWardNumber().getValue().toString(),
                            createNewPatientAlertForm.getWayOfComing().getValue().toString(),
                            createNewPatientAlertForm.getDiagnosis().getText(), p);

                    tableFormPatients.renewTable(patientsData.getPatients());


                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Ошибка");
                    errorAlert.setHeaderText("Невозможно изменить данные");
                    errorAlert.setContentText("Необходимо, чтобы все обязательные поля были заполнены");
                    errorAlert.show();
                }
            }
        });

        createGeneralInfAlert.showAndWait();
    }

    public GridPane getRoot() {
        return root;
    }
}
