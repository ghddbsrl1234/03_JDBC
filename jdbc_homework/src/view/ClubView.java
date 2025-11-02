package view;

import java.util.List;
import java.util.Scanner;

import dto.Club;
import service.ClubService;

public class ClubView {

	private  Scanner sc = new Scanner(System.in);
	private  ClubService service = new ClubService();

	/** 동아리 메뉴 */
	public void clubMenu() {

		int input = -1;

		do {
			try {
				System.out.println("\n===== [동아리 관리] =====");
				System.out.println("1. 동아리 등록");
				System.out.println("2. 전체 동아리 조회");
				System.out.println("3. 동아리명 검색");
				System.out.println("4. 동아리 삭제");
				System.out.println("0. 이전 메뉴로");
				System.out.print("메뉴 선택 : ");
				input = Integer.parseInt(sc.nextLine());

				switch (input) {
				case 1:
					insertClub();
					break;
				case 2:
					selectAll();
					break;
				case 3:
					searchName();
					break;
				case 4:
					deleteClub();
					break;
				case 0:
					System.out.println("[이전 메뉴로]");
					break;
				default:
					System.out.println("번호 다시 입력!");
				}

			} catch (Exception e) {
				System.out.println("※ 오류 발생!");
				e.printStackTrace();
			}

		} while (input != 0);
	}

	/** 등록 */
	private void insertClub() throws Exception {

		System.out.println("\n--- 동아리 등록 ---");

		System.out.print("동아리 이름 : ");
		String name = sc.nextLine();

		System.out.print("분야 (예: 운동, 음악, 학술) : ");
		String cat = sc.nextLine();

		Club club = new Club();
		club.setClubName(name);
		club.setClubCategory(cat);

		int result = service.insertClub(club);

		System.out.println(result > 0 ? "등록 성공!" : "등록 실패...");
	}

	/** 전체 조회 */
	private void selectAll() throws Exception {

		System.out.println("\n--- 전체 동아리 조회 ---");

		List<Club> list = service.selectAll();

		if (list.isEmpty()) {
			System.out.println("데이터 없음");
			return;
		}

		System.out.println("ID | NAME | CATEGORY | STATUS");
		System.out.println("-------------------------------------");
		for (Club c : list) {
			System.out.printf("%d | %s | %s | %s%n", c.getClubId(), c.getClubName(), c.getClubCategory(),
					c.getStatus());
		}
	}

	/** 이름 검색 */
	private void searchName() throws Exception {
		System.out.println("\n--- 동아리 검색 ---");
		System.out.print("검색어 입력 : ");
		String keyword = sc.nextLine();

		List<Club> list = service.selectName(keyword);

		if (list.isEmpty()) {
			System.out.println("검색 결과 없음");
			return;
		}

		for (Club c : list) {
			System.out.println(c);
		}
	}

	/** 삭제 */
	private void deleteClub() throws Exception {
		System.out.println("\n--- 동아리 삭제 ---");
		System.out.print("동아리 ID 입력 : ");
		int id = Integer.parseInt(sc.nextLine());

		int result = service.deleteClub(id);

		System.out.println(result > 0 ? "삭제 성공!" : "삭제 실패...");
	}
}
