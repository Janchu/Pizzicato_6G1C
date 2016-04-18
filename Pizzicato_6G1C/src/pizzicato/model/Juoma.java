package pizzicato.model;

public class Juoma extends Tuote {

	// Attribuutit kukkuu
	private double koko;
	private int nakyvyys;
	private String nimi_eng;

	// Oletuskonstruktori
	public Juoma() {
		this.koko = 0;
		this.nakyvyys = 0;
		this.nimi_eng = "";
	}

	// Parametrillinen konstruktori
	public Juoma(int id, String tyyppi, String nimi, double hinta, double koko, int nakyvyys, String nimi_eng) {
		setId(id);
		setTyyppi(tyyppi);
		setNimi(nimi);
		setHinta(hinta);
		this.koko = koko;
		this.nakyvyys = nakyvyys;
		this.nimi_eng = nimi_eng;

	}
	
	// Oliokonstruktori
	public Juoma(Juoma juoma) {
		setId(juoma.getId());
		setTyyppi(juoma.getTyyppi());
		setNimi(juoma.getNimi());
		setHinta(juoma.getHinta());
		this.koko = juoma.getKoko();
		this.nakyvyys = juoma.getNakyvyys();
		this.nimi_eng = juoma.getNimi_eng();

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
	
	public String getNimi_eng() {
		return nimi_eng;
	}
	
	public void setNimi_eng(String nimi_eng) {
		this.nimi_eng = nimi_eng;
	}


	@Override
	public String toString() {
		return "Juoma [koko=" + koko + ", nakyvyys=" + nakyvyys + ", nimi_eng="
				+ nimi_eng + "]";
	}

}
