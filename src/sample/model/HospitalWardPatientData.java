package sample.model;

import java.time.LocalDate;

public class HospitalWardPatientData {
    private String number;
    private String phoneNumber;
    private LocalDate dateOfComing;

    public HospitalWardPatientData(String number, String phoneNumber, LocalDate dateOfComing)
    {
        this.number = number;
        this.phoneNumber = phoneNumber;
        this.dateOfComing = dateOfComing;
    }

    public String getNumber() {
        return number;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfComing() {
        return dateOfComing;
    }
}
