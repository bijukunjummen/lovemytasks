package org.bk.lmt.repository;

import java.util.List;

import org.bk.lmt.domain.Context;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ContextRepository extends PagingAndSortingRepository<Context, Long> {
	
	@Query(value="SELECT o FROM Context o WHERE o.taskUser.username = :userName order by o.name", countQuery="select count(o) from Context o where o.taskUser.username=:userName")
	Page<Context> findByUser(@Param("userName") String userName, Pageable pageable);
	
	@Query(value="SELECT o FROM Context o WHERE o.taskUser.username = :userName order by o.name",  countQuery="select count(o) from Context o where o.taskUser.username=:userName")
	List<Context> findByUser(@Param("userName") String userName);
	
	@Query(value="select count(o) from Context o where o.taskUser.username=:userName")
	long countByUser(@Param("userName") String userName);

}
