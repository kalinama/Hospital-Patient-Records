package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HospitalWardsData {

    private ObservableList<HospitalWard> hospitalWards;

    public HospitalWardsData()
    {
        hospitalWards = FXCollections.observableArrayList();
    }

    public void setHospitalWards() {
        this.hospitalWards = hospitalWards;
    }

    public ObservableList<HospitalWard> getHospitalWards() {
        return hospitalWards;
    }

    public void setHospitalWards(ObservableList<HospitalWard> hospitalWards) {
        this.hospitalWards = hospitalWards;
    }

    public HospitalWard searchNumber(String number)
    {
        for (HospitalWard hospitalWard: hospitalWards) {
            if (hospitalWard.getNumber().equals(number)) return hospitalWard;
        }
        return null;
    }
}
