package pizzicato.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import pizzicato.model.Kayttaja;

public class KayttajaDAO extends DataAccessObject {

	private static KayttajaDAO instance = new KayttajaDAO();
	
	public static KayttajaDAO getInstance() {
		return instance;
	}
	
	private Kayttaja read(ResultSet rs) throws SQLException {
		int id = rs.getInt("kayttaja.id");
		String etunimi = rs.getString("kayttaja.etunimi");
		String sukunimi = rs.getString("kayttaja.sukunimi");
		String salasana = rs.getString("kayttaja.salasana");
		String tyyppi = rs.getString("kayttaja.tyyppi");
		String puh = rs.getString("kayttaja.puh");
		String osoite = rs.getString("kayttaja.osoite");
		String postinro = rs.getString("kayttaja.postinro");
		return new Kayttaja();
	}
	
}
