package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String toEmployeeLogin;
    
    @Column
    private String fromEmployeeLogin;
    
    @Column
    private String content;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date commentDate;
    
    public Comment() {
    
    }
    
    public Comment(String toEmployeeLogin, String fromEmployeeLogin, String content, Date commentDate) {
        this.toEmployeeLogin = toEmployeeLogin;
        this.fromEmployeeLogin = fromEmployeeLogin;
        this.content = content;
        this.commentDate = commentDate;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Date getCommentDate() {
        return commentDate;
    }
    
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
    
    public String getToEmployeeLogin() {
        return toEmployeeLogin;
    }
    
    public void setToEmployeeLogin(String toEmployeeLogin) {
        this.toEmployeeLogin = toEmployeeLogin;
    }
    
    public String getFromEmployeeLogin() {
        return fromEmployeeLogin;
    }
    
    public void setFromEmployeeLogin(String fromEmployeeLogin) {
        this.fromEmployeeLogin = fromEmployeeLogin;
    }
}
