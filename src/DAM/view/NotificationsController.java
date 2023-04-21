package DAM.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAM.Model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class NotificationsController {
    @FXML
    private TableView<Patient> TableDonnees;

    @FXML
    private TableColumn<Patient, Integer> idColumn;

    @FXML
    private TableColumn<Patient, String> codeColumn;

    @FXML
    private TableColumn<Patient, String> nomEtprenomColumn;

    public ObservableList<Patient> data = FXCollections.observableArrayList();

	Connection conn;
	PreparedStatement st;
	ResultSet res;

	void afficherDonnees(String sql) {
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
    void initialize() {
    	conn = ConnexionBd.getConnection();
		afficherDonnees("SELECT * FROM patients where EXTRACT(year from now()) - EXTRACT(year from dateE) >= 14 AND  " +testController.RetrunIdService());
    }
}
