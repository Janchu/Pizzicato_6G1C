package pizzicato.model;

public class Juoma extends Tuote {

	// Attribuutit kukkuu
	private double koko;
	private int nakyvyys;

	// Oletuskonstruktori
	public Juoma() {
		this.koko = 0;
		this.nakyvyys = 0;
	}

	// Parametrillinen konstruktori
	public Juoma(int id, String tyyppi, String nimi, double hinta, double koko, int nakyvyys) {
		setId(id);
		setTyyppi(tyyppi);
		setNimi(nimi);
		setHinta(hinta);
		this.koko = koko;
		this.nakyvyys = nakyvyys;

	}
	
	// Oliokonstruktori
	public Juoma(Juoma juoma) {
		setId(juoma.getId());
		setTyyppi(juoma.getTyyppi());
		setNimi(juoma.getNimi());
		setHinta(juoma.getHinta());
		this.koko = juoma.getKoko();
		this.nakyvyys = juoma.getNakyvyys();

	}
	
	// Getterit ja setterit
	public double getKoko() {
		return koko;
	}

	public void setKoko(double koko) {
		this.koko = koko;
	}
	
	public int getNakyvyys() {
		return nakyvyys;
	}
	
	public void setNakyvyys(int nakyvyys) {
		this.nakyvyys = nakyvyys;
	}

	// To-String
		@Override
	public String toString() {
		return "Juoma [koko=" + koko + ", nakyvyys=" + nakyvyys + "]";
	}

}
