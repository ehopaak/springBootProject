package com.shinhan.education.vo2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_usercellphone3")
public class UserCellPhoneVO3 {
	
	//식별자로 참조하기(참조하는 키를 PK로 사용)
	@Id
	String userid;
	
	@MapsId
	@OneToOne
	@JoinColumn(name="user_id")
	UserVO3 user;
	
	String phoneNumber;
	String model;
	
	
}
