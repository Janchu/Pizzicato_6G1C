package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pizzicato.model.Kayttaja;
import pizzicato.model.Tilaus;
import pizzicato.model.Tilausrivi;



public class TilausDAO extends DataAccessObject {

	public void addTilaus(Tilaus tilaus, Kayttaja tilaaja, Tilausrivi tilausrivi) throws SQLException {
		
		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;
	
		try {
			connection = getConnection();
			
			stmtInsert = connection.prepareStatement("INSERT INTO posti (postinro, postitmp) VALUES (?,?);");
			stmtInsert.setString(1, tilaaja.getPostinro());
			stmtInsert.setString(2, tilaaja.getPostitmp());
			stmtInsert.executeUpdate();
			stmtInsert.close();
			
			stmtInsert = connection.prepareStatement("INSERT INTO tilaavaasiakas (id, etunimi, sukunimi, osoite, puh, email, postinro) VALUES ('last_insert_id()', ?, ?, ?, ?, ?, ?);");
			stmtInsert.setString(1, tilaaja.getEtunimi());
			stmtInsert.setString(2, tilaaja.getSukunimi());
			stmtInsert.setString(3, tilaaja.getOsoite());
			stmtInsert.setString(4, tilaaja.getPuh());
			stmtInsert.setString(5, tilaaja.getEmail());
			stmtInsert.setString(6, tilaaja.getPostinro());
			stmtInsert.close();
			
			stmtInsert = connection.prepareStatement("INSERT INTO tilaus (id, tila, maksutapa, toimitus, lisatiedot, kayttaja_id, yhthinta) VALUES ('last_insert_id()', ?, ?, ?, ?, ?, ?);");
			stmtInsert.setString(1, tilaus.getTila());
			stmtInsert.setString(2, tilaus.getMaksutapa());
			stmtInsert.setString(3, tilaus.getToimitus());
			stmtInsert.setString(4, tilaus.getLisatiedot());
			stmtInsert.setInt(5, x);
			stmtInsert.setDouble(6, tilaus.getYhthinta());
			stmtInsert.close();
			
			stmtInsert = connection.prepareStatement("INSERT INTO tuotteentilaus (tilaus_id, lkm, tuote_id) VALUES ('last_insert_id()', ?, ?);");
			stmtInsert.setInt(1, tilausrivi.getLkm());
			stmtInsert.setInt(2, x);
			
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
		
		
	}
	
	
}
