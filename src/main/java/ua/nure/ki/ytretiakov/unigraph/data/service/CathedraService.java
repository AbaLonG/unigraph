package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.ki.ytretiakov.unigraph.data.exception.DatabaseException;
import ua.nure.ki.ytretiakov.unigraph.data.model.Cathedra;
import ua.nure.ki.ytretiakov.unigraph.data.repository.CathedraRepository;

import java.util.Optional;

@Component
public class CathedraService implements Service<Cathedra, String> {

    private static Logger logger = Logger.getLogger(CathedraService.class);

    private CathedraRepository repository;

    @Autowired
    public CathedraService(final CathedraRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(final Cathedra cathedra) {
        if (cathedra == null) {
            throw new DatabaseException("Cathedra is null");
        }
        logger.info("Saving cathedra: " + cathedra.getTitle());
        repository.saveAndFlush(cathedra);
        logger.info("Cathedra " + cathedra.getTitle() + " is saved");
    }

    @Override
    public Cathedra findById(final String cathedraTitle) {
        if (cathedraTitle == null) {
            throw new DatabaseException("Cathedra title is NULL");
        }
        logger.info("Looking for cathedra: " + cathedraTitle);
        final Optional<Cathedra> byId = repository.findById(cathedraTitle);
        if (byId.isPresent()) {
            logger.info("Cathedra with id " + cathedraTitle + " is found");
            return byId.get();
        } else {
            throw new DatabaseException("No cathedra with such title: " + cathedraTitle);
        }
    }

    @Override
    public void deleteById(final String cathedraTitle) {
        if (cathedraTitle == null) {
            throw new DatabaseException("Cathedra title is NULL");
        }
        logger.info("Deleting cathedra: " + cathedraTitle);
        if (repository.existsById(cathedraTitle)) {
            repository.deleteById(cathedraTitle);
            logger.info("Cathedra has been deleted: " + cathedraTitle);
        } else {
            throw new DatabaseException("No cathedra with title " + cathedraTitle);
        }
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}