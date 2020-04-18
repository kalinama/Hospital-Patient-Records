package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.Main;
import sample.controller.ChangerHospitalData;
import sample.model.Patient;
import sample.model.PatientTreatment;

public class ShowInformationForm {

    private static final String TEXT_COMING = "Поступление в больницу-";
    private static final String TITLE = "Информация о пациенте";
    private GridPane root;
    private ListView<String> listView;
    private Patient patient;
    private ObservableList<String> comingToHospital;
   // Alert showInformationAlert;

    private Label labelFullName;
    private Label labelGender;
    private Label labelAge;
    private Label labelHeight;
    private Label labelWeight;
    private Label labelDistinguishingMarks;
    private Button buttonAddComing;
    ChangerHospitalData changerHospitalData;

   // private ListOfPatientForm listOfPatientForm;
    private SpecialHospitalComingInformationForm specialHospitalComingInformationForm;
    private EditPatientGeneralInfAlertForm editPatientGeneralInfAlertForm;
    private NewPatientComingAlertForm newPatientComingAlertForm;


    ShowInformationForm(ChangerHospitalData changerHospitalData)
{
    root = new GridPane();
    //this.listOfPatientForm = listOfPatientForm;
    comingToHospital = FXCollections.observableArrayList();
    this.changerHospitalData = changerHospitalData;
    //this.showInformationAlert = new Alert(Alert.AlertType.NONE);
    listView = new ListView<String>(comingToHospital);
    createForm();
    Scenes.getInstance().setShowInformationalScene(new Scene(root, Scenes.getWidth(), Scenes.getHeight()));
    specialHospitalComingInformationForm = new SpecialHospitalComingInformationForm(changerHospitalData);
    editPatientGeneralInfAlertForm = new EditPatientGeneralInfAlertForm();
    newPatientComingAlertForm = new NewPatientComingAlertForm(changerHospitalData);

}

public void openShowInformationAlert(Patient patient)
{

    this.patient = patient;
    renewForm();
    Main.getpStage().setScene(Scenes.getInstance().getShowInformationalScene());
}

private void initComingHistoryList()
{
    comingToHospital.clear();
    int num_coming=1;
    for (PatientTreatment patientTreatment: patient.getComingHistory())
        comingToHospital.add(TEXT_COMING + num_coming++);
}

    private void createForm() {


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

       Button buttonToMainView = new Button("Назад");
       Button buttonShowSpecialInf = new Button("Подробнее");
       Button buttonEditPatient = new Button("Редактировать");
       buttonAddComing = new Button("Добавить поступление");

        buttonToMainView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getpStage().setScene(Scenes.getInstance().getListOfPatientFormScene());
            }
        });
        buttonShowSpecialInf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = listView.getSelectionModel().getSelectedItem();
                int numb = name.indexOf("-");
                name=name.substring(numb+1);

                specialHospitalComingInformationForm.openSpecialHospitalComingInformationForm
                        (patient.getComingHistory().get(Integer.parseInt(name)-1));
            }

        });
        buttonEditPatient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             showEditAlert();
            }
        });
        buttonAddComing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showAddComingAlert();
            }
        });

     labelFullName = new Label();
     labelGender = new Label();
     labelAge = new Label();
     labelHeight = new Label();
     labelWeight = new Label();
     labelDistinguishingMarks = new Label();



        root.add(listView, 1, 6,2,1);
        root.add(labelFullName, 1, 0);
        root.add(labelGender, 1, 1);
        root.add(labelAge, 1, 2);
        root.add(labelHeight, 1, 3);
        root.add(labelWeight, 1, 4);
        root.add(labelDistinguishingMarks, 1, 5);
        root.add(buttonToMainView,0,0);
        root.add(buttonShowSpecialInf,0,1);
        root.add(buttonEditPatient,0,2);
        root.add(buttonAddComing,0,3);
    }


    private void renewForm()
    {
        initComingHistoryList();
        labelFullName.setText("ФИО: "+patient.getFullName().toString());
        labelGender.setText("Пол: " +patient.getGender());
        if (patient.getAge()!=0) labelAge.setText("Возраст: " + patient.getAge());
        else labelAge.setText("Возраст: ");
        if (patient.getHeight()!=0)  labelHeight.setText("Рост: " + patient.getHeight());
        else labelHeight.setText("Рост: " );
        if (patient.getWeight()!=0) labelWeight.setText("Вес: " + patient.getWeight());
        else labelWeight.setText("Вес: ");
        if (patient.getDescription()!=null) labelDistinguishingMarks.setText("Особые приметы: "+patient.getDescription());
        else labelDistinguishingMarks.setText("Особые приметы: отсутствуют");

        if(patient.getComingHistory().get(patient.getComingHistory().size()-1).getDischarged()) buttonAddComing.setDisable(false);
        else buttonAddComing.setDisable(true);
    }

    private void showEditAlert()
    {
        Alert editAlert = new Alert(Alert.AlertType.NONE);
        editAlert.getDialogPane().setContent(editPatientGeneralInfAlertForm.getRoot());

        editAlert.getButtonTypes().add(ButtonType.CLOSE);

        Button buttonEdit = new Button("Редактировать");

        editPatientGeneralInfAlertForm.getRoot().add(buttonEdit, 0, 8, 2, 1);
        buttonEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int weight = 0, height = 0;
                if (!editPatientGeneralInfAlertForm.getHeight().getText().equals(""))
                    height = Integer.parseInt(editPatientGeneralInfAlertForm.getHeight().getText());
                if (!editPatientGeneralInfAlertForm.getWeight().getText().equals(""))
                    weight = Integer.parseInt(editPatientGeneralInfAlertForm.getWeight().getText());


                if (!(editPatientGeneralInfAlertForm.getName().getText().isEmpty()) && !(editPatientGeneralInfAlertForm.getSurname().getText().isEmpty())
                            && editPatientGeneralInfAlertForm.getGender().getValue() != null && weight >= 0
                            && height >= 0) {


                        changerHospitalData.editGeneralData(editPatientGeneralInfAlertForm.getName().getText(), editPatientGeneralInfAlertForm.getSurname().getText(),
                                editPatientGeneralInfAlertForm.getPatronymic().getText(), editPatientGeneralInfAlertForm.getAge().getValue(),
                                editPatientGeneralInfAlertForm.getGender().getValue().toString(), weight,
                                height, editPatientGeneralInfAlertForm.getMarks().getText(), patient);

                        renewForm();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Ошибка");
                        errorAlert.setHeaderText("Невозможно изменить данные");
                        errorAlert.setContentText("Необходимо, чтобы все обязательные поля были заполнены");
                        errorAlert.show();
                    }

            }
        });

        editAlert.showAndWait();
    }

    public void showAddComingAlert()
    {
        Alert addComingAlert = new Alert(Alert.AlertType.NONE);
        addComingAlert.getDialogPane().setContent(newPatientComingAlertForm.getRoot());

        addComingAlert.getButtonTypes().add(ButtonType.CLOSE);
        Button buttonAdd = new Button("Добавить");

        newPatientComingAlertForm.getRoot().add(buttonAdd,0,4,2,1);

        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!newPatientComingAlertForm.getDiagnosis().getText().isEmpty()&&
                newPatientComingAlertForm.getWayOfComing().getValue()!=null && newPatientComingAlertForm.getDateOfComing() !=null
                && newPatientComingAlertForm.getDateOfComing().getValue().
                        isAfter(patient.getComingHistory().get(patient.getComingHistory().size()-1).getDischargeDate())
                && newPatientComingAlertForm.getWardNumber().getValue()!=null) {

                    changerHospitalData.newComing(newPatientComingAlertForm.getDateOfComing().getValue(),
                            newPatientComingAlertForm.getWardNumber().getValue().toString(),
                            newPatientComingAlertForm.getWayOfComing().getValue().toString(),
                            newPatientComingAlertForm.getDiagnosis().getText(), patient);
                    renewForm();
                }

                else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Ошибка");
                    errorAlert.setHeaderText("Невозможно добавить");
                    errorAlert.setContentText("Необходимо, чтобы все поля были заполнены и дата поступления не противрочела дате последней выписки");
                    errorAlert.show();
                }
            }
        });

        addComingAlert.showAndWait();
    }

}
