package view;

import java.util.Scanner;

public class MainVIew {
	
	private Scanner sc = new Scanner(System.in);

	    private StudentView studentView = new StudentView();
	    private FacilityView facilityView = new FacilityView();
	    private ClubView clubView = new ClubView();


	    /** 메인 메뉴 */
	    public void start() {

	        int input = -1;

	        do {
	            try {
	                System.out.println("\n===== [KH 대학교 학사 시스템] =====");
	                System.out.println("1. 학생 관리");
	                System.out.println("2. 시설 관리");
	                System.out.println("3. 동아리 관리");
	                System.out.println("0. 종료");
	                System.out.print("메뉴 선택 : ");

	                input = Integer.parseInt(sc.nextLine());

	                switch(input) {
	                case 1: studentView.studentMenu(); break;
	                case 2: facilityView.facilityMenu(); break;
	                case 3: clubView.clubMenu(); break;
	                case 0: System.out.println("프로그램 종료"); break;
	                default: System.out.println("올바른 번호를 입력하세요.");
	                }

	            } catch (Exception e) {
	                System.out.println("※ 오류 발생!");
	                e.printStackTrace();
	            }

	        } while(input != 0);

	    }
	}

