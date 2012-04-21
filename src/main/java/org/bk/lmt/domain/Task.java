package org.bk.lmt.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tasks")
public class Task {

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
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date completedDate;

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
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Title: ").append(getTitle()).append(", ");
        sb.append("Project: ").append(getProject()).append(", ");
        sb.append("Context: ").append(getContext()).append(", ");
        sb.append("StartDate: ").append(getStartDate()).append(", ");
        sb.append("CompletedDate: ").append(getCompletedDate()).append(", ");
        sb.append("IsDone: ").append(getIsDone()).append(", ");
        sb.append("Status: ").append(getStatus()).append(", ");
        sb.append("GtdUser: ").append(getGtdUser());
        return sb.toString();
    }
    
}
