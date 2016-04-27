package pizzicato.model.dao;

import java.sql.Connection; // kukkuu
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato.model.Mauste;

public class MausteDAO extends DataAccessObject {
	
	/**
	 * Kaivaa kannasta mausteet ja niiden tiedot. Luo jokaisesta olevasta mausteesta
	 * Mauste-olion ja tekee luodusta Mauste-oliosta Arraylistin
	 * 
	 * @return Palauttaa valmiin maustelista-Arraylistin
	 */
	
	public ArrayList<Mauste> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Mauste> maustelista = new ArrayList<Mauste>();
		Mauste mauste = null;
		
		try{
			conn = getConnection();
			String sqlSelect = "SELECT id, nimi, nimi_eng FROM mauste;";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);
			
			while(rs.next()) {
				mauste = readMauste(rs);
				maustelista.add(mauste);
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			close(rs, stmt, conn);
		}
		return maustelista;
	}
	
	public ArrayList<Mauste> findAllEng() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Mauste> maustelista = new ArrayList<Mauste>();
		Mauste mauste = null;
		
		try {
			conn = getConnection();
			String sqlSelect = "SELECT id, nimi_eng, hinta FROM mauste;";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);
			
			while(rs.next()) {
				mauste = readMauste(rs);
				maustelista.add(mauste);
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			close(rs, stmt, conn); 
		}
		return maustelista;
			
		}
	
	private Mauste readMauste(ResultSet rs) {
		try {
			// Haetaan yhden täytteen tiedot
			int id = rs.getInt("id");
			String nimi = rs.getString("nimi");
			String nimi_eng = rs.getString("nimi_eng");
			
			return new Mauste(id, nimi, nimi_eng);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Tuodaan lisattavaMauste-olio addMauste-metodille. Viedäänn kantaan tiedot
	 * mauste- ja pizzamauste-tauluihin.
	 * 
	 * @param lisattavamauste
	 *            Mukana tuotava Mauste-olio
	 * @throws SQLException
	 */
	public void addMauste(Mauste lisattavaMauste) throws SQLException {
		// Alustetaan Connection ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		
		try {
			connection = getConnection();
			stmtInsert = connection.
					prepareStatement("INSERT INTO mauste (nimi, nimi_eng) VALUES (?,?);");
			stmtInsert.setString(1, lisattavaMauste.getNimi());
			stmtInsert.setString(2, lisattavaMauste.getNimi_eng());
			stmtInsert.executeUpdate();
			stmtInsert.close();
		
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			close(stmtInsert, connection);
		}
	}
	
	/**
	 * Metodiin tuodaan poistettavaMauste-olio, jonka id:n perusteella kannasta
	 * poistetaan tietty mauste ja sen yhteydet pizzoihin.
	 * 
	 * @param poistettavaMauste
	 *            Mukana tuotava Mauste-olio
	 * @throws SQLException
	 */
	public void deleteMauste(Mauste poistettavaMauste) throws SQLException {

		// Alustetaan Connection ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			connection = getConnection();

			stmtInsert = connection
					.prepareStatement("DELETE FROM mauste WHERE id = (?);");
			stmtInsert.setInt(1, poistettavaMauste.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}

	/**
	 * Tuodaan mukana paivitettavaMauste-olio, jonka id:n perusteella kannassa
	 * tehdäänmuutokset mauste- ja pizzamauste-tauluihin.
	 * 
	 * @param paivitettavaMauste
	 *            Mukana tuotava Mauste-olio
	 * @throws SQLException
	 */

	public void updateMauste(Mauste paivitettavaMauste) throws SQLException {

		// Alustetaan Connection- ja PreparedStatement-oliot nulleiksi ennen
		// try-catchia
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			connection = getConnection();
			stmtInsert = connection.prepareStatement("UPDATE mauste SET nimi = (?) WHERE id = (?)");
			stmtInsert.setString(1, paivitettavaMauste.getNimi());
			stmtInsert.setInt(2, paivitettavaMauste.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

			stmtInsert = connection.prepareStatement("UPDATE mauste SET nimi_eng = (?) WHERE id = (?)");
			stmtInsert.setString(1, paivitettavaMauste.getNimi_eng());
			stmtInsert.setInt(2, paivitettavaMauste.getId());
			stmtInsert.executeUpdate();
			stmtInsert.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}

	
	}


