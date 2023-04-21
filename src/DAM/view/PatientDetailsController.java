package DAM.view;

import java.io.IOException;

import DAM.Model.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class PatientDetailsController {
	Patient patient;


    @FXML
    private AnchorPane ListeStage;

    @FXML
    private Label ipLabel;

    @FXML
    private Label nomETprenomLabel;

    @FXML
    private Label codeLabel;

    @FXML
    private Label telLabel;

    @FXML
    private Label adresseLabel;

    @FXML
    private Label dateELabel;

    @FXML
    private Label dateSLabel;

    @FXML
    private Label diagnosticELabel;

    @FXML
    private Label diagnosticSLabel;

    @FXML
    private Label prLabel;

    @FXML
    private Label drLabel;



    @FXML
    void Retourner() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Liste.fxml"));
    	ListeStage.getChildren().set(0, root);
    }



    public void setPatient(Patient patient) {
        this.patient = patient;

        ipLabel.setText(Integer.toString(patient.getIp()));
        nomETprenomLabel.setText(patient.getNomETprenom());
        codeLabel.setText(patient.getCode());
        telLabel.setText(patient.getTel());
        adresseLabel.setText(patient.getAdresse());
        dateELabel.setText(patient.getDateE());
        dateSLabel.setText(patient.getDateS());
        diagnosticELabel.setText(patient.getDiagnosticE());
        diagnosticSLabel.setText(patient.getDiagnosticS());
        prLabel.setText(patient.getPr());
        drLabel.setText(patient.getDr());

    }
}
