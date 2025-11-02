package service;

import static common.JDBCTEMPLATE.*;

import java.sql.Connection;
import java.util.List;

import dao.FacilityDAO;
import dto.Facility;

public class FacilityService {

	private FacilityDAO dao = new FacilityDAO();

	/** 시설 등록 */
	public int insertFacility(Facility fac) throws Exception {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = dao.insertFacility(conn, fac);
			if (result > 0)
				commit(conn);
			else
				rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	/** 전체 시설 조회 */
	public List<Facility> selectAll() throws Exception {
		Connection conn = getConnection();
		try {
			return dao.selectAll(conn);
		} finally {
			close(conn);
		}
	}

	/** 시설 삭제 */
	public int deleteFacility(int facilityId) throws Exception {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = dao.deleteFacility(conn, facilityId);
			if (result > 0)
				commit(conn);
			else
				rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}
}
