package service;

import static common.JDBCTEMPLATE.close;
import static common.JDBCTEMPLATE.commit;
import static common.JDBCTEMPLATE.getConnection;
import static common.JDBCTEMPLATE.rollback;

import java.sql.Connection;
import java.util.List;

import dao.ClubDAO;
import dto.Club;

public class ClubService {

	private final ClubDAO dao = new ClubDAO();

	/** 동아리 등록 */
	public int insertClub(Club club) throws Exception {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = dao.insertClub(conn, club);
			if (result > 0)
				commit(conn);
			else
				rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	/** 전체 조회 */
	public List<Club> selectAll() throws Exception {
		Connection conn = getConnection();
		try {
			return dao.selectAll(conn);
		} finally {
			close(conn);
		}
	}

	/** 이름 검색 */
	public List<Club> selectName(String keyword) throws Exception {
		Connection conn = getConnection();
		try {
			return dao.selectName(conn, keyword);
		} finally {
			close(conn);
		}
	}

	/** 삭제 */
	public int deleteClub(int clubId) throws Exception {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = dao.deleteClub(conn, clubId);
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
