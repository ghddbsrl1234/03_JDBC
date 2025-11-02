package service;

import static common.JDBCTEMPLATE.*;

import java.sql.Connection;
import java.util.List;

import dao.StudentDAO;
import dto.Student;

public class StudentService {

    private final StudentDAO dao = new StudentDAO();

    /** 학생 등록 */
    public int insertStudent(Student std) throws Exception {
        Connection conn = getConnection();
        int result = 0;
        try {
            result = dao.insertStudent(conn, std);
            if (result > 0) commit(conn);
            else rollback(conn);
        } finally {
            close(conn);
        }
        return result;
    }

    /** 전체 조회 */
    public List<Student> selectAll() throws Exception {
        Connection conn = getConnection();
        try {
            return dao.selectAll(conn);
        } finally {
            close(conn);
        }
    }

    /** 수정 */
    public int updateStudent(Student std) throws Exception {
        Connection conn = getConnection();
        int result = 0;
        try {
            result = dao.updateStudent(conn, std);
            if (result > 0) commit(conn);
            else rollback(conn);
        } finally {
            close(conn);
        }
        return result;
    }

    /** 삭제 */
    public int deleteStudent(int stdNo) throws Exception {
        Connection conn = getConnection();
        int result = 0;
        try {
            result = dao.deleteStudent(conn, stdNo);
            if (result > 0) commit(conn);
            else rollback(conn);
        } finally {
            close(conn);
        }
        return result;
    }

    /** 전공별 조회 */
    public List<Student> selectMajor(String major) throws Exception {
        Connection conn = getConnection();
        try {
            return dao.selectMajor(conn, major);
        } finally {
            close(conn);
        }
    }
}