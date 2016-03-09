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

	public void addPizza(Pizza pizza) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			connection = getConnection();
			stmtInsert = connection
					.prepareStatement("INSERT INTO tuote(tyyppi, nimi, hinta) VALUES (?, ?, ?);"); // <-----
																									// Insert
																									// lause

			stmtInsert.setString(1, pizza.getTyyppi());
			stmtInsert.setString(2, pizza.getNimi());
			stmtInsert.setDouble(3, pizza.getHinta());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection
					.prepareStatement("INSERT INTO pizza(id, nakyvyys, pohja) VALUES (last_insert_id(), ?, ?);"); // <-----
																													// Insert
																													// lause

			stmtInsert.setInt(1, pizza.getNakyvyys());
			stmtInsert.setString(2, pizza.getPohja());
			stmtInsert.executeUpdate();
			stmtInsert.close();
			System.out.println(pizza.getTaytelista());

			for (int i = 0; i < pizza.getTaytelista().size(); i++) {
				stmtInsert = connection
						.prepareStatement("INSERT INTO pizzatayte(pizza_id, tayte_id) VALUES (last_insert_id(), ?);");

				stmtInsert.setInt(1, pizza.getTaytelista().get(i).getId());
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

	public ArrayList<Pizza> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Pizza> pizzalista = new ArrayList<Pizza>();
		Pizza pizza = null;
		Tayte tayte = new Tayte();
		int pizzaIdEdellinen = 0;

		try {
			conn = getConnection();
			String sqlSelect = "SELECT pizza.id, tuote.tyyppi, pizza.nakyvyys, pizza.pohja, tuote.nimi, tuote.hinta, tayte.id, tayte.nimi AS tayte, tayte.hinta FROM tuote JOIN pizza ON tuote.id = pizza.id JOIN pizzatayte ON pizza.id = pizzatayte.pizza_id JOIN tayte ON pizzatayte.tayte_id = tayte.id;";
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
			// Haetaan yhden t�ytteen tiedot
			int id = rs.getInt("tayte.id");
			String nimi = rs.getString("tayte");
			double hinta = rs.getDouble("tayte.hinta");

			return new Tayte(id, nimi, hinta);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletePizza(Pizza poistettavapizza) throws SQLException {

		Connection connection = null; // kukkuu
		PreparedStatement stmtInsert = null;
		poistettavapizza.getId();
		System.out.println(poistettavapizza.getId());
		try {
			connection = getConnection();

			stmtInsert = connection
					.prepareStatement("DELETE FROM pizzatayte WHERE pizza_id = (?);");
			stmtInsert.setInt(1, poistettavapizza.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection
					.prepareStatement("DELETE FROM pizza WHERE id = (?);");
			stmtInsert.setInt(1, poistettavapizza.getId());// <----- DELETE
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection
					.prepareStatement("DELETE FROM tuote WHERE id = (?);");
			stmtInsert.setInt(1, poistettavapizza.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}

	}
	
	public void hidePizza (Pizza pizza) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		try {
			connection = getConnection();
			if(pizza.getNakyvyys() == 1) {
			stmtInsert = connection
					.prepareStatement("UPDATE pizza SET nakyvyys = 0 WHERE id = (?);");
			stmtInsert.setInt(1, pizza.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();
			}else if (pizza.getNakyvyys() == 0) {
				stmtInsert = connection
						.prepareStatement("UPDATE pizza SET nakyvyys = 1 WHERE id = (?);");
				stmtInsert.setInt(1, pizza.getId());
				stmtInsert.executeUpdate();
				stmtInsert.close();
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			close(stmtInsert, connection);
		}
		
	}
}
