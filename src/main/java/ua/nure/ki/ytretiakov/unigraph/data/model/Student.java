package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "STUDENTS")
public class Student extends Employee {

    public Student() {

    }

    public Student(final String firstName, final String lastName, final Date dateOfBirth, final String email, final String password) {
        super(firstName, lastName, dateOfBirth, email, password);
    }

    @Override
    public String toString() {
        return String.format("Student %s %s", getFirstName(), getLastName());
    }
}
