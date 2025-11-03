package view;

import java.util.List;
import java.util.Scanner;

import dto.Club;
import dto.Facility;
import dto.FacilityMenu;
import dto.Student;
import service.StudentService;

public class StudentView {

    private  Scanner sc = new Scanner(System.in);
    private  StudentService service = new StudentService();

    public void studentMenu() {

        int input = -1;

        do {
            try {
                System.out.println("\n===== [학생 관리] =====");
                System.out.println("1. 학생 등록");
                System.out.println("2. 전체 학생 조회");
                System.out.println("3. 학생 정보 수정");
                System.out.println("4. 학생 삭제");
                System.out.println("5. 전공별 학생 조회");
                System.out.println("0. 뒤로가기");
                System.out.print("메뉴 선택 : ");
                input = Integer.parseInt(sc.nextLine());

                switch (input) {
                    case 1: insertStudent(); break;
                    case 2: selectAll(); break;
                    case 3: updateStudent(); break;
                    case 4: deleteStudent(); break;
                    case 5: selectMajor(); break;
                    case 0: System.out.println("[이전 메뉴로 이동]"); break;
                    default: System.out.println("※ 올바른 번호를 입력하세요.");
                }

            } catch (NumberFormatException nf) {
                System.out.println("※ 숫자만 입력하세요.");
            } catch (Exception e) {
                System.out.println("※ 처리 중 오류 발생");
                e.printStackTrace();
            }

        } while (input != 0);
    }

    /** 1) 학생 등록 */
    private void insertStudent() throws Exception {
        System.out.println("\n--- 학생 등록 ---");
        System.out.print("이름 : ");
        String name = sc.nextLine();

        System.out.print("나이 : ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("전공 : ");
        String major = sc.nextLine();

        Student std = new Student();
        std.setStdName(name);
        std.setStdAge(age);
        std.setMajor(major);

        int result = service.insertStudent(std);
        System.out.println(result > 0 ? "등록 성공 " : "등록 실패");
    }

    /** 2) 전체 조회 */
    private void selectAll() throws Exception {
        System.out.println("\n--- 전체 학생 조회 ---");
        List<Student> list = service.selectAll();

        if (list.isEmpty()) {
            System.out.println("조회 결과가 없습니다.");
            return;
        }

        System.out.println("STD_NO | NAME | AGE | MAJOR | ENT_DATE");
        System.out.println("---------------------------------------");
        for (Student s : list) {
            System.out.printf("%6d | %s | %d | %s | %s%n",
                s.getStdNo(), s.getStdName(), s.getStdAge(),
                s.getMajor(), s.getEntDate());
        }
    }

    /** 3) 수정 */
    private void updateStudent() throws Exception {
        System.out.println("\n--- 학생 정보 수정 ---");
        System.out.print("수정할 학생 번호(STD_NO) : ");
        int stdNo = Integer.parseInt(sc.nextLine());

        System.out.print("새 이름 : ");
        String name = sc.nextLine();

        System.out.print("새 나이 : ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("새 전공 : ");
        String major = sc.nextLine();

        Student std = new Student();
        std.setStdNo(stdNo);
        std.setStdName(name);
        std.setStdAge(age);
        std.setMajor(major);

        int result = service.updateStudent(std);
        System.out.println(result > 0 ? "수정 성공" : "수정 실패");
    }

    /** 4) 삭제 */
    private void deleteStudent() throws Exception {
        System.out.println("\n--- 학생 삭제 ---");
        System.out.print("삭제할 학생 번호(STD_NO) : ");
        int stdNo = Integer.parseInt(sc.nextLine());

        int result = service.deleteStudent(stdNo);
        System.out.println(result > 0 ? "삭제 성공" : "삭제 실패");
    }

    /** 5) 전공별 조회 */
    private void selectMajor() throws Exception {
        System.out.println("\n--- 전공별 학생 조회 ---");
        System.out.print("전공 입력 : ");
        String major = sc.nextLine();

        List<Student> list = service.selectMajor(major);

        if (list.isEmpty()) {
            System.out.println("해당 전공 학생이 없습니다.");
            return;
        }

        System.out.println("STD_NO | NAME | AGE | MAJOR | ENT_DATE");
        System.out.println("---------------------------------------");
        for (Student s : list) {
            System.out.printf("%6d | %s | %d | %s | %s%n",
                s.getStdNo(), s.getStdName(), s.getStdAge(),
                s.getMajor(), s.getEntDate());
        }
    }
}
