package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato.model.Tayte;

public class TayteDAO extends DataAccessObject {

	/**
	 * Kaivaa kannasta kaikki täytteet ja niiden tiedot. Luo jokaisesta
	 * täytteestä Tayte-olion ja tekee luoduista Tayte-olioista ArrayListin.
	 * 
	 * @return Palauttaa valmiin taytelista-ArrayListin
	 */
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

	public ArrayList<Tayte> findAllEng() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Tayte> taytelista = new ArrayList<Tayte>();
		Tayte tayte = null;

		try {
			conn = getConnection();
			String sqlSelect = "SELECT id, nimi_eng, hinta from tayte;";
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
			String nimi_eng = rs.getString("nimi_eng");
			double hinta = rs.getDouble("hinta");

			return new Tayte(id, nimi, nimi_eng, hinta);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
