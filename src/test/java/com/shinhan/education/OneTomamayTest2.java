package com.shinhan.education;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.repository.PDSFileRepository;
import com.shinhan.education.vo.PDSBoard;
import com.shinhan.education.vo.PDSFile;


@SpringBootTest
@Commit
public class OneTomamayTest2 {
	
	@Autowired
	PDSFileRepository fileRepo;
	
	@Autowired
	PDSBoardRepository boardRepo;
	
	
	//@Test
	void test1() {
		PDSFile file = PDSFile.builder()
				.pdsfilename("첨부파일1")
				.build();
		fileRepo.save(file);
	}
	
	// 자식만 insert
	//@Test
	void test2() {
		List<PDSFile> files = (List<PDSFile>) fileRepo.findAll();
		PDSFile file = files.get(0);
	}
	
	// 부모만 insert
	//@Test
	void test3() {
		PDSBoard board = PDSBoard.builder()
				.pname("게시글")
				.pwriter("작성자")
				.build();
		boardRepo.save(board);
	}
	
	//@Test
	void test4() {
		boardRepo.findAll().forEach(b -> System.out.println(b));
	}
	
	//@Test
	void test5() {
		PDSBoard board = ((List<PDSBoard>) boardRepo.findAll()).get(0);
		PDSFile file = fileRepo.findById(1L).orElse(null);
		board.getFiles2().add(file);
		boardRepo.save(board);
	}
	
	//@Test
	void test6() {
		List<PDSFile> files = new ArrayList<PDSFile>();
		PDSFile file1 = PDSFile.builder().pdsfilename("얼굴사진1").build();
		PDSFile file2 = PDSFile.builder().pdsfilename("얼굴사진2").build();
		PDSFile file3 = PDSFile.builder().pdsfilename("얼굴사진3").build();
		files.add(file1);files.add(file2);files.add(file3);
		
		PDSBoard board = PDSBoard.builder()
				.pname("월요일이다")
				.pwriter("성진")
				.files2(files)
				.build();
		boardRepo.save(board);
	}
	
	//LAZY인 경우 ... 자식의 접근하기위해 사용
	//@Transactional
	//@Test
	void test7() {
		boardRepo.findAll().forEach(b->{
			System.out.println(b);
			System.out.println(b.getFiles2());
			
		});
	}
	
	//PDSBoardRepository(부모)룰 이용하여 자식을 수정하기
	//@Modifying을 사용한 경우 반드시 @Transactional 
	//실행은 성공하지만 Test환경은 Rollback처리됨
	//@Transactional
	//@Test
	void fileUpdate() {
		boardRepo.upedateFile(117L, "풍경사진");
	}
	
	//Board를 이용해서 File이름수정하기
	//@Test
	void boardFileUpdate() {
		boardRepo.findById(3L).ifPresent(board->{
			List<PDSFile> files2 = board.getFiles2();
			files2.forEach(f->{
				f.setPdsfilename("수정~~~");
				fileRepo.save(f);
			});
		});
	}
	
	@Test
	void boardFileInsert() {
		boardRepo.findById(3L).ifPresent(board->{
			List<PDSFile> files2 = board.getFiles2();
			PDSFile file1 = PDSFile.builder().pdsfilename("추가1!!!").build();
			PDSFile file2 = PDSFile.builder().pdsfilename("추가2!!!").build();
			files2.add(file1);
			files2.add(file2);
			boardRepo.save(board);
		});
	}
	
}
