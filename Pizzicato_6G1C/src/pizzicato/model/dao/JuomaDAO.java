package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato.model.Juoma;

public class JuomaDAO extends DataAccessObject {

	/**
	 * Kaivaa kannasta juomat ja niiden tiedot. Luo jokaisesta kannassa olevasta
	 * juomasta Juoma-olion ja tekee luoduista Juoma-olioista ArrayListin.
	 * 
	 * @return Palauttaa valmiin juomalista-ArrayListin
	 */

	public ArrayList<Juoma> findAll() {

		// Alustetaan Connection-, PreparedStatement-, ResultSet-, nulleiksi
		// ennen try-catchia
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Juoma> juomalista = new ArrayList<Juoma>();
		Juoma juoma = null;
		int juomaIdEdellinen = 0;

		try {
			conn = getConnection();
			String sqlSelect = "SELECT juoma.id, tuote.tyyppi, juoma.koko, juoma.nakyvyys, tuote.nimi, tuote.hinta FROM tuote JOIN juoma ON tuote.id = juoma.id ORDER BY tuote.tyyppi DESC, tuote.hinta ASC, tuote.id ASC;";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				// Jos juomaId eri kuin edellinen, luodaan uusi juoma

				int juomaId = rs.getInt("juoma.id");
				if (juomaId != juomaIdEdellinen) {
					juoma = readJuoma(rs);
					juomalista.add(juoma);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		return juomalista;
	}

	/**
	 * Tuodaan lisattavaJuoma-olio addJuoma-metodille. Viedäänn kantaan tiedot
	 * tuote- ja juoma-tauluihin
	 * 
	 * @param lisattavaJuoma
	 *            Mukana tuotava Juoma-olio
	 * @throws SQLException
	 */

	public void addJuoma(Juoma lisattavaJuoma) throws SQLException {
		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			connection = getConnection();
			stmtInsert = connection
					.prepareStatement("INSERT INTO tuote (tyyppi, nimi, hinta) VALUES (?,?,?);");
			stmtInsert.setString(1, lisattavaJuoma.getTyyppi());
			stmtInsert.setString(2, lisattavaJuoma.getNimi());
			stmtInsert.setDouble(3, lisattavaJuoma.getHinta());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection
					.prepareStatement("INSERT INTO juoma (id, nakyvyys, koko) VALUES (last_insert_id(), ?, ?);");
			stmtInsert.setDouble(1, lisattavaJuoma.getKoko());
			stmtInsert.executeUpdate();
			stmtInsert.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}

	/**
	 * Metodiin tuodaan poistettavaJuoma-olio, jonka id:n perusteella kannasta
	 * poistetaan tietty juoma ja sen yhteydet täytteisiin.
	 * 
	 * @param poistettavaJuoma
	 *            Mukana tuotava Juoma-olio
	 * @throws SQLException
	 */
	public void deleteJuoma(Juoma poistettavaJuoma) throws SQLException {

		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		poistettavaJuoma.getId();
		System.out.println(poistettavaJuoma.getId());
		try {
			connection = getConnection();

			stmtInsert = connection
					.prepareStatement("DELETE FROM juoma WHERE id = (?);");
			stmtInsert.setInt(1, poistettavaJuoma.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection
					.prepareStatement("DELETE FROM tuote WHERE id = (?);");
			stmtInsert.setInt(1, poistettavaJuoma.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}

	}
	
	/**
	 * Tuodaan mukana piilotettavaJuoma-olio, jonka id:n perusteella kannasta
	 * muutetaan kyseisen juoman nakyvyys-ominaisuutta.
	 * 
	 * @param piilotettavaJuoma
	 *            Mukana tuotava Juoma-olio
	 * @throws SQLException
	 */
	public void hideJuoma(Juoma piilotettavaJuoma) throws SQLException {

		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		try {
			connection = getConnection();
			if (piilotettavaJuoma.getNakyvyys() == 1) {
				stmtInsert = connection
						.prepareStatement("UPDATE juoma SET nakyvyys = 0 WHERE id = (?);");
				stmtInsert.setInt(1, piilotettavaJuoma.getId());
				stmtInsert.executeUpdate();
				stmtInsert.close();
			} else if (piilotettavaJuoma.getNakyvyys() == 0) {
				stmtInsert = connection
						.prepareStatement("UPDATE juoma SET nakyvyys = 1 WHERE id = (?);");
				stmtInsert.setInt(1, piilotettavaJuoma.getId());
				stmtInsert.executeUpdate();
				stmtInsert.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}

	}

	/**
	 * Tuodaan mukana paivitettavaJuoma-olio, jonka id:n perusteella kannassa
	 * tehdään muutokset juoma- ja tuote-tauluihin.
	 * 
	 * @param paivitettavaJuoma
	 *            Mukana tuotava Juoma-olio
	 * @throws SQLException
	 */
	public void updateJuoma(Juoma paivitettavaJuoma) throws SQLException {

		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			connection = getConnection();
			stmtInsert = connection
					.prepareStatement("UPDATE tuote SET nimi = (?) WHERE id = (?)");
			stmtInsert.setString(1, paivitettavaJuoma.getNimi());
			stmtInsert.setInt(2, paivitettavaJuoma.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection
					.prepareStatement("UPDATE tuote SET hinta = (?) WHERE id = (?)");
			stmtInsert.setDouble(1, paivitettavaJuoma.getHinta());
			stmtInsert.setInt(2, paivitettavaJuoma.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}

	}

	private Juoma readJuoma(ResultSet rs) {
		try {
			// Haetaan yhden juoman tiedot
			int id = rs.getInt("juoma.id");
			String tyyppi = rs.getString("tuote.tyyppi");
			String nimi = rs.getString("tuote.nimi");
			double hinta = rs.getDouble("tuote.hinta");
			double koko = rs.getDouble("juoma.koko");
			int nakyvyys = rs.getInt("juoma.nakyvyys");
			String nimi_eng = rs.getString("juoma.nimi_eng");

			// Palautetaan pizza
			return new Juoma(id, tyyppi, nimi, hinta, koko, nakyvyys, nimi_eng);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
