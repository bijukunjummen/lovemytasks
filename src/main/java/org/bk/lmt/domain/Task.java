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
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tasks")
@Access(AccessType.FIELD)
@NamedQueries({
		@NamedQuery(name="Task.findByUser", query="SELECT task FROM Task AS task WHERE task.taskUser = :taskUser"),
		@NamedQuery(name="Task.findIncompleteByUser", query="SELECT task FROM Task AS task WHERE task.taskUser = :taskUser AND task.isDone=FALSE"),
		@NamedQuery(name="Task.findByUserAndNamePattern", query="SELECT task FROM Task AS task WHERE task.taskUser = :taskUser AND task.title like :namePattern"),
		@NamedQuery(name="Task.countByUser", query="SELECT COUNT(task) FROM Task AS task WHERE task.taskUser = :taskUser"),
		@NamedQuery(name="Task.countIncompleteByUser", query="SELECT COUNT(task) FROM Task AS task WHERE task.taskUser = :taskUser AND task.isDone=FALSE")
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
    @JoinColumn(name="project_id")
    private Project project;

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
    private TaskStatus status;
    
    @ManyToOne
    @JoinColumn(name="taskuser_id")
    private TaskUser taskUser;

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Project getProject() {
        return this.project;
    }
    
    public void setProject(Project project) {
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
    
    public TaskStatus getStatus() {
        return this.status;
    }
    
    public void setStatus(TaskStatus status) {
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
