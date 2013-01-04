package org.bk.lmt.repository;

import org.bk.lmt.domain.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long>{
	//
}
