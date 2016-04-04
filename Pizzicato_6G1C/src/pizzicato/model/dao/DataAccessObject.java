package pizzicato.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import pizzicato.model.Accounts;

public class DataAccessObject {

	protected static Connection getConnection() {
		Connection connection = null;

		String username = Accounts.DBUSERNAME;
		String password = Accounts.DBPASSWORD;
		String url = "jdbc:mysql://localhost:3306/projekti?useUnicode=true&characterEncoding=utf-8";

		try {
			Class.forName("org.mariadb.jdbc.Driver").newInstance();

			connection = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return connection;
	}

	protected static void close(Statement stmt, Connection connection) {
		close(null, stmt, connection);
	}

	protected static void close(ResultSet rs, Statement stmt, Connection conn) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
