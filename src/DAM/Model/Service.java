package DAM.Model;

public class Service {

	private String Nom;
	private String NombrePatients;

	public Service(String nm,String NbPatients){
		this.Nom = nm;
		this.NombrePatients = NbPatients;
	}

	public Service(){

	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getNombrePatients() {
		return NombrePatients;
	}

	public void setNombrePatients(String nombrePatients) {
		NombrePatients = nombrePatients;
	}


}
