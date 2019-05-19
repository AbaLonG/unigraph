package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENTS")
public class Student extends Employee {

    @Override
    public String toString() {
        return String.format("Student %s %s", getFirstName(), getLastName());
    }
}
