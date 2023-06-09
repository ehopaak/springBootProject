package com.shinhan.education.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.shinhan.education.vo.WebBoard;
import com.shinhan.education.vo.WebReply;

public interface WebReplyRepository extends PagingAndSortingRepository<WebReply, Long>{
	public List<WebReply> findByBoard(WebBoard board);
}
