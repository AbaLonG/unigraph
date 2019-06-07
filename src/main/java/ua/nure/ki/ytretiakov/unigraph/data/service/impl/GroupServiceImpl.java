package ua.nure.ki.ytretiakov.unigraph.data.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.ki.ytretiakov.unigraph.data.exception.DatabaseException;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;
import ua.nure.ki.ytretiakov.unigraph.data.repository.GroupRepository;
import ua.nure.ki.ytretiakov.unigraph.data.service.GroupService;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
public class GroupServiceImpl implements GroupService {

    private static Logger logger = Logger.getLogger(GroupServiceImpl.class);

    private GroupRepository repository;

    @Autowired
    public GroupServiceImpl(final GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void save(final Group group) {
        if (group == null) {
            throw new DatabaseException("Group is null");
        }
        logger.info("Saving group: " + group.getTitle());
        repository.saveAndFlush(group);
        logger.info("Group " + group.getTitle() + " is saved");
    }

    @Override
    @Transactional
    public Group findById(final String groupTitle) {
        if (groupTitle == null) {
            throw new DatabaseException("Group title is null");
        }
        logger.info("Looking for group: " + groupTitle);
        final Optional<Group> byId = repository.findById(groupTitle);
        if (byId.isPresent()) {
            logger.info("Group with id " + groupTitle + " is found");
            return byId.get();
        } else {
            throw new DatabaseException("No group with title: " + groupTitle);
        }
    }

    @Override
    public void deleteById(final String groupTitle) {
        if (groupTitle == null) {
            throw new DatabaseException("Group title is NULL");
        }
        logger.info("Deleting group: " + groupTitle);
        if (repository.existsById(groupTitle)) {
            repository.deleteById(groupTitle);
            logger.info("Group has been deleted: " + groupTitle);
        } else {
            throw new DatabaseException("No group with title " + groupTitle);
        }
    }

    @Override
    public boolean existsById(final String title) {
        if (title == null) {
            throw new DatabaseException("Group title is NULL");
        }
        logger.info("Looking for Group with title: " + title);
        return repository.existsById(title);
    }
    
    @Override
    public Group findGroupOfManager(String teacherLogin) {
        return repository.findGroupByGroupManager(teacherLogin);
    }
    
    @Override
    public List<Group> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
