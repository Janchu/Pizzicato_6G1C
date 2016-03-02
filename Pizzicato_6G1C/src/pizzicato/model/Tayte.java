package pizzicato.model;

public class Tayte {

	// Attribuutit
	
	private int id;
	private int nimi;
	private double hinta;
		

	// Konstruktorit
	
	public Tayte() {
		
	}


	public Tayte(int id, int nimi, double hinta) {
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


	public int getNimi() {
		return nimi;
	}


	public void setNimi(int nimi) {
		this.nimi = nimi;
	}


	public double getHinta() {
		return hinta;
	}


	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	// To-String
	
	@Override
	public String toString() {
		return "Tayte [id=" + id + ", nimi=" + nimi + ", hinta=" + hinta + "]";
	}
		
}
