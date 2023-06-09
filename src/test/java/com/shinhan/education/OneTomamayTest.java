package com.shinhan.education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.repository.PDSFileRepository;
import com.shinhan.education.vo.PDSBoard;
import com.shinhan.education.vo.PDSFile;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class OneTomamayTest {
	
	@Autowired
	PDSFileRepository fileRepo;
	
	@Autowired
	PDSBoardRepository boardRepo;
	
	
	
	//@Test
	public void getFileCount() {
		boardRepo.findAll().forEach(board->{
			System.out.println(board.getPname() + "-->" + board.getFiles2().size());
		});
		//Eager인 경우
		boardRepo.findAll().forEach(board->{
			System.out.println(board.getPname());
		});
		
		
		//lazy인 경우
		List<Object[]> blist = boardRepo.getFilesInfo2();
		blist.forEach(arr->{
			System.out.println(Arrays.toString(arr));
		});
	}
	
	//@Test
	public void test3() {
		boardRepo.getFilesInfo(116).forEach(arr->{
			System.out.println(Arrays.toString(arr));
		});
		
		//fetch = FetchType.EAGER
		System.out.println("----------------------");
		PDSBoard board = boardRepo.findById(116L).orElse(null);
		if(board != null) {
			System.out.println(board.getPname());
			System.out.println(board.getPwriter());
			System.out.println(board.getFiles2());
		}
	}
	
	
	//@Test
	void deleteByBoard() {
		Long bno = 129L;
		boardRepo.deleteById(bno);
	}
	
	//@Test
	void deleteByFile() {
		Long[] arr = {293L, 297L, 298L, 299L};
		
		Arrays.stream(arr).forEach(bno->{
			fileRepo.deleteById(bno);
		});
	}
	
	//@Test
	void selectAllBoard() {
		boardRepo.findAll().forEach(board->{
			log.info(board.getPname() 
					+ "작성자:" + board.getPwriter() 
					+ "첨부file건수:" + board.getFiles2().size()+"건");
		});
	}
	
	//@Test
	void insertAll() {
		List<PDSFile> flist = new ArrayList<>();
		IntStream.range(1, 6).forEach(i->{
			PDSFile f = PDSFile.builder()
					.pdsfilename("firstzone" + i + ".java")
					.build();
			flist.add(f);
		});
		
		IntStream.range(20, 30).forEach(i->{
			PDSBoard board = PDSBoard.builder()
					.pname("SpringBoot수업---" + i)
					.pwriter("명수")
					.files2(flist)
					.build();
			boardRepo.save(board);
			
		});
		
	}
}
