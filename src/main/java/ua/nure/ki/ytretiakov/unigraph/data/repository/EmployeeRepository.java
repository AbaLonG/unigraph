package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    boolean existsByEmail(String email);
    
    List<Employee> findEmployeesByType(EmployeeType type);
    
    @Query(nativeQuery = true, value = "SELECT * FROM employees e LIMIT ?1")
    List<Employee> findEmployeesCount(int count);
}
