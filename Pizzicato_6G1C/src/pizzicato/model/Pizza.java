package pizzicato.model;

import java.util.ArrayList;

public class Pizza extends Tuote {
	
	// Attribuutit
	private int nakyvyys;
	private String pohja;
	private ArrayList<Tayte> taytelista;

	// Oletuskonstruktori
	public Pizza() {
		super();
		this.nakyvyys = 0;
		this.pohja = null;
		this.taytelista = null;
	}

	// Parametrillinen konstruktori
	public Pizza(int id, String tyyppi, String nimi, double hinta,
			int nakyvyys, String pohja, ArrayList<Tayte> taytelista) {
		super();
		setId(id);
		setTyyppi(tyyppi);
		setNimi(nimi);
		setHinta(hinta);
		this.nakyvyys = nakyvyys;
		this.pohja = pohja;
		this.taytelista = taytelista;
	}

	// Oliokonstruktori
	public Pizza(Pizza pizza) {
		super();
		setId(pizza.getId());
		setTyyppi(pizza.getTyyppi());
		setNimi(pizza.getNimi());
		setHinta(pizza.getHinta());
		this.nakyvyys = pizza.getNakyvyys();
		this.pohja = pizza.getPohja();
		this.taytelista = new ArrayList<Tayte>();
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

	public ArrayList<Tayte> getTaytelista() {
		return taytelista;
	}

	public void addTayte(Tayte uusiTayte) {
		this.taytelista.add(uusiTayte);
	}
	
	public void setTaytelista(ArrayList<Tayte> taytelista) {
		this.taytelista = taytelista;
	}

	// To-String
	@Override
	public String toString() {
		return "Tuote[" + super.getTyyppi() + "Pizza [nakyvyys=" + nakyvyys + ", pohja=" + pohja
				+ ", taytelista=" + taytelista + "]";
	}

}
