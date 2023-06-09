package com.shinhan.education.vo2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor  
@Entity
@Table(name = "tbl_dept1")
public class DeptVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int department_id;
	private String department_name;
	private int manager_id;
	private int location_id;
	
	
	
}
