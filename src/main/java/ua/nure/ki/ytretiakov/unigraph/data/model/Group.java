package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class Group extends IDEntity {

    @Column
    private Cathedra cathedra;

    @Column
    private String title;

    @Column
    private Employee curator;

    @Column
    private Employee headman;

    private List<Employee> students;

    public Group() {

    }

    public Group(final Cathedra cathedra, final String title, final Employee curator, final Employee headman) {
        this.cathedra = cathedra;
        this.title = title;
        this.curator = curator;
        this.headman = headman;
//        this.students = students;
    }

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        this.cathedra = cathedra;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Employee getCurator() {
        return curator;
    }

    public void setCurator(Employee curator) {
        this.curator = curator;
    }

    public Employee getHeadman() {
        return headman;
    }

    public void setHeadman(Employee headman) {
        this.headman = headman;
    }

    public List<Employee> getStudents() {
        return students;
    }

    public void setStudents(List<Employee> students) {
        this.students = students;
    }
}
