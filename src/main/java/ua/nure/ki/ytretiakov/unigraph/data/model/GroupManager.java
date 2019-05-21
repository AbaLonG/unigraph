package ua.nure.ki.ytretiakov.unigraph.data.model;

import javax.persistence.*;

@Entity
@Table(name = "GROUP_MANAGERS")
public class GroupManager extends IDEntity {

    @OneToOne(targetEntity = Employee.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", unique = true, nullable = false, foreignKey = @ForeignKey(name = "employee_id_fk"))
    private Employee groupManager;

    @OneToOne(targetEntity = Group.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", unique = true, nullable = false, foreignKey = @ForeignKey(name = "group_id_fk"))
    private Group group;

    public GroupManager() {

    }

    public Employee getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(Employee groupManager) {
        this.groupManager = groupManager;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
