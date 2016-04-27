package pizzicato.model;

public class Tuote {

	// Attribuutit
	private int id;
	private String tyyppi;
	private String nimi;
	private double hinta;

	// Oletuskonstruktori
	public Tuote() {
		super();
		this.id = 0;
		this.tyyppi = null;
		this.nimi = null;
		this.hinta = 0;
	}

	// Parametrillinen konstruktori
	public Tuote(int id, String tyyppi, String nimi, double hinta) {
		super();
		this.id = id;
		this.tyyppi = tyyppi;
		this.nimi = nimi;
		this.hinta = hinta;
	}

	// Oliokonstruktori
	public Tuote(Tuote tuote) {
		super();
		this.id = tuote.getId();
		this.tyyppi = tuote.getTyyppi();
		this.nimi = tuote.getNimi();
		this.hinta = tuote.getHinta();
	}

	// Getterit ja setterit
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTyyppi() {
		return tyyppi;
	}

	public void setTyyppi(String tyyppi) {
		this.tyyppi = tyyppi;
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
		return "Tuote [id=" + id + ", tyyppi=" + tyyppi + ", nimi=" + nimi
				+ ", hinta=" + hinta + "]";
	}

}
