package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Patient {


   private String id;
    private FullName fullName;
    private String gender;
    private int age;
    private LocalDate dateOfBirth;

    private String description;
    private int height;
    private int weight;

    private ObservableList<PatientTreatment> comingHistory;

    public Patient(String id, FullName fullName, String gender, LocalDate dateOfBirth, int height, int weight, String description)
    {
        this.id=id;
        this.gender=gender;
        this.fullName=fullName;
        this.dateOfBirth=dateOfBirth;
        calculateAge();
        this.description=description;
        this.height=height;
        this.weight=weight;
        comingHistory = FXCollections.observableArrayList();
    }

    public void calculateAge()
    {
        if(dateOfBirth!=null)
        this.age= Period.between(dateOfBirth,LocalDate.now()).getYears();
    }
    public void setComingHistory(ObservableList<PatientTreatment> comingHistory) {
        this.comingHistory = comingHistory;
    }


    public String getId() {
        return id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public ObservableList<PatientTreatment> getComingHistory() {
        return comingHistory;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
