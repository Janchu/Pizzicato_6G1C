package pizzicato.model;


public class Tilausrivi {
	private Tuote tilattuTuote;

	public Tilausrivi(Tuote tilattuTuote) {
		this.tilattuTuote = tilattuTuote;
	}
	
	public Tilausrivi() {
		
	}

	public Tuote getTilattuTuote() {
		return tilattuTuote;
	}

	public void setTilattuTuote(Tuote tilattuTuote) {
		this.tilattuTuote = tilattuTuote;
	}

	@Override
	public String toString() {
		return "Tilausrivi [tilattuTuote=" + tilattuTuote + "]";
	}

	
	
	
	
}
