package edu.kh.jdbc.delete;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class DeleteXML {

	private final String folderPath;

	// 생성자에서 XML 파일이 있는 폴더 지정
	public DeleteXML(String folderPath) {
		this.folderPath = folderPath;
	}

	// 1️⃣ 개별 XML 파일 삭제

	public boolean deleteXML(String fileName) {
		File file = new File(folderPath, fileName);

		if (!file.exists()) {
			System.out.println(fileName + " 파일이 존재하지 않습니다.");
			return false;
		}

		try {
			secureDelete(file);
			System.out.println(fileName + " 파일이 완전히 삭제되었습니다.");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 2️⃣ 폴더 내 모든 XML 파일 삭제

	public void deleteAllXML() {
		File folder = new File(folderPath);
		File[] xmlFiles = folder.listFiles((dir, name) -> name.endsWith(".xml"));

		if (xmlFiles == null || xmlFiles.length == 0) {
			System.out.println("삭제할 XML 파일이 없습니다.");
			return;
		}

		for (File file : xmlFiles) {
			try {
				secureDelete(file);
				System.out.println(file.getName() + " 삭제 완료");
			} catch (IOException e) {
				System.out.println(file.getName() + " 삭제 실패");
				e.printStackTrace();
			}
		}
	}

	// 3️⃣ 안전하게 덮어쓰기 후 삭제

	private void secureDelete(File file) throws IOException {
		long length = file.length();
		if (length > 0) {
			byte[] emptyData = new byte[(int) length];
			Arrays.fill(emptyData, (byte) 0); // 0으로 덮어쓰기
			try (FileOutputStream fos = new FileOutputStream(file)) {
				fos.write(emptyData);
				fos.flush();
			}
		}
		file.delete(); // 파일 삭제
	}

	// 테스트

	public static void main(String[] args) {
		DeleteXML manager = new DeleteXML("C:/test"); // 파일 주소

		// 개별 파일 삭제
		manager.deleteXML("user1.xml"); // XML 파일 이름

		// 전체 XML 삭제
		manager.deleteAllXML();
	}
}
