package lifelogcollector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://220.68.27.110:1433;";
	private String user = "sa";
	private String pw = "dsem1010!";
	private String db = "lifelog_db";
	private Connection conn;
	private Statement stmt;
	public DBConnector(){
		
		this.conn = null;
		this.stmt = null;	
	}
	
	public boolean connect(){
		
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
	
	public boolean close(){
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean insertLifelog(String uid, String timestamp, String uom, String type ,String value ){
		String sql = "insert into DATA_TB values('" +	uid +"','"+ timestamp +"','"+	uom +"','"+type +"'," +  value +  ")";			
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
