package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.model.FullName;
import sample.model.Patient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableFormPatients {

    private static String KOL_PRINT_NOTES = "Записей на странице:";
    private static String KOL_NOTES = "Записей всего:";
    private static String INPUT_KOL_NOTES = "Введите количество записей на странице: ";

    private GridPane rootOfTableForm;
    private List<ObservableList<Patient>> listOfPatientsForTablePrint;
    private ObservableList<Patient> patients;
    private ObservableList<Patient> searchingPatients;
    private TableView<Patient> table;
    private Label kolNotes;
    private Label kolPrintNotes;
    private Label pageNumber;
    private TextField searchTextField;

    private int amountOfPatients;
    private int amountOfPages;
    private int currentPage;
    private int amountOfPatientsOnLastPage;

    TableFormPatients (ObservableList<Patient> patients) {

        this.listOfPatientsForTablePrint = new ArrayList<>();
        this.patients = patients;
        searchingPatients = FXCollections.observableArrayList();

        amountOfPages = 1;
        amountOfPatients = 10;
        currentPage = 1;
        amountOfPatientsOnLastPage = 0;
        kolNotes = new Label();
        kolPrintNotes = new Label();
        pageNumber = new Label();
        searchTextField = new TextField();

        this.rootOfTableForm = tableFormInit();

    }

    private void createTable()
    {
        table = new TableView<>();
        renewTable(patients);
        table.setPrefWidth(200);
        table.setPrefHeight(300);

        TableColumn<Patient, FullName> fullNameColumn = new TableColumn<>("ФИО");
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        fullNameColumn.setPrefWidth(700);



        table.getColumns().addAll(fullNameColumn);

    }

    private GridPane tableFormInit()
    {
        createTable();
        kolNotes.setText(KOL_NOTES + " 0");
        kolPrintNotes.setText(KOL_PRINT_NOTES + "  0" );
        pageNumber.setText("Страница 1 из 1");
        Label inputKolNotes = new Label(INPUT_KOL_NOTES);


        Image firstPage = new Image(new File("first.png").toURI().toString(),30,
                30,true,true);
        Image lastPage = new Image(new File("last.png").toURI().toString(),30,
                30,true,true);
        Image nextPage = new Image(new File("next.png").toURI().toString(),30,
                30,true,true);
        Image previousPage = new Image(new File("previous.png").toURI().toString(),30,
                30,true,true);

        ImageView firstPageImage = new ImageView(firstPage);
        ImageView lastPageImage = new ImageView(lastPage);
        ImageView nextPageImage = new ImageView(nextPage);
        ImageView previousPageImage = new ImageView(previousPage);

        Button buttonChangeNumberOfData = new Button("Изменить");
        Button buttonNext = new Button("",nextPageImage);
        Button buttonPrevious = new Button("",previousPageImage);
        Button buttonLast = new Button("", lastPageImage);
        Button buttonFirst = new Button("",firstPageImage);
        Button searchButton = new Button("Найти");

        TextField textFieldNumberOfData = new TextField();

        GridPane root = new GridPane();
        renewTable(patients);

        buttonChangeNumberOfData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(new Scanner(textFieldNumberOfData.getText()).hasNextInt()&&Integer.parseInt(textFieldNumberOfData.getText())>0) {
                    amountOfPatients = Integer.parseInt(textFieldNumberOfData.getText());
                    renewTable(patients);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Количество записей на странице не было изменено");
                    alert.setContentText("Допускается лишь ввод чисел больше нуля!");
                    alert.show();
                }

            }
        });

        buttonFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setTable(1);
            }
        });
        buttonNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (listOfPatientsForTablePrint.size()>=currentPage+1) {
                    setTable(currentPage + 1);
                }
            }
        });

        buttonLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setTable(amountOfPages);
            }
        });

        buttonPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentPage!=1) {
                    setTable(currentPage - 1);
                }
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchPatients();
            }
        });
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(24);

        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(7);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(68);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(10);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row2);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row3);
        root.getRowConstraints().add(row);

        //root.setGridLinesVisible(true);
        GridPane.setHalignment(buttonChangeNumberOfData, HPos.LEFT);
        GridPane.setHalignment(inputKolNotes, HPos.RIGHT);
        GridPane.setHalignment(pageNumber, HPos.CENTER);
        GridPane.setHalignment(buttonPrevious, HPos.RIGHT);
        GridPane.setValignment(buttonPrevious, VPos.CENTER);
        GridPane.setHalignment(buttonNext, HPos.LEFT);
        GridPane.setValignment(buttonNext, VPos.CENTER);
        GridPane.setHalignment(buttonLast, HPos.RIGHT);
        GridPane.setValignment(buttonLast, VPos.CENTER);

        root.add(table, 0, 1, 4, 1);
        root.add(buttonFirst,0,3);
        root.add(buttonNext,2,3);
        root.add(buttonPrevious,1,3);
        root.add(buttonLast,3,3);
        root.add(kolNotes,0,0);
        root.add(kolPrintNotes,3,0);
        root.add(pageNumber,0,2,4,1);
        root.add(inputKolNotes,0,4,2,1);
        root.add(buttonChangeNumberOfData, 3, 4);
        root.add(textFieldNumberOfData, 2, 4);
        root.add(searchTextField,1,0);
        root.add(searchButton,2,0);

        return root;
    }

    void renewTable(ObservableList<Patient> patients)
    {
        listOfPatientsForTablePrint.clear();

        amountOfPages = patients.size() / amountOfPatients;

        for (int pageNumber = 0; pageNumber < amountOfPages; pageNumber++) {

            ObservableList<Patient> listForPage = FXCollections.observableArrayList();
            for (int noteNumber = 0; noteNumber < amountOfPatients; noteNumber++) {
                listForPage.add(patients.get(pageNumber * amountOfPatients + noteNumber));
            }
            listOfPatientsForTablePrint.add(listForPage);
        }

        if (patients.size() % amountOfPatients != 0) {
            ObservableList<Patient> listForPage = FXCollections.observableArrayList();
            amountOfPatientsOnLastPage = patients.size() % amountOfPatients;
            for (int noteNumber = 0; noteNumber < patients.size() % amountOfPatients; noteNumber++) {
                listForPage.add(patients.get(amountOfPages * amountOfPatients + noteNumber));
            }
            listOfPatientsForTablePrint.add(listForPage);
            amountOfPages++;
        }
        kolNotes.setText(KOL_NOTES+" "+ patients.size());
        if (currentPage>amountOfPages) setTable(1);
        setTable(currentPage);
    }

    private void setTable(int page) {
        if (listOfPatientsForTablePrint.size() == 0) {
            ObservableList<Patient> empty = FXCollections.observableArrayList();
            listOfPatientsForTablePrint.add(empty);
            table.setItems(listOfPatientsForTablePrint.get(0));
        }
        else  table.setItems(listOfPatientsForTablePrint.get(page-1));
        currentPage = page;

        if (amountOfPages==0) amountOfPages=1;
        pageNumber.setText("Страница "+ currentPage+" из "+ amountOfPages);
        if (currentPage==amountOfPages)  kolPrintNotes.setText(KOL_PRINT_NOTES + " " + amountOfPatientsOnLastPage);
        else  kolPrintNotes.setText(KOL_PRINT_NOTES + " " + amountOfPatients);

    }

    void clearList()
    {
        listOfPatientsForTablePrint.clear();
        setAmountOfPages(1);

        ObservableList<Patient> empty = FXCollections.observableArrayList();
        listOfPatientsForTablePrint.add(empty);
    }


    private void searchPatients()
    {
        if (searchTextField.getText().isEmpty()) renewTable(patients);
        else {
            clearList();
            searchingPatients.clear();
            String patronymic = searchTextField.getText();
            String surname = patronymic.substring(0, patronymic.indexOf(" "));
            patronymic = patronymic.substring(patronymic.indexOf(" ") + 1);
            String name = patronymic.substring(0, patronymic.indexOf(" "));
            patronymic = patronymic.substring(patronymic.indexOf(" ") + 1);

            for (Patient patient : patients)
                if (patient.getFullName().getSurname().equals(surname)&& patient.getFullName().getName().equals(name)
                && patient.getFullName().getPatronymic().equals(patronymic))
                    searchingPatients.add(patient);
            renewTable(searchingPatients);

        }
    }
    GridPane getRootOfTableForm() {
        return rootOfTableForm;
    }

    private void setAmountOfPages(int amountOfPages) {

        this.amountOfPages = amountOfPages;
    }

    public TableView<Patient> getTable() {
        return table;
    }

    public TextField getSearchTextField() {
        return searchTextField;
    }
}

