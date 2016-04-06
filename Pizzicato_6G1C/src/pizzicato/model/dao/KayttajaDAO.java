package pizzicato.model.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
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
		String postitmp = null; //rs.getString("kayttaja.postitmp");
		String email = rs.getString("kayttaja.sahkoposti");
		return new Kayttaja(id, etunimi, sukunimi, salasana, tyyppi, puh, osoite, postinro, postitmp, email);
	}

	// testi
	public void create(Kayttaja kayttaja) {
		PreparedStatement stmtInsert = null;
		Connection connection = null;
		try {
			connection = getConnection();
			stmtInsert = connection
					.prepareStatement("INSERT INTO posti(postinro, postitmp) VALUES(?, ?);");
			stmtInsert.setString(1, kayttaja.getPostinro());
			stmtInsert.setString(2, kayttaja.getPostitmp());
			stmtInsert.executeUpdate();
			stmtInsert.close();
			
			stmtInsert = connection.prepareStatement("INSERT INTO kayttaja(id, etunimi, sukunimi, salasana, tyyppi, puh, osoite, postinro, email) VALUES (last_insert_id(), ?, ?, ?, ?, ?, ?, ?, ?);");
			stmtInsert.setString(1, kayttaja.getEtunimi());
			stmtInsert.setString(2, kayttaja.getSukunimi());
			stmtInsert.setString(3, kayttaja.getSalasana());
			stmtInsert.setString(4, kayttaja.getTyyppi());
			stmtInsert.setString(5, kayttaja.getPuh());
			stmtInsert.setString(6, kayttaja.getOsoite());
			stmtInsert.setString(7, kayttaja.getPostinro());
			stmtInsert.setString(8, kayttaja.getEmail());
			stmtInsert.executeUpdate();
			stmtInsert.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	 public Kayttaja etsiTunnuksella(String kayttajatunnus) {
		 ResultSet rs = null;
		 PreparedStatement stmt = null;
		 Connection connection = null;
		 
		 try {
			connection = getConnection();
			String sql = "SELECT * FROM kayttaja where sahkoposti = (?);";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, kayttajatunnus);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return read(rs);
			} else {		
				return null;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, connection);
		}
	 }

}
