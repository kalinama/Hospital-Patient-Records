package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.util.Collection;

public class PatientsData {

    private ObservableList<Patient> patients;

    public void setPatients(ObservableList<Patient> patients) {
        this.patients = patients;
    }

    public PatientsData() {
        patients = FXCollections.observableArrayList();
    }

    public void removePatient(Patient patient) {
        patients.removeIf(element -> element.equals(patient));
    }

    public void generate(Collection<Patient> patients) {
        this.patients.addAll(patients);
    }

    public void addStudent(Patient patient) {
        patients.add(patient);
    }

    public void removeAllStudents() {
        patients.clear();
    }

    public ObservableList<Patient> getPatients() {
        return patients;
    }

    public Patient searchID(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) return patient;
        }
        return null;
    }

    public String newID() {
        int i = 0;
        for (Patient patient : patients)
            if (Integer.parseInt(patient.getId()) > i) i = Integer.parseInt(patient.getId());
        return String.valueOf(++i);
    }
}
