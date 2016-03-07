package pizzicato.model;

public class Pizza extends Tuote {
	private int nakyvyys;
	private String pohja;

	// Oletuskonstruktori
	public Pizza() {
		this.nakyvyys = 0;
		this.pohja = null;
	}

	// Parametrillinen konstruktori
	public Pizza(int id, String tyyppi, String nimi, double hinta,
			int nakyvyys, String pohja) {
		setId(id);
		setTyyppi(tyyppi);
		setNimi(nimi);
		setHinta(hinta);
		this.nakyvyys = nakyvyys;
		this.pohja = pohja;
	}

	// Oliokonstruktori huehue
	public Pizza(Pizza pizza) {
		setId(pizza.getId());
		setTyyppi(pizza.getTyyppi());
		setNimi(pizza.getNimi());
		setHinta(pizza.getHinta());
		this.nakyvyys = pizza.getNakyvyys();
		this.pohja = pizza.getPohja();
	}

	// Getterit ja setterit
	public int getNakyvyys() {
		return nakyvyys;
	}

	public void setNakyvyys(int nakyvyys) {
		this.nakyvyys = nakyvyys;
	}

	public String getPohja() {
		return pohja;
	}

	public void setPohja(String pohja) {
		this.pohja = pohja;
	}

	// toString
	@Override
	public String toString() {
		return "Pizza [nakyvyys=" + nakyvyys + ", pohja=" + pohja + "]";
	}

}
