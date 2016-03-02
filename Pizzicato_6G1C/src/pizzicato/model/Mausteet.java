package pizzicato.model;

public class Mausteet {
	
	private int id;
	private String mausteet;
	
	
	//Konstruktorit
	public Mausteet(int id, String mausteet) {
		
		this.id = id;
		this.mausteet = mausteet;
	}


	public Mausteet() {
		
		this.id = 0;
		this.mausteet = null;
		
	}

	//getterit ja setterit

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMausteet() {
		return mausteet;
	}


	public void setMausteet(String mausteet) {
		this.mausteet = mausteet;
	}
	
	//to string

	@Override
	public String toString() {
		return "Mausteet [id=" + id + ", mausteet=" + mausteet + "]";
	}
	
	
	
}
