package sample.view;

import javafx.scene.Scene;

public class Scenes {

    private static Scenes instance;
    private static final int width = 871;
    private static final int height =550;

   private Scene listOfPatientFormScene;
   private Scene showInformationalScene;
   private Scene specialHospitalComingInformationFormScene;
   private Scene showHospitalWardInformationViewScene;
   private Scene firstRequestScene;
   private Scene secondRequestScene;
   private Scene thirdRequestScene;

    private Scenes(){}
    public static Scenes getInstance(){
        if(instance == null){		//если объект еще не создан
            instance = new Scenes();	//создать новый объект
        }
        return instance;		// вернуть ранее созданный объект
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public Scene getListOfPatientFormScene() {
        return listOfPatientFormScene;
    }

    public Scene getShowInformationalScene() {
        return showInformationalScene;
    }

    public Scene getSpecialHospitalComingInformationFormScene() {
        return specialHospitalComingInformationFormScene;
    }

    public Scene getShowHospitalWardInformationViewScene() {
        return showHospitalWardInformationViewScene;
    }

    public Scene getFirstRequestScene() {
        return firstRequestScene;
    }

    public Scene getSecondRequestScene() {
        return secondRequestScene;
    }

    public Scene getThirdRequestScene() {
        return thirdRequestScene;
    }

    public void setListOfPatientFormScene(Scene listOfPatientFormScene) {
        this.listOfPatientFormScene = listOfPatientFormScene;
    }

    public void setShowInformationalScene(Scene showInformationalScene) {
        this.showInformationalScene = showInformationalScene;
    }

    public void setSpecialHospitalComingInformationFormScene(Scene specialHospitalComingInformationFormScene) {
        this.specialHospitalComingInformationFormScene = specialHospitalComingInformationFormScene;
    }

    public void setShowHospitalWardInformationViewScene(Scene showHospitalWardInformationViewScene) {
        this.showHospitalWardInformationViewScene = showHospitalWardInformationViewScene;
    }

    public void setFirstRequestScene(Scene firstRequestScene) {
        this.firstRequestScene = firstRequestScene;
    }

    public void setSecondRequestScene(Scene secondRequestScene) {
        this.secondRequestScene = secondRequestScene;
    }

    public void setThirdRequestScene(Scene thirdRequestScene) {
        this.thirdRequestScene = thirdRequestScene;
    }
}
