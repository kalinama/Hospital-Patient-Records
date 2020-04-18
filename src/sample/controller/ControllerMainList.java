package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.util.Pair;
import sample.Main;
import sample.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerMainList {

    private Connection conn = Main.returnCon();
    private PatientsData patientsData;
    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    private HospitalWardsData hospitalWardsData;
    private ObservableList<HospitalWard> hospitalWards = FXCollections.observableArrayList();

    public ControllerMainList(PatientsData patientsData, HospitalWardsData hospitalWardsData)
    {
        this.patientsData = patientsData;
        this.hospitalWardsData = hospitalWardsData;
        renewAllData();
    }

    public void renewAllData()
    {
        renewPatientsList();
        patientsData.setPatients(patients);
        renewHospitalWardsList();
        hospitalWardsData.setHospitalWards(hospitalWards);
    }
    private void renewPatientsList()
    {
        try (PreparedStatement preparedStatementInner = conn.prepareStatement
                ("SELECT Patient_General_Inf.id_patient, surname, name, patronymic, gender, date_of_birth, height, weight, distinguishing_marks " +
                        "FROM Patient_General_Inf\n" +
                        "    INNER JOIN Patient_Description\n" +
                        "    ON Patient_General_Inf.id_patient = Patient_Description.id_patient;");
             ResultSet rs = preparedStatementInner.executeQuery();) {
            while (rs.next()) {

                LocalDate dateOfBirth;
                if (rs.getDate(6)!=null) dateOfBirth = rs.getDate(6).toLocalDate();
                else dateOfBirth = null;


                patients.add(new Patient(rs.getString(1), new FullName(rs.getString(2), rs.getString(3),
                        rs.getString(4)), rs.getString(5), dateOfBirth,
                        rs.getInt(7), rs.getInt(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        patientsData.setPatients(patients);
        for (Patient patient: patients)
        {
           initComingHistory(patient.getId());
           initWardHistory(patient.getComingHistory(),patient.getId());
        }
    }

    private void initComingHistory(String id)
    {
        Patient p = patientsData.searchID(id);
        ObservableList<PatientTreatment> patientTreatments = FXCollections.observableArrayList();

        try (PreparedStatement preparedStatementInner = conn.prepareStatement
                (" SELECT id_patient, diagnosis, way_of_coming, Patient_Treatment_Inf.id_ward,\n" +
                        "    date_of_coming, discharge_date, discharge_reason, phone_number \n" +
                        "    FROM Patient_Treatment_Inf INNER JOIN Hospital_Ward\n" +
                        "    ON Patient_Treatment_Inf.id_ward = Hospital_Ward.id_ward\n" +
                        "    WHERE id_patient = '" + id + "' ORDER BY date_of_coming ASC;");
             ResultSet rs = preparedStatementInner.executeQuery();) {
            while (rs.next()) {

                LocalDate dischargeLocalDate;
                if (rs.getDate(6)!=null) dischargeLocalDate = rs.getDate(6).toLocalDate();
                else dischargeLocalDate = null;

                patientTreatments.add(new PatientTreatment(rs.getString(1),rs.getString(2), rs.getString(3),
                        new HospitalWard(rs.getString(4),rs.getString(8)),
                        rs.getDate(5).toLocalDate(),dischargeLocalDate, rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setComingHistory(patientTreatments);
    }

    private void initWardHistory(ObservableList<PatientTreatment> patientTreatments, String id)
    {
        int index = 0;
        ObservableList<HospitalWardPatientData> wardHistory = FXCollections.observableArrayList();
        try (PreparedStatement preparedStatementInner = conn.prepareStatement
                ("SELECT id_patient, Hospital_Ward.id_ward, phone_number, date_of_coming_ward\n" +
                        "FROM Hospital_Ward_History INNER JOIN Hospital_Ward\n" +
                        "ON Hospital_Ward_History.id_ward = Hospital_Ward.id_ward\n" +
                        "WHERE id_patient = '" +id+"' ORDER BY date_of_coming_ward ASC;");
             ResultSet rs = preparedStatementInner.executeQuery();) {
            while (rs.next()) {
                LocalDate dischargeDate = patientTreatments.get(index).getDischargeDate();

                if (dischargeDate != null) {
                    if (!rs.getDate(4).toLocalDate().isAfter(dischargeDate)) {
                        HospitalWardPatientData hospitalWardPatientData = new HospitalWardPatientData(rs.getString(2),
                                rs.getString(3),rs.getDate(4).toLocalDate() );

                        wardHistory.add(hospitalWardPatientData);
                    } else {
                        patientTreatments.get(index).setWardHistory(FXCollections.observableArrayList(wardHistory));
                        wardHistory.clear();
                        index++;
                        HospitalWardPatientData hospitalWardPatientData = new HospitalWardPatientData(rs.getString(2),
                                rs.getString(3), rs.getDate(4).toLocalDate());
                        wardHistory.add(hospitalWardPatientData);
                    }
                } else {
                    HospitalWardPatientData hospitalWardPatientData = new HospitalWardPatientData(rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate());
                    wardHistory.add(hospitalWardPatientData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        patientTreatments.get(index).setWardHistory(FXCollections.observableArrayList(wardHistory));
    }

    private void renewHospitalWardsList()
    {
        try (PreparedStatement preparedStatementInner = conn.prepareStatement
                ("SELECT * FROM Hospital_Ward;");
             ResultSet rs = preparedStatementInner.executeQuery();) {
            while (rs.next()) {

                hospitalWards.add(new HospitalWard(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
