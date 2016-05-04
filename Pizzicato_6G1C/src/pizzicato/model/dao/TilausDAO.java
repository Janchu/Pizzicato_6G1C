package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato.model.Kayttaja;
import pizzicato.model.Tilaus;
import pizzicato.model.Tilausrivi;

public class TilausDAO extends DataAccessObject {

	public ArrayList<Tilaus> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Tilaus> tilauslista = new ArrayList<Tilaus>();
		Tilaus tilaus = null;
		int tilausIdEdellinen = 0;

		try {
			conn = getConnection();
			String sqlSelect = "SELECT tilaus.id, tilaus.tila, tilaus.maksutapa, tilaus.toimitus, tilaus.lisatiedot, tilaus.kayttaja_id, tilaus.yhthinta FROM tilaus;";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				int tilausId = rs.getInt("tilaus.id");
				if (tilausId != tilausIdEdellinen) {
					tilaus = readTilaus(rs);
					tilauslista.add(tilaus);
				}
				tilausIdEdellinen = rs.getInt("tilaus.id");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		return tilauslista;
	}

	public void addTilaus(Tilaus tilaus, Kayttaja tilaaja) throws SQLException {

		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		PreparedStatement stmtSelect = null;
		ResultSet rs = null;


		try {
			connection = getConnection();

			stmtSelect = connection.prepareStatement("SELECT posti.postinro, posti.postitmp FROM posti WHERE postinro =?;");;
			stmtSelect.setString(1, tilaaja.getPostinro());
			rs = stmtSelect.executeQuery();
			
			// Jos postinumeroa ei löydy
			if(rs.next() == false) { 
			
				stmtInsert = connection
						.prepareStatement("INSERT INTO posti(postinro, postitmp) VALUES (?,?);");
				stmtInsert.setString(1, tilaaja.getPostinro());
				stmtInsert.setString(2, tilaaja.getPostitmp());
				stmtInsert.executeUpdate();
				stmtInsert.close();
			}
				stmtInsert = connection
						.prepareStatement("INSERT INTO kayttaja (id, etunimi, sukunimi, salasana, tyyppi, osoite, puh, email, postinro) VALUES ('last_insert_id()', ?, ?, ?, ?, ?, ?, ?, ?);");
				stmtInsert.setString(1, tilaaja.getEtunimi());
				stmtInsert.setString(2, tilaaja.getSukunimi());
				stmtInsert.setString(3, tilaaja.getSalasana());
				stmtInsert.setString(4, tilaaja.getTyyppi());
				stmtInsert.setString(5, tilaaja.getOsoite());
				stmtInsert.setString(6, tilaaja.getPuh());
				stmtInsert.setString(7, tilaaja.getEmail());
				stmtInsert.setString(8, tilaaja.getPostinro());
				stmtInsert.setString(9, tilaaja.getPostitmp());
				stmtInsert.executeUpdate();
				stmtInsert.close();

				stmtInsert = connection
						.prepareStatement("INSERT INTO tilaus (id, tila, maksutapa, toimitus, lisatiedot, kayttaja_id, yhthinta, tilausaika) VALUES ('last_insert_id()', ?, ?, ?, ?, ?, ?, ?);");
				stmtInsert.setString(1, tilaus.getTila());
				stmtInsert.setString(2, tilaus.getMaksutapa());
				stmtInsert.setString(3, tilaus.getToimitus());
				stmtInsert.setString(4, tilaus.getLisatiedot());
				stmtInsert.setInt(5, tilaaja.getId());
				stmtInsert.setDouble(6, tilaus.getYhthinta());
				stmtInsert.executeUpdate();
				stmtInsert.close();

				for (int i = 0; i < tilaus.getTilausrivit().size(); i++) {
					stmtInsert = connection
							.prepareStatement("INSERT INTO tilausrivi (rivinumero, tilaus_id, lkm, tuote_id) VALUES (?, 'last_insert_id()', ?, ?);");
					stmtInsert.setInt(1, tilaus.getTilausrivit().get(i)
							.getRivinumero());
					stmtInsert.setInt(2, tilaus.getTilausrivit().get(i)
							.getLkm());
					stmtInsert.setInt(3, tilaus.getTilausrivit().get(i)
							.getTilattuTuote().getId());
					stmtInsert.executeUpdate();
					stmtInsert.close();
				}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}

	}

	private Tilaus readTilaus(ResultSet rs) {
		try {
			int id = rs.getInt("tilaus.id");
			String tila = rs.getString("tilaus.tila");
			String maksutapa = rs.getString("tilaus.maksutapa");
			String toimitus = rs.getString("tilaus.toimitus");
			String lisatiedot = rs.getString("tilaus.lisatiedot");
			ArrayList<Tilausrivi> tilausrivit = new ArrayList<Tilausrivi>();
			double yhthinta = rs.getDouble("tilaus.lisatiedot");

			return new Tilaus(id, tila, maksutapa, toimitus, lisatiedot,
					tilausrivit, yhthinta);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
