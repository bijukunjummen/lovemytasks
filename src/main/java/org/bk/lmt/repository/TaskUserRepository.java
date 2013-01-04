package org.bk.lmt.repository;

import org.bk.lmt.domain.TaskUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TaskUserRepository extends PagingAndSortingRepository<TaskUser, Long>{
	
	@Query(value="select o from TaskUser o where o.username=:userName")
	public TaskUser findUserByUserName(@Param("userName") String userName); 
	
}
