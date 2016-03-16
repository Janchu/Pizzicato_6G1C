package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato.model.Tayte;

public class TayteDAO extends DataAccessObject {

	public ArrayList<Tayte> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();
		Tayte tayte = null;

		try {
			conn = getConnection();
			String sqlSelect = "SELECT id, nimi, hinta from tayte;";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				tayte = readTayte(rs);
				taytelista.add(tayte);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		return taytelista;
	}

	private Tayte readTayte(ResultSet rs) {
		try {
			// Haetaan yhden täytteen tiedot
			int id = rs.getInt("id");
			String nimi = rs.getString("nimi");
			double hinta = rs.getDouble("hinta");

			return new Tayte(id, nimi, hinta);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
