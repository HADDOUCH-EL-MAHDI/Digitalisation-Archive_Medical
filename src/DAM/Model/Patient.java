package DAM.Model;



public class Patient {
	private int ip;
	private String code;
	private String nomETprenom;
	private String tel;
	private String adresse;
	private String dateE;
	private String dateS;
	private String diagnosticE;
	private String diagnosticS;
	private String pr;
	private String dr;



	public Patient(){

	}
	public Patient(int ip, String code, String nomETprenom, String tel, String adresse, String dateE, String dateS,
			String diagnosticE, String diagnosticS, String pr, String dr) {
		super();
		this.ip = ip;
		this.code = code;
		this.nomETprenom = nomETprenom;
		this.tel = tel;
		this.adresse = adresse;
		this.dateE = dateE;
		this.dateS = dateS;
		this.diagnosticE = diagnosticE;
		this.diagnosticS = diagnosticS;
		this.pr = pr;
		this.dr = dr;
	}


	public String getNomETprenom() {
		return nomETprenom;
	}

	public void setNomETprenom(String nomETprenom) {
		this.nomETprenom = nomETprenom;
	}

	public int getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getDateE() {
		return dateE;
	}
	public void setDateE(String dateE) {
		this.dateE = dateE;
	}
	public String getDateS() {
		return dateS;
	}
	public void setDateS(String dateS) {
		this.dateS = dateS;
	}
	public String getDiagnosticE() {
		return diagnosticE;
	}
	public void setDiagnosticE(String diagnosticE) {
		this.diagnosticE = diagnosticE;
	}
	public String getDiagnosticS() {
		return diagnosticS;
	}
	public void setDiagnosticS(String diagnosticS) {
		this.diagnosticS = diagnosticS;
	}
	public String getPr() {
		return pr;
	}
	public void setPr(String pr) {
		this.pr = pr;
	}
	public String getDr() {
		return dr;
	}
	public void setDr(String dr) {
		this.dr = dr;
	}







}
