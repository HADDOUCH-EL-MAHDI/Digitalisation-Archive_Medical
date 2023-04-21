package DAM.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import DAM.Model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

public class AjouterController {
	Connection conn;
	PreparedStatement statement;
	ResultSet result;


	 @FXML
	    private TextField nomETprenom;

	    @FXML
	    private TextField code;

	    @FXML
	    private TextField tel;

	    @FXML
	    private TextField diagnosticE;

	    @FXML
	    private TextField diagnosticS;

	    @FXML
	    private TextField ip;

	    @FXML
	    private TextField adresse;

	    @FXML
	    private TextField dr;

	    @FXML
	    private TextField pr;

	    @FXML
	    private DatePicker dateE;

	    @FXML
	    private DatePicker dateS;

	    @FXML
	    private Label LabelSignal;

    String pattern = "yyyy-MM-dd";



    @FXML
    void Ajouter(ActionEvent event) {
    		String sqlTest = "SELECT * FROM patients WHERE ip = " + ip.getText();


			String sql = "INSERT INTO patients VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	    	String iptxt = ip.getText();
		    String codetxt = code.getText();
		    String nomETprenomtxt = nomETprenom.getText();
		    String teltxt = tel.getText();
		    String adressetxt = adresse.getText();
		    String diagnosticEtxt = diagnosticE.getText();
		    String diagnosticStxt = diagnosticS.getText();
		    String prtxt = pr.getText();
		    String drtxt = dr.getText();
    	 try {
    		 statement = conn.prepareStatement(sqlTest);
    		 result = statement.executeQuery();
    		 if(result.next()){
    			 afficherSignal("Il existe deja un patient avec cette IP",Color.RED);
    		 }else{

    		 statement = conn.prepareStatement(sql);
    		 statement.setString(1, iptxt);
    		 statement.setString(2, codetxt);
    		 statement.setString(3, testController.RetrunIdService());
    		 statement.setString(4, nomETprenomtxt);
    		 statement.setString(5, teltxt);
    		 statement.setString(6, adressetxt);
    		 statement.setString(7, ((TextField)dateE.getEditor()).getText());
    		 statement.setString(8, ((TextField)dateS.getEditor()).getText());
    		 statement.setString(9, diagnosticEtxt);
    		 statement.setString(10, diagnosticStxt);
    		 statement.setString(11, prtxt);
    		 statement.setString(12, drtxt);
 	         statement.execute();
 	         ViderDonnees();
 	         afficherSignal("Patient ajouté avec succès",Color.GREEN);
    		 }

 		} catch (SQLException e) {
 			e.printStackTrace();
 		}

    }

    StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter =
                  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }
        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    };

    void afficherSignal(String txt,Color couleur){
    	LabelSignal.setTextFill(couleur);
    	LabelSignal.setText(txt);
    }

    private void ViderDonnees(){
    	ip.setText("");
	    code.setText("");
	    nomETprenom.setText("");
	    tel.setText("");
	    adresse.setText("");
	    diagnosticE.setText("");
	    diagnosticS.setText("");
	    pr.setText("");
	    dr.setText("");
    }


    @FXML
    private void initialize() throws SQLException {
		conn = ConnexionBd.getConnection();
		dateE.setConverter(converter);
		dateS.setConverter(converter);

    }
}
