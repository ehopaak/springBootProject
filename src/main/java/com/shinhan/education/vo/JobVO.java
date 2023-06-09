package com.shinhan.education.vo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_jobs")
public class JobVO {
	
	@Id
	private String jobId;
	@Column(unique = true, nullable = false, length = 35)
	private String jobTitle;
	private int minSalary;
	private int maxSalary;
	@CreationTimestamp	//insert시 시각이 입력
	private Timestamp regdate;
	@UpdateTimestamp	//update시 수정시각입력
	private Timestamp updatedate;
}
