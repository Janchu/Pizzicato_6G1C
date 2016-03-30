package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato.model.Tayte;

public class TayteDAO extends DataAccessObject {

	/**
	 * Kaivaa kannasta kaikki t‰ytteet ja niiden tiedot. Luo jokaisesta
	 * t‰ytteest‰ Tayte-olion ja tekee luoduista Tayte-olioista ArrayListin.
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
			String sqlSelect = "SELECT id, nimi, nimi_eng, hinta, kilohinta FROM tayte;";
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
			// Haetaan yhden t‰ytteen tiedot
			int id = rs.getInt("id");
			String nimi = rs.getString("nimi");
			String nimi_eng = rs.getString("nimi_eng");
			double hinta = rs.getDouble("hinta");
			double kilohinta = rs.getDouble("kilohinta");

			return new Tayte(id, nimi, nimi_eng, hinta, kilohinta);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Tuodaan lisattavaTayte-olio addTayte-metodille. Vied‰‰n kantaan tiedot
	 * tayte- ja pizzatayte-tauluihin.
	 * 
	 * @param lisattavaTayte
	 *            Mukana tuotava Tayte-olio
	 * @throws SQLException
	 */
	public void addTayte(Tayte lisattavaTayte) throws SQLException {

		// Alustetaan Connection ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			connection = getConnection();
			stmtInsert = connection
					.prepareStatement("INSERT INTO tayte(id, nimi, hinta, nimi_eng, kilohinta) VALUES (last_insert_id(), ?, ?, ?, ?);");

			stmtInsert.setString(2, lisattavaTayte.getNimi());
			stmtInsert.setDouble(3, lisattavaTayte.getHinta());
			stmtInsert.setString(4, lisattavaTayte.getNimi_eng());
			stmtInsert.setDouble(5, lisattavaTayte.getKilohinta());
			stmtInsert.executeUpdate();
			stmtInsert.close();

		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}

	/**
	 * Metodiin tuodaan poistettavaTayte-olio, jonka id:n perusteella kannasta
	 * poistetaan tietty tayte ja sen yhteydet pizzoihin.
	 * 
	 * @param poistettavaTayte
	 *            Mukana tuotava Tayte-olio
	 * @throws SQLException
	 */
	public void deleteTayte(Tayte poistettavaTayte) throws SQLException {

		// Alustetaan Connection ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		poistettavaTayte.getId();
		System.out.println(poistettavaTayte.getId());
		try {
			connection = getConnection();

			stmtInsert = connection
					.prepareStatement("DELETE FROM tayte WHERE id = (?);");
			stmtInsert.setInt(1, poistettavaTayte.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}

	/**
	 * Tuodaan mukana paivitettavaTayte-olio, jonka id:n perusteella kannassa
	 * tehd‰‰n muutokset tayte- ja pizzatayte-tauluihin.
	 * 
	 * @param paivitettavaTayte
	 *            Mukana tuotava Tayte-olio
	 * @throws SQLException
	 */

	public void updateTayte(Tayte paivitettavaTayte) throws SQLException {

		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			connection = getConnection();
			stmtInsert = connection.prepareStatement("UPDATE tayte SET nimi = (?) WHERE id = (?)");
			stmtInsert.setString(1, paivitettavaTayte.getNimi());
			stmtInsert.setInt(2, paivitettavaTayte.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection.prepareStatement("UPDATE tayte SET hinta = (?) WHERE id = (?)");
			stmtInsert.setDouble(1, paivitettavaTayte.getHinta());
			stmtInsert.setInt(2, paivitettavaTayte.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection.prepareStatement("UPDATE tayte SET nimi_eng = (?) WHERE id = (?)");
			stmtInsert.setString(1, paivitettavaTayte.getNimi_eng());
			stmtInsert.setInt(2, paivitettavaTayte.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection.prepareStatement("UPDATE tayte SET kilohinta = (?) WHERE id = (?)");
			stmtInsert.setDouble(1, paivitettavaTayte.getKilohinta());
			stmtInsert.setInt(2, paivitettavaTayte.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}

}
