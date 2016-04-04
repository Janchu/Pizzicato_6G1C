package pizzicato.model;

public class Juoma extends Tuote {

	// Attribuutit kukkuu
	private double koko;

	// Oletuskonstruktori
	public Juoma() {
		this.koko = 0;
	}

	// Parametrillinen konstruktori
	public Juoma(int id, String tyyppi, String nimi, double hinta, double koko) {
		setId(id);
		setTyyppi(tyyppi);
		setNimi(nimi);
		setHinta(hinta);
		this.koko = koko;

	}
	
	// Oliokonstruktori
	public Juoma(Juoma juoma) {
		setId(juoma.getId());
		setTyyppi(juoma.getTyyppi());
		setNimi(juoma.getNimi());
		setHinta(juoma.getHinta());
		this.koko = juoma.getKoko();

	}
	
	// Getterit ja setterit
	public double getKoko() {
		return koko;
	}

	public void setKoko(double koko) {
		this.koko = koko;
	}

	// To-String
	@Override
	public String toString() {
		return "Juoma [koko=" + koko + "]";
	}

}
