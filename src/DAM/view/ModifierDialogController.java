package DAM.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAM.MainApp;
import DAM.Model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ModifierDialogController {

	Connection conn;
	PreparedStatement st;
	ResultSet res;
	MainApp mainApp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ModifierStage;

    @FXML
    private TextField nomEtprenom;

    @FXML
    private TextField Code;

    @FXML
    private TextField Tel;

    @FXML
    private TextField DiagEnt;

    @FXML
    private TextField DateEnt;

    @FXML
    private TextField DateSort;

    @FXML
    private TextField DiagSort;

    @FXML
    private TextField Ip;

    @FXML
    private TextField Adresse;

    @FXML
    private TextField Prof;

    @FXML
    private TextField Doctor;

    ModifierController modifier;

    @FXML
    private Label LabelSignal;

    private int IP;
    private Stage dialogStage;
    private boolean okClicked = false;
	private Patient patient;

    @FXML
    void Modifier(ActionEvent event) throws SQLException {
    	String sqlTest = "Select * FROM patients WHERE ip = "+Integer.parseInt(Ip.getText());

    	st =  conn.prepareStatement(sqlTest);
    	res = st.executeQuery();

    	if(res.next()){
    		afficherSignal("Impossible, cette IP deja existe",Color.RED);

    	}else{

	    	String sql ="UPDATE patients set ip = '"+Integer.parseInt(Ip.getText())+"', code = '"+Code.getText()+"', nomETprenom = '"+nomEtprenom.getText()+"', tel = '"+Tel.getText()+"', adresse = '"+Adresse.getText()+"', dateE = '"+DateEnt.getText()+"', dateS = '"+DateSort.getText()+"', diagnosticE = '"+DiagEnt.getText()+"', diagnosticS = '"+DiagSort.getText()+"', pr = '"+Prof.getText()+"', dr = '"+Doctor.getText() +"' WHERE ip = '"+IP+"'";

	    	conn.prepareStatement(sql).executeUpdate();
	    	afficherSignal("Succes",Color.GREEN);



	    	dialogStage = (Stage) ModifierStage.getScene().getWindow();
	    	dialogStage.close();
	    	

	    	}

    }

    String PreparerPatient(Patient pat){
    	String sql = "";


    	return sql;
    }



    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    void afficherSignal(String txt,Color couleur){
    	LabelSignal.setTextFill(couleur);
    	LabelSignal.setText(txt);
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
        IP = patient.getIp();
        Ip.setText(""+patient.getIp());
        Code.setText(patient.getCode());
        nomEtprenom.setText(patient.getNomETprenom());
        Tel.setText(patient.getTel());
        Adresse.setText(patient.getAdresse());
        DateEnt.setText(patient.getDateE());
        DateSort.setText(patient.getDateS());
        DiagEnt.setText(patient.getDiagnosticE());
        DiagSort.setText(patient.getDiagnosticS());
        Doctor.setText(patient.getDr());
        Prof.setText(patient.getPr());
    }


    @FXML
    void Retourner(ActionEvent event) {

    }


    @FXML
    void initialize() {
    	conn = ConnexionBd.getConnection();
    }
}

