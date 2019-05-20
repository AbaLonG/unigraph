package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "GROUPS")
@Entity
public class Group extends IDEntity {

    @Column
    private String title;

    public Group() {

    }

    public Group(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
