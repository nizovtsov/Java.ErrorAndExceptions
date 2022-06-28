package com.epam.java.entities;

import com.epam.java.exceptions.GroupWithoutStudentsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group implements Comparable<Group> {
    private String groupName;
    private List<Student> students;

    public Group(String groupName) {
        this.groupName = groupName;
        this.students = new ArrayList<>();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Student> getStudents() throws GroupWithoutStudentsException {
        if (students.isEmpty()) {
            throw new GroupWithoutStudentsException("В группе " + getGroupName() + " отсутствуют студенты!");
        }
        return students;
    }

    public void setStudents(Student student) {
        students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (!Objects.equals(groupName, group.groupName)) return false;
        return Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        int result = groupName != null ? groupName.hashCode() : 0;
        result = 31 * result + (students != null ? students.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", students=" + students +
                '}';
    }

    @Override
    public int compareTo(Group group) {
        return groupName.compareTo(group.groupName);
    }
}
