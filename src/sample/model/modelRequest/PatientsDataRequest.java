package sample.model.modelRequest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Patient;
import sample.model.PatientsData;

public class PatientsDataRequest {
    ObservableList<PatientRequest> patientFirstRequests;
    ObservableList<PatientRequest> patientSecondRequests;
    ObservableList<PatientRequest> patientThirdRequests;

    PatientsData patientsData;

    public PatientsDataRequest(PatientsData patientsData)
    {
        patientFirstRequests = FXCollections.observableArrayList();
        patientSecondRequests = FXCollections.observableArrayList();
        patientThirdRequests = FXCollections.observableArrayList();

        this.patientsData = patientsData;
    }

    private void initPatientFirstRequest()
    {
        for(Patient patient: patientsData.getPatients())
        {

        }
    }

    public ObservableList<PatientRequest> getPatientFirstRequests() {
        return patientFirstRequests;
    }

    public void setPatientFirstRequests(ObservableList<PatientRequest> patientFirstRequests) {
        this.patientFirstRequests = patientFirstRequests;
    }

    public ObservableList<PatientRequest> getPatientSecondRequests() {
        return patientSecondRequests;
    }

    public void setPatientSecondRequests(ObservableList<PatientRequest> patientSecondRequests) {
        this.patientSecondRequests = patientSecondRequests;
    }

    public ObservableList<PatientRequest> getPatientThirdRequests() {
        return patientThirdRequests;
    }

    public void setPatientThirdRequests(ObservableList<PatientRequest> patientThirdRequests) {
        this.patientThirdRequests = patientThirdRequests;
    }
}

