package sample.model;

public class FullName {

    private String name;
    private String surname;
    private String patronymic;

    public FullName(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String toString() {
        return surname + " " + name + " " + patronymic;
    }

}
