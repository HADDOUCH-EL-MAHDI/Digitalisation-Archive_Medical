package DAM;




import java.io.IOException;

import DAM.Model.Patient;
import DAM.view.ModifierDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class MainApp extends Application {

    public static Stage primaryStage;

    private ObservableList<Patient> personData = FXCollections.observableArrayList();

    public  MainApp(){

    }


    public static boolean showPersonEditDialog(Patient patient) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ModifierDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier Patient");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.

            ModifierDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage); //add to ModifierDialogueController
            controller.setPatient(patient); //add to ModifierDialogueController
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


            return controller.isOkClicked(); //add to ModifierDialogueController
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MyProject");


        initRootLayout();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            Parent  root = FXMLLoader.load(getClass().getResource("view/connexion.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public Stage getPrimaryStage() {
		return primaryStage;
	}



    public static void main(String[] args) {
        launch(args);
    }

}

