package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.ki.ytretiakov.unigraph.data.exception.DatabaseException;
import ua.nure.ki.ytretiakov.unigraph.data.model.Faculty;
import ua.nure.ki.ytretiakov.unigraph.data.repository.FacultyRepository;

import java.util.Optional;

@Component
public class FacultyService implements Service<Faculty, String> {

    private static Logger logger = Logger.getLogger(Faculty.class);

    private FacultyRepository repository;

    @Autowired
    public FacultyService(final FacultyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(final Faculty faculty) {
        if (faculty == null) {
            throw new DatabaseException("Faculty is null");
        }
        logger.info("Saving faculty: " + faculty.getTitle());
        repository.saveAndFlush(faculty);
        logger.info("Faculty " + faculty.getTitle() + " is saved");
    }

    @Override
    public Faculty findById(final String facultyTitle) {
        if (facultyTitle == null) {
            throw new DatabaseException("Faculty title is NULL");
        }
        logger.info("Looking for faculty: " + facultyTitle);
        final Optional<Faculty> byId = repository.findById(facultyTitle);
        if (byId.isPresent()) {
            logger.info("Faculty with id " + facultyTitle + " is found");
            return byId.get();
        } else {
            throw new DatabaseException("No faculty with such title: " + facultyTitle);
        }
    }

    @Override
    public void deleteById(final String facultyTitle) {
        if (facultyTitle == null) {
            throw new DatabaseException("Faculty title is NULL");
        }
        logger.info("Deleting faculty: " + facultyTitle);
        if (repository.existsById(facultyTitle)) {
            repository.deleteById(facultyTitle);
            logger.info("Faculty has been deleted: " + facultyTitle);
        } else {
            throw new DatabaseException("No faculty with title " + facultyTitle);
        }
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}