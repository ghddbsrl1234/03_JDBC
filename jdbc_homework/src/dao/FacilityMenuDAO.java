package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.FacilityMenu;

import static common.JDBCTEMPLATE.*;

public class FacilityMenuDAO {

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    /** 시설 ID 기반 메뉴 조회 */
    public List<FacilityMenu> selectMenuByFacility(Connection conn, int facId) throws Exception {

        List<FacilityMenu> list = new ArrayList<>();

        try {
            String sql = 
                "SELECT MENU_ID, FACILITY_ID, NAME, PRICE "
              + "FROM KH_STUDENT_FACILITY_MENU "
              + "WHERE FACILITY_ID = ? "
              + "ORDER BY MENU_ID";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, facId);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                FacilityMenu menu = new FacilityMenu(
                    rs.getInt("MENU_ID"),
                    rs.getInt("FACILITY_ID"), 
                    rs.getString("NAME"),
                    rs.getInt("PRICE")
                );
                list.add(menu);
            }

        } finally {
            close(rs);
            close(pstmt);
        }

        return list;
    }
}
