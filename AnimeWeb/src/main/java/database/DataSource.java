package database;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/projectweb";
	private static final String USER = "root";
	private static final String PASS = "Linh@27092002";
	private static HikariConfig config = new HikariConfig();
	private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static HikariDataSource ds;

	static {

		config.setJdbcUrl(DB_URL);
		config.setUsername(USER);
		config.setPassword(PASS);
		config.setDriverClassName(CLASS_NAME);
		config.setMinimumIdle(100);
		config.setMaximumPoolSize(5000);
		config.setConnectionTimeout(50000);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.setIdleTimeout(Long.MAX_VALUE);
		ds = new HikariDataSource(config);

	}

	private DataSource() {
	}

	public static void closeConnection() {
		ds.close();
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}