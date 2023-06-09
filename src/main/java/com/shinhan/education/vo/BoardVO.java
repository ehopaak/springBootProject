package com.shinhan.education.vo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
//@Entity : JAP가 관리
@Entity
@Table(name = "t_boards")	//칼럼이름은 예약어불가, 카멜표기법으로 작성된 이름은 DB칼럼은 언더바로 변경
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bno;
	@NonNull	//생성시 반드시 값이 있어야한다.(lombok)
	@Column(nullable = false)	//DB에 칼럼이 NOT NULL
	private String title;
	@Column(length = 100)
	private String content;
	private String writer;
	@CreationTimestamp	//insert시 시각이 입력
	private Timestamp regdate;
	@UpdateTimestamp	//update시 수정시각입력
	private Timestamp updatedate;
	

}
