package pizzicato.model;

public class Pizza extends Tuote {
	private int nakyvyys;
	private String pohja;
	
	// Konstruktorit
	public Pizza(int id, String tyyppi, String nimi, double hinta,
			int nakyvyys, String pohja) {
		this.nakyvyys = nakyvyys;
		this.pohja = pohja;
	}

	public Pizza() {
		this.nakyvyys = 0;
		this.pohja = null;
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
