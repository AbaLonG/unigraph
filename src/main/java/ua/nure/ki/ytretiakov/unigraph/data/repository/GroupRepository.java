package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByTitle(final String title);

    void deleteByTitle(final String title);

    boolean existsByTitle(final String title);

}
