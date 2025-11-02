package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTEMPLATE.*;
import dto.Club;

public class ClubDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/** 동아리 등록 */
	public int insertClub(Connection conn, Club club) throws Exception {

		int result = 0;

		try {
			String sql = "INSERT INTO KH_STUDENT_CLUB VALUES(SEQ_CLUB_NO.NEXTVAL, ?, ?, DEFAULT)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, club.getClubName());
			pstmt.setString(2, club.getClubCategory());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/** 전체 동아리 조회 */
	public List<Club> selectAll(Connection conn) throws Exception {

		List<Club> list = new ArrayList<>();

		try {
			String sql = "SELECT CLUB_ID, CLUB_NAME, CLUB_CATEGORY, STATUS " + "FROM KH_STUDENT_CLUB ORDER BY CLUB_ID";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Club(rs.getInt("CLUB_ID"), rs.getString("CLUB_NAME"), rs.getString("CLUB_CATEGORY"),
						rs.getString("STATUS")));
			}

		} finally {
			close(rs);
			close(stmt);
		}

		return list;
	}

	/** 동아리명으로 검색 */
	public List<Club> selectName(Connection conn, String keyword) throws Exception {

		List<Club> list = new ArrayList<>();

		try {
			String sql = "SELECT CLUB_ID, CLUB_NAME, CLUB_CATEGORY, STATUS " + "FROM KH_STUDENT_CLUB "
					+ "WHERE CLUB_NAME LIKE ? ORDER BY CLUB_ID";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Club(rs.getInt("CLUB_ID"), rs.getString("CLUB_NAME"), rs.getString("CLUB_CATEGORY"),
						rs.getString("STATUS")));
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return list;
	}

	/** 동아리 삭제 */
	public int deleteClub(Connection conn, int clubId) throws Exception {

		int result = 0;

		try {
			String sql = "DELETE FROM KH_STUDENT_CLUB WHERE CLUB_ID=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, clubId);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

}
