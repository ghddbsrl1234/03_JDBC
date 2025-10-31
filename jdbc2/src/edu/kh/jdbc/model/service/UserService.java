package edu.kh.jdbc.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.model.dao.UserDAO;
import edu.kh.jdbc.model.dto.User;

// (Model 중 하나) Service : 비즈니스 로직을 처리하는 계층.
// 데이터를 가공하고 트랜잭션(commit, rollback) 관리 수행
public class UserService {

	// 필드
	private UserDAO dao = new UserDAO();

	/**
	 * 1. User 등록 서비스
	 * 
	 * @param user : 입력받은 id, pw , name이 세팅된 객체
	 * @return insert 된 결과 행의 갯수
	 */
	public int insertUser(User user) throws Exception {

		// 1. 커넥션 생성
		Connection conn = JDBCTemplate.getConnection();

		// 2. 데이터 가공(할 것 없으면 생략)

		// 3. DAO 메서드 호출 후 결과 반환받기
		int result = dao.insertUser(conn, user);

		// 4. DML(INSERT) 수행 결과에 따라 트랜잭션 제어 처리
		if (result > 0) { // INSERT 성공
			JDBCTemplate.commit(conn);

		} else { // INSERT 실패
			JDBCTemplate.rollback(conn);
		}

		// 5. Connection 반환하기
		JDBCTemplate.close(conn);

		// 6. 결과 반환
		return result;
	}

	/**
	 * 2. User 전체 조회 서비스
	 * 
	 * @return 조회된 User들이 담긴 List
	 */
	public List<User> selectAll() throws Exception {

		// 1. 커넥션 생성
		Connection conn = JDBCTemplate.getConnection();

		// 2. DAO 메서드 호출(SELECT) 후 결과반환(List<User>) 받기
		List<User> userList = dao.selectAll(conn);

		// 3. Connection 반환
		JDBCTemplate.close(conn);

		// 4. 결과 반환
		return userList;
	}

	/**
	 * 3. User 중 이름에 검색어가 포함된 회원 조회 서비스
	 * 
	 * @param keyword : 입력받은 키워드
	 * @return seacrchList : 조회된 회원 리스트
	 */
	public List<User> selectName(String keyword) throws Exception {

		Connection conn = JDBCTemplate.getConnection();

		List<User> searchList = dao.selectName(conn, keyword);

		JDBCTemplate.close(conn);

		return searchList;
	}

	/**
	 * 4. USER_NO 를 입력받아 일치하는 USER 조회 서비스
	 * @param userNo
	 * @return
	 */
	public User selectUser(int input) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		User user = dao.selectUser(conn, input);

		JDBCTemplate.close(conn);

		return user;
	}

	/** 5. USER_NO를 입력받아 일치하는 User 삭제 서비스
	 * @param userNo
	 * @return
	 */
	public int deleteUser(int input) throws Exception {

		Connection conn = null;
		int result = 0;

		try {
			conn = JDBCTemplate.getConnection();
			result = dao.deleteUser(conn, input);

			if (result > 0) {

				JDBCTemplate.commit(conn);

			} else {

				JDBCTemplate.rollback(conn);
			}


		} finally {
			
			JDBCTemplate.close(conn);
		}

		return result;

	}

	/** 6. ID, PW가 일치하는 회원이 있는지 조회(SELECT)
	 * 6-1. 쌤이하신 풀이는 USER_NO로 조회한다.
	 * @param user
	 * @return
	 */
	public int updateName(User user) throws Exception{
		
		
		 Connection conn = JDBCTemplate.getConnection();
		int result = 0;

		try {
			
			result = dao.updateUser(conn, user);

			if (result > 0) {

				JDBCTemplate.commit(conn);
				System.out.println("업데이트 성공!");

			} else {

				JDBCTemplate.rollback(conn);
				System.out.println("사용자 존재하지 않습니다.");
			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("\n***업데이트 중 오류 발생!***\n");

		} finally {

			JDBCTemplate.close(conn);

		}

		return result; 
		
		// 6-1. USER_NO 로 조회할경우
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	/** 7. 아이디 중복 확인 서비스
	 * @param userId
	 * @return userId
	 */
	public int idCheck(String userId) throws Exception{
		
		Connection conn = JDBCTemplate.getConnection();
		
		int count = dao.idCheck(conn, userId);
		
		JDBCTemplate.close(conn);
		
		
		return count;
	}

	/** 8. userList에 있는 모든 User 객체를 Insert 서비스
	 * @param userList
	 * @return
	 */
	public int multiInsertUser(List<User> userList) throws Exception{
		
		// 다중 INSERT 방법
		// 1) SQL 을 이용한 다중 INSERT
		// 2) Java 반복문을 이용한 다중 INSERT (이거 사용!)
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		int count = 0; // 삽입 성공한 행의 갯수 count
		
		for(User user : userList) {
			int result = dao.insertUser(conn, user);
			count += result; // 삽입 성공한 행의 갯수를 count 누적
		}
		
		// count --; 롤백되는지 확인용
		
		// 전체 삽입 성공 시 commit/ 아니면 rollback(일부 삽입, 전체실패)
		
		if(count == userList.size()) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return count;
	}

}
