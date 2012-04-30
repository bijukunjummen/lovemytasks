package org.bk.lmt.domain;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name="tasks")
@Access(AccessType.FIELD)
@NamedQueries({
		@NamedQuery(name="Task.findByUser", query="SELECT task FROM Task AS task WHERE task.gtdUser = :gtdUser"),
		@NamedQuery(name="Task.countByUser", query="SELECT COUNT(task) FROM Task AS task WHERE task.gtdUser = :gtdUser")
})

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Integer version;
    @Size(min = 1, max = 100)
    private String title;

    @ManyToOne
    @JoinColumn(name="gtdproject_id")
    private GtdProject project;

    @ManyToOne
    @JoinColumn(name="context_id")
    private Context context;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    @Column(name = "startdate")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    @Column(name = "completeddate")
    private Date completedDate;

    @Column(name = "isdone")
    private Boolean isDone;

    @Enumerated(EnumType.STRING)
    private GtdStatus status;
    
    @ManyToOne
    @JoinColumn(name="gtduser_id")
    private GtdUser gtdUser;

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public GtdProject getProject() {
        return this.project;
    }
    
    public void setProject(GtdProject project) {
        this.project = project;
    }
    
    public Context getContext() {
        return this.context;
    }
    
    public void setContext(Context context) {
        this.context = context;
    }
    
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getCompletedDate() {
        return this.completedDate;
    }
    
    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }
    
    public Boolean getIsDone() {
        return this.isDone;
    }
    
    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }
    
    public GtdStatus getStatus() {
        return this.status;
    }
    
    public void setStatus(GtdStatus status) {
        this.status = status;
    }
    

    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @JsonIgnore
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    @JsonIgnore
    public GtdUser getGtdUser() {
        return this.gtdUser;
    }
    
    public void setGtdUser(GtdUser gtdUser) {
        this.gtdUser = gtdUser;
    }
    
    @Override
    public String toString() {
    	return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}
