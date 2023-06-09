package com.shinhan.education.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder	//@AllArgsConstructor + @NoArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class CarVO {
	private String model;
	private int price;
	
}
