package pizzicato.model;

public class Tayte {

	// Attribuutit

	private int id;
	private String nimi;
	private String nimi_eng;
	private double hinta;

	// Konstruktorit

	public Tayte() {

	}

	public Tayte(int id, String nimi, String nimi_eng, double hinta) {

		this.id = id;
		this.nimi = nimi;
		this.nimi_eng = nimi_eng;
		this.hinta = hinta;
	}

	// Getterit & Setterit

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

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	// toString
	@Override
	public String toString() {
		return "Tayte [id=" + id + ", nimi=" + nimi + ", nimi_eng=" + nimi_eng
				+ ", hinta=" + hinta + "]";
	}

}
