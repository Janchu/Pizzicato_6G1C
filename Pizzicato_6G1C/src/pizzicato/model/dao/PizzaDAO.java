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
			String sqlInsert = "INSERT INTO tuote(id, tyyppi, nimi, hinta) VALUES (?, ?, ?, ?);";   // <----- Insert lause
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setInt(1, pizza.getId());
			stmtInsert.setString(2, pizza.getTyyppi());
			stmtInsert.setString(3, pizza.getNimi());
			stmtInsert.setDouble(4, pizza.getHinta());
			stmtInsert.executeUpdate();
			
			
			
			sqlInsert = "INSERT INTO pizza(id, nakyvyys, pohja) VALUES (?, ?, ?);";   // <----- Insert lause
			stmtInsert.setInt(1, pizza.getId());
			stmtInsert.setInt(2, pizza.getNakyvyys());
			stmtInsert.setString(3, pizza.getPohja());
			stmtInsert.executeUpdate();
			
			for (int i = 0; i < pizza.getTaytelista().size(); i++) {
				sqlInsert = "INSERT INTO pizzatayte(pizza_id, tayte_id) VALUES (?, ?);";
				stmtInsert.setInt(1, pizza.getId());
				stmtInsert.setInt(2, pizza.getTaytelista().get(i).getId());
				stmtInsert.executeUpdate();
			}
			

			
		} catch (SQLException e) {
			System.out.println("PizzaDAO -> addPizza ei onnistunut.");
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
			return new Pizza(id, tyyppi, nimi, hinta, nakyvyys, pohja, taytelista);
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
	
	public void deletePizza(Pizza pizza) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		
		try {
		connection = getConnection();
		String sqlInsert = "DELETE FROM tuote WHERE id = ?";   // <----- DELETE lause
		stmtInsert = connection.prepareStatement(sqlInsert);
		stmtInsert.setInt(1, pizza.getId());
		stmtInsert.executeUpdate();
		
		
		
		sqlInsert = "DELETE FROM pizza WHERE id = ?;";   // <----- DELETE lause
		stmtInsert.setInt(1, pizza.getId());
		stmtInsert.executeUpdate();
		
		for (int i = 0; i < pizza.getTaytelista().size(); i++) {
			sqlInsert = "INSERT INTO pizzatayte WHERE pizza_id = ?;"; // DELETE lause
			stmtInsert.setInt(1, pizza.getId());
			stmtInsert.executeUpdate();
		}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
