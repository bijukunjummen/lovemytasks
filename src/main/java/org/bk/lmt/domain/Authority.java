package org.bk.lmt.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
public class Authority {
	
    @Enumerated(EnumType.STRING)
    private AuthorityNames name;



	public AuthorityNames getName() {
    	return name;
    }

	public void setName(AuthorityNames name) {
    	this.name = name;
    }

}
