package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEES")
public class Employee extends IDEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @ManyToOne(targetEntity = Group.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "group_id_fk"))
    private Group group;

    public Employee() {

    }

    public Employee(final String firstName, final String lastName, final Date dateOfBirth, final String email, final String password, final EmployeeType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public EmployeeType getType() {
        return type;
    }

    public Group getGroup() {
        return group;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
