package com.epam.java.entities;

import com.epam.java.exceptions.FacultyWithoutGroupsException;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class Faculty {
    private String facultyName;
    private Set<Group> groups;

    public Faculty(String facultyName) {
        this.facultyName = facultyName;
        groups = new TreeSet<>();
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Set<Group> getGroups() throws FacultyWithoutGroupsException {
        if (groups.isEmpty()) {
            throw new FacultyWithoutGroupsException("На факультете " + getFacultyName() + " отсутствуют группы!");
        }
        return groups;
    }

    public void setGroup(String groupName) {
        groups.add(new Group(groupName));
    }

    public Group getGroup(String groupName) throws FacultyWithoutGroupsException {
        Optional<Group> optionalGroup = getGroups().stream().
                filter(group -> group.getGroupName().equals(groupName)).findFirst();
        return optionalGroup.orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faculty faculty = (Faculty) o;

        if (facultyName != null ? !facultyName.equals(faculty.facultyName) : faculty.facultyName != null) return false;
        if (groups != null ? !groups.equals(faculty.groups) : faculty.groups != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = facultyName != null ? facultyName.hashCode() : 0;
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder facultiesInString = new StringBuilder("Faculty= " + facultyName + "\n");
        for (Group group : groups) {
            facultiesInString.append(group.toString());
        }
        return facultiesInString.toString();
    }
}
