package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Student;
import static common.JDBCTEMPLATE.*;

public class StudentDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

		
		/** 학생 등록 DAO */
		public int insertStudent(Connection conn, Student std) throws Exception {

			int result = 0;

			try {

				String sql = "INSERT INTO KH_STUDENT VALUES(SEQ_STD_NO.NEXTVAL, ?, ?, ?, SYSDATE, NULL)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, std.getStdName());
				pstmt.setInt(2, std.getStdAge());
				pstmt.setString(3, std.getMajor());

				result = pstmt.executeUpdate();

			} finally {
				close(pstmt);
			}

			return result;
		}


		/** 전체 학생 조회 DAO */
		public List<Student> selectAll(Connection conn) throws Exception {

			List<Student> list = new ArrayList<>();

			try {

				String sql = 
				"SELECT STD_NO, STD_NAME, STD_AGE, MAJOR, " +
				"TO_CHAR(ENT_DATE, 'YYYY-MM-DD') ENT_DATE " +
				"FROM KH_STUDENT ORDER BY STD_NO";

				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				while(rs.next()) {
					list.add( new Student(
						rs.getInt("STD_NO"),
						rs.getString("STD_NAME"),
						rs.getInt("STD_AGE"),
						rs.getString("MAJOR"),
						rs.getString("ENT_DATE")
					));
				}

			} finally {
				close(rs);
				close(stmt);
			}

			return list;
		}


		/** 학생 정보 수정 DAO */
		public int updateStudent(Connection conn, Student std) throws Exception {

			int result = 0;

			try {

				String sql = "UPDATE KH_STUDENT SET STD_NAME=?, STD_AGE=?, MAJOR=? WHERE STD_NO=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, std.getStdName());
				pstmt.setInt(2, std.getStdAge());
				pstmt.setString(3, std.getMajor());
				pstmt.setInt(4, std.getStdNo());

				result = pstmt.executeUpdate();

			} finally {
				close(pstmt);
			}

			return result;
		}


		/** 학생 삭제 DAO */
		public int deleteStudent(Connection conn, int stdNo) throws Exception {

			int result = 0;

			try {
				String sql = "DELETE FROM KH_STUDENT WHERE STD_NO=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, stdNo);

				result = pstmt.executeUpdate();

			} finally {
				close(pstmt);
			}

			return result;
		}


		/** 전공별 조회 DAO */
		public List<Student> selectMajor(Connection conn, String major) throws Exception {

			List<Student> list = new ArrayList<>();

			try {
				String sql = "SELECT STD_NO, STD_NAME, STD_AGE, MAJOR, "
						   + "TO_CHAR(ENT_DATE,'YYYY-MM-DD') ENT_DATE "
						   + "FROM KH_STUDENT WHERE MAJOR=? ORDER BY STD_NO";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, major);

				rs = pstmt.executeQuery();

				while(rs.next()) {
					list.add(new Student(
						rs.getInt("STD_NO"),
						rs.getString("STD_NAME"),
						rs.getInt("STD_AGE"),
						rs.getString("MAJOR"),
						rs.getString("ENT_DATE")
					));
				}

			} finally {
				close(rs);
				close(pstmt);
			}

			return list;
		}
	
}
