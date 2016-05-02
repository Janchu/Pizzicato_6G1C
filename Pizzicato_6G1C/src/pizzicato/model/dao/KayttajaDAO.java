package pizzicato.model.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato.model.Kayttaja;

public class KayttajaDAO extends DataAccessObject {

	private static KayttajaDAO instance = new KayttajaDAO();

	public static KayttajaDAO getInstance() {
		return instance;
	}

	public ArrayList<Kayttaja> findAll() {
		// Alustetaan Connection-, PreparedStatement-, ResultSet-, nulleiksi
		// ennen try-catchia
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Kayttaja> kayttajalista = new ArrayList<Kayttaja>();
		Kayttaja kayttaja = null;
		int kayttajaIdEdellinen = 0;

		try {
			conn = getConnection();
			String sqlSelect = "SELECT kayttaja.id, kayttaja.etunimi, kayttaja.sukunimi, kayttaja.salasana, kayttaja.tyyppi, kayttaja.puh, kayttaja.osoite, kayttaja.postinro, kayttaja.postitmp, kayttaja.email FROM kayttaja;";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				// Jos kayttajaId eri kuin edellinen, luodaan uusi kayttaja

				int kayttajaId = rs.getInt("kayttaja.id");
				if (kayttajaId != kayttajaIdEdellinen) {
					kayttaja = read(rs);
					kayttajalista.add(kayttaja);
				}
				
				kayttajaIdEdellinen = rs.getInt("kayttaja.id");

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		return kayttajalista;
	}

	private Kayttaja read(ResultSet rs) {
		
		try {
		int id = rs.getInt("kayttaja.id");
		String etunimi = rs.getString("kayttaja.etunimi");
		String sukunimi = rs.getString("kayttaja.sukunimi");
		String salasana = rs.getString("kayttaja.salasana");
		String tyyppi = rs.getString("kayttaja.tyyppi");
		String puh = rs.getString("kayttaja.puh");
		String osoite = rs.getString("kayttaja.osoite");
		String postinro = rs.getString("kayttaja.postinro");
		String postitmp = rs.getString("kayttaja.postitmp");
		String email = rs.getString("kayttaja.email");

		// Palautetaan kayttaja
		return new Kayttaja(id, etunimi, sukunimi, salasana, tyyppi, puh,
				osoite, postinro, postitmp, email);
		
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// testi
	public void create(Kayttaja kayttaja) throws SQLException {

		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			stmtInsert = connection
					.prepareStatement("INSERT INTO kayttaja(id, etunimi, sukunimi, salasana, tyyppi, puh, osoite, postinro, email, postitmp) VALUES (last_insert_id(), ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			stmtInsert.setString(1, kayttaja.getEtunimi());
			stmtInsert.setString(2, kayttaja.getSukunimi());
			stmtInsert.setString(3, kayttaja.getSalasana());
			stmtInsert.setString(4, kayttaja.getTyyppi());
			stmtInsert.setString(5, kayttaja.getPuh());
			stmtInsert.setString(6, kayttaja.getOsoite());
			stmtInsert.setString(7, kayttaja.getPostinro());
			stmtInsert.setString(9, kayttaja.getEmail());
			stmtInsert.setString(8, kayttaja.getPostitmp());
			stmtInsert.executeUpdate();
			stmtInsert.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}

	}

	public Kayttaja etsiTunnuksella(String kayttajatunnus) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection connection = null;

		try {
			connection = getConnection();
			String sql = "SELECT kayttaja.id, kayttaja.etunimi, kayttaja.sukunimi, kayttaja.salasana, kayttaja.tyyppi, kayttaja.puh, kayttaja.osoite, kayttaja.postinro, kayttaja.postitmp, kayttaja.email FROM kayttaja WHERE email = (?);";
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
