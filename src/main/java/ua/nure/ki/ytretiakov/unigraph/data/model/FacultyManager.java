package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;

@Entity
@Table(name = "FACULTY_MANAGERS")
public class FacultyManager extends IDEntity {

    @OneToOne(targetEntity = Cathedra.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id", unique = true, nullable = false, foreignKey = @ForeignKey(name = "faculty_id_fk"))
    private Faculty faculty;

    @OneToOne(targetEntity = Employee.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_manager_id", unique = true, nullable = false, foreignKey = @ForeignKey(name = "faculty_manager_id_fk"))
    private Employee employee;

    public FacultyManager() {

    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
