package DAM.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import DAM.Model.Patient;
import DAM.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ListeController {
	Connection conn;
	PreparedStatement st;
	ResultSet res;
	MainApp mainApp;

 	  @FXML
      private AnchorPane ListeStage;
	  @FXML
	  private TableView<Patient> TableDonnees;

	  @FXML
	  private TextField Recherche;

	  @FXML
	  private Button ButtonRecherche;

	  @FXML
	  private SplitMenuButton MenuAnnees;

	  @FXML
	  private TableColumn<Patient, Integer> idColumn;

	  @FXML
	  private TableColumn<Patient, String> nomEtprenomColumn;

	  @FXML
	  private TableColumn<Patient, String> codeColumn;

	  public ObservableList<Patient> data = FXCollections.observableArrayList();


	private void afficherDonnees(String sql) {
		ClearTable();
		try {
			st = conn.prepareStatement(sql);
	        res = st.executeQuery();
	        while (res.next()) {
	            data.add(new Patient(res.getInt(1), res.getString(2), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11), res.getString(12)));
	         }

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		idColumn.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("ip"));
		codeColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("code"));
		nomEtprenomColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("nomETprenom"));
		TableDonnees.setItems(data);
	}

	@FXML
	private void Rechercher(){
		String x = Recherche.getText();
		ClearTable();
		afficherDonnees("SELECT * FROM patients WHERE ip LIKE('"+x+"%') or "+"code LIKE('"+x+"%') or "+"nomETprenom LIKE('"+x+"%') and id = '"+testController.RetrunIdService()+"'");
	}

	private void ClearTable(){
		for ( int i = 0; i<TableDonnees.getItems().size(); i++) {
			TableDonnees.getItems().clear();
		}
	}


	@FXML
    void Rafrechir() {
		ClearTable();
		afficherDonnees("SELECT * FROM patients where id = '" +testController.RetrunIdService()+"'");
    }


	@FXML
    private void initialize() {
		conn = ConnexionBd.getConnection();
		afficherDonnees("SELECT * FROM patients where id = " +testController.RetrunIdService());
		Annees();
    }

	private void Annees(){
		String sql ="SELECT EXTRACT(year from dateE) as year FROM patients WHERE id = '" +testController.RetrunIdService()+"' GROUP BY year";
		int count = 0;
		try {
			st = conn.prepareStatement(sql);
	        res = st.executeQuery();
	        while (res.next()) {
	        	MenuAnnees.getItems().add(new MenuItem(res.getString(1)));
	        	count++;
	         }

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i=0;i<count;i++){
			 String ann;
			 ann = MenuAnnees.getItems().get(i).getText();
			 MenuAnnees.getItems().get(i).setOnAction((e)->{
				 MenuAnnees.setText(ann);
				 afficherDonnees("SELECT * FROM patients WHERE YEAR(dateE) = "+ann+"and id = '"+testController.RetrunIdService()+"'");
			});
		}
	}

	@FXML
	public void AfficheDetails() throws IOException {
    	Patient selectedPerson = TableDonnees.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = showPatientDetails(selectedPerson);
            showPatientDetails(selectedPerson);

            if (okClicked) {
            	showPatientDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            //alert.initOwner(mainApp.getPrimaryStage());  // erreur si aucun patient n'est selectionné
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }


	boolean showPatientDetails(Patient patient) throws IOException{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PatientDetails.fxml"));
        Parent root = (Parent) loader.load();
    	ListeStage.getChildren().set(0, root);
    	PatientDetailsController controller = loader.getController();
        controller.setPatient(patient);
		return true;
	}

}


