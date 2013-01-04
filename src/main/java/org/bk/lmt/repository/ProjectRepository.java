package org.bk.lmt.repository;

import java.util.List;

import org.bk.lmt.domain.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{
	
	@Query(name="Project.findByUser")
	List<Project> findByUser(String userName, Pageable pageable);

	@Query(name="Project.countByUser")
	long countByUser(String userName);

	
}
