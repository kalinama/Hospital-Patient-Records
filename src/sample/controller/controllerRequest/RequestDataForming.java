package sample.controller.controllerRequest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Main;
import sample.model.FullName;
import sample.model.Patient;
import sample.model.modelRequest.PatientRequest;
import sample.model.modelRequest.PatientsDataRequest;

import java.sql.*;
import java.time.LocalDate;

public class RequestDataForming {
    private Connection conn = Main.returnCon();
    private PatientsDataRequest patientsDataRequest;

    public RequestDataForming(PatientsDataRequest patientsDataRequest)
    {
        this.patientsDataRequest = patientsDataRequest;
    }

    public void requestFirst(String name, String surname, String patronymic)
    {
        try {PreparedStatement preparedStatementInner = conn.prepareStatement
                ("SELECT Patient_General_Inf.id_patient, name, surname, patronymic, date_of_birth, Patient_Treatment_Inf.id_ward, discharge_date, phone_number\n" +
                        "FROM Patient_General_Inf INNER JOIN Patient_Treatment_Inf\n" +
                        "ON Patient_General_Inf.id_patient= Patient_Treatment_Inf.id_patient\n" +
                        "INNER JOIN Hospital_Ward ON Patient_Treatment_Inf.id_ward = Hospital_Ward.id_ward\n" +
                        "WHERE discharge_date IS NULL AND name =? AND surname =? AND patronymic = ?;");

             preparedStatementInner.setString(1, name);
             preparedStatementInner.setString(2, surname);
            preparedStatementInner.setString(3, patronymic);

             ResultSet rs = preparedStatementInner.executeQuery();
            {
                ObservableList searchList = FXCollections.observableArrayList();

                while (rs.next()) {
                    LocalDate dateOfBirth;
                    if (rs.getDate(5) != null) dateOfBirth = rs.getDate(5).toLocalDate();
                    else dateOfBirth = null;

                    searchList.add(new PatientRequest(rs.getString(1),
                            new FullName(rs.getString(3), rs.getString(2), rs.getString(4)),
                            dateOfBirth, rs.getString(6), rs.getString(8)));
                }
                patientsDataRequest.setPatientFirstRequests(searchList);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void requestSecond(LocalDate date)
    {
        try {PreparedStatement preparedStatementInner = conn.prepareStatement
                ("SELECT Patient_General_Inf.id_patient, name, surname, patronymic, date_of_birth, Patient_Treatment_Inf.id_ward, date_of_coming, discharge_date\n" +
                        "FROM Patient_General_Inf INNER JOIN Patient_Treatment_Inf\n" +
                        "ON Patient_General_Inf.id_patient= Patient_Treatment_Inf.id_patient\n" +
                        "WHERE (discharge_date>? AND date_of_coming<?) OR (discharge_date IS NULL AND date_of_coming<?);");

            preparedStatementInner.setDate(1, Date.valueOf(date));
            preparedStatementInner.setDate(2, Date.valueOf(date));
            preparedStatementInner.setDate(3, Date.valueOf(date));


            ResultSet rs = preparedStatementInner.executeQuery();
            {
                ObservableList searchList = FXCollections.observableArrayList();

                while (rs.next()) {
                    LocalDate dateOfBirth;
                    if (rs.getDate(5) != null) dateOfBirth = rs.getDate(5).toLocalDate();
                    else dateOfBirth = null;

                    searchList.add(new PatientRequest(rs.getString(1),
                            new FullName(rs.getString(3), rs.getString(2), rs.getString(4)),
                            dateOfBirth, rs.getString(6), rs.getDate(7).toLocalDate()));

                }
                patientsDataRequest.setPatientSecondRequests(searchList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void requestThird(int age)
    {
        try {PreparedStatement preparedStatementInner = conn.prepareStatement
                ("SELECT Patient_General_Inf.id_patient, name, surname, patronymic, gender, date_of_birth, discharge_date,\n" +
                        "EXTRACT(YEAR FROM AGE(current_date, date_of_birth)) AS age\n" +
                        "FROM Patient_General_Inf INNER JOIN Patient_Treatment_Inf\n" +
                        "ON Patient_General_Inf.id_patient= Patient_Treatment_Inf.id_patient\n" +
                        "WHERE discharge_date IS NULL AND gender='Женский' AND EXTRACT(YEAR FROM AGE(current_date, date_of_birth))>=?;");

            preparedStatementInner.setInt(1, age);

            ResultSet rs = preparedStatementInner.executeQuery();
            {
                ObservableList searchList = FXCollections.observableArrayList();

                while (rs.next()) {
                    LocalDate dateOfBirth;
                    if (rs.getDate(6) != null) dateOfBirth = rs.getDate(6).toLocalDate();
                    else dateOfBirth = null;

                    searchList.add(new PatientRequest(rs.getString(1),
                            new FullName(rs.getString(3), rs.getString(2), rs.getString(4)),
                            dateOfBirth));

                }
                patientsDataRequest.setPatientThirdRequests(searchList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
