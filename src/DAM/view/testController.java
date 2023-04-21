package DAM.view;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
public class testController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private String State;
	static String sess;
	static String idService;
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	 @FXML
	    private AnchorPane centrePane;
	 @FXML
	    private BorderPane Home;

	 @FXML
	    private AnchorPane Container;

	 @FXML
	    private Button idListe;

	 @FXML
	   	private Button idAjouter;

	 @FXML
	    private Button idNotif;

	 @FXML
	    private Button idModifier;

	 @FXML
	    private Button idSupprimer;

	 @FXML
	    private Button idInfo;

	 @FXML
	    private Button Exit;

	 @FXML
	    private Label menuClose;

	 @FXML
	    private Label menu;

	 @FXML
	    private VBox slider;

	 @FXML
	    private Label ServiceLabel;

	 @FXML
	private Label SessionLabel;

	    @FXML
	    private Label ServiceLabel1;


	private TranslateTransition slide = new TranslateTransition();


	 @FXML
	 void AfficheListe() {
		 LoadPage("Liste");
		 SupID();
		 idListe.setId("active");
	 }

	 @FXML
	 void AfficheAjouter() {
		 LoadPage("Ajouter");
		 SupID();
		 idAjouter.setId("active");
	 }

	 @FXML
	 void AfficheModifier() {
		 String txt = "Modifier"+State;
		 LoadPage(txt);
		 SupID();
		 idModifier.setId("active");
	 }

	 @FXML
	 void AfficheSupprimer() {
		 String txt = "Supprimer"+State;
		 LoadPage(txt);
		 SupID();
		 idSupprimer.setId("active");
	 }

	 @FXML
	 void AfficheInfo() {
		 LoadPage("Info");
		 SupID();
		 idInfo.setId("active");
	 }
	 @FXML
	 void AfficheNotifications() {
		 LoadPage("Notifications");
		 SupID();
		 idNotif.setId("active");
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


	private void LoadPage(String x){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(x+".fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		Home.setCenter(root);
	}
	private void SupID(){
		idListe.setId("Noactive");
		idAjouter.setId("Noactive");
		idModifier.setId("Noactive");
		idSupprimer.setId("Noactive");
		idInfo.setId("Noactive");
		idNotif.setId("Noactive");
	}


	public void ExitHome(){
		System.exit(0);
	}

	public void ActiverSlide(){
		slide.setDuration(Duration.seconds(0.4));

		slide.setNode(slider);

        slide.setToX(0);
        slide.play();

        slider.setTranslateX(-143);

        slide.setOnFinished((ActionEvent e)-> {
            menu.setVisible(false);
            menuClose.setVisible(true);
            AnchorPane f = (AnchorPane)Home.getCenter();
            f.setPrefWidth(f.getWidth()-slider.getWidth());
            f.setTranslateX(0);

        });

    };

    public void DesactiverSilde(){
    	slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);

        slide.setToX(-143);
        slide.play();

        slider.setTranslateX(0);

        slide.setOnFinished((ActionEvent e)-> {
            menu.setVisible(true);
            menuClose.setVisible(false);
            AnchorPane f = (AnchorPane)Home.getCenter();
            f.setPrefWidth(f.getWidth()+slider.getWidth());
            f.setTranslateX(-70);
        });

    };
    static String RetrunIdService(){
    	return idService;
    }
    public void NameServiceAndSession(String NameService,String Session) throws SQLException{
    	ServiceLabel.setText(NameService);
    	ServiceLabel1.setText(NameService);
    	SessionLabel.setText(Session);
    	sess = NameService;
    	State = Session;

    	conn = ConnexionBd.getConnection();
	    String sql1 = "Select * from services where nom = '"+sess+"'";
		st = conn.prepareStatement(sql1);
		rs =  st.executeQuery();
		while (rs.next()) {
			idService = rs.getString(1);
		}

    }





}
