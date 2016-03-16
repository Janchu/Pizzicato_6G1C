package pizzicato.model;

public class Tayte {

	// Attribuutit

	private int id;
	private String nimi;
	private double hinta;

	// Konstruktorit

	public Tayte() {

	}

	public Tayte(int id, String nimi, double hinta) {

		this.id = id;
		this.nimi = nimi;
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

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	// toString

	@Override
	public String toString() {
		return "Tayte [id=" + id + ", nimi=" + nimi + ", hinta=" + hinta + "]";
	}

}
