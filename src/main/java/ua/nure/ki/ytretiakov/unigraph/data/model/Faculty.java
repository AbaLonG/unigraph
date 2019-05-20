package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

//@Table
//@Entity
public class Faculty extends IDEntity {

//    @Column
    private String title;

//    @Column
    private Employee dean;

    private List<Cathedra> cathedras;

    public Faculty() {

    }

    public Faculty(String title, Employee dean) {
        this.title = title;
        this.dean = dean;
//        this.cathedras = cathedras;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Employee getDean() {
        return dean;
    }

    public void setDean(Employee dean) {
        this.dean = dean;
    }

    public List<Cathedra> getCathedras() {
        return cathedras;
    }

    public void setCathedras(List<Cathedra> cathedras) {
        this.cathedras = cathedras;
    }
}
