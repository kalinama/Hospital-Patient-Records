package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Main;
import sample.model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;

import static java.sql.Types.NULL;

public class ChangerHospitalData {
    private Connection conn = Main.returnCon();

    private PatientsData patientsData;
    private HospitalWardsData hospitalWardsData;

    public ChangerHospitalData(PatientsData patientsData, HospitalWardsData hospitalWardsData) {
        this.patientsData = patientsData;
        this.hospitalWardsData = hospitalWardsData;
    }

    public ObservableList<Patient> getPatientsData(){
        return patientsData.getPatients();
    }

    public ObservableList<HospitalWard> getWardsData(){
        return hospitalWardsData.getHospitalWards();
    }

    public void dischargePatient(PatientTreatment patientTreatment, String reason, LocalDate date)
    {
        try {
           PreparedStatement preparedStatement = conn.prepareStatement
                    ("UPDATE Patient_Treatment_Inf SET discharge_date =?, discharge_reason =? WHERE id_patient =? AND date_of_coming =?;");


            preparedStatement.setDate(1,Date.valueOf(date));
            preparedStatement.setString(2, reason);
            preparedStatement.setString(3, patientTreatment.getId());
            preparedStatement.setDate(4, Date.valueOf(patientTreatment.getDateOfComing()));
            preparedStatement.executeUpdate();

            patientTreatment.setDischargeReason(reason);
            patientTreatment.setDischargeDate(date);
            patientTreatment.setDischarged(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

public void editGeneralData(String name, String surname, String patronymic, LocalDate dateOfBirth, String gender, int weight,
                            int height, String marks, Patient patient)
{
    try {
        PreparedStatement preparedStatement = conn.prepareStatement
                ("UPDATE Patient_General_Inf SET name =?, surname =?, patronymic =?, date_of_birth=?, gender=? WHERE id_patient =?;");


        preparedStatement.setString(1,name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, patronymic);
        if (dateOfBirth!=null) preparedStatement.setDate(4, Date.valueOf(dateOfBirth));
        else preparedStatement.setNull(4, NULL);
        preparedStatement.setString(5, gender);
        preparedStatement.setString(6, patient.getId());
        preparedStatement.executeUpdate();

       patient.setFullName(new FullName(surname, name, patronymic));
       patient.setDateOfBirth(dateOfBirth);
       patient.calculateAge();
       patient.setGender(gender);

    } catch (SQLException e) {
        e.printStackTrace();
    }

    try {
        PreparedStatement preparedStatement = conn.prepareStatement
                ("UPDATE Patient_Description SET weight =?, height =?, distinguishing_marks =? WHERE id_patient =?;\n");
      if (weight==0) preparedStatement.setNull(1,NULL);
      else preparedStatement.setInt(1,weight);
      if (height==0) preparedStatement.setNull(2,NULL);
       else preparedStatement.setInt(2, height);
        preparedStatement.setString(3, marks);
        preparedStatement.setString(4, patient.getId());
        preparedStatement.executeUpdate();

        patient.setHeight(height);
        patient.setWeight(weight);
        patient.setDescription(marks);

    } catch (SQLException e) {
        e.printStackTrace();
    }

}

public void editTreatmentData(PatientTreatment patientTreatment, String diagnosis, String wayOfComing)
{
    try {
        PreparedStatement preparedStatement = conn.prepareStatement
                (" UPDATE Patient_Treatment_Inf SET diagnosis =?, way_of_coming =? WHERE id_patient =? AND date_of_coming =?;");


        preparedStatement.setString(1,diagnosis);
        preparedStatement.setString(2, wayOfComing);
        preparedStatement.setString(3, patientTreatment.getId());
        preparedStatement.setDate(4, Date.valueOf(patientTreatment.getDateOfComing()));
        preparedStatement.executeUpdate();

        patientTreatment.setDiagnosis(diagnosis);
        patientTreatment.setWayOfComing(wayOfComing);

    } catch (SQLException e) {
        e.printStackTrace();
    }

}
    public void removePatient(Patient patient) {
        patientsData.removePatient(patient);
    }

    public void generate() throws IOException {
        patientsData.generate(new Generator().generate());
    }

    public void addStudent(Patient patient) {
        patientsData.addStudent(patient);
    }

    public void newFile()
    {
        patientsData.removeAllStudents();
    }


    public void searchPatients() {

    }

    private void addHospitalWardHistory(LocalDate date, String number, PatientTreatment patientTreatment)
    {try{
        PreparedStatement preparedStatement = conn.prepareStatement
                ("INSERT INTO Hospital_Ward_History VALUES (?,?,?);");


        preparedStatement.setString(1,patientTreatment.getId());
        preparedStatement.setString(2, number);
        preparedStatement.setDate(3, Date.valueOf(date));

        preparedStatement.executeUpdate();
        if (patientTreatment.getWardHistory()!=null) patientTreatment.getWardHistory().
                add(new HospitalWardPatientData(number,hospitalWardsData.searchNumber(number).getPhoneNumber(),date));
        else {
            ObservableList<HospitalWardPatientData> wardHistory = FXCollections.observableArrayList();
            wardHistory.add(new HospitalWardPatientData(number, hospitalWardsData.searchNumber(number).getPhoneNumber(), date));

            patientTreatment.setWardHistory(wardHistory);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }


    }

    public void addPatientTreatmentInf(LocalDate date, String number, String wayOfComing, String diagnosis, Patient patient)
    {

        try {
            PreparedStatement preparedStatement = conn.prepareStatement
                    ("INSERT INTO Patient_Treatment_Inf VALUES (?,?,?,?,?,NULL,NULL);");

            preparedStatement.setString(1, patient.getId());
            preparedStatement.setString(2, diagnosis);
            preparedStatement.setString(3, wayOfComing);
            preparedStatement.setString(4, number);
            preparedStatement.setDate(5, Date.valueOf(date));
            preparedStatement.executeUpdate();

            patient.getComingHistory().add(new PatientTreatment(patient.getId(),diagnosis, wayOfComing,
                    hospitalWardsData.searchNumber(number), date, null, null ));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cometoAnotherWard(LocalDate date, String number, PatientTreatment patientTreatment)
    {
        addHospitalWardHistory(date,number, patientTreatment);

    try {
        PreparedStatement preparedStatement = conn.prepareStatement
                ("UPDATE Patient_Treatment_Inf SET id_ward =? WHERE id_patient =? AND date_of_coming =?;");

        preparedStatement.setString(1, number);
        preparedStatement.setString(2, patientTreatment.getId());
        preparedStatement.setDate(3, Date.valueOf(patientTreatment.getDateOfComing()));
        preparedStatement.executeUpdate();


    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void newComing(LocalDate date, String number, String wayOfComing, String diagnosis, Patient patient)
    {
        addPatientTreatmentInf(date, number, wayOfComing, diagnosis, patient);
       addHospitalWardHistory(date,number,patient.getComingHistory().get(patient.getComingHistory().size()-1));
    }

    public Patient newPatient(String name, String surname, String patronymic, LocalDate dateOfBirth, String gender, int weight,
                           int height, String marks)
    {
        Patient p = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement
                    ("INSERT INTO Patient_General_Inf VALUES (?,?,?,?,?,?);");

            preparedStatement.setString(1, patientsData.newID());
            preparedStatement.setString(3,name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(4, patronymic);
            if (dateOfBirth!=null) preparedStatement.setDate(6, Date.valueOf(dateOfBirth));
            else preparedStatement.setNull(6, NULL);
            preparedStatement.setString(5, gender);
            preparedStatement.executeUpdate();

            p = new Patient(patientsData.newID(),new FullName(surname, name, patronymic),gender,
                    dateOfBirth, height, weight, marks);
            ObservableList<PatientTreatment> comingHistory = FXCollections.observableArrayList();
            p.setComingHistory(comingHistory);

            patientsData.getPatients().add(p);
            try {
               preparedStatement = conn.prepareStatement
                        ("INSERT INTO Patient_Description VALUES (?,?,?,?);");
                if (weight==0) preparedStatement.setNull(2,NULL);
                else preparedStatement.setInt(2,weight);
                if (height==0) preparedStatement.setNull(3,NULL);
                else preparedStatement.setInt(3, height);
                preparedStatement.setString(4, marks);
                preparedStatement.setString(1, p.getId());
                preparedStatement.executeUpdate();

                p.setHeight(height);
                p.setWeight(weight);
                p.setDescription(marks);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    public void deletePatient(Patient patient)
    {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement
                    ("DELETE FROM Patient_general_inf WHERE id_patient=?;");

            preparedStatement.setString(1, patient.getId());
            preparedStatement.executeUpdate();

            Iterator<Patient> patientIterator = patientsData.getPatients().iterator();//создаем итератор
            while(patientIterator.hasNext()) {//до тех пор, пока в списке есть элементы

                Patient nextPatient = patientIterator.next();//получаем следующий элемент
                if (nextPatient.getId().equals(patient.getId())) {
                    patientIterator.remove();//удаляем кота с нужным именем
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editHospitalWard(String newName, String phoneNumber, HospitalWard hospitalWard)
    {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement
                    ("UPDATE Hospital_Ward SET id_ward=?, phone_number=? WHERE id_ward=?;");

            preparedStatement.setString(1,newName);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, hospitalWard.getNumber());

            preparedStatement.executeUpdate();
            hospitalWard.setNumber(newName);
            hospitalWard.setPhoneNumber(phoneNumber);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addHospitalWard(String newName, String phoneNumber)
    {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement
                    ("INSERT INTO Hospital_Ward VALUES (?,?);");

            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, phoneNumber);

            preparedStatement.executeUpdate();

           HospitalWard hospitalWard = new HospitalWard(newName, phoneNumber);
           hospitalWardsData.getHospitalWards().add(hospitalWard);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
