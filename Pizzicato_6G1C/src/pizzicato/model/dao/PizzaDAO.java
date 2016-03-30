package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato.model.Pizza;
import pizzicato.model.Tayte;
import pizzicato.model.dao.DataAccessObject;

public class PizzaDAO extends DataAccessObject {

	/**
	 * Kaivaa kannasta pizzat ja niiden tiedot. Luo jokaisesta kannassa olevasta
	 * pizzasta Pizza-olion ja tekee luoduista Pizza-olioista ArrayListin.
	 * 
	 * @return Palauttaa valmiin pizzalista-ArrayListin
	 */
	public ArrayList<Pizza> findAll() {

		// Alustetaan Connection-, PreparedStatement-, ResultSet-, nulleiksi
		// ennen try-catchia
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Pizza> pizzalista = new ArrayList<Pizza>();
		Pizza pizza = null;
		Tayte tayte = new Tayte();
		int pizzaIdEdellinen = 0;

		try {
			conn = getConnection();
			String sqlSelect = "SELECT pizza.id, tuote.tyyppi, pizza.nakyvyys, pizza.pohja, tuote.nimi, tuote.hinta, tayte.id, tayte.nimi AS tayte, tayte.nimi_eng AS tayte_eng, tayte.hinta, tayte.kilohinta FROM tuote JOIN pizza ON tuote.id = pizza.id JOIN pizzatayte ON pizza.id = pizzatayte.pizza_id JOIN tayte ON pizzatayte.tayte_id = tayte.id ORDER BY tuote.hinta DESC;";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				// Jos pizzaId eri kuin edellinen, luodaan uusi pizza

				int pizzaId = rs.getInt("pizza.id");
				if (pizzaId != pizzaIdEdellinen) {
					pizza = readPizza(rs);
					pizzalista.add(pizza);
				}
				tayte = readTayte(rs);
				pizza.addTayte(tayte);

				pizzaIdEdellinen = rs.getInt("pizza.id");

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		return pizzalista;
	}

	/**
	 * Tuodaan lisattavaPizza-olio addPizza-metodille. Vied‰‰n kantaan tiedot
	 * tuote-, pizza- ja pizzatayte-tauluihin.
	 * 
	 * @param lisattavaPizza
	 *            Mukana tuotava Pizza-olio
	 * @throws SQLException
	 */
	public void addPizza(Pizza lisattavaPizza) throws SQLException {

		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			connection = getConnection();
			stmtInsert = connection
					.prepareStatement("INSERT INTO tuote(tyyppi, nimi, hinta) VALUES (?, ?, ?);");

			stmtInsert.setString(1, lisattavaPizza.getTyyppi());
			stmtInsert.setString(2, lisattavaPizza.getNimi());
			stmtInsert.setDouble(3, lisattavaPizza.getHinta());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection
					.prepareStatement("INSERT INTO pizza(id, nakyvyys, pohja) VALUES (last_insert_id(), ?, ?);");

			stmtInsert.setInt(1, lisattavaPizza.getNakyvyys());
			stmtInsert.setString(2, lisattavaPizza.getPohja());
			stmtInsert.executeUpdate();
			stmtInsert.close();
			System.out.println(lisattavaPizza.getTaytelista());

			for (int i = 0; i < lisattavaPizza.getTaytelista().size(); i++) {
				stmtInsert = connection
						.prepareStatement("INSERT INTO pizzatayte(pizza_id, tayte_id) VALUES (last_insert_id(), ?);");

				stmtInsert.setInt(1, lisattavaPizza.getTaytelista().get(i)
						.getId());
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

	/**
	 * Metodiin tuodaan poistettavaPizza-olio, jonka id:n perusteella kannasta
	 * poistetaan tietty pizza ja sen yhteydet t‰ytteisiin.
	 * 
	 * @param poistettavaPizza
	 *            Mukana tuotava Pizza-olio
	 * @throws SQLException
	 */
	public void deletePizza(Pizza poistettavaPizza) throws SQLException {

		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		poistettavaPizza.getId();
		System.out.println(poistettavaPizza.getId());
		try {
			connection = getConnection();

			stmtInsert = connection
					.prepareStatement("DELETE FROM pizzatayte WHERE pizza_id = (?);");
			stmtInsert.setInt(1, poistettavaPizza.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection
					.prepareStatement("DELETE FROM pizza WHERE id = (?);");
			stmtInsert.setInt(1, poistettavaPizza.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection
					.prepareStatement("DELETE FROM tuote WHERE id = (?);");
			stmtInsert.setInt(1, poistettavaPizza.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}

	}

	/**
	 * Tuodaan mukana piilotettavaPizza-olio, jonka id:n perusteella kannasta
	 * muutetaan kyseisen pizzan nakyvyys-ominaisuutta.
	 * 
	 * @param piilotettavaPizza
	 *            Mukana tuotava Pizza-olio
	 * @throws SQLException
	 */
	public void hidePizza(Pizza piilotettavaPizza) throws SQLException {

		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		try {
			connection = getConnection();
			if (piilotettavaPizza.getNakyvyys() == 1) {
				stmtInsert = connection
						.prepareStatement("UPDATE pizza SET nakyvyys = 0 WHERE id = (?);");
				stmtInsert.setInt(1, piilotettavaPizza.getId());
				stmtInsert.executeUpdate();
				stmtInsert.close();
			} else if (piilotettavaPizza.getNakyvyys() == 0) {
				stmtInsert = connection
						.prepareStatement("UPDATE pizza SET nakyvyys = 1 WHERE id = (?);");
				stmtInsert.setInt(1, piilotettavaPizza.getId());
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
	 * Tuodaan mukana paivitettavaPizza-olio, jonka id:n perusteella kannassa
	 * tehd‰‰n muutokset pizza-, tuote- ja pizzatayte-tauluihin.
	 * 
	 * @param paivitettavaPizza
	 *            Mukana tuotava Pizza-olio
	 * @throws SQLException
	 */
	public void updatePizza(Pizza paivitettavaPizza) throws SQLException {

		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			connection = getConnection();
			stmtInsert = connection
					.prepareStatement("UPDATE tuote SET nimi = (?) WHERE id = (?)");
			stmtInsert.setString(1, paivitettavaPizza.getNimi());
			stmtInsert.setInt(2, paivitettavaPizza.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection
					.prepareStatement("UPDATE tuote SET hinta = (?) WHERE id = (?)");
			stmtInsert.setDouble(1, paivitettavaPizza.getHinta());
			stmtInsert.setInt(2, paivitettavaPizza.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}

	private Pizza readPizza(ResultSet rs) {
		try {
			// Haetaan yhden pizzan tiedot
			int id = rs.getInt("pizza.id");
			String tyyppi = rs.getString("tuote.tyyppi");
			String nimi = rs.getString("tuote.nimi");
			double hinta = rs.getDouble("tuote.hinta");
			int nakyvyys = rs.getInt("pizza.nakyvyys");
			String pohja = rs.getString("pizza.pohja");
			ArrayList<Tayte> taytelista = new ArrayList<Tayte>();

			// Palautetaan pizza
			return new Pizza(id, tyyppi, nimi, hinta, nakyvyys, pohja,
					taytelista);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private Tayte readTayte(ResultSet rs) {
		try {
			// Haetaan yhden t‰ytteen tiedot
			int id = rs.getInt("tayte.id");
			String nimi = rs.getString("tayte");
			String nimi_eng = rs.getString("tayte_eng");
			double hinta = rs.getDouble("tayte.hinta");
			double kilohinta = rs.getDouble("tayte.kilohinta");
			
			return new Tayte(id, nimi, nimi_eng, hinta, kilohinta);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
