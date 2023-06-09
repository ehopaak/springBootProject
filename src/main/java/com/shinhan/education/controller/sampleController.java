package com.shinhan.education.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shinhan.education.repository.BoardRepository;
import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.vo.CarVO;
import lombok.extern.java.Log;

@RestController	//@Controller + @ResponseBody
				//response.getWriter(.append("jsp/servlet")
@Log
public class sampleController {
	
	Logger logger = LoggerFactory.getLogger(sampleController.class);
	
	@Autowired
	BoardRepository brepo;
	
	@Autowired
	PDSBoardRepository psdBoardRepo;
	
	@GetMapping("/monday")
	String fileUpdate() {
		int result = psdBoardRepo.upedateFile(4L, "풍경사진!!");
		return "OK:" + result;
	}
	
	@GetMapping("/sunday")
	void dynamicSQLTest() {
		
	}
	
	@GetMapping("/friday")
	public Map<String, Object> sample1() {
		long rowCount = brepo.count();
		log.info(rowCount + "건");
		
		boolean result = brepo.existsById(100l);
		log.info(result?"존재한다":"존재하지않는다");
		
		Map<String, Object> map = new HashMap<>();
		map.put("aa", rowCount + "건");
		map.put("bb", result?"존재한다":"존재하지않는다");
		map.put("data", brepo.findById(200L).orElse(null));
		
		return map;
		
	}
	
	@GetMapping("/cartest2")
	public List<CarVO> test4() {	//Jackson이 java객체를 json으로 만들어서 return
		List<CarVO> carlist = new ArrayList<>();
		
		IntStream.rangeClosed(1, 10).forEach(index->{
			CarVO car1 = CarVO.builder()
					.model("Benz" + index)
					.price(70000000)
					.build();
			
			carlist.add(car1);
		});
				
		return carlist;
	}
	
	@GetMapping("/cartest")
	public CarVO test3() {	//Jackson이 javj객체를 json으로 만들어서 return
		CarVO car1 = CarVO.builder()
				.model("ionic6")
				.price(70000000)
				.build();
		return car1;
	}
	
	
	//@RequestMapping(value = "/sample1", method=RequestMethod.GET)
	@GetMapping("/sample1")
	public String test1() {
		return "SpringBoot 시발 왜 안되냐 뒤질래?";
	}
	@GetMapping("/sample2")
	public String test2() {
		return "SpringBoot 쳐맞기전에 잘되라";
	}

}
