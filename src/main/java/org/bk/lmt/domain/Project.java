package org.bk.lmt.domain;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="projects")
@NamedQueries({
	@NamedQuery(name="Project.findByUser", query="select o from Project o where o.taskUser.username=:userName"),
	@NamedQuery(name="Project.countByUser", query="select count(o) from Project o where o.taskUser.username=:userName")	
}
		
)
public class Project{

    @Size(min = 1, max = 100)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss a")
    @Column(name = "startdate")
    private Calendar startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss a")
    @Column(name = "completeddate")
    private Calendar completedDate;

    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name="taskuser_id")
    private TaskUser taskUser;
    
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Calendar getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
    
    public Calendar getCompletedDate() {
        return this.completedDate;
    }
    
    public void setCompletedDate(Calendar completedDate) {
        this.completedDate = completedDate;
    }
    
    public Boolean getIsDone() {
        return this.isDone;
    }
    
    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }
    
    @JsonIgnore
    public TaskUser getTaskUser() {
        return this.taskUser;
    }
    
    public void setTaskUser(TaskUser taskUser) {
        this.taskUser = taskUser;
    }

    public String toString() {
    	return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}
