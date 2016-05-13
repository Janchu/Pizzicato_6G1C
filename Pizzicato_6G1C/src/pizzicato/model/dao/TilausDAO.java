package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato.model.Kayttaja;
import pizzicato.model.Tilaus;
import pizzicato.model.Tilausrivi;
import pizzicato.model.Tuote;

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
			String sqlSelect = "SELECT tilaus.id, tilaus.tila, tilaus.maksutapa, tilaus.toimitus, tilaus.lisatiedot, tilaus.yhthinta, tilausrivi.lkm, tilaus.tilausaika FROM tilaus;";
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
			
				if (tilaaja.getTyyppi().equalsIgnoreCase("asiakas")) {
				stmtInsert = connection
						.prepareStatement("INSERT INTO kayttaja (id, etunimi, sukunimi, salasana, tyyppi, osoite, puh, email, postinro) VALUES (last_insert_id(), ?, ?, ?, ?, ?, ?, ?, ?);");
				stmtInsert.setString(1, tilaaja.getEtunimi());
				stmtInsert.setString(3, tilaaja.getSalasana());
				stmtInsert.setString(4, tilaaja.getTyyppi());
				stmtInsert.setString(5, tilaaja.getOsoite());
				stmtInsert.setString(6, tilaaja.getPuh());
				stmtInsert.setString(7, tilaaja.getEmail());
				stmtInsert.setString(8, tilaaja.getPostinro());
				stmtInsert.executeUpdate();
				stmtInsert.close();
				
				}


				stmtInsert = connection
						.prepareStatement("INSERT INTO tilaus (id, tila, maksutapa, toimitus, lisatiedot, yhthinta, etunimi, sukunimi, osoite, puh, email, postinro, kayttaja_id, tilausaika) VALUES (last_insert_id(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				stmtInsert.setString(1, tilaus.getTila());
				stmtInsert.setString(2, tilaus.getMaksutapa());
				stmtInsert.setString(3, tilaus.getToimitus());
				stmtInsert.setString(4, tilaus.getLisatiedot());
				stmtInsert.setDouble(5, tilaus.getYhthinta());
				stmtInsert.setString(6, tilaaja.getEtunimi());
				stmtInsert.setString(7, tilaaja.getSukunimi());
				stmtInsert.setString(8, tilaaja.getOsoite());
				stmtInsert.setString(9, tilaaja.getPuh());
				stmtInsert.setString(10, tilaaja.getEmail());
				stmtInsert.setString(11, tilaaja.getPostinro());
				if (tilaaja.getId() == 0) {
					stmtInsert.setString(12, null);
				}else {
				stmtInsert.setInt(12, tilaaja.getId());
				}
				System.out.println(tilaaja.getId());
				stmtInsert.setString(13, tilaus.getTilausaika());
				System.out.println(tilaus.getTilausaika());
				stmtInsert.executeUpdate();
				stmtInsert.close();
				

				for (int i = 0; i < tilaus.getTilausrivit().size(); i++) {
					stmtInsert = connection
							.prepareStatement("INSERT INTO tilausrivi (rivinumero, tilaus_id, lkm, tuote_id) VALUES (?, last_insert_id(), ?, ?);");
					stmtInsert.setInt(1, tilaus.getTilausrivit().get(i)
							.getRivinumero());
					stmtInsert.setInt(2, tilaus.getTilausrivit().get(i)
							.getLkm());
					stmtInsert.setInt(3, tilaus.getTilausrivit().get(i)
							.getTilattuTuote().getId());
					stmtInsert.executeUpdate();
					stmtInsert.close();
				}
				for(int j = 0; j < tilaus.getTilausrivit().size(); j++) {
				stmtInsert = connection.prepareStatement("INSERT INTO tilausmauste (rivinumero, tilaus_id, mauste_id) VALUES(?,last_insert_id(), ?);");
				stmtInsert.setInt(1, tilaus.getTilausrivit().get(j).getRivinumero());
				stmtInsert.setInt(2, tilaus.getTilausrivit().get(j).getMaustelista().get(j).getId());
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
	
	public ArrayList<Tilaus> findAll2() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Tilaus> tilauslista = new ArrayList<Tilaus>();
		Tilaus tilaus = null;
		int tilausIdEdellinen = 0;

		try {
			conn = getConnection();
			String sqlSelect = "SELECT tilausrivi.tilaus_id, tilausrivi.tuote_id, tilausrivi.lkm, tilaus.tilausaika, tilaus.tila FROM tilausrivi JOIN tilaus ON tilausrivi.tilaus_id = tilaus.id ORDER BY tilausaika DESC;";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				int tilausId = rs.getInt("tilausrivi.tilaus_id");
				if (tilausId != tilausIdEdellinen) {
					tilaus = readTilaus2(rs);
					tilauslista.add(tilaus);
				}
				tilausIdEdellinen = rs.getInt("tilausrivi.tilaus_id");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		return tilauslista;
	}
	
	private Tilaus readTilaus2(ResultSet rs) {
		try {
			int tilausId = rs.getInt("tilausrivi.tilaus_id");
			int tuoteId = rs.getInt("tilausrivi.tuote_id");
			String tila = rs.getString("tilaus.tila");
			int lkm = rs.getInt("tilausrivi.lkm");
			String tilausaika = rs.getString("tilaus.tilausaika");

			Tilaus tilaus = new Tilaus();
			tilaus.setId(tilausId);
			tilaus.setTila(tila);
			tilaus.setTilausaika(tilausaika);
			ArrayList<Tilausrivi> tilausrivit = new ArrayList<Tilausrivi>();
			Tilausrivi tilausrivi = new Tilausrivi();
			tilausrivi.setLkm(lkm);
			Tuote tuote = new Tuote();
			tuote.setId(tuoteId);
			tilausrivi.setTilattuTuote(tuote);
			tilausrivit.add(tilausrivi);
			
			

			return tilaus;

		} catch (SQLException e) {
			throw new RuntimeException(e);
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
			double yhthinta = rs.getDouble("tilaus.yhthinta");
			int yhtlkm = rs.getInt("tilausrivi.lkm");
			String tilausaika = rs.getString("tilaus.tilausaika");

			

			return new Tilaus(id, tila, maksutapa, toimitus, lisatiedot,
					tilausrivit, yhthinta, yhtlkm, tilausaika);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
