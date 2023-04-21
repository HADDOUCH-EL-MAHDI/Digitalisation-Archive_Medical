package DAM.view;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAM.Model.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GererServicesController {

	Connection conn;
	PreparedStatement st;
	ResultSet res;

	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private TableView<Service> TableDonnees;

    @FXML
    private TableColumn<Service, String> NomColumn;

    @FXML
    private TableColumn<Service, String> NbPatientsColumn;

    @FXML
    private TextField NomService;

    @FXML
    private TextField PasseNormalConfirmation;

    @FXML
    private TextField PasseAdminConfirmation;

    @FXML
    private Button BtnAjouter;

    @FXML
    private Label LabelSignal;

    @FXML
    private PasswordField PasseNormal;

    @FXML
    private PasswordField PasseAdmin;

	    public ObservableList<Service> data = FXCollections.observableArrayList();

		private void afficherDonnees(String sql) {
			ViderTableau();
			try {
				st = conn.prepareStatement(sql);
		        res = st.executeQuery();
		        while (res.next()) {
		            data.add(new Service(res.getString(1), res.getString(2)));
		         }

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			NomColumn.setCellValueFactory(new PropertyValueFactory<Service,String>("Nom"));
			NbPatientsColumn.setCellValueFactory(new PropertyValueFactory<Service,String>("NombrePatients"));
			TableDonnees.setItems(data);
		}

	    @FXML
	    void AjouterServices() {

	    	String sqlTest = "SELECT * FROM services WHERE nom = '" + NomService.getText()+"'";


			String sql = "INSERT INTO services(nom,mot_de_passe,AdminPassword) VALUES (?,?,?)";
	    	String nom = NomService.getText();
		    String MpNormal = PasseNormal.getText();
		    String MpAdmin = PasseAdmin.getText();

    	 try {
    		 if(PasseNormal.getText().compareTo(PasseNormalConfirmation.getText()) != 0){
    			 afficherSignal("Erreur Mot de passe Normal",Color.RED);
    		 }
    		 else{
    			 if(PasseAdmin.getText().compareTo(PasseAdminConfirmation.getText()) != 0){
    				 afficherSignal("Erreur Mot de passe Admin",Color.RED);
    			 }else{
    				 st = conn.prepareStatement(sqlTest);
    	    		 res = st.executeQuery();
    	    		 if(res.next()){
    	    			 afficherSignal("Erreur Il existe deja ce nom de Service",Color.RED);
    	    		 }else{

    	    		 st = conn.prepareStatement(sql);
    	    		 st.setString(1, nom);
    	    		 st.setString(2, MpNormal);
    	    		 st.setString(3, MpAdmin);
    	 	         st.execute();
    	 	         ViderDonnees();
    	 	         afficherSignal("Service ajouté avec succès",Color.GREEN);
    	 			afficherDonnees("SELECT DISTINCT nom,(SELECT COUNT(*) FROM patients WHERE services.id = patients.id)as nb FROM services,patients");
    			 }
    		 }

 		}} catch (SQLException e) {
 			e.printStackTrace();
 		}


	    }

	    private void ViderDonnees(){
		    NomService.setText("");

		    PasseNormal.setText("");

		    PasseNormalConfirmation.setText("");

		    PasseAdmin.setText("");

		    PasseAdminConfirmation.setText("");
	    }

	    void afficherSignal(String msg,Color couleur){
	    	LabelSignal.setTextFill(couleur);
	    	LabelSignal.setText(msg);
	    }

		@FXML
	    private void initialize() {
			conn = ConnexionBd.getConnection();
			afficherDonnees("SELECT DISTINCT nom,(SELECT COUNT(*) FROM patients WHERE services.id = patients.id)as nb FROM services,patients");
	    }


		 @FXML
			public void ToConnexion(ActionEvent event) throws IOException{
				 root = FXMLLoader.load(getClass().getResource("connexion.fxml"));

				 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		         scene = new Scene(root);

		         stage.setScene(scene);
		         stage.setResizable(false);
		         stage.show();
			}

		 private void ViderTableau(){
				for ( int i = 0; i<TableDonnees.getItems().size(); i++) {
					TableDonnees.getItems().clear();
				}
			}

}
