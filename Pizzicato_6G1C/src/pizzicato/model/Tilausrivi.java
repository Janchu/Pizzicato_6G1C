package pizzicato.model;

import java.util.ArrayList;


public class Tilausrivi {
	private int rivinumero;
	private double rivihinta;
	private Tuote tilattuTuote;
	private int lkm;
	private ArrayList<Mauste> maustelista;

	
	public Tilausrivi() {
		
	}


	public Tilausrivi(int rivinumero, double rivihinta, Tuote tilattuTuote, int lkm, ArrayList<Mauste> maustelista) {
		this.rivinumero = rivinumero;
		this.rivihinta = rivihinta;
		this.tilattuTuote = tilattuTuote;
		this.lkm = lkm;
		this.maustelista = maustelista;
	}

	public int getRivinumero() {
		return rivinumero;
	}
	
	public void setRivinumero (int rivinumero) {
		this.rivinumero = rivinumero;
	}

	public double getRivihinta() {
		return rivihinta;
	}
	
	public void setRivihinta (double rivihinta) {
		this.rivihinta = rivihinta;
	}	

	public Tuote getTilattuTuote() {
		return tilattuTuote;
	}


	public void setTilattuTuote(Tuote tilattuTuote) {
		this.tilattuTuote = tilattuTuote;
	}


	public int getLkm() {
		return lkm;
	}


	public void setLkm(int lkm) {
		this.lkm = lkm;
	}


	public ArrayList<Mauste> getMaustelista() {
		return maustelista;
	}


	public void setMaustelista(ArrayList<Mauste> maustelista) {
		this.maustelista = maustelista;
	}


	@Override
	public String toString() {
		return "Tilausrivi [rivinumero=" + rivinumero + ", rivihinta="
				+ rivihinta + ", tilattuTuote=" + tilattuTuote + ", lkm=" + lkm
				+ ", maustelista=" + maustelista + "]";
	}



	

	
	
	
}
