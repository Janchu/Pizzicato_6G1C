package pizzicato.model;

public class Mauste {

	private int id;
	private String mausteet;

	// Konstruktorit
	public Mauste(int id, String mausteet) {

		this.id = id;
		this.mausteet = mausteet;
	}

	public Mauste() {

		this.id = 0;
		this.mausteet = null;

	}

	// getterit ja setterit

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMausteet() {
		return mausteet;
	}

	public void setMausteet(String mausteet) {
		this.mausteet = mausteet;
	}

	// toString

	@Override
	public String toString() {
		return "Mausteet [id=" + id + ", mausteet=" + mausteet + "]";
	}

}
