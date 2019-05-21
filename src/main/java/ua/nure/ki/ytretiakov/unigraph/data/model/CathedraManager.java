package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;

@Entity
@Table(name = "CATHEDRA_MANAGERS")
public class CathedraManager extends IDEntity {

    @OneToOne(targetEntity = Cathedra.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cathedra_id", unique = true, nullable = false, foreignKey = @ForeignKey(name = "cathedra_id_fk"))
    private Cathedra cathedra;

    @OneToOne(targetEntity = Employee.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cathedra_manager_id", unique = true, nullable = false, foreignKey = @ForeignKey(name = "cathedra_manager_id_fk"))
    private Employee employee;

    public CathedraManager() {

    }

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        this.cathedra = cathedra;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
