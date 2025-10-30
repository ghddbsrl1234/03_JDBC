package edu.kh.jdbc.delete;

import java.io.File;

public class DeleteXML {

	public static void main(String[] args) {
		
		File folder = new File("C:\\workspace\\03_JDBC\\jdbc2"); 
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".xml"));

        if (files != null) {
            for (File f : files) {
                if (f.delete()) {
                    System.out.println(f.getName() + " 삭제 완료");
                } else {
                    System.out.println(f.getName() + " 삭제 실패");
                }
            }
        }
    }

}
