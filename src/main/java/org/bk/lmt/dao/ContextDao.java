package org.bk.lmt.dao;

import java.util.List;

import org.bk.lmt.domain.Context;

public interface ContextDao extends BaseDao<Long, Context>{
    List<Context> findContextsByGtdUser(String userName, int firstResult, int maxResults);
    List<Context> findContextsByGtdUser(String userName);
    Long countContextsByUserName(String userName);
}
