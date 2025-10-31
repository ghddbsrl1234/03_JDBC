package common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTEMPLATE {
	
	private static Connection conn = null; 
	
	
	
	/**
	 * 호출 시 Connection 객체를 생성하여 호출한곳으로 반환하는 메서드 + AutoCommit 끄기
	 * 
	 * @return
	 */
	public static Connection getConnection () {
		
		try {
			
			if (conn != null && !conn.isClosed())
				return conn;
			
			Properties prop =  new Properties();
			
			prop.loadFromXML(new FileInputStream("driver.xml"));
			
			
			Class.forName(prop.getProperty("driver"));
			// Class.forName("oracle.jdbc.driver.OracleDriver")
			
			conn = DriverManager.getConnection(prop.getProperty("url"),
					prop.getProperty("userName"),
					prop.getProperty("password"));
			
			conn.setAutoCommit(false);
			
		} catch (Exception e) {
			 System.out.println("커넥션 생성 중 예외 발생(JDBCTEMPLATE의 getConnection())");
			e.printStackTrace();
			
		}
		return conn;
		
		
	}
	
	/**
	 * 전달 받은 커넥션에서 수행한 SQL을 commit 하는 메서드
	 * 
	 */
	public static void commit() {
		
		try {
			if (conn != null && !conn.isClosed())
				conn.commit();
			
			
		} catch (Exception e) {
			System.out.println("커밋 중 예외 발생");
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 전달 받은 커넥션에서 수행한 SQL을 rollback 하는 메서드
	 * 
	 */
	public static void rollback() {
		
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
			
			
		} catch (Exception e) {
			System.out.println("롤백 중 예외발생");
			e.printStackTrace();
		}
		
		
		
	}
	
	// Connection, Statement(PrepatedStatement), ResultSet
	
	/**
	 * 전달받은 커넥션을 close(자원반환) 하는 메서드
	 * 
	 */
	public static void close(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
			
			
		} catch (Exception e) {
			System.out.println("커넥션 close()중 예외 발생");
			e.printStackTrace();
		}
		
		
	}
	
	/** 전달 받은 Statement or PreparedStatement 둘 다 close() 할 수 있는 메서드
	 * + 다형성의 업캐스팅 저 ㄱ용
	 * -> PreparedStatemnt는 Statement의 자식
	 */
	public static void close(Statement stmt) {
		
		try {
			if(stmt != null && !stmt.isClosed())
			stmt.close();
			
		} catch (Exception e) {
			System.out.println("Statement close()중 예외 발생");
			e.printStackTrace();

		}
		
		
		
	}
	
	public static void clise(ResultSet rs) {
		
		try {
			if(rs != null && !rs.isClosed())
			rs.close();
			
		} catch (Exception e) {
			System.out.println("ResultSet close()중 예외 발생");
			e.printStackTrace();
			
		}
		
		
	}
	
	
	
	
	

}
