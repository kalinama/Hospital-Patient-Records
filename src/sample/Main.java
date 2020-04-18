package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.controller.ChangerHospitalData;
import sample.controller.ControllerMainList;
import sample.controller.controllerRequest.RequestDataForming;
import sample.model.HospitalWardsData;
import sample.model.PatientsData;
import sample.model.modelRequest.PatientsDataRequest;
import sample.view.ListOfPatientForm;
import sample.view.Scenes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main extends Application {
    private static Connection conn;

    private static Stage pStage = new Stage();

    public static Connection returnCon() {
        return conn;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        PatientsData patientsData = new PatientsData();
        HospitalWardsData hospitalWardsData = new HospitalWardsData();
        ControllerMainList controllerMainList = new ControllerMainList(patientsData, hospitalWardsData);
        ChangerHospitalData changerHospitalData = new ChangerHospitalData(patientsData, hospitalWardsData);

        PatientsDataRequest patientsDataRequest = new PatientsDataRequest(patientsData);
        RequestDataForming requestDataForming = new RequestDataForming(patientsDataRequest);

        ListOfPatientForm listOfPatientForm = new ListOfPatientForm(patientsData, hospitalWardsData, changerHospitalData, patientsDataRequest, requestDataForming);



       // Parent root = FXMLLoader.load(getClass().getResource("fxml/WinMain.fxml"));
        primaryStage.setTitle("HOSPITAL");
        primaryStage.setScene(Scenes.getInstance().getListOfPatientFormScene());
        setpStage(primaryStage);
        pStage.show();

    }

    public static Connection connect() throws SQLException {
        final String url = "jdbc:postgresql://localhost/postgres";
        final String user = "postgres";
        final String password = "12345";
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {

        connect();
        launch(args);

    }

    public static Stage getpStage() {
        return pStage;
    }

    public static void setpStage(Stage pStage) {
        Main.pStage = pStage;
    }
}

