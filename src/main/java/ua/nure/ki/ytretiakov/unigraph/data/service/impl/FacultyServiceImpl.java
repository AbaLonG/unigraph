package ua.nure.ki.ytretiakov.unigraph.data.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.ki.ytretiakov.unigraph.data.exception.DatabaseException;
import ua.nure.ki.ytretiakov.unigraph.data.model.Faculty;
import ua.nure.ki.ytretiakov.unigraph.data.repository.FacultyRepository;
import ua.nure.ki.ytretiakov.unigraph.data.service.FacultyService;

import java.util.Optional;

@Service
//@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
public class FacultyServiceImpl implements FacultyService {

    private static Logger logger = Logger.getLogger(FacultyServiceImpl.class);

    private FacultyRepository repository;

    @Autowired
    public FacultyServiceImpl(final FacultyRepository repository) {
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
    public boolean existsById(final String title) {
        if (title == null) {
            throw new DatabaseException("Faculty title is NULL");
        }
        logger.info("Looking for Faculty with title: " + title);
        return repository.existsById(title);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
