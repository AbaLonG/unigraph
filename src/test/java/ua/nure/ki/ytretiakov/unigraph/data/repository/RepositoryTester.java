package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.junit.Before;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.ki.ytretiakov.unigraph.WebContextLoader;

public abstract class RepositoryTester<T, ID> extends WebContextLoader {

    abstract JpaRepository<T, ID> getRepository();

    @Before
    public void before() {
        getRepository().deleteAll();
    }

    private void deleteAll() {
        getRepository().deleteAll();
    }
}
