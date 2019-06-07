package ua.nure.ki.ytretiakov.unigraph.data.model;

import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.GenderType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private EmployeeType type = EmployeeType.None;

    @Column
    @Enumerated(EnumType.STRING)
    private GenderType genderType = GenderType.Male;

    @Column
    private String avatarFile;

    @ManyToOne(targetEntity = Group.class, cascade = CascadeType.ALL)
    private Group group;

    @ManyToMany(targetEntity = Employee.class, cascade = CascadeType.ALL)
    private List<Employee> friends;

    public Employee(final String login, final String email, final String password, final String firstName, final String lastName, final Date dateOfBirth, final EmployeeType type) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
    }

    public Employee(final String login, final String email, final String password, final String firstName, final String lastName, final Date dateOfBirth, final EmployeeType type, final GenderType genderType) {
        this(login, email, password, firstName, lastName, dateOfBirth, type);
        this.genderType = genderType;
    }

    public Employee() {

    }
    
    public String getFullName() {
        return getFirstName() +
                " " + getLastName();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public String getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(String avatarFile) {
        this.avatarFile = avatarFile;
    }

    public List<Employee> getFriends() {
        return friends;
    }

    public void setFriends(List<Employee> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getLogin().equals(employee.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin());
    }
}
