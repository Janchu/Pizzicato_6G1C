package pizzicato.model;

import java.util.ArrayList;

public class Tilaus {
	
	// Attribuutit
	
	private int id;
	private String tila;
	private String maksutapa;
	private String toimitus;
	private String lisatiedot;
	private ArrayList<Tilausrivi> tilausrivit;
	private double yhthinta;
	private int yhtlkm;
	
	
	// Oletuskonstruktori
	
	public Tilaus() {
		this.id = 0;
		this.tila = null;
		this.maksutapa = null;
		this.toimitus = null;
		this.lisatiedot = null;
		this.tilausrivit = new ArrayList<Tilausrivi>();
		this.yhthinta = 0;
		this.yhtlkm = 0;
	}

	// Parametrillinen konstruktori
	
	public Tilaus(int id, String tila, String maksutapa, String toimitus,
			String lisatiedot, ArrayList<Tilausrivi> tilausrivit, double yhthinta, int yhtlkm) {
		super();
		this.id = id;
		this.tila = tila;
		this.maksutapa = maksutapa;
		this.toimitus = toimitus;
		this.lisatiedot = lisatiedot;
		this.tilausrivit = tilausrivit;
		this.yhthinta = yhthinta;
		this.yhtlkm = yhtlkm;

	}
	
	// Getters & Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTila() {
		return tila;
	}

	public void setTila(String tila) {
		this.tila = tila;
	}

	public String getMaksutapa() {
		return maksutapa;
	}

	public void setMaksutapa(String maksutapa) {
		this.maksutapa = maksutapa;
	}

	public String getToimitus() {
		return toimitus;
	}

	public void setToimitus(String toimitus) {
		this.toimitus = toimitus;
	}

	public String getLisatiedot() {
		return lisatiedot;
	}

	public void setLisatiedot(String lisatiedot) {
		this.lisatiedot = lisatiedot;
	}
	
	public ArrayList<Tilausrivi> getTilausrivit() {
		return tilausrivit;
	}
	
	public void addTilausrivi(Tilausrivi uusiTilausrivi) {
		this.tilausrivit.add(uusiTilausrivi);
	}
	
	public void setTilausrivit(ArrayList<Tilausrivi> tilausrivit) {
		this.tilausrivit = tilausrivit;
	}

	
	public double getYhthinta() {
		return yhthinta;
	}
	
	public void setYhthinta(double yhthinta) {
		this.yhthinta = yhthinta;
	}
	
	public int getYhtlkm() {
		return yhtlkm;
	}
	
	public void setYhtlkm(int yhtlkm) {
		this.yhtlkm = yhtlkm;
	}
		
	// ToString
	@Override
	public String toString() {
		return "Tilaus [id=" + id + ", tila=" + tila + ", maksutapa="
				+ maksutapa + ", toimitus=" + toimitus + ", lisatiedot="
				+ lisatiedot + ", tilausrivit=" + tilausrivit + ", yhthinta="
				+ yhthinta + "]";
	}

	
	

	
	

}
