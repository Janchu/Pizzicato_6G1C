package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato.model.Pizza;
import pizzicato.model.dao.DataAccessObject;


public class PizzaDAO extends DataAccessObject {
	
	public ArrayList<Pizza> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Pizza> pizzalista = new ArrayList<Pizza>();
		Pizza pizza = null;
		
		try {
			conn = getConnection();
			String sqlSelect = "SELECT id, tyyppi, nimi, hinta, nakyvyys, pohja FROM tuote JOIN pizza USING (id);";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);
			
			while (rs.next()) {
				pizza = readPizza(rs);
				pizzalista.add(pizza);
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
			int id = rs.getInt("id");
			System.out.println(id);
			String tyyppi = rs.getString("tyyppi");
			System.out.println(tyyppi);
			String nimi = rs.getString("nimi");
			System.out.println(nimi);
			double hinta = rs.getDouble("hinta");
			System.out.println(hinta);
			int nakyvyys = rs.getInt("nakyvyys");
			System.out.println(nakyvyys);
			String pohja = rs.getString("pohja");
			System.out.println(pohja);		
			
			// Palautetaan pizza
			return new Pizza(id, tyyppi, nimi, hinta, nakyvyys, pohja);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	
}
