package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

public class PatientTreatment {

    private String diagnosis;
    private String wayOfComing;
    private String id;

    private HospitalWard hospitalWard;

    private LocalDate dateOfComing;
    private Boolean isDischarged;
    private LocalDate dischargeDate;
    private String dischargeReason;


    private ObservableList<HospitalWardPatientData> wardHistory;

    public PatientTreatment(String id, String diagnosis, String wayOfComing, HospitalWard hospitalWard, LocalDate dateOfComing,
                            LocalDate dischargeDate, String dischargeReason)
    {
        this.id=id;
        this.diagnosis=diagnosis;
        this.wayOfComing=wayOfComing;
        this.hospitalWard=hospitalWard;
        this.dateOfComing=dateOfComing;
        this.dischargeDate=dischargeDate;
        this.dischargeReason=dischargeReason;
        if (dischargeDate != null) isDischarged=true;
        else isDischarged=false;
        wardHistory = FXCollections.observableArrayList();
    }

    public void setWardHistory(ObservableList<HospitalWardPatientData> wardHistory) {
        this.wardHistory =  wardHistory;
    }

    public HospitalWard getHospitalWard() {
        return hospitalWard;
    }

    public LocalDate getDateOfComing() {
        return dateOfComing;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getWayOfComing() {
        return wayOfComing;
    }

    public Boolean getDischarged() {
        return isDischarged;
    }

    public String getDischargeReason() {
        return dischargeReason;
    }

    public ObservableList<HospitalWardPatientData> getWardHistory() {
        return wardHistory;
    }

    public String getId() {
        return id;
    }

    public void setDischarged(Boolean discharged) {
        isDischarged = discharged;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public void setDischargeReason(String dischargeReason) {
        this.dischargeReason = dischargeReason;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setWayOfComing(String wayOfComing) {
        this.wayOfComing = wayOfComing;
    }
}
