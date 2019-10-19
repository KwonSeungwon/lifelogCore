package lifelogcollector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnetorDb {
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://220.68.27.110:1433;";
	private String user = "dsem";
	private String pw = "dsem";
	private String db = "TestDb1";
	private Connection conn;
	private Statement stmt;

	public ConnetorDb() {

		this.conn = null;
		this.stmt = null;
	}

	public boolean connect() {

		try {

			Class.forName(driver);
			this.conn = DriverManager.getConnection(url + "databaseName=" + db, user, pw);
			this.stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	public boolean close() {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean insertLifelog(String uid, String timestamp, String uom, String devicetype, String hr, String steps,String lat, String lon,
			String alt, String x, String y, String z) {
		String sql = "insert into TEST_TB1 values('" + uid + "','" + timestamp + "','" + uom + "','" + devicetype + "',"
				+ hr + "," + steps + "," + lat +"," + lon + "," + alt + "," + x + "," + y + "," + z + ")";
		System.out.println(sql);
		try {
			stmt.executeLargeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
