package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FACULTIES")
public class Faculty extends IDEntity {

    @Column
    private String title;

    @OneToOne(targetEntity = Employee.class, cascade = CascadeType.ALL)
    private Employee manager;

    @OneToMany(targetEntity = Cathedra.class, cascade = CascadeType.ALL)
    private List<Cathedra> cathedras;

    public Faculty(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Cathedra> getCathedras() {
        return cathedras;
    }

    public void setCathedras(List<Cathedra> cathedras) {
        this.cathedras = cathedras;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                '}';
    }
}
