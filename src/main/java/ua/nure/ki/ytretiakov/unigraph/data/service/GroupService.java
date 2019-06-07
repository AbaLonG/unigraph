package ua.nure.ki.ytretiakov.unigraph.data.service;

import ua.nure.ki.ytretiakov.unigraph.data.model.Group;

public interface GroupService extends Service<Group, String> {

    Group findGroupOfManager(String teacherLogin);

}
