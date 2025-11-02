package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTEMPLATE.*;
import dto.Facility;

public class FacilityDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/** 시설 등록 */
	public int insertFacility(Connection conn, Facility fac) throws Exception {

		int result = 0;

		try {
			String sql = "INSERT INTO KH_STUDENT_FACILITY VALUES(SEQ_FACILITY_NO.NEXTVAL, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fac.getFacilityName());
			pstmt.setString(2, fac.getFacilityType());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/** 전체 시설 조회 */
	public List<Facility> selectAll(Connection conn) throws Exception {

		List<Facility> list = new ArrayList<>();

		try {
			String sql = "SELECT FACILITY_ID, FACILITY_NAME, FACILITY_TYPE "
					+ "FROM KH_STUDENT_FACILITY ORDER BY FACILITY_ID";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Facility(rs.getInt("FACILITY_ID"), rs.getString("FACILITY_NAME"),
						rs.getString("FACILITY_TYPE")));
			}

		} finally {
			close(rs);
			close(stmt);
		}

		return list;
	}

	/** 시설 삭제 */
	public int deleteFacility(Connection conn, int facilityId) throws Exception {

		int result = 0;

		try {
			String sql = "DELETE FROM KH_STUDENT_FACILITY WHERE FACILITY_ID=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, facilityId);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

}
