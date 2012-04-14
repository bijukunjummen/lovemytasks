package org.bk.lmt.dao;

import java.util.Date;
import java.util.List;

import org.bk.lmt.domain.GtdAction;
import org.bk.lmt.domain.GtdUser;

public interface GtdActionDao extends BaseDao<Long, GtdAction>{
    List<GtdAction> findGTDActionsByGtdUser(GtdUser gtdUser);
    
    List<GtdAction> findGTDActionsByStartDateLessThanEquals(Date startDate);

}
