package sample.model;

import javafx.collections.ObservableList;

public class HospitalWard {
    private String number;
    private String phoneNumber;

    public HospitalWard(String number, String phoneNumber)
    {
        this.number=number;
        this.phoneNumber=phoneNumber;
    }

    public String getNumber() {
        return number;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
