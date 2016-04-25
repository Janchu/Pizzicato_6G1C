package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pizzicato.model.Kayttaja;
import pizzicato.model.Tilaus;



public class TilausDAO extends DataAccessObject {

	public void addTilaus(Tilaus tilaus, Kayttaja tilaaja) throws SQLException {
		
		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;
	
		try {
			connection = getConnection();
			stmtInsert = connection.prepareStatement("");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
		
		
	}
	
	
}
