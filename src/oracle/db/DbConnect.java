package oracle.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
	static final String ORACLEDRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//����Ŭ Ŭ���忡 19c �߰��� ����� �߰�
	static final String ORACLE_CLOUD = "jdbc:oracle:thin:@dbyj_high?TNS_ADMIN=D:/bitjava0719/OracleCloud";
	
	//���÷� ����Ŭ ������ ��� ��� �߰�
	static final String ORACLE_LOCAL = "jdbc:oracle:thin:@localhost:1521:xe";

	//������
	public DbConnect() {
		try {
			Class.forName(ORACLEDRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("����Ŭ ����̹� ����:"+e.getMessage());
		}
	}
	
	public Connection getLocalOracle() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(ORACLE_LOCAL, "angel", "a1234");
		} catch (SQLException e) {
			System.out.println("���� ����Ŭ ���� ���� :"+e.getMessage());
		}
		return conn;
	}
	
	public Connection getCloudOracle() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(ORACLE_CLOUD, "admin", "Dotory0812!!");
		} catch (SQLException e) {
			System.out.println("Ŭ���� ����Ŭ ���� ���� :"+e.getMessage());
		}
		return conn;
	}
	
	
	//close �޼���� �� 4��, �����ε� �޼���
	public void dbClose(ResultSet rs, Statement stmt, Connection conn) {
		try {//try catch ���� �ۼ�
			if(rs!= null) rs.close();
			if(stmt!= null) stmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException e) {
			
		}
		
	}
	
	public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try { 
			if(rs!= null) rs.close();
			if(pstmt!= null) pstmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException e) {
			
		}
		
	}
	
	public void dbClose(Statement stmt, Connection conn) {
		try {
			if(stmt!= null) stmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException e) {
			
		}
		
	}
	
	public void dbClose(PreparedStatement pstmt, Connection conn) {
		try { 
			if(pstmt!= null) pstmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException e) {
			
		}
		
	}


}
