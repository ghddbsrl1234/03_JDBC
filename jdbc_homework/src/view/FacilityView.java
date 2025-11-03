package view;

import java.util.List;
import java.util.Scanner;

import dto.Facility;
import dto.FacilityMenu;
import service.FacilityService;
import service.FacilityMenuService;

public class FacilityView {

    private Scanner sc = new Scanner(System.in);
    private FacilityService service = new FacilityService();
    private FacilityMenuService menuService = new FacilityMenuService();

    /** 시설 관리 메뉴 */
    public void facilityMenu() {

        int input = -1;

        do {
            try {
                System.out.println("\n===== [시설 관리] =====");
                System.out.println("1. 시설 등록");
                System.out.println("2. 전체 시설 조회");
                System.out.println("3. 시설 삭제");
                System.out.println("4. 시설 메뉴 조회"); // ✅ 추가
                System.out.println("0. 이전 메뉴로");
                System.out.print("메뉴 선택 : ");
                input = Integer.parseInt(sc.nextLine());

                switch (input) {
                case 1:
                    insertFacility();
                    break;
                case 2:
                    selectAll();
                    break;
                case 3:
                    deleteFacility();
                    break;
                case 4:
                    selectFacilityMenu(); // ✅ 추가
                    break;
                case 0:
                    System.out.println("[이전 메뉴로]");
                    break;
                default:
                    System.out.println("번호 다시 입력!");
                }

            } catch (Exception e) {
                System.out.println("오류 발생!");
                e.printStackTrace();
            }

        } while (input != 0);
    }

    /** 시설 등록 */
    private void insertFacility() throws Exception {

        System.out.println("\n--- 시설 등록 ---");

        System.out.print("시설명 입력 : ");
        String name = sc.nextLine();

        System.out.print("시설 유형(CAFETERIA/CAFE) : ");
        String type = sc.nextLine();

        Facility fac = new Facility();
        fac.setFacilityName(name);
        fac.setFacilityType(type);

        int result = service.insertFacility(fac);

        System.out.println(result > 0 ? "등록 성공!" : "등록 실패...");
    }

    /** 전체 시설 조회 */
    private void selectAll() throws Exception {

        System.out.println("\n--- 전체 시설 조회 ---");

        List<Facility> list = service.selectAll();

        if (list.isEmpty()) {
            System.out.println("조회 결과 없음");
            return;
        }

        System.out.println("ID | NAME | TYPE");
        System.out.println("-----------------------");
        for (Facility fac : list) {
            System.out.printf("%d | %s | %s%n",
                    fac.getFacilityId(), fac.getFacilityName(), fac.getFacilityType());
        }
    }

    /** 시설 삭제 */
    private void deleteFacility() throws Exception {

        System.out.println("\n--- 시설 삭제 ---");

        System.out.print("삭제할 시설 ID 입력 : ");
        int id = Integer.parseInt(sc.nextLine());

        int result = service.deleteFacility(id);

        System.out.println(result > 0 ? "삭제 성공!" : "삭제 실패...");
    }

    /** 시설 메뉴 조회 */
    private void selectFacilityMenu() {

        System.out.println("\n--- 시설 메뉴 조회 ---");

        System.out.print("시설 번호 입력 : ");
        int facilityId = Integer.parseInt(sc.nextLine()); // ✅ 개선

        try {
            List<FacilityMenu> list = menuService.selectMenuByFacility(facilityId);

            if (list.isEmpty()) {
                System.out.println("해당 시설의 메뉴가 없습니다.");
                return;
            }

            for (FacilityMenu m : list) {
                System.out.printf("[%d] %s (%d원)\n",
                        m.getMenuId(),
                        m.getName(),
                        m.getPrice()
                );
            }

        } catch (Exception e) {
            System.out.println("메뉴 조회 중 오류 발생!");
            e.printStackTrace();
        }

    }
}
