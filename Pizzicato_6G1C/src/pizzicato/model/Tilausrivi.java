package pizzicato.model;

import java.util.ArrayList;


public class Tilausrivi {
	private Tuote tilattuTuote;
	private int lkm;
	private ArrayList<Mauste> maustelista;

	
	public Tilausrivi() {
		
	}


	public Tilausrivi(Tuote tilattuTuote, int lkm, ArrayList<Mauste> maustelista) {	
		this.tilattuTuote = tilattuTuote;
		this.lkm = lkm;
		this.maustelista = maustelista;
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
		return "Tilausrivi [tilattuTuote=" + tilattuTuote + ", lkm=" + lkm
				+ "]";
	}
	

	
	
	
}
