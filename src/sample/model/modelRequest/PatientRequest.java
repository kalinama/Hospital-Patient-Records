package sample.model.modelRequest;

import sample.model.FullName;

import java.time.LocalDate;
import java.time.Period;

public class PatientRequest {
    private String id;
    private FullName fullName;
    private String gender;
    private int age;
    private LocalDate dateOfBirth;

    private LocalDate dateOfComing;
    private Boolean isDischarged;
    private LocalDate dischargeDate;
    private String dischargeReason;

    private String number;
    private String phoneNumber;

    public PatientRequest(String id, FullName fullName, LocalDate dateOfBirth, String number, String phoneNumber)
    {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        calculateAge();
        this.number = number;
        this.phoneNumber = phoneNumber;
    }

    public PatientRequest(String id, FullName fullName, LocalDate dateOfBirth, String number, LocalDate dateOfComing)
    {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        calculateAge();
        this.number = number;
        this.dateOfComing = dateOfComing;
    }

    public PatientRequest(String id, FullName fullName, LocalDate dateOfBirth)
    {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        calculateAge();
    }




    private void calculateAge()
    {
        if(dateOfBirth!=null)
            this.age= Period.between(dateOfBirth,LocalDate.now()).getYears();
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfComing() {
        return dateOfComing;
    }

    public Boolean getDischarged() {
        return isDischarged;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public String getDischargeReason() {
        return dischargeReason;
    }

    public String getNumber() {
        return number;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
