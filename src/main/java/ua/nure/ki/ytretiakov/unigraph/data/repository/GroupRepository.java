package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;

public interface GroupRepository extends JpaRepository<Group, String> {
    
    @Query("SELECT g FROM Group g WHERE g.groupManager.login = ?1")
    Group findGroupByGroupManager(String groupManagerLogin);
    
}
