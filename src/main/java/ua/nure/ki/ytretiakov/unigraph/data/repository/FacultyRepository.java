package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.ki.ytretiakov.unigraph.data.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, String> {
}
