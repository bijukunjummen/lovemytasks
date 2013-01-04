package org.bk.lmt.service;

import java.util.List;

import org.bk.lmt.domain.Context;
import org.springframework.data.domain.Page;

public interface ContextService {
	Context persistForUser(Context gtdContext, String userName);
	Context findById(Long id);
	Page<Context> findContextsByGtdUserName(String userName, int firstResult, int maxResults);
	List<Context> findContextsByGtdUserName(String userName);
	Long countContextsByUserName(String userName);
	Context updateForUser(Context gtdContext, String userName);
	void remove(Context gtdContext);
}
