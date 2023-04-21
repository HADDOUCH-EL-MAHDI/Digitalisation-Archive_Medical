package DAM.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAM.MainApp;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class connexionController {
	 @FXML
	    private TextField NomUtilisateur;

	    @FXML
	    private TextField MotDePasse;

	    @FXML
	    private RadioButton RadioBtnAdmin;

	    @FXML
	    private Label LabelSignal;

	    @FXML
	    private RadioButton RadioBtnGererServices;


	private Stage stage;
	private Scene scene;
	private Parent root;

	Connection conn;
	public PreparedStatement st;
	public ResultSet res;
	MainApp mainApp;


	@FXML
	public void ToHome(Event event) throws IOException, InterruptedException{

		if(RadioBtnGererServices.isSelected()){

			if(NomUtilisateur.getText().compareTo("admin") == 0 && MotDePasse.getText().compareTo("admin") == 0){

				 FXMLLoader loader = new FXMLLoader(getClass().getResource("GererServices.fxml"));
	        	 root =(Parent)loader.load();
	        	 //GererServicesController ServicesControl = loader.getController();
	    		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	             scene = new Scene(root);
	             stage.setResizable(true);
	             stage.setScene(scene);
	             stage.show();

			}else{
				AfficherMsg("<< Erreur >> Mp :"+MotDePasse.getText());
			}

		}else{
			String sql = "SELECT * FROM services Where nom = ? and mot_de_passe = ?";
			String sqlAdmin = "SELECT * FROM services Where nom = ? and AdminPassword = ?";
	        String user = NomUtilisateur.getText();
	        String pass = MotDePasse.getText();
	        String Session;
	        try {
	        	if(RadioBtnAdmin.isSelected()){
	        		st = conn.prepareStatement(sqlAdmin);
	        		Session = "Admin";
	        	}
	        	else{
	        		st = conn.prepareStatement(sql);
	        		Session = "Normal";
	        	}
	        	st.setString(1, user);
		        st.setString(2, pass);
		        res = st.executeQuery();
		        if (!res.next()) {
		        	AfficherMsg("<< Erreur >> Mp :"+MotDePasse.getText());
		         } else {
		        	 FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
		        	 root =(Parent)loader.load();
		        	 testController testCont = loader.getController();
		        	 testCont.NameServiceAndSession(res.getString(2),Session);
		    		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		             scene = new Scene(root);
		             stage.setResizable(true);
		             stage.setScene(scene);
		             stage.show();
		         }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

    @FXML
    void SelectionnerServices() {
    	RadioBtnAdmin.setSelected(false);
    }


    @FXML
    void selectionnerAdmin() {
    	RadioBtnGererServices.setSelected(false);
    }

	void AfficherMsg(String txt) throws InterruptedException{
		LabelSignal.setText(txt);
	}

	@FXML
    private void initialize() {
		conn = ConnexionBd.getConnection();

    }
}
