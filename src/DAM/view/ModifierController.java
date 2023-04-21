package DAM.view;

import java.io.IOException;
//import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAM.MainApp;
import DAM.Model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class ModifierController {

	Connection conn;
	PreparedStatement st;
	ResultSet res;
	MainApp mainApp;

    @FXML
    private ResourceBundle resources;


    @FXML
    private AnchorPane ModifierStage;

    @FXML
    private TableView<Patient> TableDonnees;

    @FXML
    private TableColumn<Patient, Integer> idColumn;

    @FXML
    private TableColumn<Patient, String> codeColumn;

    @FXML
    private TableColumn<Patient, String> nomEtprenomColumn;

    @FXML
    private TextField Recherche;

    public ObservableList<Patient> data = FXCollections.observableArrayList();

    public Patient select;



	@FXML
	private void Rechercher(){
		String x = Recherche.getText();
		ClearTable();
		afficherDonnees("SELECT * FROM patients WHERE ip LIKE('"+x+"%') or "+"code LIKE('"+x+"%') or "+"nomETprenom LIKE('"+x+"%') and id = '"+testController.RetrunIdService()+"'");
	}



    //handle edit
    @FXML
    void modifierP(ActionEvent event) throws SQLException, IOException {

    	Patient selectedPatient = TableDonnees.getSelectionModel().getSelectedItem();

    	if (selectedPatient != null) {
    		MainApp.showPersonEditDialog(selectedPatient);
    		Rafrechir();

        }else {

            Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Update");
    		alert.setHeaderText("No Person Selected");
    		alert.setContentText("Select a person in the table");
    		alert.showAndWait();
    		}
	}

	@FXML
    void Rafrechir() {
		ClearTable();
		afficherDonnees("SELECT * FROM patients where id = '" +testController.RetrunIdService()+"'");
    }

    @FXML
    void initialize() {
    	conn = ConnexionBd.getConnection();
		afficherDonnees("SELECT * FROM patients where id = " +testController.RetrunIdService());
    }


  //affichage
	void afficherDonnees(String sql) {
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


    //clear table
    private void ClearTable(){
		for ( int i = 0; i<TableDonnees.getItems().size(); i++) {
			TableDonnees.getItems().clear();
		}

	}







}




