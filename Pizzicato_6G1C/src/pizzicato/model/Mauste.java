package pizzicato.model;

public class Mauste {

	private int id;
	private String nimi;
	private String nimi_eng;

	// Konstruktorit
	public Mauste(int id, String nimi, String nimi_eng) {

		this.id = id;
		this.nimi = nimi;
		this.nimi_eng = nimi_eng;
	}

	public Mauste() {

		this.id = 0;
		this.nimi = null;
		this.nimi_eng = null;

	}

	// getterit ja setterit

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getNimi_eng() {
		return nimi_eng;
	}

	public void setNimi_eng(String nimi_eng) {
		this.nimi_eng = nimi_eng;
	}

	// toString
	
	@Override
	public String toString() {
		return "Mauste [id=" + id + ", nimi=" + nimi + ", nimi_eng=" + nimi_eng
				+ "]";
	}




}
