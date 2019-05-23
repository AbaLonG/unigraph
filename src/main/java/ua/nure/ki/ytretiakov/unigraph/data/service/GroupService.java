package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.ki.ytretiakov.unigraph.data.exception.DatabaseException;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;
import ua.nure.ki.ytretiakov.unigraph.data.repository.GroupRepository;

import java.util.Optional;

@Component
public class GroupService implements Service<Group, String> {

    private static Logger logger = Logger.getLogger(GroupService.class);

    private GroupRepository repository;

    @Autowired
    public GroupService(final GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(final Group group) {
        if (group == null) {
            throw new DatabaseException("Group is null");
        }
        logger.info("Saving group: " + group.getTitle());
        repository.saveAndFlush(group);
        logger.info("Group " + group.getTitle() + " is saved");
    }

    @Override
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
    public void deleteAll() {
        repository.deleteAll();
    }
}
