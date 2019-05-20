package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

//@Table
//@Entity
public class Cathedra extends IDEntity {

//    @Column
    private Faculty faculty;

//    @Column
    private String title;


    private List<Group> groups;

//    @Column
    private Employee manager;

    public Cathedra() {

    }

    public Cathedra(final Faculty faculty, final String title, final Employee manager) {
        this.faculty = faculty;
        this.title = title;
//        this.groups = groups;
        this.manager = manager;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
