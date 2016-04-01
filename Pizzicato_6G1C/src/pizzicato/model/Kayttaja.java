package pizzicato.model;

public class Kayttaja {
	
	// Attribuutit
	
	private int id;
	private String etunimi;
	private String sukunimi;
	private String salasana;
	private String tyyppi;
	private String puh;
	private String osoite;
	private String postinro;
	
	// Oletuskonstruktori
	
	public Kayttaja() {
		super();
	}
	
	// Parametrillinen konstruktori

	public Kayttaja(int id, String etunimi, String sukunimi, String salasana,
			String tyyppi, String puh, String osoite, String postinro) {
		super();
		this.id = id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.salasana = salasana;
		this.tyyppi = tyyppi;
		this.puh = puh;
		this.osoite = osoite;
		this.postinro = postinro;
	}
	
	// Get & Set

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	public String getSalasana() {
		return salasana;
	}

	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}

	public String getTyyppi() {
		return tyyppi;
	}

	public void setTyyppi(String tyyppi) {
		this.tyyppi = tyyppi;
	}

	public String getPuh() {
		return puh;
	}

	public void setPuh(String puh) {
		this.puh = puh;
	}

	public String getOsoite() {
		return osoite;
	}

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}

	public String getPostinro() {
		return postinro;
	}

	public void setPostinro(String postinro) {
		this.postinro = postinro;
	}
	
	// ToString

	@Override
	public String toString() {
		return "Kayttaja [id=" + id + ", etunimi=" + etunimi + ", sukunimi="
				+ sukunimi + ", salasana=" + salasana + ", tyyppi=" + tyyppi
				+ ", puh=" + puh + ", osoite=" + osoite + ", postinro="
				+ postinro + "]";
	}
	
	
	
	

}
