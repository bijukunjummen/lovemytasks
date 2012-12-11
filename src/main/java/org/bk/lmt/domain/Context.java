package org.bk.lmt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="contexts")
@NamedQueries({
	@NamedQuery(name="Context.findContextByTaskUser", query="SELECT o FROM Context o WHERE o.taskUser.username = :userName order by o.name"),
	@NamedQuery(name="Context.countContextByTaskUser", query="select count(o) from Context o where o.taskUser.username=:userName")
})
public class Context {

    @Size(min = 1, max = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name="taskuser_id")
    private TaskUser taskUser;
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Integer version;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonIgnore
    public TaskUser getTaskUser() {
        return this.taskUser;
    }
    
    public void setTaskUser(TaskUser taskUser) {
        this.taskUser = taskUser;
    }

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
