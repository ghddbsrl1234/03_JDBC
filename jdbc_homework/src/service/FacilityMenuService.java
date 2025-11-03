package service;

import java.sql.Connection;
import java.util.List;

import dao.FacilityMenuDAO;
import dto.FacilityMenu;

import static common.JDBCTEMPLATE.*;

public class FacilityMenuService {

    private FacilityMenuDAO dao = new FacilityMenuDAO();

    /** 시설 메뉴 조회 서비스 */
    public List<FacilityMenu> selectMenuByFacility(int facilityId) throws Exception {

        Connection conn = getConnection();

        List<FacilityMenu> list = dao.selectMenuByFacility(conn, facilityId);

        close(conn);

        return list;
    }

}
