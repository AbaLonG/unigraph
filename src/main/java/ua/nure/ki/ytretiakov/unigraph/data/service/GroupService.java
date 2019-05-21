package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.ki.ytretiakov.unigraph.data.repository.GroupRepository;

@Component
public class GroupService {

    private GroupRepository groupRepository;

    @Autowired
    public GroupService(final GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
}
